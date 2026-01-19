package com.auto.framework.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.auto.framework.config.MyProperties;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Base class for all action classes in the framework. This class provides common dependencies
 *                  and shared functionality for UI automation actions including WebDriver, WebDriverWait,
 *                  ApplicationContext, configuration properties, and utility methods.
 * @Version : 1.1
 ************************************************************************************************************************/

/**
 * Base class for all action classes in the automation framework.
 * This class provides common dependencies and shared functionality that are
 * injected across all action implementations to promote code reuse and consistency.
 * 
 * <p>This class uses lazy initialization for WebDriver and WebDriverWait to ensure
 * the browser is started only when actually needed during test execution.</p>
 * 
 * @author Ravi Kukreja
 * @version 1.1
 * @since 1.0
 */
@Component
public class ActionsBaseClass {

    /**
     * The WebDriver instance used for browser automation.
     * Initialized lazily to start browser only when script execution begins.
     */
    @Autowired
    @Lazy // to start browser only on script execution
    public WebDriver driver;

    /**
     * The WebDriverWait instance for explicit waits in test automation.
     * Initialized lazily along with the WebDriver instance.
     */
    @Autowired
    @Lazy
    public WebDriverWait webDriverWait;

    /**
     * The Spring application context for bean injection and dependency management.
     * Provides access to all Spring-managed beans in the application.
     */
    @Autowired
    public ApplicationContext applicationContext;

    /**
     * Configuration properties for the automation framework.
     * Contains settings such as browser type, timeouts, URLs, and grid configuration.
     */
    @Autowired
    public MyProperties myProperties;
    
    /**
     * Utility class instance providing helper methods for common UI interactions.
     * Includes scroll helpers and other utility functions for web automation.
     */
    @Autowired
    public UtilityClass utilityClass;

}
