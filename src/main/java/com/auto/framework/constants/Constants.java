package com.auto.framework.constants;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Final class to maintain constants used throughout the automation framework. This class contains
 *                  error message templates and page path constants for consistent usage across all test classes.
 * @Version : 1.1
 ************************************************************************************************************************/

/**
 * Constants class containing framework-wide constant values.
 * This class provides centralized management of all constant values used
 * throughout the automation framework, including error messages and page paths.
 * 
 * <p>This class is designed to be non-instantiable to ensure constants are accessed
 * in a static manner, maintaining consistency across the framework.</p>
 * 
 * @author Ravi Kukreja
 * @version 1.1
 * @since 1.0
 */
public final class Constants {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * 
     * @throws IllegalStateException always, to prevent instantiation
     */
    private Constants() {
        throw new IllegalStateException("Constants Class");
    }

    /**
     * Error message template used when an element cannot be found.
     * Format: "No element with locator [%s] containing text [%s] found"
     * 
     * @param locator The locator strategy used
     * @param text The text that was searched for
     */
    public static final String UIELEMENT_ERROR_TEXT = "No element with locator [%s] containing text [%s] found";

    /**
     * Path segment for the Elements page in the demoqa application.
     */
    public static final String ELEMENTS_PAGE = "elements";
    
    /**
     * Path segment for the Text Box page in the demoqa application.
     */
    public static final String TEXTBOX_PAGE = "text-box";
    
    /**
     * Path segment for the Check Box page in the demoqa application.
     */
    public static final String CHECKBOX_PAGE = "checkbox";
    
    /**
     * Path segment for the Radio Button page in the demoqa application.
     */
    public static final String RADIOBUTTON_PAGE = "radio-button";
    
    /**
     * Path segment for the Web Tables page in the demoqa application.
     */
    public static final String WEBTABLES_PAGE = "webtables";

}
