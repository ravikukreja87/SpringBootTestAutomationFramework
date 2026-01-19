package com.auto.framework.pageobjects.demoqa;

import static com.auto.framework.constants.Constants.WEBTABLES_PAGE;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.auto.framework.pageobjects.common.BasePageObject;
import com.auto.framework.testdata.UserModal;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Page object fragment for Web Table
 * @Version : 1.0
 ************************************************************************************************************************/
@Component
@AllArgsConstructor
public class WebTablePF extends BasePageObject {

	private static By addButton = By.cssSelector("#addNewRecordButton");
	private static By firstNameTextField = By.cssSelector("#firstName");
	private static By lastNameTextField = By.id("lastName");
	private static By emailTextField = By.xpath("//*[@id='userEmail']");
	private static By ageTextField = By.cssSelector("#age");
	private static By salaryTextField = By.id("salary");
	private static By departmentTextField = By.id("department");

	public void openWebTablesPage() {
		webElementInteraction.openURL(frameworkProperties.getDemoUrl() + WEBTABLES_PAGE);
	}

	@Step("Add User Data")
	public void addUserData(UserModal userData) {
		webElementInteraction.click(addButton);
		webElementInteraction.sendKeys(firstNameTextField, userData.getFirstName());
		webElementInteraction.sendKeys(lastNameTextField, userData.getLastName());
		webElementInteraction.sendKeys(emailTextField, userData.getEmail());
		webElementInteraction.sendKeys(ageTextField, userData.getAge());
		webElementInteraction.sendKeys(salaryTextField, userData.getSalary());
		webElementInteraction.sendKeys(departmentTextField, userData.getDepartment());
	}


}
