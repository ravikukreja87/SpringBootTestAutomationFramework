package com.auto.framework.interfaces;

import org.openqa.selenium.By;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Element validator interface defining the contract for retrieving page information
 *                  and element text content. This interface provides methods for page title verification and
 *                  element text extraction to support test assertions and validation.
 * @Version : 1.2
 ************************************************************************************************************************/

/**
 * Interface defining the contract for element verification operations in web automation.
 * This interface provides methods for retrieving page titles and element text content,
 * essential for test assertions and validation in automated testing.
 * 
 * <p>Implementations should include proper logging and error handling for debugging
 * and test reporting purposes.</p>
 * 
 * @author Ravi Kukreja
 * @version 1.2
 * @since 1.0
 * @see org.openqa.selenium.WebDriver
 * @see org.openqa.selenium.By
 */
public interface IElementValidator {
    
    /**
     * Retrieves the title of the current web page.
     * 
     * @return The title of the current page as a String
     */
    public String getTitle();

    /**
     * Retrieves the text content of a web element identified by the given locator.
     * 
     * @param by The locator strategy used to find the element
     * @return The text content of the found element as a String
     * 
     * @throws org.openqa.selenium.NoSuchElementException if the element is not found
     */
    public String getText(By by);

}
