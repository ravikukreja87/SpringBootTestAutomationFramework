package com.auto.framework.interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Explicit wait interface defining the contract for waiting until elements are in specific
 *                  states before proceeding with test actions. This interface provides methods for handling
 *                  dynamic web pages where elements may take time to load or become interactive.
 * @Version : 1.1
 ************************************************************************************************************************/

/**
 * Interface defining the contract for explicit wait operations in web automation.
 * This interface provides methods to wait for elements to reach specific states
 * before performing actions, ensuring reliable test execution.
 * 
 * <p>Explicit waits are essential for handling dynamic web pages where elements
 * may take time to load or become interactive, improving test reliability.</p>
 * 
 * @author Ravi Kukreja
 * @version 1.1
 * @since 1.0
 * @see org.openqa.selenium.support.ui.ExpectedConditions
 * @see org.openqa.selenium.support.ui.WebDriverWait
 * @see org.openqa.selenium.WebElement
 * @see org.openqa.selenium.By
 */
public interface IExplicitWait {
    
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
    public WebElement waitForElementToBeClickable(By by);
}
