package com.shi.rtcp.business.keywords.naresh;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.locators.LocatorVO;
import com.shi.rtcp.utils.AutomationUtil;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

public class EnterTextValue implements KeywordInterface{

	@Override
	public TestStepExecutionResultVO execute(WebDriver webDriver,
			String... param) {
		TestStepExecutionResultVO result = new TestStepExecutionResultVO();

		String testBox = param[0];
		String valueToenter = param[1];

		LocatorVO locatorVO=new LocatorVO(testBox);
		WebElement textBoxObj=null;
		try {
			textBoxObj = AutomationUtil.getElement(webDriver, locatorVO);
			String actualValue=textBoxObj.getText();
		} catch (Exception e) {

			result.setDefectDesc(e.getMessage());
			return result;

		}

		try {
			textBoxObj.clear();

			//textBoxObj.sendKeys(valueToenter);

			((JavascriptExecutor) webDriver).executeScript("arguments[0].value=arguments[1];", textBoxObj , valueToenter);

			System.out.println("text after enter ..."+textBoxObj.getAttribute("value"));

			Thread.sleep(500);

		} catch (Exception e) {
			result.setDefectDesc(e.getMessage());
			return result;
		}

		result.setStatus(Constants.PASS);
		return result;
	}

}
