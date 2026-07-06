package com.commonmethods;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIMethods {
    public static Response Trigger_Post_API(String endpoint, String requestBody) {
		RequestSpecification req_spec = RestAssured.given();
		req_spec.body(requestBody);
		Response response = req_spec.post(endpoint);
		return response;
	}
	
	public static Response Trigger_Put_API(String endpoint, String requestBody) {
		RequestSpecification req_spec = RestAssured.given();
		req_spec.body(requestBody);
		Response response = req_spec.put(endpoint);
		return response;
	}
	
	public static Response Trigger_Get_API(String endpoint) {
		RequestSpecification req_spec = RestAssured.given();
		Response response = req_spec.get(endpoint);
		return response;
    }

}
