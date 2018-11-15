package com.ctc.shorturl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ShortUrlTest {
	private static final int CON_TIMEOUT = 60000;
	private static final int READ_TIMEOUT = 60000;

	public static void main(String[] args) {
		shortUrlSend();
		
		getShortUrl();
	}

	public static String shortUrlSend() {
		String remote_url = "http://172.18.1.104:8081/json/sms/ShortUrlSend";// 短地址发送接口服务地址
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = "";
		try {
			File file = new File("‪‪E:\\测试文件\\测试号码\\500W.zip");
			FileInputStream fin = new FileInputStream(file);
			String fileName = file.getName();
			HttpPost httpPost = new HttpPost(remote_url);
			httpPost.addHeader("charset", "UTF-8");

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.addBinaryBody("phonesFile", fin, ContentType.MULTIPART_FORM_DATA, fileName);// 号码文件流
			builder.addBinaryBody("account", "zxldh".getBytes());// 账号
			builder.addBinaryBody("password", MD5.MD5Encode("456.com").getBytes());// 密码
			builder.addBinaryBody("days", "5".getBytes());// 短地址有效天数
			builder.addBinaryBody("url", "http://www.webuy.ai/shwelcome.html".getBytes());// url地址
			builder.addBinaryBody("sign", "【甩货宝宝】".getBytes());// 签名
			builder.addBinaryBody("content", "宝宝送您65元优惠券，限时领取！甩宝品牌大升级！茜舞美衣秋上新疯抢中！快点击 http://www.webuy.ai/shwelcome.html 退订回TD".getBytes());// 内容
			HttpEntity entity = builder.build();
			httpPost.setEntity(entity);
			HttpResponse response = httpClient.execute(httpPost);// 执行提交
			HttpEntity responseEntity = response.getEntity();
			if (responseEntity != null) {
				// 将响应内容转换为字符串
				result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("请求响应：" + result);
		return result;
	}

	public static String getShortUrl() {
		String url = "http://172.18.1.104:8081/json/sms/GetShortUrl";
		String res = null;
		PostMethod postMethod = null;
		try {
			postMethod = new PostMethod(url);
			postMethod.addRequestHeader("Content-Type", "application/xml;charset=UTF-8");
			postMethod.setRequestHeader("Connection", "close");
			
			JSONObject message = new JSONObject();
			message.put("account", "zxldh");
			message.put("password", MD5.MD5Encode("456.com"));
			message.put("url", "http://www.webuy.ai/shwelcome.html");
			message.put("days", 5);

			ByteArrayInputStream bint = new ByteArrayInputStream(message.toString().getBytes());
			RequestEntity requestEntity = new InputStreamRequestEntity(bint);
			postMethod.setRequestEntity(requestEntity);
			HttpClient httpClient = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
			HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
			managerParams.setConnectionTimeout(CON_TIMEOUT);
			managerParams.setSoTimeout(READ_TIMEOUT);
			httpClient.executeMethod(postMethod);
			int code = postMethod.getStatusCode();
			if (code == HttpStatus.SC_OK) {
				res = new String(postMethod.getResponseBody(), "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
		System.out.println("请求响应：" + res);
		return res;
	}

}
