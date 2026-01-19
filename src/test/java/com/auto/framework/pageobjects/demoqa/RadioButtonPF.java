package com.auto.framework.pageobjects.demoqa;

import static com.auto.framework.constants.Constants.RADIOBUTTON_PAGE;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.auto.framework.pageobjects.common.BasePageObject;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Page object fragment for Radio button
 * @Version : 1.0
 ************************************************************************************************************************/
@Component
@AllArgsConstructor
public class RadioButtonPF extends BasePageObject {

	private static By radioButtons = By.xpath("//*[@class='custom-control custom-radio custom-control-inline']");
	private static By successMsg = By.className("text-success");

	public void openRadioButtonPage() {
		webElementInteraction.openURL(frameworkProperties.getDemoUrl() + RADIOBUTTON_PAGE);
	}

	@Step("Clicked on the Radio Button")
	public void clickRadioButton(String text) {
		webElementInteraction.searchAndClickByText(radioButtons, text);
	}

	public String getConfirmationMessage() {
		return elementValidator.getText(successMsg);
	}

}
