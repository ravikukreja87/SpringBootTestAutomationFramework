package com.auto.framework.actions;

import static com.auto.framework.constants.Constants.UIELEMENT_ERROR_TEXT;
import static java.lang.String.format;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

import com.auto.framework.interfaces.WebElementInteraction;

import lombok.extern.slf4j.Slf4j;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Web element interactor implementation class providing comprehensive methods for web element
 *                  interactions including clicking, typing, navigation, nested menu handling, relative locators,
 *                  and table interactions. This class extends BaseAction and implements WebElementInteraction interface.
 * @Version : 1.2
 ************************************************************************************************************************/

/**
 * Implementation of UI element interactions using Selenium WebDriver.
 * This class provides comprehensive methods for interacting with web elements including
 * basic operations like click and sendKeys, as well as advanced operations like nested
 * menu navigation, relative locators, and table interactions.
 * 
 * <p>All methods in this class include automatic scrolling to ensure elements are visible
 * before interaction, and comprehensive logging for debugging purposes.</p>
 * 
 * @author Ravi Kukreja
 * @version 1.2
 * @since 1.0
 * @see com.auto.framework.interfaces.WebElementInteraction
 * @see org.openqa.selenium.WebDriver
 * @see org.openqa.selenium.WebElement
 */
@Slf4j
@Component
public class WebElementInteractor extends BaseAction implements WebElementInteraction {

    /**
     * Clicks on a web element identified by the given locator.
     * Automatically scrolls to the element before clicking.
     * 
     * @param by The locator strategy used to find the element
     * 
     * @throws org.openqa.selenium.NoSuchElementException if the element is not found
     * @throws org.openqa.selenium.ElementNotInteractableException if the element is not clickable
     */
    @Override
    public void click(By by) {
        interactionHelper.scrollHelper(driver, webDriverWait, applicationContext, by);
        driver.findElement(by).click();
        log.debug("Clicked on Link..");

    }

    /**
     * Sends text to a web element after clearing any existing content.
     * Automatically scrolls to the element before typing.
     * 
     * @param by The locator strategy used to find the element
     * @param keysToSend The text to be typed into the element
     * 
     * @throws org.openqa.selenium.NoSuchElementException if the element is not found
     * @throws org.openqa.selenium.ElementNotInteractableException if the element is not interactable
     */
    @Override
    public void sendKeys(By by, String keysToSend) {
        interactionHelper.scrollHelper(driver, webDriverWait, applicationContext, by);
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(keysToSend);
        log.debug("Typed in text : {}", keysToSend);

    }

    /**
     * Navigates to the specified URL in the current browser window.
     * 
     * @param url The URL to navigate to
     */
    @Override
    public void openURL(String url) {
        log.info("Loading {}", url);
        driver.get(url);

    }

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
    @Override
    public void clickNestedMenus(By by, String tagName, List<String> menuList) {

        interactionHelper.scrollHelper(driver, webDriverWait, applicationContext, by);
        WebElement headerWebElement = driver.findElements(by).stream()
                .filter(element -> element.getText().toLowerCase().contains(menuList.get(0).toLowerCase())).findFirst()
                .orElseThrow(() -> new NoSuchElementException(format(UIELEMENT_ERROR_TEXT, by, menuList.get(0))));
        headerWebElement.click();
        log.debug("Found and clicked on Header Element with Text : {}", menuList.get(0));

        for (int i = 1; i < menuList.size(); i++) {
            String subMenuName = menuList.get(i);
            headerWebElement = headerWebElement.findElements(By.tagName(tagName)).stream()
                    .filter(element -> element.getText().toLowerCase().contains(subMenuName.toLowerCase())).findFirst()
                    .orElseThrow(() -> new NoSuchElementException(format(UIELEMENT_ERROR_TEXT, by, subMenuName)));
            headerWebElement.click();
            log.debug("Found and clicked on Sub Element with Text : {}", subMenuName);
        }
    }

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
    @Override
    public String findElementsbyIndex(By by, int index) {
        interactionHelper.scrollHelper(driver, webDriverWait, applicationContext, by);
        return driver.findElements(by).get(index).getText();
    }

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
    @Override
    public void searchAndClickByText(By by, String textForSearch) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElements(by).stream()
                .filter(element -> element.getText().toLowerCase().contains(textForSearch.toLowerCase())).findFirst()
                .orElseThrow(() -> new NoSuchElementException(format(UIELEMENT_ERROR_TEXT, by, textForSearch))).click();

        log.debug("Found and clicked on Element with Text : {}", textForSearch);

    }

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
    @Override
    public void clickRelativeLeftElement(By toLeftoFBy, By withBy) {
        interactionHelper.scrollHelper(driver, webDriverWait, applicationContext, withBy);
        driver.findElement(RelativeLocator.with(withBy).toLeftOf(toLeftoFBy)).click();
        log.debug("Clicked on Link..");

    }

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
    @Override
    public void searchAndClickRelativeLeftElement(By toLeftoFBy, By withBy, String textForSearch) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(toLeftoFBy));
        driver.findElement(RelativeLocator.with(withBy).toLeftOf(driver.findElements(toLeftoFBy).stream()
                .filter(element -> element.getText().toLowerCase().contains(textForSearch.toLowerCase())).findFirst()
                .orElseThrow(
                        () -> new NoSuchElementException(format(UIELEMENT_ERROR_TEXT, toLeftoFBy, textForSearch)))))
                .click();

    }

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
    @Override
    public void searchAndClickTableByText(By by, String textForSearch, String value) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        WebElement webElement = driver.findElements(by).stream()
                .flatMap(row -> row.findElements(By.tagName("td")).stream())
                .filter(column -> column.getText().toLowerCase().contains(textForSearch.toLowerCase())).findFirst()
                .orElseThrow(() -> new NoSuchElementException(format(UIELEMENT_ERROR_TEXT, by, textForSearch)));
        driver.findElement(RelativeLocator.with(By.tagName("input")).toRightOf(webElement)).sendKeys(value);

    }

    /**
     * Returns the current WebDriver instance.
     * 
     * @return The WebDriver instance used by this class
     */
    @Override
    public WebDriver getWebDriver() {
        return driver;
    }
}
