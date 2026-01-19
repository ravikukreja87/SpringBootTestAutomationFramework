package com.auto.framework.config;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Framework properties class for configuration management. This class maps application.properties
 *                  prefixed with 'my.properties' to provide centralized configuration access throughout
 *                  the framework including browser settings, timeouts, and Selenium Grid configuration.
 * @Version : 1.2
 ************************************************************************************************************************/

/**
 * Configuration properties class for the automation framework.
 * This class maps properties from application.properties with the prefix 'my.properties'
 * to provide centralized configuration management throughout the framework.
 * 
 * <p>Properties include browser configuration, timeout settings, Selenium Grid details,
 * and application URLs for test execution.</p>
 * 
 * @author Ravi Kukreja
 * @version 1.2
 * @since 1.0
 * @see org.springframework.boot.context.properties.ConfigurationProperties
 */
@Data
@ConfigurationProperties("my.properties")
@Component
public class FrameworkProperties {
    
    /**
     * The browser type to be used for test execution.
     * Supported values: chrome, firefox
     */
    private String browser;
    
    /**
     * The explicit wait timeout for WebDriver operations.
     * Configured as a Duration object for flexible time specification.
     */
    private Duration explicitTimeout;
    
    /**
     * The URL of the Selenium Grid hub when running tests in distributed mode.
     */
    private String gridUrl;
    
    /**
     * Authentication token for Selenium Grid access.
     */
    private String gridToken;
    
    /**
     * Username for authentication (if required by the test application).
     */
    private String username;
    
    /**
     * Password for authentication (if required by the test application).
     */
    private String password;
    
    /**
     * Flag to enable or disable Selenium Grid execution.
     * Values: true (use Grid), false (use local driver)
     */
    private String grid;
    
    /**
     * The base URL of the demo application for testing.
     */
    private String demoUrl;	
}
