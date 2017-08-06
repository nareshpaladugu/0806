package com.shi.rtcp.business.keywords;

import org.openqa.selenium.WebDriver;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.business.keywords.util.RTCPWait;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

public class WaitForSomeTime  implements KeywordInterface{

	@Override
	public TestStepExecutionResultVO execute(WebDriver webDriver, String... params) {

		TestStepExecutionResultVO  testCaseExecutionResult = new TestStepExecutionResultVO();

		try {

			RTCPWait myWait=new RTCPWait();
			myWait.waitFor(Long.parseLong(params[0]) * 1000);

		} catch (Exception e) {

			testCaseExecutionResult.setDefectDesc("'"+params[0] + "' is a Invalid wait time ");
			return testCaseExecutionResult;
		}
		
		testCaseExecutionResult.setStatus(1);
		return testCaseExecutionResult;
	}



}
