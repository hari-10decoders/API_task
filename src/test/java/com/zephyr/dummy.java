package com.zephyr;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class dummy {

	public String projectid = "SCRUM";
	public static String AUTH_TOKEN;

	public static void main(String[] args) throws IOException {
		FileInputStream file = new FileInputStream(
				"C:\\Users\\hari1\\eclipse-workspace\\Zephyr_task_1\\src\\test\\resources\\data.properties");
		Properties p = new Properties();
		p.load(file);

		String BASE_URL = p.getProperty("BaseURI");
		RestAssured.baseURI = BASE_URL;

		String token = p.getProperty("Auth_token");
		AUTH_TOKEN = token;
//	         Step 1: Create a test plan
		String testPlanId = createTestPlan("My Test Plan");

		// Step 2: Create a test cycle
		String testCycleId = createTestCycle(testPlanId, "My Test Cycle");
//
//	        // Step 3: Create a test case
		String testCaseId = createTestCase(testCycleId, "My Test Case");
	}

	private static String createTestPlan(String testPlanName) {
		String endpoint = "/testplans";

		String requestBody = "{ \"name\": \"" + testPlanName + "\",\"projectKey\": \"SCRUM\" }";

		Response response = RestAssured.given().header("Authorization", "Bearer " + AUTH_TOKEN)
				.contentType(ContentType.JSON).body(requestBody).post(endpoint);

		int statusCode = response.getStatusCode();
		String message = response.getBody().asString();

		System.out.println("Test Plan:");
		System.out.println("Status Code: " + statusCode);
		System.out.println("Message: " + message);
		return message;

	}

	private static String createTestCycle(String testPlanId, String testCycleName) {
		String endpoint = "/testcycles";

		String requestBody = "{ \"testPlanId\": \"" + testPlanId + "\", \"name\": \"" + testCycleName
				+ "\",\"projectKey\": \"SCRUM\" }";

		Response response = RestAssured.given().header("Authorization", "Bearer " + AUTH_TOKEN)
				.contentType(ContentType.JSON).body(requestBody).post(endpoint);

		int statusCode = response.getStatusCode();
		String message = response.getBody().asString();

		System.out.println("Test Plan:");
		System.out.println("Status Code: " + statusCode);
		System.out.println("Message: " + message);
		return message;
	}

	private static String createTestCase(String testCycleId, String testCaseName) {
		String endpoint = "/testcases";

		String requestBody = "{ \"testCycleId\": \"" + testCycleId + "\", \"name\": \"" + testCaseName
				+ "\",\"projectKey\": \"SCRUM\" }";

		Response response = RestAssured.given().header("Authorization", "Bearer " + AUTH_TOKEN)
				.contentType(ContentType.JSON).body(requestBody).post(endpoint);

		int statusCode = response.getStatusCode();
		String message = response.getBody().asString();

		System.out.println("Test Plan:");
		System.out.println("Status Code: " + statusCode);
		return message;
	}
}