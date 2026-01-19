package com.auto.framework.actions;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import com.auto.framework.interfaces.IElementVerification;

import lombok.extern.slf4j.Slf4j;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Element verification interface implementation providing methods to retrieve page titles
 *                  and element text content. This class extends ActionsBaseClass and implements IElementVerification
 *                  interface to provide comprehensive element verification capabilities for test automation.
 * @Version : 1.1
 ************************************************************************************************************************/

/**
 * Implementation of element verification methods for web automation testing.
 * This class provides methods to retrieve page titles and element text content,
 * with comprehensive logging for debugging and test reporting purposes.
 * 
 * <p>All methods include debug logging to track verification operations during test execution.</p>
 * 
 * @author Ravi Kukreja
 * @version 1.1
 * @since 1.0
 * @see com.auto.framework.interfaces.IElementVerification
 * @see org.openqa.selenium.WebDriver
 * @see org.openqa.selenium.By
 */
@Slf4j
@Component
public class ElementVerification extends ActionsBaseClass implements IElementVerification {

    /**
     * Retrieves the title of the current web page.
     * 
     * @return The title of the current page as a String
     */
    @Override
    public String getTitle() {
        String title = driver.getTitle();
        log.debug("Opened Page : {}", title);
        return title;
    }
    
    /**
     * Retrieves the text content of a web element identified by the given locator.
     * 
     * @param by The locator strategy used to find the element
     * @return The text content of the found element as a String
     * 
     * @throws org.openqa.selenium.NoSuchElementException if the element is not found
     */
    @Override
    public String getText(By by) {
        String text = driver.findElement(by).getText();
        log.debug("Text Value: {}", text);
        return text;
    }


}
