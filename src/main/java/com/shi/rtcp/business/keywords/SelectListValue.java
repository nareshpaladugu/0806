package com.shi.rtcp.business.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.business.keywords.util.CommonAutomationTasks;
import com.shi.rtcp.business.keywords.util.RTCPWait;
import com.shi.rtcp.locators.LocatorVO;
import com.shi.rtcp.utils.AutomationUtil;
import com.shi.rtcp.utils.RTCPException;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

public class SelectListValue implements KeywordInterface {

	@Override
	public TestStepExecutionResultVO execute(WebDriver webDriver,
			String... params) {

		TestStepExecutionResultVO testStepExecutionResultVO=new TestStepExecutionResultVO();

		if(params[0].isEmpty())
		{
			testStepExecutionResultVO.setDefectDesc("Object locator not given");

			return testStepExecutionResultVO;
		}
		LocatorVO locatorVO=new LocatorVO(params[0]);

		WebElement dropDownListBox=null;
		try {
			dropDownListBox = AutomationUtil.getElement(webDriver, locatorVO);
		} catch (RTCPException e) {

			testStepExecutionResultVO.setDefectDesc(e.getMessage());
			return testStepExecutionResultVO;

		}

		Select selectDropDown = new Select(dropDownListBox);

		try
		{
			selectDropDown.selectByVisibleText(params[1]);
			testStepExecutionResultVO.setStatus(1);
		}
		catch(Exception e)
		{ 
			try {
				selectDropDown.selectByValue(params[1]);
				testStepExecutionResultVO.setStatus(1);

			} catch (Exception e1) {

				testStepExecutionResultVO.setDefectDesc("Unable to select required value from the list box. ");
				return testStepExecutionResultVO;
			}

			RTCPWait wait=new RTCPWait();
			wait.waitFor(2000);

			CommonAutomationTasks.waitForLoadingStage(webDriver);
		}
		return testStepExecutionResultVO;
	}

}
