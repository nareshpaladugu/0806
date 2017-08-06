package com.shi.rtcp.business.keywords.excelexport;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import au.com.bytecode.opencsv.CSVReader;

import com.shi.rtcp.locators.LocatorVO;
import com.shi.rtcp.utils.AutomationUtil;
import com.shi.rtcp.utils.RTCPConstants;
import com.shi.rtcp.utils.RTCPException;


public class ExcelExportDataCheckUtil {


	public static boolean compareSingleRow(LinkedList<String> singleRowFromUITable,List<LinkedList<String>> rowsToMatch,
			LinkedList<MismatchDetails>  mismatchDetails,int iRowNumberFromTable) 
	{

		String sActaulValue=null;

		String sExpected=null;

		boolean foundFlag = true;

		if(rowsToMatch.size()==1)
		{
			// one row to one row mapping

			//System.out.println("one row to one row mapping ... "+iRowNumberFromTable);

			LinkedList<String> rowFromCSV  = rowsToMatch.get(0);

			foundFlag = true;

			for (int j = 0; j < singleRowFromUITable.size(); j++) 
			{

				sExpected = singleRowFromUITable.get(j).trim();
				sActaulValue = rowFromCSV.get(j).trim();

				sExpected =myTrim(sExpected);
				sActaulValue =myTrim(sActaulValue);

				if(NumberUtils.isNumber(sExpected))
				{
					try {
						if(Double.parseDouble(sExpected)!=Double.parseDouble(sActaulValue))
						{
							mismatchDetails.add(new MismatchDetails(iRowNumberFromTable+1+RTCPConstants.EMPTY_STRING, j+1+RTCPConstants.EMPTY_STRING, sExpected, sActaulValue));
						}
					} catch (Exception e) {

						System.out.println("Number format exception : "+sActaulValue);
					}
				}
				else
				{
					if(sActaulValue.equals("NULL"))
					{ 
						sActaulValue=RTCPConstants.EMPTY_STRING;
					}

					if(sExpected.equals("Not Available"))
					{
						sExpected = RTCPConstants.EMPTY_STRING;
					}

					sExpected=sExpected.replaceAll("\"", RTCPConstants.EMPTY_STRING);

					if(!sExpected.equalsIgnoreCase(sActaulValue))
					{
						mismatchDetails.add(new MismatchDetails(iRowNumberFromTable+1+RTCPConstants.EMPTY_STRING, j+1+RTCPConstants.EMPTY_STRING, sExpected, sActaulValue));
					}
				}
			}
		}
		else
		{
			//one row to many row mapping
			//System.out.println("one row to many row mapping ... "+iRowNumberFromTable);
			for (int i = 0; i < rowsToMatch.size(); i++) 
			{

				LinkedList<String> rowFromCSV  = rowsToMatch.get(i);

				foundFlag = true;

				for (int j = 0; j < singleRowFromUITable.size(); j++) {

					try {

						sExpected = singleRowFromUITable.get(j).trim();
						sActaulValue = rowFromCSV.get(j).trim();

						sExpected =myTrim(sExpected);
						sActaulValue =myTrim(sActaulValue);

						if(NumberUtils.isNumber(sExpected))
						{
							try {
								if(Double.parseDouble(sExpected)!=Double.parseDouble(sActaulValue))
								{
									foundFlag=false;
									break;
								}
							} catch (Exception e) {

								System.out.println("Number format exception : "+sActaulValue);
							}
						}
						else
						{
							/*---------------------replace NULL with blank ------------------*/

							if(sActaulValue.equals("NULL"))
							{ 
								sActaulValue=RTCPConstants.EMPTY_STRING;
							}

							/*--------------replace 'Not Available' with blank ----------*/
							if(sExpected.equals("Not Available"))
							{
								sExpected = RTCPConstants.EMPTY_STRING;
							}

							sExpected=sExpected.replaceAll("\"", RTCPConstants.EMPTY_STRING);

							if(!sExpected.equalsIgnoreCase(sActaulValue))
							{
								foundFlag=false;
								break;
							}
						}

					} catch (Exception e) {
						System.out.println(sExpected);
						e.printStackTrace();
					}

					if(foundFlag)
					{
						//got required row
						break;
					} 
					//else try for next row
				}

				if(foundFlag)
				{
					//System.out.println("Got required row in "+i+" attempts");
					break;
				} 
			}
		}
		return foundFlag;
	}
	/**
	 * Compare data from csv and data from ui
	 * @param csvData
	 * @param tableDataParsed
	 * @return
	 */
	public static LinkedList<MismatchDetails> compareData(LinkedList<LinkedList<String>> csvData ,	LinkedList<LinkedList<String>> tableDataParsed ,String sSortedColumn) throws RTCPException			
	{
		System.out.println("csvData... "+csvData.size());
		
		System.out.println("tableDataParsed... "+tableDataParsed.size());
		

		LinkedList<MismatchDetails>  mismatchDetails=new LinkedList<MismatchDetails>();

		int iSortedColumn = -1;
		
		LinkedList<String> csvFirstRow =  csvData.get(0);
		for (int i = 0; i < csvFirstRow.size(); i++) {
			
			if(csvFirstRow.get(i).trim().equals(sSortedColumn.trim()))
			{
				iSortedColumn = i;
				break;
			}
		}
		
		System.out.println("iSortedColumn ... "+iSortedColumn);
		
		int tableRowCount = tableDataParsed.size();
		
		String sActaulValue=null;

		String sExpected=null;
		
		for (int i = 1; i < tableRowCount; i++) {
		
			sExpected = tableDataParsed.get(i).get(iSortedColumn);
			
			try {
				sActaulValue = csvData.get(i).get(iSortedColumn);
			} catch (Exception e) {
				sActaulValue="-NA-";
			}
			
			sActaulValue = myTrim(sActaulValue);
			sExpected=myTrim(sExpected);
			
			if(!sActaulValue.equalsIgnoreCase(sExpected))
			{
				mismatchDetails.add(new MismatchDetails(i+"",iSortedColumn+"",sExpected,sActaulValue));
			}
		}
		
		Map<String, List<LinkedList<String>>> csvDataMap = convert(csvData,iSortedColumn);
		csvDataMap.keySet();

		String key ="";
		try 
		{

			for (int i = 0; i < tableDataParsed.size(); i++)
			{
				key  =  tableDataParsed.get(i).get(iSortedColumn);
				key  =  myTrim(key);

				List<LinkedList<String>> rowsToMatch = csvDataMap.get(key);

				if(rowsToMatch==null)
				{
					System.out.println("rowsToMatch not found !!" +key);
				}
				else
				{
					if(!compareSingleRow(tableDataParsed.get(i),rowsToMatch, mismatchDetails,i))
					{
						mismatchDetails.add(new MismatchDetails(i+"",tableDataParsed.get(i).toString()));
						System.out.println("Row NOT FOUND  : "+i);
					}
				}
			}

		} catch (Exception e)
		{

			e.printStackTrace();
			throw new RTCPException(e.getMessage());
		}


		return mismatchDetails;

	}


	public static Map<String, List<LinkedList<String>>> convert(LinkedList<LinkedList<String>> csvFileData,int sortedColumn)
	{
		Map<String, List<LinkedList<String>>> mapVal = new HashMap<String, List<LinkedList<String>>>();

		String sortVal = "";

		for (int i = 0; i < csvFileData.size(); i++) {

			sortVal = csvFileData.get(i).get(sortedColumn);

			List<LinkedList<String>> mapvallist = mapVal.get(sortVal);       

			if(mapvallist==null)
			{
				mapvallist = new ArrayList<LinkedList<String>>();
			}

			mapvallist.add(csvFileData.get(i));

			mapVal.put(sortVal,mapvallist);
		}		

		return mapVal;
	}

	public static String myTrim(String sVal)
	{
		if(sVal!=null && !sVal.isEmpty())
		{
			while(sVal.length()!=0 && (int)sVal.charAt(0)==160)
			{
				sVal=sVal.substring(1);
			}

			return sVal.trim();
		}
		return sVal;
	}

	public static void writeResultsToFile(String sFileName,List<MismatchDetails> mismatches)
	{
		try {

			FileWriter fstream = new FileWriter(sFileName);
			BufferedWriter out = new BufferedWriter(fstream);

			out.write("Row,Col,Expected Value,Actual Value");

			for (MismatchDetails mismatchDetails : mismatches) {

				if(mismatchDetails.getEntireRow().isEmpty())
					out.write("\n"+mismatchDetails.getRow()+","+mismatchDetails.getCol()+","+mismatchDetails.getExpVal()+","+mismatchDetails.getActVal());
				else
					out.write("\n"+mismatchDetails.getRow()+","+mismatchDetails.getCol()+","+mismatchDetails.getExpVal()+
							","+mismatchDetails.getActVal()+","+mismatchDetails.getEntireRow());
			}
			//Close the output stream
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Converts string table data to lists
	 * @param tableDt
	 * @return
	 */
	public static LinkedList<LinkedList<String>> parseTableData(String tableDt,int mainTableFlag)
	{

		//System.out.println("Table Data "+tableDt);
		LinkedList<LinkedList<String>> tableData= new LinkedList<LinkedList<String>>();

		String splittedRows[] = tableDt.split("@DR@");


		for (String rowSingle : splittedRows) {

			String spliitedCells[] = rowSingle.split("@DC@");

			LinkedList<String> row = new LinkedList<String>();

			for (int i = mainTableFlag; i < spliitedCells.length; i++) {

				String string = spliitedCells[i];
				row.add(string);
				//	System.out.print(string+" | ");
			}

			//System.out.println();

			tableData.add(row);
		}

		return tableData;
	}


	/**
	 * Read csv file
	 * @param fileName
	 * @return
	 */
	public static LinkedList<LinkedList<String>> readCsv(String fileName)
	{

		LinkedList<LinkedList<String>> csvData= new LinkedList<LinkedList<String>>();

		CSVReader reader=null;
		try {

			reader = new CSVReader(new FileReader(fileName));

			String [] nextLine;

			while ((nextLine = reader.readNext()) != null)
			{
				LinkedList<String> row = new LinkedList<String>();
				for (String string : nextLine)
				{
					row.add(string);
				}

				csvData.add(row);
			}

			return csvData;
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return csvData;
	}


	/**
	 * Get visible header map (title with index)
	 * @param webDriver
	 * @param loc
	 * @return
	 */
	public 	static LinkedHashMap<String, Integer> getTableHeader(WebDriver webDriver,LocatorVO loc)
	{

		LinkedHashMap<String, Integer> headers=new LinkedHashMap<String, Integer>();
		Object ret =null;

		try {

			WebElement ele = AutomationUtil.getElement(webDriver, loc);

			String h="firstHeader"+Math.random();

			((JavascriptExecutor)webDriver).executeScript("arguments[0].id='"+h+"';",ele);

			String sCheckJs=

					"		VerifyRowData();																							" +
							"		function VerifyRowData()																			" +
							"		{																									" +
							"  			var rtbl=document.getElementById('"+h+"');														" +
							"			var ColCount=rtbl.rows[0].cells.length;															" +
							"			var valueToReturn='';																			" +
							"			var row ='';																					" +
							"			var counter=0;																					" +
							"					var curRow = rtbl.rows[0]; 																" +
							"					row =''; 																				" +
							"					for(j=0;j<ColCount;j++)																	" +
							"						{																					" +
							"							if(curRow.cells[j].style.display!='none')										" +
							"							{																				" +
							"								if(j==0)																	" +
							"									row = curRow.cells[j].innerText+'@D@'+j;								" +
							"								else 																		" +
							"									row = row+'@DC@'+curRow.cells[j].innerText+'@D@'+j;						" +
							"							}																				" +
							"						}																					" +
							"						valueToReturn = valueToReturn+'@DR@'+row; 											" +
							"				return valueToReturn;																		" +
							"		}	";




			String sBrowserType=RTCPConstants.EMPTY_STRING;

			sBrowserType = ((JavascriptExecutor)webDriver).executeScript("return navigator.userAgent").toString();

			if(!sBrowserType.contains("MSIE")) 
			{
				sCheckJs=sCheckJs.replaceAll("innerText", "textContent");
			}

			ret = ((JavascriptExecutor)webDriver).executeScript("return "+sCheckJs);

			String header = ret.toString().split("@DR@")[1];
			String headerCells[] = header.split("@DC@");

			for (String string : headerCells) {

				headers.put(string.split("@D@")[0], Integer.parseInt(string.split("@D@")[1]));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return headers;

	}


	/**
	 * get table data - single page
	 * @param webDriver
	 * @param tableId
	 * @param cols
	 * @return
	 */
	public static  String getTableDataByJs(WebDriver webDriver,String tableId,String cols)
	{

		String sCheckJs=

				"		VerifyRowData();																							" +
						"		function VerifyRowData()																			" +
						"		{																									" +
						"  			var rtbl=document.getElementById('"+tableId+"');												" +
						"			var ColCount=rtbl.rows[1].cells.length;															" +
						"			var Columns = \""+ cols+ "\";																	" +
						"      		var SplitedColumns=Columns.split(\"|\");														" +
						" 			var SplitedColumnsLen=SplitedColumns.length;													" +
						"      		var rwsCount=rtbl.rows.length;																	" +
						"			var valueToReturn='';																			" +
						"			var row ='';																					" +
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
						"								row = row+'@DC@'+curRow.cells[parseInt(SplitedColumns[j])].innerText;		" +
						"						}																					" +
						"						valueToReturn = valueToReturn+'@DR@'+row; 											" +
						"				}																							" +
						"				return valueToReturn;																		" +
						"		}	";


		Object ret =null;

		String tabledata=RTCPConstants.EMPTY_STRING;

		String sBrowserType=RTCPConstants.EMPTY_STRING;

		try {
			sBrowserType = ((JavascriptExecutor)webDriver).executeScript("return navigator.userAgent").toString();

			if(!sBrowserType.contains("MSIE")) 
			{
				sCheckJs=sCheckJs.replaceAll("innerText", "textContent");
			}

			ret = ((JavascriptExecutor)webDriver).executeScript("return "+sCheckJs);

		} catch (Exception e) {

			e.printStackTrace();
		}


		if(ret!=null)
			tabledata =ret.toString();

		return tabledata;

	}

}
