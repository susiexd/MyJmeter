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
//		String standardData = "{'code':0,'data':{'invoiceInfo':{},'orderInfo':{'createDate':'2017-01-12 15:41:32','id':'148420689278793'},'payMoney':'999.00','products':[{'s':1,'m':'hello'},{'s':2,'m':'hello2'},{'s':3,'m':'hello3'},{'s':2,'m':'hello2'}],'arrayJson':[{'a':1,'b':{'b1':'ok','b2':1},'c':1},{'a':2,'b':{'b1':'ok','b2':6},'c':1}],'remark':'?','status':1,'statusDescription':'订单提交','userInfo':{'address':'ddfsfdsfdsfdsdsffds','name':'fudali','phone':'15182996991'}}}";
//		String resData = "{'data':{'code':'ok','data':{'invoiceInfo':{},'orderInfo':{'createDate':'1','id':'148420689278793'},'payMoney':'999.00','products':[{'s':1,'m':'hello'},{'s':1,'m':'hello'},{'s':2,'m':'hello2'},{'s':4,'m':2}],'arrayJson':[{'a':1,'b':1},{'a':2,'b':1}],'remark':'?','status':1,'statusDescription':'订单提交','userInfo':{'address':'ddfsfdsfdsfdsdsffds','name':'fudali','phone':'15182996991'}}}}";

		String standardData = "{'error_code':0,'error_message':'SUCCESS::Api success','error_description':null,'data':{'order_id':'123638','order_sn':'test1707926413','pay_time':0,'remark':'','promotion_discount_amount':'0.00','voucher_discount_amount':'20.00','can_cod':false,'cannot_cod_reason':'\\u5b9e\\u4ed8\\u91d1\\u989d\\u4e0d\\u6ee1159\\u5143','save_price':3.99,'order_items':[{'goods_id':'257410','goods_item_id':282337,'group_and_spec_name':'\\u89c4\\u683c1\\uff1a\\u89c4\\u683c1','image':{'s':{'url':'http:\\/\\/cdn1.meijiabang.cn\\/public\\/upload\\/goodsspc\\/origin\\/2017\\/02\\/28\\/8846b4152caf6f9e3857df5b8d8e6ea996gMjg.jpg@320w_100Q.webp','width':320,'height':0}},'is_freebies':false,'limited_use_voucher':false,'name':'\\u63a5\\u53e3\\u81ea\\u52a8\\u5316_\\u4e70\\u8d609_\\u6d3b\\u52a8\\u4e2d\\uff0c2\\u8d601\\uff0c\\u6700\\u591a\\u8d60\\u90012\\u4ef6','stock':9999,'count':1,'sale_price':10,'pay_price_string':null,'coupon_info':null}],'shipping_address':{'province':'\\u5e7f\\u4e1c\\u7701','city':'\\u5e7f\\u5dde\\u5e02','district':'\\u8d8a\\u79c0\\u533a','street':'\\u5927\\u4e1c\\u8857\\u9053','name':'\\u63a5\\u53e3\\u81ea\\u52a8\\u5316\\u54af','phone':'10311111115','detail':'\\u6d4b\\u8bd5\\u7ec4\\u54e6\\u54e6\\u54e6'},'time_out':1490097521,'status_text':'\\u5f85\\u4ed8\\u6b3e','actions':[{'action':'cancel_unpaid','is_highlight':false},{'action':'modify','is_highlight':false},{'action':'pay','is_highlight':true}],'price_grade':null,'pay_mode':'\\u672a\\u4ed8\\u6b3e','share_entity':{'share_entity_id':'mall_order|123638','title':'\\u6211\\u5728\\u7f8e\\u7532\\u5e2e\\u4e0b\\u5355\\uff0c\\u4e00\\u5355\\u5c31\\u7701\\u4e864\\u5143\\u5462\\uff01\\u5feb\\u6765\\u770b\\u770b\\u5427','text':'\\u7f8e\\u7532\\u5e2e\\u5546\\u57ce\\uff0c\\u7f8e\\u7532\\u3001\\u7f8e\\u776b\\u3001\\u5de5\\u5177\\u3001\\u9970\\u54c1\\uff0c\\u5929\\u5929\\u5e95\\u4ef7\\u72c2\\u6b22','image_url':'https:\\/\\/cdn1.meijiabang.cn\\/public\\/images\\/meijiabang_icon192.png','link_url':'https:\\/\\/m.meijiabang.cn\\/single-page\\/share-order\\/index.html?save_amount=4'},'create_time':1490090321,'pay_price':6.01,'goods_price':10,'shipment_fee':6},'extra':null}";
		String resData = "{'error_code':20207,'error_message':'','error_description':'\\u60a8\\u7684\\u64cd\\u4f5c\\u592a\\u9891\\u7e41\\uff0c\\u8bf7\\u7a0d\\u540e\\u518d\\u8bd5','data':null,'extra':null}";
//		EqualsJsonType eqType = new EqualsJsonType();
//		boolean result;
//		result = eqType.respTypeAssertion(standardData, resData);
//        System.out.println(result);

		EqualsJsonValue eqValue = new EqualsJsonValue();
		boolean result2;
		result2 = eqValue.respValueAssertion(standardData, resData);
		System.out.println(result2);
	}

}
