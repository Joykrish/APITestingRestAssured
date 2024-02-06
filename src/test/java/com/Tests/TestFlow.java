package com.Tests;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Payload.PayLoad;
import com.Payload.Pojo;
import com.baseReusable.ReusableUtilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestFlow extends ReusableUtilities {

	Pojo pjStudent = new Pojo();
	public String id;
	ReusableUtilities RU = new ReusableUtilities();

	@Test(priority = 1)
//Test case to add new student
	public void addNewStudentPOST() throws JsonMappingException, JsonProcessingException {
		String endPoints = "http://localhost:3000";
		String resources = "/Students";
		ContentType contentType = ContentType.JSON;

		pjStudent.setName(PayLoad.Name);
		pjStudent.setClassValue(PayLoad.Class);
		pjStudent.setLocation(PayLoad.location);
		String[] Subjects = { PayLoad.subject1, PayLoad.subject2 };
		pjStudent.setSubjects(Subjects);
		//below is when we  want to execute the post method using pojo
		Response res = performPost(endPoints, resources, contentType, pjStudent, new HashMap<String, String>());
		//below is when we  want to execute the post method using payload from com.Payload.PayLoad
		//Response res = performPost(endPoints, resources, contentType, PayLoad.NewStudent(), new HashMap<String, String>());
		Pojo PR = res.as(Pojo.class);
		String responseName = PR.getName();
		String location = PR.getLocation();
		String[] SubjectsValue = PR.getSubjects();

		Assert.assertEquals(res.getStatusCode(), 201);
		Assert.assertEquals(responseName, "Ghandharvika");
		Assert.assertEquals(location, "Mayapur");

		Assert.assertEquals(SubjectsValue[0], "Vedas");
		Assert.assertEquals(SubjectsValue[1], "Bhagavatam");

		extractResponseandPrint(res);
		getStatusCode(res);

		JsonPath js = RU.rawToJson(res);

		id = js.get("id");
		System.out.println("ID is:" + id);

	}

	@Test(priority = 2)
	// Test case to retrieve the newly added student from test
	// one(addNewStudentPOST)
	public void oneStudentGET() {

		String endPoints = "http://localhost:3000";
		String resources = "/Students/" + id + "";
		ContentType contentType = ContentType.JSON;
		Response res = performGet(endPoints, resources, contentType, new HashMap<String, String>());
		extractResponseandPrint(res);
		getStatusCode(res);
		Assert.assertEquals(res.getStatusCode(), 200);

	}

	@Test(priority = 3)
	// Test case to retrieve all the students on the server
	public void studentsListGET() {
		String endPoints = "http://localhost:3000";
		String resources = "/Students";
		ContentType contentType = ContentType.JSON;
		Response res = performGet(endPoints, resources, contentType, new HashMap<String, String>());
		extractResponseandPrint(res);
		getStatusCode(res);
		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test(priority = 4)
	// Test case to update the newly added student from test one(addNewStudentPOST)
	public void updateStudentPUT() {

		String endPoints = "http://localhost:3000";
		String resources = "/Students/" + id + "";
		ContentType contentType = ContentType.JSON;

		Response res = performPut(endPoints, resources, contentType, PayLoad.updateStudent(),
				new HashMap<String, String>());

		extractResponseandPrint(res);
		getStatusCode(res);

		Assert.assertEquals(res.getStatusCode(), 200);
		JsonPath js = RU.rawToJson(res);
		String updatedName = js.get("Name");
		String updatedLocation = js.get("location");
		Assert.assertEquals(updatedName, "GokulandaMadhav");
		Assert.assertEquals(updatedLocation, "Mayapur");

	}
	
	@Test(priority = 5)
	// Test case to update the some part of output json body of  newly added student from test one(addNewStudentPOST)
	public void updateStudentPATCH() {

		String endPoints = "http://localhost:3000";
		String resources = "/Students/" + id + "";
		ContentType contentType = ContentType.JSON;

		Response res = performPatch(endPoints, resources, contentType, PayLoad.updateNameAndLocation(),
				new HashMap<String, String>());

		extractResponseandPrint(res);
		getStatusCode(res);

		Assert.assertEquals(res.getStatusCode(), 200);
		JsonPath js = RU.rawToJson(res);
		String updatedName = js.get("Name");
		String updatedLocation = js.get("location");
		Assert.assertEquals(updatedName, "Giridhari Radhe");
		Assert.assertEquals(updatedLocation, "MathuraVrindavan");

	}

	@Test(priority = 6)
	// Test case to delete the newly added student from test one(addNewStudentPOST)

	public void studentDELETE() {

		String endPoints = "http://localhost:3000";
		String resources = "/Students/" + id + "";
		ContentType contentType = ContentType.JSON;

		Response res = performDelete(endPoints, resources, contentType, new HashMap<String, String>());
		extractResponseandPrint(res);
		getStatusCode(res);

		Assert.assertEquals(res.getStatusCode(), 200);
	}

}
