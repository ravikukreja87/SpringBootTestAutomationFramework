package com.auto.framework;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.auto.framework.listeners.TestExecutionListener;
import com.auto.framework.pageobjects.common.BasePageObject;
import com.auto.framework.pageobjects.demoqa.ElementsPage;
import com.auto.framework.testdata.UserDataProvider;
import com.auto.framework.testdata.UserModal;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Element validation tests for the demoqa.com Elements page. This test class validates
 *                  various UI interactions including page navigation, form submissions, radio button
 *                  selection, and checkbox interactions. Tests are organized with proper dependencies
 *                  and data providers for comprehensive validation of web element functionality.
 * @Version : 1.2
 ************************************************************************************************************************/

/**
 * Test class for validating Elements page functionality on demoqa.com.
 * This class contains comprehensive tests for various UI interactions including
 * page navigation, form submissions, radio button selection, and checkbox interactions.
 * 
 * <p>Tests are organized with proper dependencies and data providers to ensure
 * reliable execution and comprehensive validation of web element functionality.</p>
 * 
 * @author Ravi Kukreja
 * @version 1.2
 * @since 1.0
 * @see org.springframework.boot.test.context.SpringBootTest
 * @see org.testng.annotations.Test
 * @see com.auto.framework.pageobjects.demoqa.ElementsPage
 */
@SpringBootTest
@Listeners(TestExecutionListener.class)
public class ElementValidationTests extends AbstractTestNGSpringContextTests {

    /**
     * Autowired BasePageObject instance providing common page functionality.
     */
    @Autowired
    @Qualifier("basePageObject")
    private BasePageObject basePage;

    /**
     * Autowired ElementsPage instance for Elements page specific interactions.
     */
    @Autowired
    public ElementsPage elementsPage;

	/**
	 * Verifies that the Elements page loads correctly and displays the expected title.
	 * This is a basic sanity check that should always pass if the application is running.
	 * 
	 * @throws AssertionError if the page title does not match the expected value
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
     * @param userData Test data containing user information from the data provider
     * @throws AssertionError if any submitted data does not match the output
     */
	@Test(dependsOnGroups = "Sanity Test", dataProvider = "User Data", dataProviderClass = UserDataProvider.class)
	public void whenSubmitTextBoxFormWithValidData_thenDisplayCorrectOutput(UserModal userData) {
		elementsPage.textBoxPF.openTextBoxPage();

		elementsPage.textBoxPF.enterFullname(userData.getFirstName());
		elementsPage.textBoxPF.enterEmail(userData.getEmail());
		elementsPage.textBoxPF.enterCurrentAddress(userData.getCurrAddress());
		elementsPage.textBoxPF.enterPermanentAddress(userData.getPermAddress());
		elementsPage.textBoxPF.submitForm();

		assertThat(elementsPage.textBoxPF.getConfirmationMessage().getFirstName(), is(userData.getFirstName()));
		assertThat(elementsPage.textBoxPF.getConfirmationMessage().getEmail(), is(userData.getEmail()));
		assertThat(elementsPage.textBoxPF.getConfirmationMessage().getCurrAddress(), is(userData.getCurrAddress()));
		assertThat(elementsPage.textBoxPF.getConfirmationMessage().getPermAddress(), is(userData.getPermAddress()));

	}

	/**
	 * Tests the Check Box functionality by expanding the tree and selecting an option.
	 * Verifies that the selected option is correctly reflected in the confirmation message.
     * 
     * @throws AssertionError if the confirmation message does not contain the expected text
     */
	@Test(dependsOnGroups = "Sanity Test")
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
     * 
     * @throws AssertionError if the confirmation message does not match the selected value
     */
	@Test(dependsOnGroups = "Sanity Test")
	public void whenSelectRadioButton_thenDisplaySelectedValue() {
		elementsPage.radioButtonPF.openRadioButtonPage();
		elementsPage.radioButtonPF.clickRadioButton("Impressive");
		assertThat(elementsPage.radioButtonPF.getConfirmationMessage(), is("Impressive"));

	}

	/**
	 * Prepares the Spring test context before each test method execution.
	 * This method ensures proper Spring context initialization for test dependency injection.
	 * 
	 * @throws Exception if context preparation fails
	 */
	@BeforeMethod
	@Override
	public void springTestContextPrepareTestInstance() throws Exception {
		super.springTestContextPrepareTestInstance();
	}

	/**
	 * Cleans up test resources after each test method execution.
	 * This method ensures proper cleanup of WebDriver instances and other resources.
	 */
	@AfterMethod(alwaysRun = true)
	public void teardownDriver() {
		basePage.teardownDriver();
	}

}
