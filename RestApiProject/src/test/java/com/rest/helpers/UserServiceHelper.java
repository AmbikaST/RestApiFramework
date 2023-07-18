package com.rest.helpers;


import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;

import com.rest.constants.Endpoints;
import com.rest.pojo.GetAllEmployeesDataPOJO;
import com.rest.utils.Utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserServiceHelper {
	protected static Utils ob = Utils.getInstance();
	protected static Logger log;
	
	@BeforeTest
	public void setUpForBeforeTest() {
		 log = LogManager.getLogger(UserServiceHelper.class);
	}
	
	public static String getBaseURI() {
		Properties application = ob.loadFile();
		String baseUri = application.getProperty("uri");
		return baseUri;
	}
	
	public static GetAllEmployeesDataPOJO  getEmployeesData(){
		Response res = RestAssured.given().
				contentType(ContentType.JSON).when().get(Endpoints.EMPLOYEES);
		ResuableMethods.validateStatusCode(res, 200);
		GetAllEmployeesDataPOJO employeesData = res.as(GetAllEmployeesDataPOJO.class);
		return employeesData;
	}
	
	public static Response addEmployeeData() {
		Response res = RestAssured.given().contentType(ContentType.JSON)
				.body(ResuableMethods.addUerData()).when()
				.post(Endpoints.CREATE);
		return res;
	}
	
	public static int getid(Response res) {
		System.out.println("res id-->"+res.getBody().jsonPath().get("data.id"));
		int id =res.body().jsonPath().get("data.id");
		//id = data.getData().get(0).getId();
		return id;
	}
	
	public static Response deleteEmployeeData(int id) {

		System.out.println("id="+id);
		Response res = RestAssured.given().
				contentType(ContentType.JSON).when().delete(Endpoints.DELETE+"/"+id);
		return res;
	}
	
	public static Response getSpecificEmployee(int empid) {
		Response res = RestAssured.given().
				contentType(ContentType.JSON).when().get(Endpoints.EMPLOYEE+"/"+empid);
		return res;
	}
}
