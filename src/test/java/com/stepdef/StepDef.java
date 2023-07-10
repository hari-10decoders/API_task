package com.stepdef;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StepDef {

	public String authToken;
	public Response response;
	public String testPlanName;
	String path = "C:\\Users\\hari1\\eclipse-workspace\\Zephyr_task_1\\src\\test\\resources\\data.properties";
		
	@Given("I have a valid authentication token")
	public void i_have_a_valid_authentication_token() throws IOException {
		FileInputStream f = new FileInputStream(path);
		Properties p = new Properties();
		p.load(f);
		String BASE_URL = p.getProperty("BaseURI");
		String AUTH_TOKEN = p.getProperty("Auth_token");
		String planname = p.getProperty("testPlanName");
		
		testPlanName= planname;
		authToken = AUTH_TOKEN;
		RestAssured.baseURI = BASE_URL;
	}

	@When("I create a test plan with name My Automation Test Plan")
	public void i_create_a_test_plan_with_name_my_automation_test_plan() {

		String endpoint = "/testplans";
		
		String payload = "{ \"name\": \"" + testPlanName + "\",\"projectKey\": \"SCRUM\" }";
		Response response1 = RestAssured.given()
		.header("Authorization", "Bearer " + authToken)
		.contentType(ContentType.JSON).body(payload).post(endpoint);
		response = response1;
	}

	@Then("the test plan should be created successfully")
	public void the_test_plan_should_be_created_successfully() {
		assertEquals(201, response.getStatusCode());
		System.out.println(response.getBody().asPrettyString());
	}

}
