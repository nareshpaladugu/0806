package com.shi.rtcp.business.keywords.naresh;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.locators.LocatorVO;
import com.shi.rtcp.utils.AutomationUtil;
import com.shi.rtcp.utils.RTCPException;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

public class ClickWithEvent implements KeywordInterface{

	@Override
	public TestStepExecutionResultVO execute(WebDriver webDriver,
			String... param) {
		TestStepExecutionResultVO result = new TestStepExecutionResultVO();

		String locator = param[0];

		LocatorVO locatorVO=new LocatorVO(locator);

		WebElement objectToClick=null;
		try {
			objectToClick = AutomationUtil.getElement(webDriver, locatorVO);
		} catch (Exception e) {

			result.setDefectDesc(e.getMessage());
			return result;

		}

		System.out.println("ClickWithEvent... "+locator);
		try {

			((JavascriptExecutor) webDriver).executeScript("return arguments[0].click()",objectToClick);

		}
		catch(StaleElementReferenceException ee)
		{
			try {
				objectToClick = AutomationUtil.getElement(webDriver, locatorVO);
				((JavascriptExecutor) webDriver).executeScript("return arguments[0].click()",objectToClick);
			} catch (Exception e) {
			}
		}	
		catch (Exception e) {
			e.printStackTrace();
			result.setDefectDesc(e.getMessage());
			return result;
		}

		result.setStatus(Constants.PASS);
		return result;
	}

}
