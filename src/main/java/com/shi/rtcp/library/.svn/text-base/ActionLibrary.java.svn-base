package com.shi.rtcp.library;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.shi.rtcp.business.keywords.util.CommonAutomationTasks;
import com.shi.rtcp.locators.LocatorVO;
import com.shi.rtcp.utils.AutomationUtil;
import com.shi.rtcp.utils.RTCPException;

public class ActionLibrary {


	public static void click(WebDriver webDriver,LocatorVO locator)
	{
		try {

			WebElement ele = AutomationUtil.getElement(webDriver, locator);

			ele.click();

			Thread.sleep(500);

			CommonAutomationTasks.waitForLoadingStage(webDriver);

		} catch (Exception e) {
		}


	}

	/**
	 * @param ele
	 */
	public static void click(WebDriver webDriver,WebElement ele)
	{
		ele.click();

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}

		CommonAutomationTasks.waitForLoadingStage(webDriver);
	}
	
	/**
	 * @param ele
	 */
	public static void clickWithoutWait(WebDriver webDriver,WebElement ele)
	{
		ele.click();

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}

	}


	/**
	 * @param element
	 * @param text
	 */
	public static void enterText(WebElement input,String text)
	{

		try {
			String getLengthfromTextBox = input.getAttribute("value").toString();
			int slength=getLengthfromTextBox.length();
			for (int o=0;o<slength;o++)
			{
				input.sendKeys(Keys.BACK_SPACE);
			}
			input.clear();
		} catch (Exception e) {
		}

		input.sendKeys(text);
	}


	/**
	 * Launch browser
	 * @param url
	 */
	public static WebDriver launchBrowser(String url,String browser) throws RTCPException
	{
		WebDriver webDriver=null;

		DesiredCapabilities capabilities = AutomationUtil.getBrowserCapabilities();

		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

			webDriver = new ChromeDriver(capabilities);
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			webDriver = new FirefoxDriver(capabilities);
		}
		else
		{
			throw new RTCPException("unsupported browser");
		}

		webDriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

		webDriver.get(url);

		return webDriver;

	}
}
