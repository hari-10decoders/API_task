package com.runner;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\java\\com\\feature",glue = "com\\stepdef" 
,plugin = {"pretty","html:target/CucumberReport.html"})

public class runner {
	
	public static String testcycle_ID;

	
	
	@BeforeClass
	public static void Creat_testcycle() throws IOException {
		 String BaseURI = "https://api.zephyrscale.smartbear.com/v2";
		 String Auth_token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjb250ZXh0Ijp7ImJhc2VVcmwiOiJodHRwczovL2FwaS10ZXN0aW5nNDEuYXRsYXNzaWFuLm5ldCIsInVzZXIiOnsiYWNjb3VudElkIjoiNzEyMDIwOmVkNzc5Nzk1LTA3M2QtNDA2OC04YTljLTJjOGM4N2Y0NWQ2NyJ9fSwiaXNzIjoiY29tLmthbm9haC50ZXN0LW1hbmFnZXIiLCJzdWIiOiJiZWJiZTIwZi1kZTEzLTM5NzktOWE5OC0zODFkN2ExYzU1MDQiLCJleHAiOjE3MTk0OTM0NjgsImlhdCI6MTY4Nzk1NzQ2OH0.y9Shrmd9dt0wlB3NsY6AlHenWS5Ws5-cFuRFZhictUc";
		 RestAssured.baseURI = BaseURI;		
		String endpoint = "/testcycles";
		String pro_path ="C:\\Users\\10Decoders\\eclipse-workspace\\TestExecution\\src\\test\\resources\\execution_keys.properties";

		JSONObject json = new JSONObject();
		Map<String, String> payload = new HashMap<>();
		json.put("projectKey", "AT");
		json.put("name", "test_Cycle_execution");
		

		Response response = RestAssured.given().header("Authorization", "Bearer " + Auth_token)
				.contentType(ContentType.JSON).body(json.toString()).post(endpoint);

		 String testCaseKey = response.jsonPath().getString("key");
			String string1 = testCaseKey.toString();
			testcycle_ID=string1;
			
			assertEquals(201, response.getStatusCode());
			System.out.println("Status Code: " + " " + response.getStatusCode());
			System.out.println(testcycle_ID);
			System.out.println(response.getBody().asPrettyString());
			System.out.println("**************testplan sucessfully created**************");
			
			Properties p = new Properties();
			FileInputStream fis = new FileInputStream(pro_path);
			p.load(fis);
			System.out.println(p.getProperty("testcyclekey"));
			System.out.println(p.setProperty("testcyclekey", testcycle_ID));
			
			FileOutputStream f = new FileOutputStream(pro_path);
			p.store(f, null);
			System.out.println(p.getProperty("testcyclekey"));
			
	}
	@AfterClass
	public static void test_executed_Result() {
		System.out.println("test sucessfully executed");
	}

}
