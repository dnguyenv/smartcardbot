package com.ieee.smartcard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;

public class CardSubmitter {

	public void submitCardInfo(String text) throws CardBotException {

		HttpClient httpClient = HttpClientBuilder.create().build();

		/*Bluemix node-red REST endpoint */

		String targetUrl = "https://smartcard.mybluemix.net/ieee/postCard";

		HttpResponse response = null;
		HttpEntity entity = null;
		HttpPost httpost = new HttpPost(targetUrl);

		JSONObject json = new JSONObject();
		json.put("card",text);

		try {
			System.out.println(json.toString());
		    StringEntity param =new StringEntity(json.toString());
		    httpost.addHeader("content-type", "application/json");
		    httpost.setEntity(param);
			response = httpClient.execute(httpost);
			entity = response.getEntity();

			// Log response content to the console to inspect

			InputStream inputStream = entity.getContent();
			String responseStr = logFromStream(inputStream);
			System.out.println("Response from server : " + responseStr);

		} catch (Exception e) {
			System.out.println(e);
		}

		httpClient.getConnectionManager().shutdown();

	}


	private static String logFromStream(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder buff = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				buff.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buff.toString();
	}
}
