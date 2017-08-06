package com.shi.rtcp.business.keywords;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.business.keywords.util.CommonAutomationTasks;
import com.shi.rtcp.utils.AutomationUtil;
import com.shi.rtcp.utils.RTCPConstants;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

public class SelectTableRowByJS implements KeywordInterface
{

	@Override
	public TestStepExecutionResultVO execute(WebDriver webDriver,
			String... params) {

		TestStepExecutionResultVO  testStepExecutionResultVO = new TestStepExecutionResultVO();

		String retVal =null;

		try {

			List<Integer> indices = CommonAutomationTasks.getColumnNumbers(webDriver,AutomationUtil.getTableLocatorByName(params[0]+" HEADER"), params[1]);

			String cols=null;

			for (Integer integer : indices) {
				if(cols==null)
					cols= integer+RTCPConstants.EMPTY_STRING;
				else
					cols= cols+integer+RTCPConstants.EMPTY_STRING;
			}

			System.out.println("Cols... "+cols);

			String tableId =AutomationUtil.getTableIdByName(params[0]); 

			System.out.println("tableId... "+tableId);

			String sSelectRowJs=

					"		VerifyRowData();																							" +
							"		function VerifyRowData()																			" +
							"		{																									" +
							"  			var rtbl=document.getElementById('"+tableId+"');												" +
							"			var ColCount=rtbl.rows[1].cells.length;															" +
							"			var Columns = \""+ cols+ "\";																	" +
							"			var rowData = \""+ params[2]+ "\";																" +
							"      		var SplitedColumns=Columns.split(\"|\");														" +
							" 			var SplitedColumnsLen=SplitedColumns.length;													" +
							"      		var rwsCount=rtbl.rows.length;																	" +
							"			var row ='';																					" +
							"			var passFlag=0;																					" +
							"			var counter=0;																					" +
							" 			for(i=1;i<rwsCount;i++)																			" +
							"				{																							" +
							"					var curRow = rtbl.rows[i]; 																" +
							"					row =''; 																				" +
							"					for(j=0;j<SplitedColumnsLen;j++)														" +
							"						{																					" +
							"							if(j==0)																		" +
							"								row = curRow.cells[parseInt(SplitedColumns[j])].innerText;					" +
							"							else 																			" +
							"								row = row+'|'+curRow.cells[parseInt(SplitedColumns[j])].innerText;			" +
							"						}																					" +
							"					if(row.trim().match(rowData))															" +
							"					{																						" +
							"						passFlag=1;																			" +
							"						rtbl.rows[i].cells[1].click();														" +
							"					}																						" +
							"				}																							" +
							"				if(passFlag==1)																				" +
							"					return \"PASS\" ;																		" +
							"				else 																						" +
							"					return \"FAIL\";																		" +
							"		}																									";



			String sBrowserType=RTCPConstants.EMPTY_STRING;

			try {
				sBrowserType = ((JavascriptExecutor)webDriver).executeScript("return navigator.userAgent").toString();

				if(!sBrowserType.contains("MSIE")) 
				{
					sSelectRowJs=sSelectRowJs.replaceAll("innerText", "textContent");
				}

				retVal = ((JavascriptExecutor)webDriver).executeScript("return "+sSelectRowJs).toString();

			} catch (Exception e) {

				retVal=RTCPConstants.FAIL_STEP_STATUS;

				e.printStackTrace();
			}


		} catch (Exception e) {

			testStepExecutionResultVO.setDefectDesc("Row not found/visible with Data "+params[2]);
		}

		System.out.println("retVal... "+retVal);

		if(retVal.equals(RTCPConstants.PASS_STEP_STATUS))
		{
			testStepExecutionResultVO.setStatus(1);
		}
		else
		{
			testStepExecutionResultVO.setDefectDesc("Row not found with Data "+params[2]);
		}

		return testStepExecutionResultVO;

	}
}
