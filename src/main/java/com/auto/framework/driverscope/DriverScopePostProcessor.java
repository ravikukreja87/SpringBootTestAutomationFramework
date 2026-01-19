package com.auto.framework.driverscope;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : BeanFactoryPostProcessor implementation for registering the custom DriverScope with Spring.
 *                  This class registers the 'driverscope' with the bean factory to enable thread-local
 *                  WebDriver instances for parallel test execution, ensuring each test thread has its own
 *                  isolated browser session.
 * @Version : 1.1
 ************************************************************************************************************************/

/**
 * BeanFactoryPostProcessor implementation for registering the custom DriverScope.
 * This class registers the 'driverscope' with the Spring bean factory to enable
 * thread-local WebDriver instances for parallel test execution.
 * 
 * <p>The post-processor runs during the bean factory initialization phase,
 * registering the custom scope before any beans are created, ensuring that
 * WebDriver beans can be properly scoped to individual threads.</p>
 * 
 * @author Ravi Kukreja
 * @version 1.1
 * @since 1.0
 * @see org.springframework.beans.factory.config.BeanFactoryPostProcessor
 * @see org.springframework.beans.factory.config.ConfigurableListableBeanFactory
 * @see com.auto.framework.driverscope.DriverScope
 */
public class DriverScopePostProcessor implements BeanFactoryPostProcessor {

    /**
     * Registers the custom 'driverscope' with the bean factory.
     * This method is called during the bean factory post-processing phase,
     * allowing the registration of custom scopes before bean creation.
     * 
     * @param beanFactory The configurable bean factory to register the scope with
     * @throws BeansException if an error occurs during scope registration
     */
    @Override
    public void postProcessBeanFactory(
            ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerScope("driverscope", new DriverScope());
    }

}
