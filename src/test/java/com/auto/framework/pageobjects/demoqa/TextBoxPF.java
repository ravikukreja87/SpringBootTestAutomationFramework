package com.auto.framework.pageobjects.demoqa;

import static com.auto.framework.constants.Constants.TEXTBOX_PAGE;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.auto.framework.pageobjects.common.BasePageObject;
import com.auto.framework.testdata.UserModal;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Page object fragment for Text Box menu
 * @Version : 1.0
 ************************************************************************************************************************/
@Component
@AllArgsConstructor
@Slf4j
public class TextBoxPF extends BasePageObject {

	private static By fullnameTF = By.id("userName");
	private static By emailTF = By.id("userEmail");
	private static By currentAddressTF = By.cssSelector("#currentAddress");
	private static By permanentAddressTF = By.xpath("//*[@id='permanentAddress']");
	private static By submitButton = By.cssSelector("#submit");

	private static By nameText = By.cssSelector("p#name");
	private static By emailText = By.cssSelector("p#email");
	private static By currAddText = By.cssSelector("p#currentAddress");
	private static By permAddText = By.cssSelector("p#permanentAddress");

	public void openTextBoxPage() {
		webElementInteraction.openURL(frameworkProperties.getDemoUrl() + TEXTBOX_PAGE);
	}

	public void updateTextBoxes(UserModal userData) {
		enterFullname(userData.getFirstName());
		enterEmail(userData.getEmail());
		enterCurrentAddress(userData.getCurrAddress());
		enterPermanentAddress(userData.getPermAddress());
		submitForm();
	}

	@Step("Enter Fullname")
	public void enterFullname(String fullname) {
		webElementInteraction.sendKeys(fullnameTF, fullname);
	}

	@Step("Enter Email")
	public void enterEmail(String email) {
		webElementInteraction.sendKeys(emailTF, email);
	}

	@Step("Enter Current Address")
	public void enterCurrentAddress(String currAddress) {
		webElementInteraction.sendKeys(currentAddressTF, currAddress);
	}

	@Step("Enter Permanent Address")
	public void enterPermanentAddress(String permAddress) {
		webElementInteraction.sendKeys(permanentAddressTF, permAddress);
	}

	@Step("Submit form")
	public void submitForm() {
		webElementInteraction.click(submitButton);
	}

	public UserModal getConfirmationMessage() {
		UserModal userModal = UserModal.builder().firstName(elementValidator.getText(nameText).split(":")[1])
				.email(elementValidator.getText(emailText).split(":")[1])
				.currAddress(elementValidator.getText(currAddText).split(":")[1])
				.permAddress(elementValidator.getText(permAddText).split(":")[1]).build();
		log.info("Confirmation Data: {}", userModal);
		return userModal;
	}

}
