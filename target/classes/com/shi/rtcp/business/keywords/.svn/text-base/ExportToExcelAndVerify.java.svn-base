package com.shi.rtcp.business.keywords;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.business.keywords.excelexport.ExcelExportDataCheckUtil;
import com.shi.rtcp.business.keywords.excelexport.MismatchDetails;
import com.shi.rtcp.business.keywords.util.RTCPWait;
import com.shi.rtcp.library.ActionLibrary;
import com.shi.rtcp.locators.Locators;
import com.shi.rtcp.utils.AutomationUtil;
import com.shi.rtcp.utils.FileDownloadUtil;
import com.shi.rtcp.utils.RTCPConstants;
import com.shi.rtcp.utils.RTCPException;
import com.shi.rtcp.utils.ResultUtil;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

public class ExportToExcelAndVerify implements KeywordInterface {

	@Override
	public TestStepExecutionResultVO execute(WebDriver webDriver,
			String... params) {


		TestStepExecutionResultVO testStepExecutionResultVO=new TestStepExecutionResultVO();

		String resultFileName = null;
		String exportedCsvFileName = params[1];

		if(params[2].isEmpty())
		{
			resultFileName = "result"+"_"+ResultUtil.getTimeStamp()+".csv";
		}
		else
		{
			resultFileName = params[2];
		}

		int mainTableFlag = 1;

		if(params[0].equals(RTCPConstants.PRICE_MAIN_DATA_TABLE_VARIABLE))
		{
			mainTableFlag=1;
		}
		else if(params[0].equals(RTCPConstants.PRICE_DATA_TABLE_VARIABLE))
		{
			mainTableFlag=0;
		}


		if(mainTableFlag==1)
		{
			ActionLibrary.click(webDriver, Locators.ExportToExcel_RealTimePriceMainDataTable);
		}
		else
		{
			ActionLibrary.click(webDriver, Locators.ExportToExcel_RealTimePriceDataTable);
		}


		if(!FileDownloadUtil.saveAsFile(exportedCsvFileName))
		{
			testStepExecutionResultVO.setDefectDesc("No Download/File Save As Window Found");
			return testStepExecutionResultVO;
		}

		new RTCPWait().waitFor(1000);

		Map<String, Integer> headers  = ExcelExportDataCheckUtil.getTableHeader(webDriver,AutomationUtil.getTableLocatorByName(params[0]+" HEADER"));

		String cols=null;
		String headerToCheck=null;

		Iterator<Entry<String, Integer>> it = headers.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Integer> pairs = it.next();

			if(cols==null)
				cols = pairs.getValue()+RTCPConstants.EMPTY_STRING;
			else
				cols = cols +"|"+pairs.getValue();


			if(headerToCheck==null)
				headerToCheck = pairs.getKey();
			else
				headerToCheck = headerToCheck +"@DC@"+pairs.getKey();
		}

		int pageCount=0;

		if(mainTableFlag==1)
		{
			pageCount  = AutomationUtil.getPageCounts(webDriver,Locators.TotalAvailablePages_MainData);
		}
		else
		{
			pageCount  = AutomationUtil.getPageCounts(webDriver,Locators.TotalAvailablePages_Data);
		}

		new RTCPWait().waitFor(1000);

		String tabledata=RTCPConstants.EMPTY_STRING;
		for (int i = 0; i < pageCount; i++) {

			tabledata = tabledata+""+ExcelExportDataCheckUtil.getTableDataByJs(webDriver, AutomationUtil.getTableIdByName(params[0]),cols);

			if(i!=pageCount)
			{
				if(mainTableFlag==1)
				{
					ActionLibrary.click(webDriver,Locators.NextPageButton_RealTimePriceMainDataTable);
				}
				else
				{
					ActionLibrary.click(webDriver,Locators.NextPageButton_RealTimePriceDataTable);
				}

			}
		}

		try 
		{


			System.out.println("exportedCsvFileName.................. "+exportedCsvFileName);

			LinkedList<LinkedList<String>> csvData = ExcelExportDataCheckUtil.readCsv(exportedCsvFileName);

			LinkedList<LinkedList<String>> tableDataParsed = ExcelExportDataCheckUtil.parseTableData(headerToCheck+tabledata,mainTableFlag);

			String sSortedColumn  = getCurrentSortedColumn(webDriver, params);
			
			System.out.println("sSortedColumn... "+sSortedColumn);

			LinkedList<MismatchDetails> mismatchDetails = ExcelExportDataCheckUtil.compareData(csvData, tableDataParsed,sSortedColumn);

			System.out.println("Done........ Found mismatches "+mismatchDetails.size());

			if(mismatchDetails.size()!=0)
			{
				testStepExecutionResultVO.setDefectDesc("Verification failed, please check file - "+resultFileName);

				ExcelExportDataCheckUtil.writeResultsToFile(resultFileName,mismatchDetails);

				return testStepExecutionResultVO;

			}


		} catch (RTCPException e) {
			e.printStackTrace();
			return AutomationUtil.returnExceptionResult(testStepExecutionResultVO, e);
		}


		testStepExecutionResultVO.setStatus(1);
		return testStepExecutionResultVO;
	}

	public String getCurrentSortedColumn(WebDriver webDriver,String... params) throws RTCPException
	{
		WebElement elementTable;
		try {
			elementTable = AutomationUtil.getElement(webDriver,AutomationUtil.getTableLocatorByName(params[0]+" HEADER"));
		} catch (RTCPException e) {

			throw e;
		}

		WebElement elementHeaderRow = elementTable.findElements(By.tagName("tr")).get(0);

		List<WebElement> ths =  elementHeaderRow.findElements(By.tagName("th"));

		for (int i = 0; i < params.length; i++) {

			WebElement header = ths.get(i);

			WebElement div = header.findElement(By.tagName("div"));

			{
				WebElement ascArrow  = div.findElement(By.tagName("span")).findElements(By.tagName("span")).get(0);

				String classAsc = ascArrow.getAttribute("class");

				if(!classAsc.contains("disabled"))
				{
					return header.getText();
				}

				WebElement descArrow  = div.findElement(By.tagName("span")).findElements(By.tagName("span")).get(1);

				String classDesc = descArrow.getAttribute("class");

				if(!classDesc.contains("disabled"))
				{
					return header.getText();
				}

			}

		}
		return "Product Id";
	}
}