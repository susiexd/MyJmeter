package com.susie.action.equals;

import org.json.JSONArray;
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
        System.out.println("<<======================== TestInit! ========================>>");
    }

    private String fatherName = "";

    private String equalsJsonValue(JSONObject standardJson, JSONObject responseJson) {//输入两个json，判断第一个里面的所有字段在第二个中的类型是否相同
        String err_message = "";
        Iterator it = standardJson.keys(); // 储存所有要验证的Key
        while (it.hasNext()) {
            String key = (String) it.next();

            if (!responseJson.has(key)) {  // 判断response中有无当前Key
                log1 = "------ExistError: " + fatherName + key + " Not found.";
                err_message = (err_message + "\n" + log1);
            } else if (standardJson.isNull(key)) { // 当前字段值为NULL：判断响应字段是否也为NULL
                if (!responseJson.isNull(key)) {
                    String respKeyValue = responseJson.get(key).toString(); //获取响应的字段值
                    log1 = "------ValueError : " + fatherName + key + " is " + respKeyValue + " (should be NULL!)";
                    err_message = (err_message + "\n" + log1);
                }
            } else if (responseJson.isNull(key)) { // 当前字段不为空：若响应字段为NULL报错
                String thisKeyValue = standardJson.get(key).toString(); //获取当前Key的值
                log1 = "------ValueError : " + fatherName + key + " is NULL" + " (should be " + thisKeyValue + ")";
                err_message = (err_message + "\n" + log1);
            } else { // 响应存在key：则查询value是否正确
                String respKeyValue = responseJson.get(key).toString(); //获取响应的字段值
                String thisKeyValue = standardJson.get(key).toString(); //获取当前Key的值

                if (!respKeyValue.equals(thisKeyValue)) { // 字段值不同时,定位出错字段（相同则直接退出这层循环）

                    String thisKeyType = standardJson.get(key).getClass().getName(); // 获取Key的标准类型
                    String respKeyType = responseJson.get(key).getClass().getName(); // 获取Key的返回类型

                    if (thisKeyType.equals("org.json.JSONObject")) {
                        if (respKeyType.equals("org.json.JSONObject")) { //都是object：递归判断
                            fatherName += key + "->";
                            err_message += equalsJsonValue(standardJson.getJSONObject(key), responseJson.getJSONObject(key)); //!!进入递归时，保存当前错误信息
                            fatherName = fatherName.replace(key + "->", "");
                        } else { //响应并非object类型：保存错误信息
                            String log1 = "------ValueError : " + fatherName + key + " is " + respKeyValue + " (should be " + thisKeyValue + ")";
                            err_message = (err_message + "\n" + log1);
                        }
                    } else if (thisKeyType.equals("org.json.JSONArray")) { // 标准是Array类型
                        if (respKeyType.equals("org.json.JSONArray")) { // 响应也是数组
                            String err_message1 ="";
                            System.out.println("key:" + key);
                            System.out.println("keyType:" + key.getClass().getName());
                            if(key.equals("order_items")){//商品类型就特殊比较
                                fatherName += key + "->";
                                err_message1 = arrayCompare2(thisKeyValue, respKeyValue);
                                fatherName = fatherName.replace(key + "->", "");
                            }
                            else{
                                err_message1 = arrayCompare(thisKeyValue, respKeyValue); // 两个数组对比
                            }
                            if(!err_message1.replaceAll("\n", "").equals("")){ // 数组不匹配:保存到错误信息里面
                                err_message = (err_message + "\n------ArrayError : " + fatherName + key + "\n" + err_message1);
                            }
                        } else { //响应并非Array类型：保存错误信息
                            String log1 = "------ValueError : " + fatherName + key + " is " + respKeyValue + " (should be " + thisKeyValue + ")";
                            err_message = (err_message + "\n" + log1);
                        }
                    } else { //两者都非object类型：保存错误信息
                        String log1 = "------ValueError : " + fatherName + key + " is " + respKeyValue + " (should be " + thisKeyValue + ")";
                        err_message = (err_message + "\n" + log1);
                    }
                }

            }
        }
        return err_message;
    }

    public Boolean respValueAssertion(String standardData, String resData) { //输入标准响应，转为json并调用比较函数，得到断言结果
        JSONObject standardJson = new JSONObject(standardData);
        JSONObject responseJson = new JSONObject(resData);
        message = equalsJsonValue(standardJson, responseJson);
        log.info(message);
        if (message.replaceAll(" ", "").equals("")) {    //如果错误信息是空，说明断言结果通过
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

    public String arrayCompare(String string1, String string2) { // 数组A,B比较，不考虑元素顺序，返回信息包括B中缺少的数组和多余的数组
        JSONArray array1 = new JSONArray(string1); // 转化为数组
        JSONArray array2 = new JSONArray(string2);
        String result = "";
        int i = 0;
        int j = 0;
        for (; i < array1.length(); i++) { // 遍历array1
            JSONObject object1 = (JSONObject) array1.get(i); // array1的元素object1
            for (; j < array2.length(); j++) { // 遍历array2有没有object1
                if (array2.get(j) != "hasMatched") {
                    String itemExist = equalsJsonValue(object1, (JSONObject) array2.get(j));
                    if (itemExist.replaceAll(" ", "").equals("")) {  // 若当前object匹配，标志为匹配并进行下一个判断
                        array2.put(j, "hasMatched");  //
                        break;
                    }
                }
            }
            if (j == array2.length()) { // 遍历结束没有找到匹配的项则该项缺失
                result += "---------------- " + object1.toString() + " Not found.\n";
            }
        }
        for (j = 0; j < array2.length(); j++){ //遍历array2，未被匹配的是多余的项
            String item = array2.get(j).toString();
            if(!item.equals("hasMatched")){
                result += "---------------- " + item + " Unnecessary.（Index=" + j +")";
            }
            if(j==(array2.length()-2)){
                result += "\n";
            }
        }
        return result;
    }

    public String arrayCompare2(String string1, String string2) { // 商品数组A,B比较，考虑相同goods_item_id元素，返回信息包括B中商品
        JSONArray array1 = new JSONArray(string1); // 转化为数组
        JSONArray array2 = new JSONArray(string2);
        String keyItem = "goods_item_id";
        String result = "";
        int i = 0;
        int j = 0;
        for (; i < array1.length(); i++) { // 遍历array1
            JSONObject object1 = (JSONObject) array1.get(i); // array1的元素object1
            String key_value1 = object1.get(keyItem).toString(); //得到规格id

            for (; j < array2.length(); j++) { // 遍历array2有没有该规格，有就比较其他属性
                if (array2.get(j) != "hasMatched") {
                    JSONObject object2 = (JSONObject) array2.get(j);
                    String key_value2 = object1.get(keyItem).toString();
                    if(key_value1.equals(key_value2)){  // 规格一致，比较其他属性是否一致
                        String itemExist = equalsJsonValue(object1, object2);
                        array2.put(j, "hasMatched");  //比较完毕，标志该元素为匹配
                        if (!itemExist.replaceAll(" ", "").equals("")) {  // 若该商品详情不完全匹配，保存错误信息
                            result += "---------->>商品有误，goods_item_id: " + key_value1 +itemExist;
                        }
                    }
                    break;
                }
            }
            if (j == array2.length()) { // 遍历结束没有找到匹配的项则该项缺失
                result += "\n---------->>商品缺失，goods_item_id: " + key_value1 + " Not found.\n";
            }
        }
        for (j = 0; j < array2.length(); j++){ // 遍历array2，未被匹配的是多余的项
            String item = array2.get(j).toString();
            if(!item.equals("hasMatched")){
                result += "---------------- " + item + " Unnecessary.（Index=" + j +")";
            }
            if(j==(array2.length()-2)){
                result += "\n";
            }
        }
        return result;
    }

}