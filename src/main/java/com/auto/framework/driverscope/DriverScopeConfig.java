package com.auto.framework.driverscope;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Spring configuration class for creating a BeanFactoryPostProcessor to register the custom
 *                  DriverScope. This configuration enables the custom 'driverscope' for thread-local WebDriver
 *                  instances, supporting parallel test execution with isolated browser sessions per thread.
 * @Version : 1.1
 ************************************************************************************************************************/

/**
 * Spring configuration class for registering the custom DriverScope.
 * This class creates a BeanFactoryPostProcessor to register the custom 'driverscope'
 * with the Spring application context, enabling thread-local WebDriver instances.
 * 
 * <p>This configuration is essential for parallel test execution where each thread
 * needs its own isolated WebDriver instance to prevent interference between tests.</p>
 * 
 * @author Ravi Kukreja
 * @version 1.1
 * @since 1.0
 * @see org.springframework.beans.factory.config.BeanFactoryPostProcessor
 * @see org.springframework.context.annotation.Configuration
 * @see com.auto.framework.driverscope.DriverScope
 * @see com.auto.framework.driverscope.DriverScopePostProcessor
 */
@Configuration
public class DriverScopeConfig {

    /**
     * Creates and returns a BeanFactoryPostProcessor for registering the DriverScope.
     * This static bean is used to register the custom 'driverscope' with the Spring
     * application context during the bean factory post-processing phase.
     * 
     * @return BeanFactoryPostProcessor instance that registers the DriverScope
     */
    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return new DriverScopePostProcessor();
    }

}
