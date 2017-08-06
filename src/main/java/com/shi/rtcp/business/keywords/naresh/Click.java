package com.shi.rtcp.business.keywords.naresh;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.locators.LocatorVO;
import com.shi.rtcp.utils.AutomationUtil;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

public class Click implements KeywordInterface{

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


		System.out.println("clicking... "+locator);
		try {


			((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", objectToClick);

			objectToClick.click();
			System.out.println("");
		}
		catch(StaleElementReferenceException ee)
		{
			try {
				objectToClick = AutomationUtil.getElement(webDriver, locatorVO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			objectToClick.click();
		}
		catch (Exception e) {
			result.setDefectDesc(e.getMessage());
			return result;
		}

		result.setStatus(Constants.PASS);
		return result;
	}

}
