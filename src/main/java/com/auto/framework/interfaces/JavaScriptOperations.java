package com.auto.framework.interfaces;

import org.openqa.selenium.By;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : JavaScript operations interface defining the contract for JavaScript-based interactions with
 *                  web elements. This interface provides methods for alternative interaction approaches when
 *                  standard Selenium actions are insufficient, particularly for complex or obscured elements.
 * @Version : 1.2
 ************************************************************************************************************************/

/**
 * Interface defining the contract for JavaScript-based actions in web automation.
 * This interface provides methods to interact with web elements using JavaScript
 * execution, offering alternatives to standard Selenium actions when needed.
 * 
 * <p>JavaScript actions are particularly useful for handling elements that are
 * difficult to interact with using standard Selenium methods, such as elements
 * that are obscured or have complex event handlers.</p>
 * 
 * @author Ravi Kukreja
 * @version 1.2
 * @since 1.0
 * @see org.openqa.selenium.JavascriptExecutor
 * @see org.openqa.selenium.WebDriver
 * @see org.openqa.selenium.By
 */
public interface JavaScriptOperations {

    /**
     * Scrolls the specified element into view using JavaScript.
     * This method waits for the element to be clickable before scrolling.
     * 
     * @param by The locator strategy used to find the element
     * 
     * @throws org.openqa.selenium.TimeoutException if the element is not clickable within the timeout period
     * @throws org.openqa.selenium.NoSuchElementException if the element is not found
     */
    public void scrollIntoView(By by);

    /**
     * Clicks on an element using JavaScript execution.
     * This method first scrolls the element into view, then performs a JavaScript click.
     * 
     * @param by The locator strategy used to find the element
     * 
     * @throws org.openqa.selenium.TimeoutException if the element is not clickable within the timeout period
     * @throws org.openqa.selenium.NoSuchElementException if the element is not found
     */
    public void click(By by);

}
