package com.susie.action.equals;

import org.json.JSONArray;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//各种类型的数据
//		String standardData = "{'uid':123,'phone':null,'has_password':{'hight':true,'wight':1},'location':{'province':true,'city':'123'},'cpma':1,'lo':true}";
//		String resData = "{data:{'uid':123,'phone':1,'has_password':{'hight':true,'wight':true},'location':null,'lo':{'pre':true,'ci':ok}}}";

		//打印外层字段
		String standardData = "{'code':0,'data':{'invoiceInfo':{},'orderInfo':{'createDate':'2017-01-12 15:41:32','id':'148420689278793'},'payMoney':'999.00','products':[{'s':1,'m':'hello'},{'s':2,'m':'hello2'},{'s':3,'m':'hello3'},{'s':2,'m':'hello2'}],'arrayJson':[{'a':1,'b':{'b1':'ok','b2':1},'c':1},{'a':2,'b':{'b1':'ok','b2':6},'c':1}],'remark':'?','status':1,'statusDescription':'订单提交','userInfo':{'address':'ddfsfdsfdsfdsdsffds','name':'fudali','phone':'15182996991'}}}";
		String resData = "{'data':{'code':'ok','data':{'invoiceInfo':{},'orderInfo':{'createDate':'1','id':'148420689278793'},'payMoney':'999.00','products':[{'s':1,'m':'hello'},{'s':1,'m':'hello'},{'s':2,'m':'hello2'},{'s':4,'m':2}],'arrayJson':[{'a':1,'b':1},{'a':2,'b':1}],'remark':'?','status':1,'statusDescription':'订单提交','userInfo':{'address':'ddfsfdsfdsfdsdsffds','name':'fudali','phone':'15182996991'}}}}";

		EqualsJsonType eqType = new EqualsJsonType();
		boolean result;
		result = eqType.respTypeAssertion(standardData, resData);
        System.out.println(result);

		EqualsJsonValue eqValue = new EqualsJsonValue();
		boolean result2;
		result2 = eqValue.respValueAssertion(standardData, resData);
		System.out.println(result2);
	}

}
