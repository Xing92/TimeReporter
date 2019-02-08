package com.xing.TimeReporterClient.requests;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class Request {

	String username = "testname2";
	String password = "testpass2";
	String method = "met";
	String request = "req";
	
	
	
	public Request(String username, String password, String method, String request) {
		super();
		this.username = username;
		this.password = password;
		this.method = method;
		this.request = request;
	}



	public String sendRequest(String urlAddress) {
		String response = "noResponse";
		try {
			URL url = new URL(urlAddress);
			String encoding = Base64.getEncoder().encodeToString((username + ":" + password).getBytes("UTF-8"));

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.setDoOutput(true);
			connection.setRequestProperty("Authorization", "Basic " + encoding);
			InputStream content = (InputStream) connection.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(content));
			String line;
			while ((line = in.readLine()) != null) {
				response = line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
