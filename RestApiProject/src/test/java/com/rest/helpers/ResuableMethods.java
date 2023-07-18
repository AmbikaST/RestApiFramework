package com.rest.helpers;

import com.rest.pojo.AddEmployeeDataPOJO;
import com.rest.pojo.GetAllEmployeesDataPOJO;

import io.restassured.response.Response;

public class ResuableMethods {
	
	public static AddEmployeeDataPOJO addData;
	
	public static AddEmployeeDataPOJO addUerData() {
		addData = new AddEmployeeDataPOJO();
		addData.setEmployee_age(30);
		addData.setEmployee_salary(676900);
		addData.setEmployee_name("RestAPITest");
		return addData;
		
	}
	
	public static int getStatusCode(Response response) {
		return response.statusCode();
	}
	
	public static void validateStatusCode(Response res, int status) {
		res.then().statusCode(status); 
	}
	
	public static String getStatus(GetAllEmployeesDataPOJO data) {
		return data.getStatus();
	}
	
	public static String getJsonResponseStatus(Response res) {
		return res.body().jsonPath().getString("status");
	}

}
