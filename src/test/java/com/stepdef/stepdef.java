package com.stepdef;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.json.JSONObject;

import com.runner.runner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class stepdef extends runner {

	public String authToken;
	public Response response;
	public String value_name;
	public String key_name;
	public String name;
	public String testcycleID = testcycle_ID;

	String path = "C:\\Users\\10Decoders\\eclipse-workspace\\TestExecution\\src\\test\\resources\\INputdata.properties";

	@Given("I given valid authentication token")
	public void i_given_valid_authentication_token() throws IOException {
		FileInputStream f = new FileInputStream(path);
		Properties p = new Properties();
		p.load(f);

		String BASE_URL = p.getProperty("BaseURI");
		String AUTH_TOKEN = p.getProperty("Auth_token");
		authToken = AUTH_TOKEN;
		RestAssured.baseURI = BASE_URL;

		String PK = p.getProperty("projectKey");
		key_name = PK;
		String PK_V = p.getProperty("projectvalue");
		value_name = PK_V;
		String key = p.getProperty("keyname");
		name = key;
	}

	@When("I Execute the test Method for automate first test method passed as we expected")
	public void i_execute_the_test_method_for_automate_first_test_method_passed_as_we_expected() {
		System.out.println("pre_condition method sucessfully passed");

	}

	@When("I execute the test result as a pass with test key {string} when test method works as we expected")
	public void i_execute_the_test_result_as_a_pass_with_test_key_when_test_method_works_as_we_expected(String string) {
		String endpoint = "/testexecutions";
		JSONObject json = new JSONObject();
//		Map<String, String> payload = new HashMap<>();

		json.put(key_name, "AT");
		json.put("testCaseKey", string);
		json.put("testCycleKey", testcycleID);
		json.put("statusName", "pass");

		Response response1 = RestAssured.given().header("Authorization", "Bearer " + authToken)
				.contentType(ContentType.JSON).body(json.toString()).post(endpoint);
		response = response1;
	}
	
	
	   
	@Then("I get the API response test case one staus details")
	public void i_get_the_api_response_test_case_one_staus_details() {
		assertEquals(201, response.getStatusCode());
		System.out.println("Status Code: " + " " + response.getStatusCode());
		System.out.println(response.getBody().asPrettyString());
		System.out.println("**************sucessfully testExecution completed**************");
	}

	@When("I Execute the test Method for automate second test case get blocked as we expected")
	public void i_execute_the_test_method_for_automate_second_test_case_get_blocked_as_we_expected() {
		System.out.println("pre_condition method sucessfully Blocked");
	}

	@When("I execute the test result as a Blocked with test key {string} when test method works as we expected")
	public void i_execute_the_test_result_as_a_blocked_with_test_key_when_test_method_works_as_we_expected(String string) {
		String endpoint = "/testexecutions";
		JSONObject json = new JSONObject();
//		Map<String, String> payload = new HashMap<>();

		json.put(key_name, "AT");
		json.put("testCaseKey", string);
		json.put("testCycleKey", testcycleID);
		json.put("statusName", "Blocked");

		Response response1 = RestAssured.given().header("Authorization", "Bearer " + authToken)
				.contentType(ContentType.JSON).body(json.toString()).post(endpoint);
		response = response1;
	}

	@Then("I get the API response test case two staus details")
	public void i_get_the_api_response_test_case_two_staus_details() {
		assertEquals(201, response.getStatusCode());
		System.out.println("Status Code: " + " " + response.getStatusCode());
		System.out.println(response.getBody().asPrettyString());
		System.out.println("**************sucessfully testExecution completed**************");
	}

	@When("I Execute the test Method to automate third test case get failed as we expected")
	public void i_execute_the_test_method_to_automate_third_test_case_get_failed_as_we_expected() {
		System.out.println("pre_condition method sucessfully Blocked");
	}

	@When("I execute the test result as a failed with test key {string} when test method works as we expected")
	public void i_execute_the_test_result_as_a_failed_with_test_key_when_test_method_works_as_we_expected(String string) {
		String endpoint = "/testexecutions";
		JSONObject json = new JSONObject();
//		Map<String, String> payload = new HashMap<>();

		json.put(key_name, "AT");
		json.put("testCaseKey", string);
		json.put("testCycleKey", testcycleID);
		json.put("statusName", "Fail");

		Response response1 = RestAssured.given().header("Authorization", "Bearer " + authToken)
				.contentType(ContentType.JSON).body(json.toString()).post(endpoint);
		response = response1;
	}

	@Then("I get the API response test case three staus details")
	public void i_get_the_api_response_test_case_three_staus_details() {
		assertEquals(201, response.getStatusCode());
		System.out.println("Status Code: " + " " + response.getStatusCode());
		System.out.println(response.getBody().asPrettyString());
		System.out.println("**************sucessfully testExecution completed**************");
	}

}
