package com.shi.rtcp.business.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.locators.LocatorVO;
import com.shi.rtcp.utils.AutomationUtil;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

public class SelectIframe implements KeywordInterface {

	@Override
	public TestStepExecutionResultVO execute(WebDriver webDriver, String... params) {
		TestStepExecutionResultVO result =new TestStepExecutionResultVO();
		String  framename = params[0];

		try {

			if(framename.contains("="))
			{
				//some location like id=,name=,xpath=....
				WebElement ele = AutomationUtil.getElement(webDriver, new LocatorVO(framename));
				webDriver.switchTo().frame(ele);
				
				System.out.println(webDriver.getPageSource());
				System.out.println("");
			}
			else
			{
				//user has given direct id
				webDriver.switchTo().frame(framename);
			}
		}catch (Exception ex) {
			result.setDefectDesc(ex.getMessage());
			return result;
		}
		result.setStatus(1);
		return result;
	}
}


