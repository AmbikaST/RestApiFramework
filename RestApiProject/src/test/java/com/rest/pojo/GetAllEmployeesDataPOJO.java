package com.rest.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetAllEmployeesDataPOJO {
	
	private String message;
	private String status;
	//@JsonProperty("data")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<EmployeeData> data;
	
	public List<EmployeeData> getData() {
		return data;
	}

	public void setData(List<EmployeeData> data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
