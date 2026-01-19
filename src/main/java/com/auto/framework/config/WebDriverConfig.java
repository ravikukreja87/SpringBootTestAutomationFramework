package com.auto.framework.config;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Bean configuration for WebDriver and WebDriver wait objects based on browser type and
 *                  execution environment. This configuration class supports both local Chrome driver and
 *                  Selenium Grid execution with automatic browser maximization and debugging capabilities.
 * @Version : 1.1
 ************************************************************************************************************************/

/**
 * Spring configuration class for WebDriver and WebDriverWait beans.
 * This class provides conditional bean definitions for different browser types
 * and execution environments (local vs Selenium Grid).
 * 
 * <p>Features include automatic browser maximization, remote debugging support,
 * and proper scope management for parallel test execution.</p>
 * 
 * @author Ravi Kukreja
 * @version 1.1
 * @since 1.0
 * @see org.openqa.selenium.WebDriver
 * @see org.openqa.selenium.chrome.ChromeDriver
 * @see org.openqa.selenium.remote.RemoteWebDriver
 * @see io.github.bonigarcia.wdm.WebDriverManager
 */
@Slf4j
@Configuration
public class WebDriverConfig {

    /**
     * Autowired configuration properties for browser settings.
     */
    @Autowired
    private MyProperties myProperties;

    /**
     * Creates and configures a Chrome WebDriver bean for local execution.
     * This bean is created when Selenium Grid is disabled and browser is set to Chrome.
     * 
     * @return Configured Chrome WebDriver instance with maximized window and debugging support
     */
    @Bean
    @ConditionalOnExpression("'${my.properties.grid}'.equals('false') and '${my.properties.browser}'.equals('chrome')")
    @Scope("driverscope")
    public WebDriver getChromeDriver() {
        log.info("Creating Driver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }

    /**
     * Creates a WebDriverWait bean for local Chrome driver execution.
     * This bean is created when Selenium Grid is disabled and browser is set to Chrome.
     * 
     * @return WebDriverWait instance configured with the explicit timeout from properties
     */
    @Bean
    @ConditionalOnExpression("'${my.properties.grid}'.equals('false') and '${my.properties.browser}'.equals('chrome')")
    @Scope("driverscope")
    public WebDriverWait getChromeDriverWait() {
		return new WebDriverWait(getChromeDriver(), myProperties.getExplicitTimeout());
	}

	/**
	 * Creates and configures a RemoteWebDriver bean for Selenium Grid execution.
	 * This bean is created when Selenium Grid is enabled, regardless of browser type.
	 * 
	 * @return Configured RemoteWebDriver instance with file detection and window maximization
	 * @throws MalformedURLException if the grid URL is malformed
	 */
	@Bean
	@ConditionalOnProperty(name = "my.properties.grid", havingValue = "true")
	@Scope("driverscope")
	public WebDriver getGridDriver() throws MalformedURLException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setBrowserName(myProperties.getBrowser());
		dc.setCapability("e34:token", myProperties.getGridToken());
		log.info("Creating Driver");
		WebDriver driver = new RemoteWebDriver(new URL(myProperties.getGridUrl()), dc);
		((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
		driver.manage().window().maximize();
		return driver;
	}

	/**
	 * Creates a WebDriverWait bean for Selenium Grid driver execution.
	 * This bean is created when Selenium Grid is enabled, regardless of browser type.
	 * 
	 * @return WebDriverWait instance configured with the explicit timeout from properties
	 * @throws MalformedURLException if the grid URL is malformed
	 */
	@Bean
	@ConditionalOnProperty(name = "my.properties.grid", havingValue = "true")
	@Scope("driverscope")
	public WebDriverWait getGridDriverWait() throws MalformedURLException {
		return new WebDriverWait(getGridDriver(), myProperties.getExplicitTimeout());
	}


}
