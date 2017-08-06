package com.shi.rtcp.business.keywords;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

public class LaunchBrowser implements KeywordInterface{

	public static String mainWindowHandle="";
	WebDriver webDriver;

	@Override
	public TestStepExecutionResultVO execute(WebDriver wd, String... params) {

		
		TestStepExecutionResultVO testStepExecutionResultVO = new TestStepExecutionResultVO();

		DesiredCapabilities capabilities = null;

		if(params[1].equalsIgnoreCase("chrome") || params[1].trim().isEmpty())
		{
			try {
				Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			capabilities = DesiredCapabilities.chrome();
			
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
			capabilities = DesiredCapabilities.firefox();
			
			ProfilesIni profile = new ProfilesIni();

			FirefoxProfile myprofile = profile.getProfile("C:\\Users\\vinusha\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\rwrqdehl.driverTest");
			
			webDriver = new FirefoxDriver(myprofile);
		}
		
		
		else if(params[1].equalsIgnoreCase("ie"))
		{
			try {
				Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
				Thread.sleep(2000);
				capabilities = null;
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability("nativeEvents", false);
				capabilities.setCapability("ignoreZoomSetting", true);			
				capabilities.setCapability("enablePersistentHover", true);
				capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
				capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);	
				capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				capabilities.setCapability("requireWindowFocus", true);	
				System.setProperty("webdriver.ie.driver", "C:\\Users\\vinusha\\Downloads\\IEDriverServer_x64_2.39.0\\IEDriverServer.exe");
				webDriver=new InternetExplorerDriver(capabilities);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else
		{
			testStepExecutionResultVO.setDefectDesc("Unknown browser type");
			return testStepExecutionResultVO;
		}

		webDriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		webDriver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
		webDriver.manage().deleteAllCookies();
		webDriver.manage().window().maximize();

		System.out.println("hit url..");

		webDriver.get(params[0]);

		mainWindowHandle=webDriver.getWindowHandle();

		System.out.println("title.."+webDriver.getTitle());
		
		System.out.println("mainWindowHandle.."+mainWindowHandle);

		testStepExecutionResultVO.setStatus(1);
		return testStepExecutionResultVO;
	}

	public WebDriver getWebDriverInstance() {

		return this.webDriver;
	}

}
