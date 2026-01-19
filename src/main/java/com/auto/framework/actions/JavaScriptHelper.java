package com.auto.framework.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

import com.auto.framework.interfaces.JavaScriptOperations;

import lombok.extern.slf4j.Slf4j;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : JavaScript helper implementation class providing JavaScript-based interactions
 *                  with web elements. This class extends BaseAction and implements JavaScriptOperations interface
 *                  to offer alternative interaction methods when standard Selenium actions are insufficient.
 * @Version : 1.2
 ************************************************************************************************************************/

/**
 * Implementation of JavaScript-based actions for web automation testing.
 * This class provides methods to interact with web elements using JavaScript
 * execution, offering alternatives to standard Selenium actions when needed.
 * 
 * <p>JavaScript actions are particularly useful for handling elements that are
 * difficult to interact with using standard Selenium methods, such as elements
 * that are obscured or have complex event handlers.</p>
 * 
 * @author Ravi Kukreja
 * @version 1.2
 * @since 1.0
 * @see com.auto.framework.interfaces.JavaScriptOperations
 * @see org.openqa.selenium.JavascriptExecutor
 * @see org.openqa.selenium.WebDriver
 */
@Slf4j
@Component
public class JavaScriptHelper extends BaseAction implements JavaScriptOperations {

    /**
     * Scrolls the specified element into view using JavaScript.
     * This method waits for the element to be clickable before scrolling.
     * 
     * @param by The locator strategy used to find the element
     * 
     * @throws org.openqa.selenium.TimeoutException if the element is not clickable within the timeout period
     * @throws org.openqa.selenium.NoSuchElementException if the element is not found
     */
    @Override
    public void scrollIntoView(By by) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        log.debug("Test {}", driver.getTitle());
        ((JavascriptExecutor) applicationContext.getBean(WebDriver.class))
                .executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));

        log.debug("Scrolled into View..");
    }

    /**
     * Clicks on an element using JavaScript execution.
     * This method first scrolls the element into view, then performs a JavaScript click.
     * 
     * @param by The locator strategy used to find the element
     * 
     * @throws org.openqa.selenium.TimeoutException if the element is not clickable within the timeout period
     * @throws org.openqa.selenium.NoSuchElementException if the element is not found
     */
    @Override
    public void click(By by) {
        scrollIntoView(by);
        WebElement element = driver.findElement(by);
        ((JavascriptExecutor) applicationContext.getBean(WebDriver.class)).executeScript("arguments[0].click();",
                element);

        log.debug("Clicked on Link..");

    }
}
