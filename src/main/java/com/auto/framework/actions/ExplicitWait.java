package com.auto.framework.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

import com.auto.framework.interfaces.IExplicitWait;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Explicit wait implementation class providing methods for waiting until elements are in
 *                  specific states before proceeding with test actions. This class extends ActionsBaseClass and
 *                  implements IExplicitWait interface to provide robust wait mechanisms for test automation.
 * @Version : 1.1
 ************************************************************************************************************************/

/**
 * Implementation of explicit wait methods for web automation testing.
 * This class provides methods to wait for elements to reach specific states
 * before performing actions, ensuring reliable test execution.
 * 
 * <p>Explicit waits are used to handle dynamic web pages where elements may
 * take time to load or become interactive.</p>
 * 
 * @author Ravi Kukreja
 * @version 1.1
 * @since 1.0
 * @see com.auto.framework.interfaces.IExplicitWait
 * @see org.openqa.selenium.support.ui.ExpectedConditions
 * @see org.openqa.selenium.support.ui.WebDriverWait
 */
@Component
public class ExplicitWait extends ActionsBaseClass implements IExplicitWait {

    /**
     * Waits for an element to be clickable before proceeding.
     * This method polls the DOM until the element is both visible and enabled.
     * 
     * @param by The locator strategy used to find the element
     * @return The WebElement that is clickable
     * 
     * @throws org.openqa.selenium.TimeoutException if the element is not clickable within the timeout period
     * @throws org.openqa.selenium.NoSuchElementException if the element is not found
     */
    @Override
    public WebElement waitForElementToBeClickable(By by) {
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
    }

}
