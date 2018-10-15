package com.redsource.samples.httpproxy.wrapper;

import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;

import javax.annotation.PostConstruct;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RequestBuilder {
	HttpClient client;
	ObjectMapper mapper;

	@PostConstruct
	public void init() {
		client = HttpClientBuilder.create().build();
		mapper = new ObjectMapper();
	}
	public String createGetReuqest(String url) {
		HttpGet httpGet = new HttpGet(url);
		String response = "";
		try {
			HttpResponse httpResponse = client.execute(httpGet);
			HttpEntity responseEntity = httpResponse.getEntity();
			if(responseEntity!=null) {
				response = EntityUtils.toString(responseEntity);
			}
		}catch(Exception e) {
			
		}
		return response;
	}
	public String createPostRequest(String url, Object requestBody)
			throws UnsupportedCharsetException, JsonProcessingException, UnsupportedEncodingException {
		String res="";
		StringEntity entity = new StringEntity(mapper.writeValueAsString(requestBody));
		HttpPost request = new HttpPost(url);
		request.addHeader("content-type", "application/json");
		request.addHeader("Accept", "application/json");
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		request.setEntity(entity);
		try {
			HttpResponse response = client.execute(request);
			HttpEntity responseEntity = response.getEntity();
			if(responseEntity!=null) {
			    res = EntityUtils.toString(responseEntity);
			}
			System.out.println(res);
			
		}catch(Exception e) {
			
		}
		return res;

	}

}
