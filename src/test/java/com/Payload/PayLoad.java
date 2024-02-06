package com.Payload;

import java.util.HashMap;
import java.util.Map;

import com.Utilities.ExcelReader;

public class PayLoad {

	public static String path = System.getProperty("user.dir") + "\\APIPayloaddata.xlsx";

	static ExcelReader exl = new ExcelReader(path);
	public static String Name = exl.getCellData("AddStudents", "Value", 2);
	public static String Class = exl.getCellData("AddStudents", "Value", 3);
	public static String location = exl.getCellData("AddStudents", "Value", 4);
	public static String subject1 = exl.getCellData("AddStudents", "Value", 5);
	public static String subject2 = exl.getCellData("AddStudents", "Value", 6);

	public static Map<String, Object> NewStudent() {

		Map<String, Object> students = new HashMap<String, Object>();
		students.put("Name", "Gokulanda");
		students.put("Class", "1");
		students.put("location", "Krishnaloka");
		String[] subjects = { "oppulence", "Bhagavatam" };
		students.put("Subjects", subjects);

		return students;

	}

	public static Map<String, Object> updateStudent() {

		Map<String, Object> students = new HashMap<String, Object>();
		students.put("Name", "GokulandaMadhav");
		students.put("Class", "1");
		students.put("location", "Mayapur");
		String[] subjects = { "oppulence", "Chaitanya Charitamruta" };
		students.put("Subjects", subjects);

		return students;

	}

	public static String updateNameAndLocation() {

		return "{\r\n"
				+ "    \"Name\": \"Giridhari Radhe\",\r\n"
				+ "    \r\n"
				+ "    \"location\": \"MathuraVrindavan\"\r\n"
				+ "    \r\n"
				+ "}";

	}

}
