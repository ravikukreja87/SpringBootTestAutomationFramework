package com.auto.framework.interfaces;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Interface for web element interactions defining the contract for web element interactions.
 *                  This interface provides methods for basic operations like click and sendKeys, as well as
 *                  advanced operations like nested menu navigation, relative locators, and table interactions.
 * @Version : 1.2
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
 * @version 1.2
 * @since 1.0
 * @see org.openqa.selenium.WebDriver
 * @see org.openqa.selenium.WebElement
 * @see org.openqa.selenium.By
 */
public interface WebElementInteraction {

    /**
     * Returns the current WebDriver instance.
     * 
     * @return The WebDriver instance used for web automation
     */
    WebDriver getWebDriver();

    /**
     * Sends text to a web element after clearing any existing content.
     * 
     * @param by The locator strategy used to find the element
     * @param keysToSend The text to be typed into the element
     * 
     * @throws org.openqa.selenium.NoSuchElementException if the element is not found
     * @throws org.openqa.selenium.ElementNotInteractableException if the element is not interactable
     */
    void sendKeys(By by, String keysToSend);

    /**
     * Clicks on a web element identified by the given locator.
     * 
     * @param by The locator strategy used to find the element
     * 
     * @throws org.openqa.selenium.NoSuchElementException if the element is not found
     * @throws org.openqa.selenium.ElementNotInteractableException if the element is not clickable
     */
    void click(By by);

    /**
     * Navigates to the specified URL in the current browser window.
     * 
     * @param url The URL to navigate to
     */
    void openURL(String url);

    /**
     * Searches for an element containing the specified text and clicks on it.
     * This method finds the first element that contains the search text (case-insensitive).
     * 
     * @param by The locator strategy used to find multiple elements
     * @param textForSearch The text to search for within element content
     * 
     * @throws NoSuchElementException if no element containing the search text is found
     * @throws org.openqa.selenium.ElementNotInteractableException if the found element is not clickable
     */
    void searchAndClickByText(By by, String textForSearch);

    /**
     * Clicks on nested menu items in a hierarchical menu structure.
     * This method handles multi-level menu navigation by finding and clicking
     * each menu item in the provided list sequentially.
     * 
     * @param by The locator strategy for finding menu elements
     * @param tagName The HTML tag name used to find sub-menu elements
     * @param menuList List of menu item names to click in order (from parent to child)
     * 
     * @throws NoSuchElementException if any menu item in the hierarchy is not found
     * @throws org.openqa.selenium.ElementNotInteractableException if any menu item is not clickable
     */
    void clickNestedMenus(By by, String tagName, List<String> menuList);

    /**
     * Searches for text within a table and types a value in an input field to the right of the found cell.
     * This method is specifically designed for table interactions where you need to find a specific
     * cell and then interact with an adjacent input field.
     * 
     * @param by The locator strategy used to find table rows or cells
     * @param textForSearch The text to search for within table cells
     * @param value The value to type into the input field found to the right of the search text
     * 
     * @throws NoSuchElementException if no cell containing the search text is found
     * @throws org.openqa.selenium.ElementNotInteractableException if the input field is not interactable
     */
    void searchAndClickTableByText(By by, String textForSearch, String value);

    /**
     * Retrieves the text content of an element at the specified index from a list of elements.
     * 
     * @param by The locator strategy used to find multiple elements
     * @param index The zero-based index of the element in the list
     * @return The text content of the element at the specified index
     * 
     * @throws IndexOutOfBoundsException if the index is out of range for the element list
     * @throws NoSuchElementException if no elements are found with the given locator
     */
    String findElementsbyIndex(By by, int index);

    /**
     * Searches for an element containing specific text and clicks on an element to its left.
     * This method combines text search with relative locator functionality.
     * 
     * @param toLeftoFBy The locator for elements to search within for the text
     * @param withBy The locator of the element to click (relative to the found element)
     * @param textForSearch The text to search for within elements located by toLeftoFBy
     * 
     * @throws NoSuchElementException if no element containing the search text is found
     * @throws org.openqa.selenium.ElementNotInteractableException if the target element is not clickable
     */
    void searchAndClickRelativeLeftElement(By toLeftoFBy, By withBy, String textForSearch);

    /**
     * Clicks on an element that is positioned to the left of another element.
     * Uses Selenium's relative locator functionality.
     * 
     * @param toLeftoFBy The locator of the reference element (the element to the right)
     * @param withBy The locator of the element to click (the element to the left)
     * 
     * @throws NoSuchElementException if either element is not found
     * @throws org.openqa.selenium.ElementNotInteractableException if the target element is not clickable
     */
    void clickRelativeLeftElement(By toLeftoFBy, By withBy);

}
