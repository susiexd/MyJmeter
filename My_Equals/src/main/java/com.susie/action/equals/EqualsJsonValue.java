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

    private  String equalsJsonValue(JSONObject standardJson, JSONObject responseJson) {//输入两个json，判断第一个里面的所有字段在第二个中的类型是否相同
        String err_message = "";
        Iterator it = standardJson.keys(); // 储存所有要验证的Key
        while (it.hasNext()) {
            String key = (String) it.next();

            if(!responseJson.has(key)) {  // 判断response中有无当前Key
                log1 = "------ExistError : " + key + " Not found.";
                log.info("!!Failed: " + log1);
                err_message = (err_message + "\n" + log1);
            }

            else if(standardJson.isNull(key)) { // 如果当前字段值为NULL：判断响应字段是否也为NULL
                if(!responseJson.isNull(key)){
                    log1 = "------ValueError : " + key + " should be NULL!";
                    log.info("!!Failed: " + log1);
                    err_message = (err_message + "\n" + log1);
                }
            }

            else{ // 响应存在key：则查询value是否正确
                String thisKeyType = standardJson.getClass().getName(); //获取当前Key的标准值
                String thisKeyValue = standardJson.get(key).toString(); //获取当前Key的类型
                if(thisKeyType.equals("org.json.JSONObject")){ //object类型的字段继续往内层判断
                    err_message += equalsJsonValue(standardJson.getJSONObject(key), responseJson.getJSONObject(key)); //!!进入递归时，保存当前错误信息
                }
                else{
                    String respKeyValue = responseJson.get(key).toString(); //获取响应的字段值
                    if(!respKeyValue.equals(thisKeyValue)){
                        String log1 = "------ValueError : " + key + " is " + respKeyValue + " (should be " + thisKeyValue + ")";
                        log.info("!!Failed: " + log1);
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
        if(message == ""){    //如果错误信息是空，说明断言结果通过
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
