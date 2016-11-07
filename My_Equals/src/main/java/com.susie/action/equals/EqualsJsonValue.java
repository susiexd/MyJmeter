package com.susie.action.equals;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/**
 * Created by susie on 2016/10/17.
 */

public class EqualsJsonValue {

    private static String log1;
    private String message;
    private static Logger log = (Logger) LoggerFactory.getLogger(EqualsJsonType.class);

    public EqualsJsonValue() {
        // TODO Auto-generated constructor stub
        System.out.println("init!");
    }

    private String fatherName = "";
    private  String equalsJsonValue(JSONObject standardJson, JSONObject responseJson) {//输入两个json，判断第一个里面的所有字段在第二个中的类型是否相同
        String err_message = "";
        Iterator it = standardJson.keys(); // 储存所有要验证的Key
        while (it.hasNext()) {
            String key = (String) it.next();

            if(!responseJson.has(key)) {  // 判断response中有无当前Key
                log1 = "------ExistError: " + fatherName + key + " Not found.";
                err_message = (err_message + "\n" + log1);
            }

            else if(standardJson.isNull(key)) { // 当前字段值为NULL：判断响应字段是否也为NULL
                if(!responseJson.isNull(key)){
                    String respKeyValue = responseJson.get(key).toString(); //获取响应的字段值
                    log1 = "------ValueError : " + fatherName + key + " is " + respKeyValue + " (should be NULL!)";
                    err_message = (err_message + "\n" + log1);
                }
            }

            else if(responseJson.isNull(key)) { // 当前字段不为空：若响应字段为NULL报错
                String thisKeyValue = standardJson.get(key).toString(); //获取当前Key的值
                log1 = "------ValueError : " + fatherName + key + " is NULL" + " (should be " + thisKeyValue + ")";
                err_message = (err_message + "\n" + log1);
            }

            else{ // 响应存在key：则查询value是否正确
                String respKeyValue = responseJson.get(key).toString(); //获取响应的字段值
                String thisKeyValue = standardJson.get(key).toString(); //获取当前Key的值

                if(!respKeyValue.equals(thisKeyValue)){ // 字段值不同时,定位出错字段（相同则直接退出这层循环）

                    String thisKeyType = standardJson.get(key).getClass().getName(); // 获取Key的标准类型
                    String respKeyType = responseJson.get(key).getClass().getName(); // 获取Key的返回类型

                    if(thisKeyType.equals("org.json.JSONObject")){
                        if(respKeyType.equals("org.json.JSONObject")) { //都是object：递归判断
                            fatherName += key + "->";
                            err_message += equalsJsonValue(standardJson.getJSONObject(key), responseJson.getJSONObject(key)); //!!进入递归时，保存当前错误信息
                            fatherName = fatherName.replace(key+"->","");
                        }
                        else { //响应并非object类型：保存错误信息
                            String log1 = "------ValueError : " + fatherName + key + " is " + respKeyValue + " (should be " + thisKeyValue + ")";
                            err_message = (err_message + "\n" + log1);
                        }
                    }

                    else { //两者都非object类型：保存错误信息
                        String log1 = "------ValueError : " + fatherName + key + " is " + respKeyValue + " (should be " + thisKeyValue + ")";
                        err_message = (err_message + "\n" + log1);
                    }
                }

            }
        }
        return err_message;
    }

    public Boolean respValueAssertion(String standardData, String resData) { //输入标准响应，转为json并调用比较函数，得到断言结果
        log.info("res: " + resData);
        JSONObject standardJson = new JSONObject(standardData);
        JSONObject jo = new JSONObject(resData);
        JSONObject responseJson = jo.getJSONObject("data");
        message = equalsJsonValue(standardJson, responseJson);
        log.info("------------------------ResultMessage--------------------" + message);
        if(message.replaceAll(" ","").equals("")){    //如果错误信息是空，说明断言结果通过
            return true;
        }
        return false;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
