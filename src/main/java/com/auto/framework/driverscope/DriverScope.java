package com.auto.framework.driverscope;

import org.springframework.context.support.SimpleThreadScope;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Configuration class to register driver as simple thread scope for parallel test execution.
 *                  This class extends SimpleThreadScope to provide thread-local WebDriver instances,
 *                  enabling safe parallel test execution with isolated browser sessions per thread.
 * @Version : 1.1
 ************************************************************************************************************************/

/**
 * Custom scope implementation for WebDriver instances in parallel test execution.
 * This class extends SimpleThreadScope to provide thread-local WebDriver instances,
 * ensuring that each test thread gets its own isolated browser session.
 * 
 * <p>This scope is essential for parallel test execution where multiple threads
 * need to run tests simultaneously without interfering with each other's browser sessions.</p>
 * 
 * @author Ravi Kukreja
 * @version 1.1
 * @since 1.0
 * @see org.springframework.context.support.SimpleThreadScope
 * @see org.openqa.selenium.WebDriver
 */
public class DriverScope extends SimpleThreadScope {
}
