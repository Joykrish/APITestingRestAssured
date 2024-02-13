package com.Reporting;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class SetUp implements ITestListener {

	private static ExtentReports extentReport;

	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		ExtentTest test = extentReport
				.createTest("TestName: " + result.getName() + "-" + result.getMethod().getMethodName());
		extentTest.set(test);

	}

	public void onTestFailure(ITestResult result) {
		ExtentReportManager.logFailDetails(result.getThrowable().getMessage());
		String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
		stackTrace = stackTrace.replaceAll(",", "<br>");
		String formattedTrace = "<details>\r\n" + "<summary>click here to see  Exception Logs</summary>\r\n" + ""
				+ stackTrace + "\r\n" + "</details>";
		ExtentReportManager.logExceptionDetails(formattedTrace);
	}

	public void onStart(ITestContext context) {
		String fileName = ExtentReportManager.getReportNamewithTimeStamp();
		String fullReportPath = System.getProperty("user.dir") + "\\reports\\" + "TestReport";
		extentReport = ExtentReportManager.createInstance(fullReportPath, "API Test Report", "Test Execution Summary");

	}

	public void onFinish(ITestContext context) {
		if (extentReport != null) {
			extentReport.flush();
		}
	}

}
