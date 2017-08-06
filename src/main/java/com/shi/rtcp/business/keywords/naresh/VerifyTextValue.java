package com.shi.rtcp.business.keywords.naresh;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.locators.LocatorVO;
import com.shi.rtcp.utils.AutomationUtil;
import com.shi.rtcp.vos.TestStepExecutionResultVO;


public class VerifyTextValue implements KeywordInterface {

	@Override
	public TestStepExecutionResultVO execute(WebDriver webDriver,
			String... params) {
		TestStepExecutionResultVO result = new TestStepExecutionResultVO();
		String expectedValue = params[1];
		LocatorVO locatorVO=new LocatorVO(params[0]);
		WebElement textBoxObj=null;
		try {
			textBoxObj = AutomationUtil.getElement(webDriver, locatorVO);
			//String actualValue=textBoxObj.getAttribute("value");
			String actualValue=textBoxObj.getText();
			System.out.println(actualValue);
			if(actualValue.equalsIgnoreCase(expectedValue))
			{
				result.setStatus(1);
			}
			else
			{
				result.setDefectDesc("text in not matching exp : "+expectedValue+ " Act: "+actualValue);

			}
		} catch (Exception e) {

			result.setDefectDesc(e.getMessage());
			return result;

		}
		return result;
	}

}
