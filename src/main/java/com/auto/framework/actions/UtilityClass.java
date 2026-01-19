package com.auto.framework.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Helper class for UI action classes providing utility methods for common web interactions.
 *                  This class contains scroll helper functionality and other utility methods that support
 *                  UI automation operations throughout the framework.
 * @Version : 1.1
 ************************************************************************************************************************/

/**
 * A utility class that provides helper methods for UI interactions in web automation.
 * This class is Spring-managed and can be injected into other components.
 * 
 * @author Ravi Kukreja
 * @version 1.1
 * @since 1.0
 */
@Component
public class UtilityClass {

    /**
     * Scrolls the specified element into view to ensure it's visible on the page.
     * This method waits for the element to be clickable before scrolling.
     * 
     * @param driver The WebDriver instance used to find and interact with elements
     * @param webDriverWait The WebDriverWait instance for explicit waiting
     * @param applicationContext The Spring application context for bean injection
     * @param by The locator strategy used to find the element to scroll to
     * 
     * @throws org.openqa.selenium.TimeoutException if the element is not clickable within the wait timeout
     * @throws org.openqa.selenium.NoSuchElementException if the element cannot be found
     * 
     * @see org.openqa.selenium.JavascriptExecutor
     * @see org.openqa.selenium.support.ui.ExpectedConditions
     */
    public void scrollHelper(WebDriver driver, WebDriverWait webDriverWait, ApplicationContext applicationContext,
            By by) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        ((JavascriptExecutor) applicationContext.getBean(WebDriver.class))
                .executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
    }

}
