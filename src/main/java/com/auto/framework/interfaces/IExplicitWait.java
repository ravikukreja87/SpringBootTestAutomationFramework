package com.auto.framework.interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/************************************************************************************************************************
 * @Author : Ravi Kukreja
 * @Description : Explicit wait interface
 * @Version : 1.0
 ************************************************************************************************************************/
public interface IExplicitWait {
	public WebElement waitForElementToBeClickable(By by);
}
