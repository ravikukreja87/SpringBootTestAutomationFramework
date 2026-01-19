package com.auto.framework.pageobjects.demoqa;

import static com.auto.framework.constants.Constants.ELEMENTS_PAGE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auto.framework.pageobjects.common.BasePageObject;

import io.qameta.allure.Step;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Elements page object
 * @Version : 1.0
 ************************************************************************************************************************/
@Component
public class ElementsPage extends BasePageObject {

	@Autowired
	public TextBoxPF textBoxPF;

	@Autowired
	public CheckBoxPF checkBoxPF;

	@Autowired
	public RadioButtonPF radioButtonPF;

	@Autowired
	public WebTablePF webTablePF;

	@Step("Open webpage")
	public void openElementsPage() {
		webElementInteraction.openURL(frameworkProperties.getDemoUrl() + ELEMENTS_PAGE);
	}

	@Step("Verify Page Title")
	public String getPageTitle() {
		return elementValidator.getTitle();
	}

}
