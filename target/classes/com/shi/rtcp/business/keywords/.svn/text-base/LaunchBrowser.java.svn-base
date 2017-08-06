package com.shi.rtcp.business.keywords;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

public class LaunchBrowser implements KeywordInterface{

	WebDriver webDriver;

	@Override
	public TestStepExecutionResultVO execute(WebDriver wd, String... params) {

		TestStepExecutionResultVO testStepExecutionResultVO = new TestStepExecutionResultVO();

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();

		if(params[1].equalsIgnoreCase("chrome") || params[1].trim().isEmpty())
		{
			ChromeOptions options = new ChromeOptions();

			options.addArguments("user-data-dir=C:/Users/"+System.getProperty("user.name")+"/AppData/Local/Google/Chrome/D");
			options.addArguments("--start-maximized");
			options.addArguments("test-type");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

			webDriver = new ChromeDriver(capabilities);

		}
		else if(params[1].equalsIgnoreCase("firefox"))
		{

			webDriver = new FirefoxDriver(capabilities);
		}
		else
		{
			testStepExecutionResultVO.setDefectDesc("Unknown browser type");
			return testStepExecutionResultVO;
		}

		webDriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		webDriver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);

		System.out.println("hit url..");

		webDriver.get(params[0]);


		testStepExecutionResultVO.setStatus(1);
		return testStepExecutionResultVO;
	}

	public WebDriver getWebDriverInstance() {

		return this.webDriver;
	}

}
