package com.amaker.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	// Base URL
	public static final String BASE_URL="http://192.168.0.1:8888/WirelessOrder_Server/";
	// Get request
	public static HttpGet getHttpGet(String url){
		HttpGet request = new HttpGet(url);
		 return request;
	}
	// Post request
	public static HttpPost getHttpPost(String url){
		 HttpPost request = new HttpPost(url);
		 return request;
	}
	// Get response
	public static HttpResponse getHttpResponse(HttpGet request) throws ClientProtocolException, IOException{
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}
	// Get response
	public static HttpResponse getHttpResponse(HttpPost request) throws ClientProtocolException, IOException{
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}
	
	// Send Post request£¬gain the response
	public static String queryStringForPost(String url){
		// Get HttpPost according to URL
		HttpPost request = HttpUtil.getHttpPost(url);
		String result = null;
		try {
			// Get resopnse
			HttpResponse response = HttpUtil.getHttpResponse(request);
			// If successful?
			if(response.getStatusLine().getStatusCode()==200){
				// Get response
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "Network Failed£¡";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "Network Failed£¡";
			return result;
		}
        return null;
    }
	// Get the response
	public static String queryStringForPost(HttpPost request){
		String result = null;
		try {
			HttpResponse response = HttpUtil.getHttpResponse(request);
			if(response.getStatusLine().getStatusCode()==200){
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "Network Failed£¡";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "Network Failed£¡";
			return result;
		}
        return null;
    }
	public static  String queryStringForGet(String url){
		HttpGet request = HttpUtil.getHttpGet(url);
		String result = null;
		try {
			HttpResponse response = HttpUtil.getHttpResponse(request);
			if(response.getStatusLine().getStatusCode()==200){
				result = EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = "Network Failed£¡";
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			result = "Network Failed£¡";
			return result;
		}
        return null;
    }
}
