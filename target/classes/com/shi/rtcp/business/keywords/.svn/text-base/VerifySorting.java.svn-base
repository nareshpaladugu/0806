package com.shi.rtcp.business.keywords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.business.keywords.util.CommonAutomationTasks;
import com.shi.rtcp.utils.AutomationUtil;
import com.shi.rtcp.utils.RTCPConstants;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

public class VerifySorting implements KeywordInterface {

	@Override
	public TestStepExecutionResultVO execute(WebDriver webDriver,
			String... params) {


		TestStepExecutionResultVO  testCaseExecutionResult = new TestStepExecutionResultVO();

		try {

			CommonAutomationTasks.waitForLoadingStage(webDriver);

			//Should be Y or Yes if column contains numeric values
			String amountOrPRiceFlag= params[3];

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

			boolean failFlag = false;

			String columnValuesFromUI_String[]=sColumnValues.split("@D@");

			String columnValuesFromUICopy_String[]=columnValuesFromUI_String.clone();

			if(amountOrPRiceFlag.equalsIgnoreCase("Yes")||amountOrPRiceFlag.equalsIgnoreCase("Y"))
			{
				/*------------------ handle numeric sorting -------------------*/

				List<Double> columnValuesFromUI_Double=new ArrayList<Double>();

				List<Double> columnValuesFromUICopy_Double=new ArrayList<Double>();

				for (String string : columnValuesFromUICopy_String) {

					//test non blank values ONLY
					if(!string.isEmpty())
					{
						columnValuesFromUI_Double.add(Double.parseDouble(string));
					}
				}


				for (String string : columnValuesFromUICopy_String) {

					if(!string.isEmpty())
					{
						columnValuesFromUICopy_Double.add(Double.parseDouble(string));
					}
				}

				Double[] columnValuesFromUI_Double_Array = new Double[columnValuesFromUI_Double.size()];

				int ind=0;
				for (Double double1 : columnValuesFromUI_Double) {

					columnValuesFromUI_Double_Array[ind++]=double1;
				}

				Double[] columnValuesFromUICopy_Double_Array = new Double[columnValuesFromUICopy_Double.size()];

				ind=0;
				for (Double double1 : columnValuesFromUI_Double) {

					columnValuesFromUICopy_Double_Array[ind++]=double1;
				}

				if(params[2].equalsIgnoreCase("asc"))
				{
					Arrays.sort(columnValuesFromUICopy_Double_Array);
				}
				else
				{
					Arrays.sort(columnValuesFromUICopy_Double_Array,Collections.reverseOrder());
				}

				for (int i = 0; i < columnValuesFromUICopy_Double_Array.length; i++) {

					if(!columnValuesFromUI_Double_Array[i].equals(columnValuesFromUICopy_Double_Array[i]))
					{
						failFlag = true;
						break;
					}
				}
			}
			else
			{
				if(params[2].equalsIgnoreCase("asc"))
				{
					Arrays.sort(columnValuesFromUICopy_String);
				}
				else
				{
					Arrays.sort(columnValuesFromUICopy_String,Collections.reverseOrder());
				}

				for (int i = 0; i < columnValuesFromUI_String.length; i++) {

					if(!columnValuesFromUI_String[i].equals(columnValuesFromUICopy_String[i]))
					{
						failFlag = true;
						break;
					}

				}

				/*System.out.println("sColumnValuesSPCopy... "+Arrays.toString(columnValuesFromUICopy_String));
				System.out.println("sColumnValuesSP... "+Arrays.toString(columnValuesFromUI_String));*/
			}

			if(failFlag)
			{
				testCaseExecutionResult.setDefectDesc( params[1] +" column is not sorted in order  "+params[2]);
				System.out.println("Not sorted !!!");
			}
			else
			{
				testCaseExecutionResult.setStatus(1);
				System.out.println("Sorted !");
			}

		} catch (Exception e) {

			e.printStackTrace();
			return AutomationUtil.returnExceptionResult(testCaseExecutionResult, e);
		}

		return testCaseExecutionResult;
	}
}
