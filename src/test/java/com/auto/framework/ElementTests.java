package com.auto.framework;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.auto.framework.listeners.TestListener;
import com.auto.framework.pageobjects.common.BasePage;
import com.auto.framework.pageobjects.demoqa.ElementsPage;
import com.auto.framework.testdata.UserDataProvider;
import com.auto.framework.testdata.UserModal;

/************************************************************************************************************************
 * @Date : Oct. 20, 2023
 * @Author : naveenchr
 * @Description : Element validation tests
 * @Version : 1.0
 ************************************************************************************************************************/
@SpringBootTest
@Listeners(TestListener.class)
public class ElementTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private BasePage basePage;

	@Autowired
	public ElementsPage elementsPage;

	/**
	 * Verifies that the Elements page loads correctly and displays the expected title.
	 * This is a basic sanity check that should always pass if the application is running.
	 */
	@Test(groups = "Sanity Test")
	public void whenPageLoads_thenDisplayCorrectTitle() {
		elementsPage.openElementsPage();
		assertThat(elementsPage.getPageTitle(), is("DEMOQA"));
	}

	/**
	 * Tests the Text Box form submission with valid user data.
	 * Verifies that all submitted data is correctly displayed in the output section.
     *
     * @param userData Test data containing user information
     */
	@Test(dependsOnGroups = "Sanity Test", dataProvider = "User Data", dataProviderClass = UserDataProvider.class)
	public void whenSubmitTextBoxFormWithValidData_thenDisplayCorrectOutput(UserModal userData) {
		// Opens browser page
		elementsPage.textBoxPF.openTextBoxPage();

		// Perform testing actions
		elementsPage.textBoxPF.enterFullname(userData.getFirstName());
		elementsPage.textBoxPF.enterEmail(userData.getEmail());
		elementsPage.textBoxPF.enterCurrentAddress(userData.getCurrAddress());
		elementsPage.textBoxPF.enterPermanentAddress(userData.getPermAddress());
		elementsPage.textBoxPF.submitForm();

		// Assert data points
		assertThat(elementsPage.textBoxPF.getConfirmationMessage().getFirstName(), is(userData.getFirstName()));
		assertThat(elementsPage.textBoxPF.getConfirmationMessage().getEmail(), is(userData.getEmail()));
		assertThat(elementsPage.textBoxPF.getConfirmationMessage().getCurrAddress(), is(userData.getCurrAddress()));
		assertThat(elementsPage.textBoxPF.getConfirmationMessage().getPermAddress(), is(userData.getPermAddress()));

	}

	/**
	 * Tests the Check Box functionality by expanding the tree and selecting an option.
	 * Verifies that the selected option is correctly reflected in the confirmation message.
     * 
     * Note: This test is currently disabled. To enable, uncomment the @Test annotation.
     */
	// @Test(dependsOnGroups = "Sanity Test")
	public void whenSelectCheckBoxOption_thenDisplayConfirmation() {
		elementsPage.checkBoxPF.openCheckBoxPage();

		elementsPage.checkBoxPF.expandLevel1Menu();
		elementsPage.checkBoxPF.expandLevel2Menu("Documents");
		elementsPage.checkBoxPF.expandLevel3Menu("Workspace");
		elementsPage.checkBoxPF.clickLevel4Option("Angular");

		assertThat(elementsPage.checkBoxPF.getConfirmationMessage(), is("angular"));

	}

	/**
	 * Tests the Radio Button functionality by selecting an option.
     * Verifies that the selected radio button's value is correctly displayed.
     */
	@Test(dependsOnGroups = "Sanity Test")
	public void whenSelectRadioButton_thenDisplaySelectedValue() {
		elementsPage.radioButtonPF.openRadioButtonPage();
		elementsPage.radioButtonPF.clickRadioButton("Impressive");
		assertThat(elementsPage.radioButtonPF.getConfirmationMessage(), is("Impressive"));

	}

	@BeforeMethod
	@Override
	public void springTestContextPrepareTestInstance() throws Exception {
		super.springTestContextPrepareTestInstance();
	}

	@AfterMethod(alwaysRun = true)
	public void teardownDriver() {
		basePage.teardownDriver();
	}

}
