package com.stepdef;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.json.JSONObject;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.runner.runner;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.TestStep;
import io.cucumber.messages.types.TestStepResult;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Test;
import junit.framework.TestFailure;

public class stepdef {

	public String authToken;
	public Response response;
	public String value_name;
	public String key_name;
	public String name;
	public static String status;
	public static String zpe_fail;
	public static WebDriver driver = runner.driver;

	String path1 = "C:\\Users\\10Decoders\\eclipse-workspace\\TestExecution_3\\src\\test\\resources\\INputdata.properties";
	String Test_cyclepath = "C:\\Users\\10Decoders\\eclipse-workspace\\TestExecution_3\\src\\test\\resources\\execution_keys.properties";

	@AfterStep
	public void executionedded(Scenario s) {
		if (s.isFailed()) {
			String status1 = "fail";
			status = status1.toString();

		} else {
			String status2 = "pass";
			status = status2.toString();
		}
	}

	@After
	public void after_scenario(Scenario scenario) throws IOException {
		String scenario_name = scenario.getName();
		if (scenario.isFailed()) {
			String status1 = "fail";
			status = status1.toString();
			System.out.println("after scenario need to post as fail:" + status);

		} else {
			String status2 = "pass";
			status = status2.toString();
			System.out.println("after scenario need to post as pass:" + status);

		}
		List<String> resultList = Arrays.asList(scenario_name.split("'"));
		System.out.println("testcase Title: " + resultList);

		String test_casekey = resultList.get(1).toString();
		System.out.println("testcase_key:" + test_casekey);

		String endpoint = "/testexecutions";
		JSONObject json = new JSONObject();
		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream(Test_cyclepath);
		pro.load(fis);
		String testcycle_ID1 = pro.getProperty("testcyclekey");
		System.out.println("testCycle:" + testcycle_ID1);

		json.put("projectKey", "AT");
		json.put("testCaseKey", test_casekey);
		json.put("testCycleKey", testcycle_ID1);
		System.out.println("after post status :" + status);
		json.put("statusName", status);

		Response response1 = RestAssured.given().header("Authorization", "Bearer " + authToken)
				.contentType(ContentType.JSON).body(json.toString()).post(endpoint);
		response = response1;
		assertEquals(201, response.getStatusCode());
		System.out.println("Status Code: " + " " + response.getStatusCode());
		System.out.println(response.getBody().asPrettyString());

	}

	@Given("I given valid authentication token")
	public void i_given_valid_authentication_token() throws IOException {

		FileInputStream f = new FileInputStream(path1);
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

		driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");

	}

	@When("I Execute with selenium code test one to open the log in page and enter valid username {string} and password {string}")
	public void i_execute_with_selenium_code_test_one_to_open_the_log_in_page_and_enter_valid_username_and_password(
			String string, String string2) {
		driver.findElement(By.name("email")).sendKeys(string);
		driver.findElement(By.name("password")).sendKeys(string2);

	}

	@When("To check test case one and Click the login button it should navigate to home page")
	public void to_check_test_case_one_and_click_the_login_button_it_should_navigate_to_home_page()
			throws InterruptedException {
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Thread.sleep(3000);
		String text = driver.findElement(By.xpath("//div[@id='content']/h2")).getText();
		driver.findElement(By.xpath("//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		System.out.println("Sucessfully account created: " + text);

	}

	@When("I Execute with selenium code test two to open the log in page and enter Invalid username and password")
	public void i_execute_with_selenium_code_test_two_to_open_the_log_in_page_and_enter_invalid_username_and_password() {
		driver.findElement(By.name("email")).sendKeys("hphari2026@gmail.com");
//		driver.findElement(By.name("password")).sendKeys("Avengers@20288");
	}

	@When("To check test case two and Click the login button it should navigate to home page")
	public void to_check_test_case_two_and_click_the_login_button_it_should_navigate_to_home_page() {
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		WebElement value = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"));
		System.out.println(value.getText());
		System.out.println("sucessfully passed the testcases1");
	}

}