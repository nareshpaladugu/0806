package com.shi.rtcp.business.keywords.naresh;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.locators.LocatorVO;
import com.shi.rtcp.utils.AutomationUtil;
import com.shi.rtcp.vos.TestStepExecutionResultVO;


public class VerifyAttributeValue implements KeywordInterface {

	@Override
	public TestStepExecutionResultVO execute(WebDriver webDriver,
			String... params) {


		TestStepExecutionResultVO result = new TestStepExecutionResultVO();
		LocatorVO locatorVO=new LocatorVO(params[0]);
		WebElement genericObject=null;
		try {
			genericObject = AutomationUtil.getElement(webDriver, locatorVO);
			String actualValue=genericObject.getAttribute(params[1]);
			System.out.println(actualValue);
			if(actualValue.toLowerCase().contains(params[2].toLowerCase()))
			{
				result.setStatus(1);
			}
			else
			{
				result.setDefectDesc("text in not matching exp : "+params[2]+ " Act: "+actualValue);

			}
		} catch (Exception e) {

			result.setDefectDesc(e.getMessage());
			return result;

		}
		return result;
	
	}

}
