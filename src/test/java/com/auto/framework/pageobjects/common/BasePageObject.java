package com.auto.framework.pageobjects.common;

import static java.util.Objects.nonNull;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.auto.framework.actions.BaseAction;
import com.auto.framework.interfaces.IElementValidator;
import com.auto.framework.interfaces.WaitOperations;
import com.auto.framework.interfaces.JavaScriptOperations;
import com.auto.framework.interfaces.WebElementInteraction;

import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Base page object for all POM page classes providing common functionality and dependency injection.
 *                  This class extends BaseAction and serves as the foundation for all page object
 *                  classes, providing access to UI elements, verification, wait operations, and JavaScript
 *                  actions. Includes screenshot capture functionality for test reporting and debugging.
 * @Version : 1.2
 ************************************************************************************************************************/

/**
 * Base page object class for all Page Object Model (POM) page classes.
 * This class provides common functionality and dependency injection for all page objects,
 * serving as the foundation for consistent page object implementation across the framework.
 * 
 * <p>This class extends BaseAction and provides access to all UI interaction interfaces,
 * verification methods, wait operations, and JavaScript actions. It also includes screenshot
 * capture functionality for test reporting and debugging purposes.</p>
 * 
 * @author Ravi Kukreja
 * @version 1.2
 * @since 1.0
 * @see com.auto.framework.actions.BaseAction
 * @see com.auto.framework.interfaces.WebElementInteraction
 * @see com.auto.framework.interfaces.ElementValidator
 * @see com.auto.framework.interfaces.WaitOperations
 * @see com.auto.framework.interfaces.JavaScriptOperations
 */
@Slf4j
@Component("basePageObject")
public class BasePageObject extends BaseAction {

    /**
     * Autowired interface for UI element interactions.
     * Provides methods for clicking, typing, navigation, and advanced element operations.
     */
    @Autowired
    public WebElementInteraction webElementInteraction;

    /**
     * Autowired interface for element verification operations.
     * Provides methods for retrieving page titles and element text content.
     */
    @Autowired
    public IElementValidator elementValidator;

    /**
     * Autowired interface for explicit wait operations.
     * Provides methods for waiting for elements to reach specific states.
     */
    @Autowired
    public WaitOperations waitOperations;

    /**
     * Autowired interface for JavaScript-based actions.
     * Provides alternative interaction methods when standard Selenium actions are insufficient.
     */
    @Autowired
	public JavaScriptOperations javaScriptOperations;

    /**
     * Tears down the WebDriver instance after test execution.
     * Captures a screenshot for test reporting and closes the browser.
     */
    public void teardownDriver() {
        log.info("Taking Screenshots");
        attachScreenShot();
        log.info("Closing Browsers");
        if (nonNull(driver)) {
            driver.close();
        }
    }

	/**
	 * Captures a screenshot of the current browser state and attaches it to test reports.
	 * This method is annotated with @Attachment to automatically include screenshots in test reports.
	 * 
	 * @return Byte array representing the screenshot image, or empty array if capture fails
	 */
	@Attachment(value = "Screen shot", type = "image/png", fileExtension = ".png")
	public byte[] attachScreenShot() {
		try {
			return ((TakesScreenshot) applicationContext.getBean(WebDriver.class)).getScreenshotAs(OutputType.BYTES);
		} catch (WebDriverException e) {
			log.error("Selenium screenshot capture failed: {}", e.getMessage());
		}
		return new byte[0];
	}

}
