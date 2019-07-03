package com.homework.v1.cases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.homework.v1.util.HttpUtil;

public class RegisterCase {
	@Test(dataProvider="datas")
	public void test(String url,String method,String apiType,String mobilephone,String pwd) throws ClientProtocolException, IOException {
		//组装aipType为form类型的请求的参数
		String params = "mobilephone="+mobilephone+"&pwd="+pwd;
		if("get".equalsIgnoreCase(method)) {
			HttpUtil.doGet(url,params);
		}else if("post".equalsIgnoreCase(method)) {
			if("form".equalsIgnoreCase(apiType)) {
				HttpUtil.doPostByForm(url,params);
			}else if("json".equalsIgnoreCase(apiType)) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("mobilephone",mobilephone);
				map.put("pwd",pwd);
				params = JSONObject.toJSONString(map);
				HttpUtil.doPostByJson(url,params);
			}
		}
	}
	
	@DataProvider
	public Object [][] datas(){
		Object [][] datas = {
				{"http://test.lemonban.com/futureloan/mvc/api/member/register","post","form","18813989449",""},
				{"http://test.lemonban.com/futureloan/mvc/api/member/register","post","form","","123456"},
				{"http://test.lemonban.com/futureloan/mvc/api/member/register","post","form","123","123456"},
				{"http://test.lemonban.com/futureloan/mvc/api/member/register","post","form","18813989449","12345"},
				{"http://test.lemonban.com/futureloan/mvc/api/member/register","post","form","18813989449","123456"},
				{"http://test.lemonban.com/futureloan/mvc/api/member/register","post","form","18813989449","123456"}
		};
		return datas;
	}
}
