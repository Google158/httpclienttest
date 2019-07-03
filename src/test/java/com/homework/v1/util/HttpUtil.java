package com.homework.v1.util;

import java.io.IOException;
import java.util.Arrays;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	public static String doGet(String url, String params) throws ClientProtocolException, IOException {
		//1   准备HttpClient客户端
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//2   创建HttpGet对象
		HttpGet httpGet = new HttpGet(url+"?"+params);
		//3   发送请求
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
		//4   取出响应状态码
		int code = httpResponse.getStatusLine().getStatusCode();
		System.out.println("响应状态码为："+code);
		//5   取出响应头
		Header[] headers = httpResponse.getAllHeaders();
		System.out.println("响应头信息为："+Arrays.toString(headers));
		//6   取出响应报文
		HttpEntity entity = httpResponse.getEntity();
		String result = EntityUtils.toString(entity);
		System.out.println("响应报文为："+result);
		return result;
	}

	public static String doPostByForm(String url, String params) throws ClientProtocolException, IOException {
		//1   准备客户端
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//2   创建HttpPost对象
		HttpPost httpPost = new HttpPost(url);
		//3   添加请求头（指定以表单方式来提交参数），不指定的情况下默认为form或txt方式提交参数
		httpPost.addHeader(new BasicHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8"));
		//4   添加参数到请求体
		httpPost.setEntity(new StringEntity(params,"UTF-8"));
		//5   发送请求
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
		//6   取出响应状态码
		int code = httpResponse.getStatusLine().getStatusCode();
		System.out.println("状态码为："+code);
		//7   取出响应头
		Header [] headers = httpResponse.getAllHeaders();
		System.out.println("响应头："+Arrays.toString(headers));
		//8   取出响应报文
		String result = EntityUtils.toString(httpResponse.getEntity());
		System.out.println("响应报文："+result);
		return result;
	}

	public static String doPostByJson(String url, String params) throws ClientProtocolException, IOException {
		//1   准备客户端
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//2   创建HttpPost对象
		HttpPost httpPost = new HttpPost(url);
		//3   添加请求头（指定以表单方式来提交参数）
		httpPost.addHeader(new BasicHeader("Content-Type","application/json;charset=UTF-8"));
		//4   添加参数到请求体
		httpPost.setEntity(new StringEntity(params,"UTF-8"));
		//5   发送请求
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
		//6   取出响应状态码
		int code = httpResponse.getStatusLine().getStatusCode();
		System.out.println("状态码为："+code);
		//7   取出响应头
		Header [] headers = httpResponse.getAllHeaders();
		System.out.println("响应头："+Arrays.toString(headers));
		//8   取出响应报文
		String result = EntityUtils.toString(httpResponse.getEntity());
		System.out.println("响应报文："+result);
		return result;
	}

}
