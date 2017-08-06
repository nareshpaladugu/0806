package com.shi.rtcp.business.keywords.naresh;

import org.openqa.selenium.WebDriver;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

public class VerifyTitle implements KeywordInterface{

	@Override
	public TestStepExecutionResultVO execute(WebDriver webDriver,
			String... params) {

		TestStepExecutionResultVO result = new TestStepExecutionResultVO();

		String sActualTitle = webDriver.getTitle();

		String sExepctedTitle = params[0];

		if(sActualTitle.equalsIgnoreCase(sExepctedTitle))
		{
			result.setStatus(1);
		}
		else
		{
			result.setDefectDesc("title in not matching exp : "+sExepctedTitle+ " Act: "+sActualTitle);

		}
		return result;
	}

}
