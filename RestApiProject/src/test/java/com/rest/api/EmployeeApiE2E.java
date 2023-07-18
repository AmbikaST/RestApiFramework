package com.rest.api;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rest.helpers.ResuableMethods;
import com.rest.helpers.UserServiceHelper;
import com.rest.pojo.GetAllEmployeesDataPOJO;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EmployeeApiE2E extends UserServiceHelper{
	
	ResuableMethods resuable = new ResuableMethods();
	static int extractedId = 0;
	
	@BeforeMethod
	public static void baseURI() {
		RestAssured.baseURI = getBaseURI();
	}
	
	//Test case 1:
	@Test(priority = 1)
	public static void getAllEmplyeesData() {
		GetAllEmployeesDataPOJO employeesData = getEmployeesData();
		try {
			ResuableMethods.getStatus(employeesData);
			log.info("Employees Count = "+employeesData.getData().size());
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}
	
	//Test case 2:
	@Test(dependsOnMethods = "getAllEmplyeesData")
	public static void addEmployee() {
		System.out.println("comming inside the add employee");
		Response res = addEmployeeData();
		
		try {
			GetAllEmployeesDataPOJO employeesData = res.body().as(GetAllEmployeesDataPOJO.class);
			Assert.assertEquals(ResuableMethods.addData.getEmployee_name(),employeesData.getData().get(0).getEmployee_name());
			Assert.assertEquals(ResuableMethods.addData.getEmployee_age(),employeesData.getData().get(0).getEmployee_age());
			Assert.assertEquals(ResuableMethods.addData.getEmployee_salary(),employeesData.getData().get(0).getEmployee_salary());
			extractedId = getid(res);
			log.info("Generated Id = "+extractedId);
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	//Test case 3:
	@Test(dependsOnMethods = "addEmployee")
	public static void deleteEmployee(){
		Response res = deleteEmployeeData(extractedId);
		try {
			ResuableMethods.validateStatusCode(res, 200);
			String status = ResuableMethods.getJsonResponseStatus(res);
			Assert.assertEquals(status, "success");
			String id = res.body().jsonPath().get("data");
			Assert.assertEquals(id, extractedId);
			log.info("Id from response = " +id);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}
	
	//Test case 4:
	@Test(dependsOnMethods = "deleteEmployee")
	public static void deleteEmployeeTestcase4() throws InterruptedException {
		extractedId = 0;
		Response res = deleteEmployeeData(extractedId);
		try {
			ResuableMethods.validateStatusCode(res, 400);
			String status = ResuableMethods.getJsonResponseStatus(res);
			Assert.assertEquals(status, "error");
			log.info("Delete Response body testcase4 :  "+ res.getBody().asString());
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	//Test case 5:
	@Test(dependsOnMethods = "deleteEmployeeTestcase4")
	public static void employeeData() {
		int empid = 2;
		Response res = getSpecificEmployee(empid);
		
		try {
			ResuableMethods.validateStatusCode(res, 200);
			Assert.assertEquals(res.getContentType(), "application/json");
			Assert.assertEquals(res.getBody().jsonPath().get("data.employee_name"), "Garrett Winters");
			Assert.assertEquals(res.getBody().jsonPath().get("data.employee_salary"), 170750);
			Assert.assertEquals(res.getBody().jsonPath().get("data.employee_age"), 63);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
