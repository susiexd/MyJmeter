package com.susie.action.equals;

import org.json.JSONArray;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//各种类型的数据
		String standardData = "{'uid':123,'phone':null,'has_password':{'hight':true,'wight':1},'location':{'province':true,'city':'123'},'cpma':1,'lo':true}";
		String resData = "{data:{'uid':123,'phone':1,'has_password':{'hight':true,'wight':true},'location':null,'lo':{'pre':true,'ci':ok}}}";
		
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
