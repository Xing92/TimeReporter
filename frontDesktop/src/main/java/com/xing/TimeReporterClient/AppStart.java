package com.xing.TimeReporterClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.xing.TimeReporterClient.requests.Request;

public class AppStart {

	@Autowired
	private static RestTemplate template;
	private Map<String, String> parameters = new HashMap();
	String username = "testname2";
	String password = "testpass2";
	String method = "met";
	String request = "req";

	public static void main(String... args) {
		AppStart appStart = new AppStart();
		appStart.start(args);
	}

	private void start(String... args) {
		System.out.println("Started");
		readParameters(args);
		
		printMyUser();
		printMyEmployee();
		
	}
	
	private void createUser() {
		String urlAddress = "http://localhost:8080/api/user/me";
		
		
	}

	private void printMyUser() {
		String urlAddress = "http://localhost:8080/api/user/me";
		String response = sendRequest(urlAddress, "GET");
		
		JSONObject objRes = new JSONObject(response);
		String username = objRes.getString("username");
		System.out.println("Username is: " + username);
	}
	
	private void printMyEmployee() {
		String urlAddress = "http://localhost:8080/api/employee/me";
		String response = sendRequest(urlAddress, "GET");
		
		JSONObject objRes = new JSONObject(response);
		String firstname = objRes.getString("firstName");
		String lastname = objRes.getString("lastName");
		String username = objRes.getJSONObject("user").getString("username");
		System.out.println(String.format("User with username: %s has firstName: %s and lastName: %s", username, firstname, lastname));
	}
	
	private void createUser(String... args) {
		String urlAddress = "http://localhost:8080/api/user";
		String method = "POST";
		
		
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
	}

	private String sendRequest(String urlAddress, String method) {
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

	private void readParameters(String... args) {
		System.out.println("Started reading parameters: " + args.length);
		for (int i = 0; i < args.length; i = i + 2) {
			parameters.put(args[i], args[i + 1]);
		}
		username = parameters.get("-u");
		password = parameters.get("-p");
		method = parameters.get("-method");
		request = parameters.get("-req");
		System.out.println("Finished reading parameters");
	}

}
