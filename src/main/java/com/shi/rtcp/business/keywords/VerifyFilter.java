package com.shi.rtcp.business.keywords;

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
public class VerifyFilter implements KeywordInterface{

	@Override
	public TestStepExecutionResultVO execute(WebDriver webDriver,
			String... params) {

		CommonAutomationTasks.waitForLoadingStage(webDriver);

		
		TestStepExecutionResultVO  testCaseExecutionResult = new TestStepExecutionResultVO();

		boolean passFlag = true;

		try {

			String condition=params[2];
			String valuesToCheck=params[3];

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
			String rowNumWithError=RTCPConstants.EMPTY_STRING;

			for (int rowNum = 0; rowNum < sColumnValuesSP.length; rowNum++) {

				sActaulValue = sColumnValuesSP[rowNum];

				switch(condition)
				{

				case "equals":case "==":

					if(!valuesToCheck.equals(sActaulValue.trim()))
					{
						
						passFlag = false;
						rowNumFailed=rowNumFailed+","+"["+rowNum+"]["+sActaulValue+"]";
					}

					break;

				case "not equals":case "!":

					if(valuesToCheck.equals(sActaulValue.trim()))
					{
						
						passFlag = false;
						rowNumFailed=rowNumFailed+","+"["+rowNum+"]["+sActaulValue+"]";
					}


					break;

				case "less":case "<":

					try {
						if(! (Double.parseDouble(sActaulValue) < Double.parseDouble(valuesToCheck)))
						{
							
							passFlag = false;
							rowNumFailed=rowNumFailed+","+"["+rowNum+"]["+sActaulValue+"]";
						}
					} catch (Exception e) {

						rowNumWithError=rowNumWithError+","+"["+rowNum+"]["+sActaulValue+"]";
						System.out.println("Could not check value at row "+rowNum +" : "+sActaulValue);
					}

					break;
				case "greater":case ">":

					try {
						if(! (Double.parseDouble(sActaulValue) > Double.parseDouble(valuesToCheck)))
						{
							
							passFlag = false;
							rowNumFailed=rowNumFailed+","+"["+rowNum+"]["+sActaulValue+"]";
						}
					} catch (Exception e) {

						rowNumWithError=rowNumWithError+","+"["+rowNum+"]["+sActaulValue+"]";
						System.out.println("Could not check value at row "+rowNum +" : "+sActaulValue);
					}

					break;
				case "less or equal": case "<=":

					try {
						if(! (Double.parseDouble(sActaulValue) <= Double.parseDouble(valuesToCheck)))
						{
							
							passFlag = false;
							rowNumFailed=rowNumFailed+","+"["+rowNum+"]["+sActaulValue+"]";
						}
					} catch (Exception e) {

						rowNumWithError=rowNumWithError+","+"["+rowNum+"]["+sActaulValue+"]";
						System.out.println("Could not check value at row "+rowNum +" : "+sActaulValue);
					}

					break;
					
				case "greater or equal":case ">=":

					try {
						if(! (Double.parseDouble(sActaulValue) >= Double.parseDouble(valuesToCheck)))
						{
							
							passFlag = false;
							rowNumFailed=rowNumFailed+","+"["+rowNum+"]["+sActaulValue+"]";
						}
					} catch (Exception e) {

						rowNumWithError=rowNumWithError+","+"["+rowNum+"]["+sActaulValue+"]";
						System.out.println("Could not check value at row "+rowNum +" : "+sActaulValue);
					}

					break;
				}

			}

			rowNumFailed=rowNumFailed.replaceFirst(",","");

			// all passed - all checked row
			if(passFlag && rowNumWithError.isEmpty())
			{
				testCaseExecutionResult.setStatus(1);
				System.out.println("Verification PASSED ");
			}
			else
			{
				System.out.println("Verification FAILED ");
				String defectDesc = "Failed - [Row][Val]  : "+rowNumFailed ;
				
				if(!rowNumWithError.isEmpty())
					defectDesc=defectDesc+" Exception :  "+rowNumWithError;
				
				testCaseExecutionResult.setDefectDesc(defectDesc);
				
				return testCaseExecutionResult;
			}
		} catch (Exception e) {

			return AutomationUtil.returnExceptionResult(testCaseExecutionResult, e);
		}

		testCaseExecutionResult.setStatus(1);
		return testCaseExecutionResult;
	}
}
