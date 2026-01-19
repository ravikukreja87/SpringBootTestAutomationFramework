package com.auto.framework.interfaces;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Interface for UIElement actions defining the contract for web element interactions.
 *                  This interface provides methods for basic operations like click and sendKeys, as well as
 *                  advanced operations like nested menu navigation, relative locators, and table interactions.
 * @Version : 1.1
 ************************************************************************************************************************/

/**
 * Interface defining the contract for UI element interactions in web automation.
 * This interface provides a comprehensive set of methods for interacting with web elements,
 * ranging from basic operations to advanced navigation and locator strategies.
 * 
 * <p>Implementations of this interface should provide automatic scrolling to ensure
 * elements are visible before interaction and include proper error handling.</p>
 * 
 * @author Ravi Kukreja
 * @version 1.1
 * @since 1.0
 * @see org.openqa.selenium.WebDriver
 * @see org.openqa.selenium.WebElement
 * @see org.openqa.selenium.By
 */
public interface IUIElements {

	WebDriver getWebDriver();

	void sendKeys(By by, String keysToSend);

	void click(By by);

	void openURL(String url);

	void searchAndClickByText(By by, String textForSearch);

	void clickNestedMenus(By by, String tagName, List<String> menuList);

	void searchAndClickTableByText(By by, String textForSearch, String value);

	String findElementsbyIndex(By by, int index);

	void searchAndClickRelativeLeftElement(By toLeftoOfBy, By withBy, String textForSearch);

	void clickRelativeLeftElement(By toLeftoOfBy, By withBy);

}
