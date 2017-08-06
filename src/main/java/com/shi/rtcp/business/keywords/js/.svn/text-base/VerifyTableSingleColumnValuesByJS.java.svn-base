package com.shi.rtcp.business.keywords.js;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.business.keywords.util.CommonAutomationTasks;
import com.shi.rtcp.utils.AutomationUtil;
import com.shi.rtcp.utils.RTCPConstants;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

/**
 * @author ddaphal
 *
 */
public class VerifyTableSingleColumnValuesByJS implements KeywordInterface {

	@Override
	public TestStepExecutionResultVO execute(WebDriver webDriver,
			String... params) {

		TestStepExecutionResultVO  testCaseExecutionResult = new TestStepExecutionResultVO();

		boolean passFlag = true;

		try {

			String valuesToCheckSp[]=params[2].split("\\|");

			List<String> valuesToCheckList = new ArrayList<String>();

			for (String string : valuesToCheckSp) {

				valuesToCheckList.add(string);
			}

			int index = CommonAutomationTasks.getColumnNumber(webDriver,AutomationUtil.getTableLocatorByName(params[0]+" HEADER"), params[1]);

			WebElement dataTable = AutomationUtil.getElement(webDriver, AutomationUtil.getTableLocatorByName(params[0]));


			String sCheckJs=

					"		VerifyRowData();																							" +
							"		function VerifyRowData()																			" +
							"		{																									" +
							"  			var rtbl=document.getElementById('"+AutomationUtil.getTableIdByName(params[0])+"');				" +
							"			var ReqCol="+index+";																			" +
							"      		var rwsCount=rtbl.rows.length;																	" +
							"			var valueToReturn=\"\";																			" +
							"			var counter=0;																					" +
							" 			for(i=1;i<rwsCount;i++)																			" +
							"				{																							" +
							"					if(i==1)																				" +
							"						valueToReturn = rtbl.rows[i].cells[ReqCol].innerText;								" +
							"					else																					" +
							"						valueToReturn = valueToReturn+'@D@'+rtbl.rows[i].cells[ReqCol].innerText;			" +
							"				}" +
							"				return valueToReturn;" +
							"		}	";


			String sBrowserType=RTCPConstants.EMPTY_STRING;
			sBrowserType = ((JavascriptExecutor)webDriver).executeScript("return navigator.userAgent").toString();

			String sColumnValues=RTCPConstants.EMPTY_STRING;

			if(!sBrowserType.contains("MSIE")) 
			{
				sCheckJs=sCheckJs.replaceAll("innerText", "textContent");
			}

			Object ret = ((JavascriptExecutor)webDriver).executeScript("return "+sCheckJs,dataTable,index);

			sColumnValues =ret.toString();

			String sColumnValuesSP[]=sColumnValues.split("@D@");

			String sActaulValue=RTCPConstants.EMPTY_STRING;

			String rowNumFailed=RTCPConstants.EMPTY_STRING;
			
			for (int rowNum = 0; rowNum < sColumnValuesSP.length; rowNum++) {

				sActaulValue = sColumnValuesSP[rowNum];
				
				if(!valuesToCheckList.contains(sActaulValue.trim()))
				{
					passFlag = false;
					rowNumFailed=rowNumFailed+","+rowNum;
				}
			}

			rowNumFailed=rowNumFailed.replaceFirst(",",RTCPConstants.EMPTY_STRING);

			if(passFlag)
			{
				testCaseExecutionResult.setStatus(1);
				System.out.println("Verification PASSED ");
			}
			else
			{
				testCaseExecutionResult.setDefectDesc("Expected value(s) not found @ rows : "+rowNumFailed);
			}
		} catch (Exception e) {

			return AutomationUtil.returnExceptionResult(testCaseExecutionResult, e);
		}
		
		testCaseExecutionResult.setStatus(1);
		return testCaseExecutionResult;
	}
}
