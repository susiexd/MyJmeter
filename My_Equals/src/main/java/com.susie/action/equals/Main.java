package com.susie.action.equals;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//各种类型的数据
		String standardData = "{'uid':123,'phone':null,'has_password':{'hight':true,'wight':1},'location':{'province':true,'city':'123'},'cpma':1,'lo':true}";
		String resData = "{data:{'uid':123,'phone':1,'has_password':{'hight':true,'wight':true},'location':null,'lo':{'pre':true,'ci':ok}}}";

		//打印外层字段
//		String standardData = "{'l':{'high':true,'wight':1},'cpma':1; 's':{'high':true,'wight':1}, 'obj':{'child1':true,'child2':{'grandChirld1':null,'grandChirld2':'ok'}}}";
//		String resData = "{'data':{'l':{'high':null,'wight':'1'},'s':{'high':0,'wight':1}, 'obj':{'child1':true,'child2':{'grandChirld1':'ok','grandChirld2':123}}}}";
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
