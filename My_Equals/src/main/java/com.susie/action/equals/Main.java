package com.susie.action.equals;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EqualsJsonType eqType = new EqualsJsonType();
		String standardData = "{'uid':123,'phone':1,'has_password':true,'location':{'province':true,'city':null},'cpma':null, 'name':'susie'}";

		String resData = "{'data':{'uid':124,'phone':'1','has_password':False,'location':{'province':true,'city':123},'cpma':null}}";

		boolean result;

		result = eqType.respTypeAssertion(standardData, resData);
        System.out.println(result);

		EqualsJsonValue eqJsonValue = new EqualsJsonValue();
		boolean result2;
		result2 = eqJsonValue.respValueAssertion(standardData, resData);
		System.out.println(result2);
	}

}
