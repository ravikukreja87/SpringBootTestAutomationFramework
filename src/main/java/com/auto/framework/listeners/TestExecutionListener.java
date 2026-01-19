package com.auto.framework.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.internal.TestResult;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Test execution listener implementation to manage driver lifecycle and log test results with Allure
 *                  reporting integration. This class extends TestListenerAdapter to provide custom test
 *                  execution handling including driver cleanup, screenshot capture on failure, and
 *                  comprehensive test logging for debugging and reporting purposes.
 * @Version : 1.2
 ************************************************************************************************************************/

/**
 * TestNG listener implementation for managing WebDriver lifecycle and test reporting.
 * This class provides comprehensive test execution management including driver cleanup,
 * screenshot capture on failures, and integration with Allure reporting framework.
 * 
 * <p>The listener is scoped to 'driverscope' ensuring it operates on the correct
 * WebDriver instance for each test thread during parallel execution.</p>
 * 
 * @author Ravi Kukreja
 * @version 1.2
 * @since 1.0
 * @see org.testng.TestListenerAdapter
 * @see org.testng.ITestResult
 * @see io.qameta.allure.Allure
 * @see org.openqa.selenium.WebDriver
 */
@Slf4j
@Component
@Scope("driverscope")
public class TestExecutionListener extends TestListenerAdapter {

	/**
	 * Called when a test starts execution.
	 * Logs the test start and performs any necessary setup.
	 * 
	 * @param iTestResult The test result object containing test information
	 */
	@Override
	public void onTestStart(ITestResult iTestResult) {
		super.onTestStart(iTestResult);
		log.info("Started: {}", iTestResult.getName());
	}

	/**
	 * Called when a test completes successfully.
	 * Updates test reports and logs successful completion.
	 * 
	 * @param iTestResult The test result object containing test information
	 */
	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		super.onTestSuccess(iTestResult);
		testReportUpdate(iTestResult);
		log.info("Finished successfully: {}", iTestResult.getName());
	}

	/**
	 * Called when a test is skipped.
	 * Updates test reports and logs the skip event.
	 * 
	 * @param iTestResult The test result object containing test information
	 */
	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		super.onTestSkipped(iTestResult);
		testReportUpdate(iTestResult);
		log.info("Skipped: {}", iTestResult.getName());
	}

	/**
	 * Called when a test fails.
	 * Captures screenshots, updates reports, and logs failure details.
	 * 
	 * @param iTestResult The test result object containing test information and failure details
	 */
	@Override
	public void onTestFailure(ITestResult iTestResult) {
		super.onTestFailure(iTestResult);
		log.error("Failed: {} with: {}", iTestResult.getName(), iTestResult.getThrowable().toString());
		testReportUpdate(iTestResult);
	}

	/**
	 * Captures a screenshot of the current browser state and attaches it to Allure reports.
	 * This method is annotated with @Attachment to automatically include screenshots in test reports.
	 * 
	 * @param driver The WebDriver instance to capture screenshot from
	 * @return Byte array representing the screenshot image, or empty array if capture fails
	 */
	@Attachment(value = "Screen shot", type = "image/png", fileExtension = ".png")
	private byte[] attachScreenShot(WebDriver driver) {
		try {
			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		} catch (WebDriverException e) {
			log.error("Selenium screenshot capture failed: {}", e.getMessage());
		}
		return new byte[0];
	}

	/**
	 * Updates Allure test reports with test information and screenshots.
	 * This method formats test data and updates the Allure lifecycle with test details.
	 * 
	 * @param iTestResult The test result object containing test information
	 */
	public void testReportUpdate(ITestResult iTestResult) {
		String testSetNumber = iTestResult.getName() + " " + (((TestResult) iTestResult).getParameterIndex() + 1);

		log.info("Allure report : {}", iTestResult);
		log.info("Test Set Number : {}", testSetNumber);
		AllureLifecycle lifecycle = Allure.getLifecycle();
		if (lifecycle.getCurrentTestCase().isPresent()) {
			lifecycle.updateTestCase(testResult -> testResult.setName(testSetNumber));
		}
	}
}
