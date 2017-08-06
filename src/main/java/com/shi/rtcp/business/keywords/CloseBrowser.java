package com.shi.rtcp.business.keywords;

import org.openqa.selenium.WebDriver;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

public class CloseBrowser implements KeywordInterface {

	@Override
	public TestStepExecutionResultVO execute(WebDriver webDriver,
			String... params) {

		TestStepExecutionResultVO testStepExecutionResultVO=new TestStepExecutionResultVO();
		try {

			webDriver.close();

		} catch (Exception e2) {

			testStepExecutionResultVO.setDefectDesc(e2.getMessage());
			return testStepExecutionResultVO;

		}

		testStepExecutionResultVO.setStatus(1);
		return testStepExecutionResultVO;
	}

}
