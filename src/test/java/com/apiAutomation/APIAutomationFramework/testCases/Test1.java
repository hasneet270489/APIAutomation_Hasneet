package com.apiAutomation.APIAutomationFramework.testCases;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.apiAutomation.APIAutomationFramework.base.BaseClass;
import com.apiAutomation.APIAutomationFramework.resources.Resources;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Test1 extends BaseClass {
	
	
	@Test
	public void findPlace() {
		RestAssured.baseURI= prop.getProperty("baseURI");
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		Response resp = given().
		   queryParam("id","524901").
		   queryParam("APPID",prop.getProperty("APPID")).
		   
		when().   
	    get(Resources.findPlace()).
		then().statusCode(200).and().contentType(ContentType.JSON).extract().response();
		
		JsonPath js = (JsonPath) convertDataFormat(resp, "Json");
		//XmlPath ps = (XmlPath) convertDataFormat(resp, "XML");
		int resultCount=js.getInt("list.size()");
		for(int i=0;i<resultCount;i++) {
		System.out.println(js.get("list["+i+"].dt"));	 // inverted commas inside inverted commas..... 		
		}
		
		
		
	}
	
	
}