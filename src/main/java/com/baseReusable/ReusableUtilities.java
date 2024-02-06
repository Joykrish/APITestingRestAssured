package com.baseReusable;

import java.util.HashMap;
import java.util.Map;

import com.Reporting.ExtentReportManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class ReusableUtilities {
	RequestSpecification request;

	public JsonPath rawToJson(Response r) {
		String res = r.asString();
		JsonPath toJson = new JsonPath(res);
		return toJson;

	}

	public RequestSpecification requestSpecification(String URI) {
		RestAssured.baseURI = URI;
		request = RestAssured.given().log().all();
		return request;

	}

	public RequestSpecification requestSpecification(String URI, ContentType contentType, Object requestPayload,
			Map<String, String> Header) {
		RestAssured.baseURI = URI;
		request = RestAssured.given().contentType(contentType).headers(Header).body(requestPayload).log().all();

		return request;

	}

	public Response extractResponseandPrint(Response r) {
		r.prettyPrint();
		return r = r.then().extract().response();

	}

	public int getStatusCode(Response r) {
		int statuscode = r.getStatusCode();
		System.out.println("Status Code is: " + statuscode);
		return statuscode;
	}

	public String getStatusMessage(Response r) {
		String statusMessage = r.getStatusLine();
		System.out.println("Status Message is: " + statusMessage);
		return statusMessage;
	}

	public Response performGet(String endPoints, String resources, ContentType contentType,
			HashMap<String, String> headers) {
		RequestSpecification requestSpecification = requestSpecification(endPoints, contentType,
				new HashMap<Object, String>(), headers);
		Response response = requestSpecification.get(resources);
		printRequestDetails(requestSpecification);
		printResponseDetails(response);
		return response;
	}

	public Response performPost(String endPoints, String resources, ContentType contentType,
			Map<String, Object> requestPayload, Map<String, String> headers) {
		RequestSpecification requestSpecification = requestSpecification(endPoints, contentType, requestPayload,
				headers);
		Response response = requestSpecification.post(resources);
		printRequestDetails(requestSpecification);
		printResponseDetails(response);
		return response;
	}

	public Response performPost(String endPoints, String resources, ContentType contentType, String requestPayload,
			Map<String, String> headers) {
		RequestSpecification requestSpecification = requestSpecification(endPoints, contentType, requestPayload,
				headers);
		Response response = requestSpecification.post(resources);
		printRequestDetails(requestSpecification);
		printResponseDetails(response);
		return response;
	}

	public Response performPost(String endPoints, String resources, ContentType contentType, Object requestPayload,
			Map<String, String> headers) {
		RequestSpecification requestSpecification = requestSpecification(endPoints, contentType, requestPayload,
				headers);
		Response response = requestSpecification.post(resources);
		printRequestDetails(requestSpecification);
		printResponseDetails(response);
		return response;
	}

	public Response performPut(String endPoints, String resources, ContentType contentType,
			Map<String, Object> requestPayload, Map<String, String> headers) {
		RequestSpecification requestSpecification = requestSpecification(endPoints, contentType, requestPayload,
				headers);
		Response response = requestSpecification.put(resources);
		printRequestDetails(requestSpecification);
		printResponseDetails(response);
		return response;
	}
	
	public Response performPatch(String endPoints, String resources, ContentType contentType,
			String requestPayload, Map<String, String> headers) {
		RequestSpecification requestSpecification = requestSpecification(endPoints, contentType, requestPayload,
				headers);
		Response response = requestSpecification.patch(resources);
		printRequestDetails(requestSpecification);
		printResponseDetails(response);
		return response;
	}

	public Response performDelete(String endPoints, String resources, ContentType contentType,
			HashMap<String, String> headers) {
		RequestSpecification requestSpecification = requestSpecification(endPoints, contentType,
				new HashMap<Object, String>(), headers);
		Response response = requestSpecification.delete(resources);
		printRequestDetails(requestSpecification);
		printResponseDetails(response);
		return response;
	}

	private static void printRequestDetails(RequestSpecification requestSpec) {
		QueryableRequestSpecification queryablerequestspecification = SpecificationQuerier.query(requestSpec);
		ExtentReportManager.logInfoDetails("End Point is: " + queryablerequestspecification.getBaseUri());
		ExtentReportManager.logInfoDetails("Method is: " + queryablerequestspecification.getMethod());
		ExtentReportManager.logInfoDetails("Headers are: ");
		ExtentReportManager.printHeaders(queryablerequestspecification.getHeaders().asList());
		ExtentReportManager.logInfoDetails("Response body: ");
		ExtentReportManager.logJson(queryablerequestspecification.getBody().toString());

	}

	private static void printResponseDetails(Response response) {
		ExtentReportManager.logInfoDetails("Response status is:  " + response.getStatusCode());
		ExtentReportManager.logInfoDetails("Response status are");
		ExtentReportManager.printHeaders(response.getHeaders().asList());
		ExtentReportManager.logInfoDetails("Reponse body is ");
		ExtentReportManager.logJson(response.getBody().prettyPrint());

	}

}
