package com.shi.rtcp.utils;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import com.shi.rtcp.vos.BusinessProcessVO;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

public class ResultUtil {

	public static Map<String, Integer > trackerForHL1 = null;
	public static Map<String, Integer > trackerForHL2 = null;

	public static void generateExcelResult(ResultVO resultvo, String sFileName)
	{

		trackerForHL1= new HashMap<String, Integer>();
		trackerForHL2= new HashMap<String, Integer>();

		try 
		{
			int ps=0,fl=0,totalStepsCnt=0;
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = null;
			BusinessProcessVO businessProcess;
			HSSFRow headerRow;
			HSSFRow summaryRow;
			int diffCount=0;
			int entered=0;	

			List<BusinessProcessVO> businessProcesses = resultvo.getListOfBusniessProcesses();

			int businessProcessCount = businessProcesses.size();

			sheet = workbook.createSheet(RTCPConstants.SUMMARY_TAB_NAME);

			for (int i = 0; i < businessProcessCount; i++) 
			{
				businessProcess = businessProcesses.get(i);

				if(businessProcess != null && businessProcess.getToBeExecuted().equalsIgnoreCase("Y"))
				{

					if(businessProcess.isExecuted())
					{
						String processName = businessProcess.getName();
						sheet = ResultCreationUtility.createSheet(workbook, processName);

						while (sheet == null) 
						{
							sheet = ResultCreationUtility.createSheet(workbook, processName);
						}

						HSSFRow row1 = sheet.createRow((short) (0));
						HSSFCell cell1 = row1.createCell(0);
						cell1 = createCellHeader(workbook,cell1, "RTCP :-");
						HSSFCellStyle cellStyle1 = cell1.getCellStyle();
						cellStyle1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
						cell1.setCellStyle(cellStyle1);
						cell1 = row1.createCell(1);
						cell1 = createCellHeader(workbook,cell1, "Result Details");

						headerRow = sheet.createRow((short) 1);

						List<TestStepExecutionResultVO> cases = businessProcess.getTestCaseExecutionList();
						int testCaseCount = cases.size();

						int iRes=2;
						for (int j = 0; j < testCaseCount; j++) {

							if (j == 0) {

								headerRow.setHeightInPoints((float) (1.3 * sheet.getDefaultRowHeightInPoints()));
								HSSFCell driver_Iteration = headerRow.createCell(0);
								driver_Iteration = createCellHeader(workbook,driver_Iteration, "Test Step");
								sheet.setColumnWidth(0, 5500);

								HSSFCell passed_Steps = headerRow.createCell(1);
								passed_Steps = createCellHeader(workbook,passed_Steps, "Step To Execute");
								sheet.setColumnWidth(1, 10800);

								HSSFCell failed_Steps = headerRow.createCell(2);
								failed_Steps = createCellHeader(workbook,failed_Steps, "Test Data");
								sheet.setColumnWidth(2, 8500);

								HSSFCell status = headerRow.createCell(3);
								status = createCellHeader(workbook, status,"Status");
								sheet.setColumnWidth(5, 4500);

								HSSFCell start_Time = headerRow.createCell(4);
								start_Time = createCellHeader(workbook,start_Time, "Defect Description");
								sheet.setColumnWidth(4, 10800);

								HSSFCell stop_Time = headerRow.createCell(5);
								stop_Time = createCellHeader(workbook,stop_Time, "Executed on");
								sheet.setColumnWidth(5, 4500);
								
								
							}

							TestStepExecutionResultVO executionResult = cases.get(j);

							if(executionResult != null)
							{

								HSSFRow row = sheet.createRow((short) (iRes));
								HSSFCell driver_Iteration = row.createCell(0);
								driver_Iteration.setCellValue(executionResult.getTestCaseID());
								
								HSSFCell passed_Steps = row.createCell(1);
								passed_Steps.setCellValue(executionResult.getStepDescription());
								
								HSSFCell failed_Steps = row.createCell(2);
								failed_Steps.setCellValue(executionResult.getTestData());
								
								HSSFCell status = row.createCell(3);
								status.setCellValue(executionResult.getStrStatus());
								
								HSSFCell message = row.createCell(4);
								message.setCellValue(executionResult.getDefectDesc());
								
								HSSFCell start_time = row.createCell(5);
								start_time.setCellValue(executionResult.getExecutionTimeStr());
								
							


								iRes++;
							}
						}

						/*---------------------------------------------------------------*/

						HSSFCell cell = null;

						HSSFCellStyle cellStyle = workbook.createCellStyle();
						cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
						cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
						cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
						cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
						cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
						cellStyle.setWrapText(true);

						for (Row formatRow : sheet) 
						{
							if (sheet.getRow(0) != formatRow && sheet.getRow(1) != formatRow) 
							{
								Iterator<Cell> it = formatRow.cellIterator();

								while (it.hasNext())
								{
									cell = (HSSFCell) it.next();

									if(cell!=null)
									{
										int colNum = cell.getColumnIndex();

										if (colNum == 1) 
										{
											cellStyle.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);

										} else 
										{
											cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
										}

										cell.setCellStyle(cellStyle);
									}
								}
							}
						}
					}
				}
			}

			String starttime = RTCPConstants.EMPTY_STRING;
			String stopTime =  RTCPConstants.EMPTY_STRING;
			String totalTime = RTCPConstants.EMPTY_STRING;
			businessProcess = businessProcesses.get(0);
			//String starttimeMain = ResultCreationUtility.getExecutionTimingString(businessProcess.getStartCalendar());

			for (int i = 0; i < businessProcessCount; i++) 
			{
				businessProcess = businessProcesses.get(i);

				if(businessProcess != null && businessProcess.getToBeExecuted().equalsIgnoreCase("Y"))
				{
					if(businessProcess.isExecuted())
					{
						String processName = businessProcess.getName();
						int passedTests = businessProcess.getPassedTests();
						int failedTests = businessProcess.getFailedTest();
						//int skippedTests = businessProcess.getSkippedTests();

						starttime = ResultCreationUtility.getExecutionTimingString(businessProcess.getStartCalendar());
						stopTime =  ResultCreationUtility.getExecutionTimingString(businessProcess.getStopCalendar());
						totalTime = giveMeTimeDiff(businessProcess.getStartCalendar(), businessProcess.getStopCalendar());
						String notes = businessProcess.getNotes();
						//String testDataWorkBookValue = businessProcess.getTestDataWorkbook();

						if (entered == 0) 
						{
							sheet = workbook.getSheet(RTCPConstants.SUMMARY_TAB_NAME);

							headerRow = sheet.createRow((short) 5);
							headerRow.setHeightInPoints((float) (1.3 * sheet.getDefaultRowHeightInPoints()));

							HSSFCell driver_Iteration1 = headerRow.createCell(2);
							driver_Iteration1 = createCellHeader(workbook,driver_Iteration1, "Business Process");

							HSSFCell notesCol = headerRow.createCell(3);
							notesCol = createCellHeader(workbook,notesCol, "Notes");

							HSSFCell passed_Steps = headerRow.createCell(4);
							passed_Steps = createCellHeader(workbook,passed_Steps, "No. Of Passed Steps");

							HSSFCell failed_Steps = headerRow.createCell(5);
							failed_Steps = createCellHeader(workbook,failed_Steps, "No. Of Failed Steps");

							HSSFCell total_Steps = headerRow.createCell(6);
							total_Steps = createCellHeader(workbook,total_Steps, "Total");

							HSSFCell start_Time = headerRow.createCell(7);
							start_Time = createCellHeader(workbook, start_Time,"Start Time");

							HSSFCell stop_Time = headerRow.createCell(8);
							stop_Time = createCellHeader(workbook, stop_Time,"Stop Time");

							HSSFCell total_Time = headerRow.createCell(9);
							total_Time = createCellHeader(workbook, total_Time,"Total Time");
							
							HSSFCell message = headerRow.createCell(10);
							message = createCellHeader(workbook,message, "Execution Messages");
							sheet.setColumnWidth(10, 20000);

							sheet.setColumnWidth(2, 10000);
							sheet.setColumnWidth(3, 7500);
							sheet.setColumnWidth(4, 6000);
							sheet.setColumnWidth(5, 7500);
							sheet.setColumnWidth(6, 4000);
							sheet.setColumnWidth(7, 4000);
							sheet.setColumnWidth(8, 4000);
							sheet.setColumnWidth(9, 4000);

							diffCount = 5;

							entered=1;

						} else 
						{
							sheet = workbook.getSheet(RTCPConstants.SUMMARY_TAB_NAME);
							diffCount = sheet.getLastRowNum();
						}

						diffCount++;
						HSSFRow row = sheet.createRow((short) diffCount);
						HSSFCell driver_Iteration = row.createCell(2);
						driver_Iteration.setCellValue(processName);

						CreationHelper createHelper = workbook.getCreationHelper();
						CellStyle hlink_style = workbook.createCellStyle();

						Font hlink_font = workbook.createFont();
						hlink_font.setUnderline((byte)1);
						hlink_font.setColor(IndexedColors.BLUE.getIndex());
						hlink_style.setFont(hlink_font);
						hlink_style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
						hlink_style.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
						hlink_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
						hlink_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
						hlink_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
						hlink_style.setBorderRight(HSSFCellStyle.BORDER_THIN);

						Hyperlink link2 = createHelper.createHyperlink(2);

						String sHLAddress= "'"+ResultCreationUtility.getSheetNameHL(processName)+"'!A1";

						link2.setAddress(sHLAddress);						

						driver_Iteration.setHyperlink(link2);
						driver_Iteration.setCellStyle(hlink_style);

						HSSFCell notesCell = row.createCell(3);
						notesCell.setCellValue(notes);
						HSSFCell passed_Steps = row.createCell(4);
						passed_Steps.setCellValue(passedTests);
						HSSFCell failed_Steps = row.createCell(5);
						failed_Steps.setCellValue(failedTests);
						HSSFCell start_Time = row.createCell(7);
						start_Time.setCellValue(starttime);
						HSSFCell stop_Time = row.createCell(8);
						stop_Time.setCellValue(stopTime);
						HSSFCell total_Time = row.createCell(9);
						total_Time.setCellValue(totalTime);
						
						HSSFCell m = row.createCell(10);
						m.setCellValue(businessProcess.getMessages());
						

						HSSFCell total_Steps = row.createCell(6);
						total_Steps.setCellValue(passedTests + failedTests);
						ps=ps+passedTests;
						fl=fl+failedTests;
						totalStepsCnt = totalStepsCnt + passedTests + failedTests;
					}
				}
			}

			//summary section
			diffCount++;

			summaryRow = sheet.createRow((short) diffCount);
			summaryRow.setHeightInPoints((float) (1.3 * sheet.getDefaultRowHeightInPoints()));

			HSSFCell rowTitle = summaryRow.createCell(2);
			rowTitle = createCellHeader(workbook,rowTitle, "Total");

			HSSFCell notes = summaryRow.createCell(3);
			notes = createCellHeader(workbook,notes, "");

			HSSFCell total_Passed_Steps = summaryRow.createCell(4);
			total_Passed_Steps = createCellHeader(workbook,total_Passed_Steps, ps + "");

			HSSFCell total_Failed_Steps = summaryRow.createCell(5);
			total_Failed_Steps = createCellHeader(workbook,total_Failed_Steps, fl + "");

			HSSFCell total_Steps = summaryRow.createCell(6);
			total_Steps = createCellHeader(workbook,total_Steps, totalStepsCnt + "");

			HSSFCell startTimeTitle = summaryRow.createCell(7);
			startTimeTitle = createCellHeader(workbook,startTimeTitle, "");

			HSSFCell stopTimeTitle = summaryRow.createCell(8);
			stopTimeTitle = createCellHeader(workbook,stopTimeTitle, "");

			String totalTimeStr = calculateTotalTime(businessProcesses);

			HSSFCell total_Time = summaryRow.createCell(9);
			total_Time = createCellHeader(workbook, total_Time, totalTimeStr);
			//summary section ends

			sheet = workbook.getSheet(RTCPConstants.SUMMARY_TAB_NAME);

			HSSFRow row = sheet.createRow((short) (0));
			HSSFCell cell = row.createCell(2);
			cell = createCellHeader(workbook,cell, "RTCP :- ");
			HSSFCellStyle cellStyle1 = cell.getCellStyle();
			cellStyle1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			cell.setCellStyle(cellStyle1);

			HSSFCell cell2 = row.createCell(3);
			cell2 = createCellHeader(workbook,cell2, "Result Summary");

			cell2 = row.createCell(4);
			cell2 = createCellHeader(workbook,cell2, "");

			cell2 = row.createCell(5);
			cell2 = createCellHeader(workbook,cell2, "");

			cell2 = row.createCell(6);
			cell2 = createCellHeader(workbook,cell2, "");

			cell2 = row.createCell(7);
			cell2 = createCellHeader(workbook,cell2, "");

			HSSFCellStyle cellStyleGrey = workbook.createCellStyle();
			cellStyleGrey.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellStyleGrey.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);

			row = sheet.createRow((short) (1));
			cell = row.createCell(2);
			cell.setCellValue(new HSSFRichTextString("Script Under Test:"));
			cell.setCellStyle(cellStyleGrey);

			cell = row.createCell(3);

			String workBookName = resultvo.getScriptName();
			
			/*int start = workBookName.lastIndexOf("\\");
			int end = workBookName.lastIndexOf(".");
			workBookName = workBookName.substring(start + 1, end);
			workBookName = workBookName.replaceAll("/", "");
			workBookName = workBookName.replaceAll("/", "");*/

			cell.setCellValue(new HSSFRichTextString(workBookName));

			try
			{
				row = sheet.createRow((short) 2);
				cell = row.createCell(2);
				cell.setCellValue(new HSSFRichTextString("Star Time:"));
				cell.setCellStyle(cellStyleGrey);

				cell = row.createCell(3);
				cell.setCellValue(new HSSFRichTextString(getDateStr(businessProcesses.get(0).getStartCalendar().getTimeInMillis()) + " " + getTimeStr(businessProcesses.get(0).getStartCalendar().getTimeInMillis())));

				cell = row.createCell(4);
				cell.setCellValue(new HSSFRichTextString("Stop Time:"));
				cell.setCellStyle(cellStyleGrey);

				cell = row.createCell(5);
				cell.setCellValue(new HSSFRichTextString(getDateStr(businessProcesses.get(businessProcesses.size() - 1).getStopCalendar().getTimeInMillis()) + " " + getTimeStr(businessProcesses.get(businessProcesses.size() - 1).getStopCalendar().getTimeInMillis())));

				cell = row.createCell(6);
				cell.setCellValue(new HSSFRichTextString("Execution Time:"));
				cell.setCellStyle(cellStyleGrey);

				cell = row.createCell(7);
				cell.setCellValue(new HSSFRichTextString(calculateTotalTime(businessProcesses)));

			} catch (Exception e) 
			{
				e.printStackTrace();
			}

			row = sheet.createRow((short) 3);
			cell = row.createCell(2);
			cell.setCellValue(new HSSFRichTextString("Browser Type:"));
			cell.setCellStyle(cellStyleGrey);
			cell = row.createCell(3);
			cell.setCellValue(resultvo.getBrowser());

			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);

			for (Row formatRow : sheet) {
				if (sheet.getRow(5) != formatRow && sheet.getRow(0) != formatRow && sheet.getRow(1) != formatRow && sheet.getRow(2) != formatRow && sheet.getRow(3) != formatRow && sheet.getRow(sheet.getLastRowNum()) != formatRow) 
				{
					Iterator<Cell> it = formatRow.cellIterator();

					while (it.hasNext()) 
					{
						cell = (HSSFCell) it.next();

						if(cell!=null)
						{
							int colNum = cell.getColumnIndex();

							if(colNum!=2)//No formating for hyperlink..							
							{
								cell.setCellStyle(cellStyle);
							}
						}
					}
				}
			}

			
			FileOutputStream stream = new FileOutputStream(sFileName);
			workbook.write(stream);
			stream.close();
			
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

	public static String getDateStr(long timeInMiliSec)
	{
		String dateStr = RTCPConstants.EMPTY_STRING;

		Date date = null;

		if(timeInMiliSec == 0)
		{
			date = new Date(System.currentTimeMillis());
		}
		else
		{
			date = new Date(timeInMiliSec);
		}

		String[] datArr = date.toString().split(" ");

		dateStr = datArr[0] + "_" + datArr[1] + "_" + datArr[2] + "_" + datArr[5];

		return dateStr;
	}

	public static String getDateStr1(long timeInMiliSec)
	{
		String dateStr = RTCPConstants.EMPTY_STRING;

		Date date = null;

		if(timeInMiliSec == 0)
		{
			date = new Date(System.currentTimeMillis());
		}
		else
		{
			date = new Date(timeInMiliSec);
		}

		String[] datArr = date.toString().split(" ");

		dateStr = datArr[0] + "," + datArr[1] + "/" + datArr[2] + "/" + datArr[5];

		return dateStr;
	}

	public static String getTimeStr(long timeInMiliSec)
	{
		String timeStr = RTCPConstants.EMPTY_STRING;

		Date date = null;

		if(timeInMiliSec == 0)
		{
			date = new Date(System.currentTimeMillis());
		}
		else
		{
			date = new Date(timeInMiliSec);
		}

		String[] datArr = date.toString().split(" ");
		String[] time = datArr[3].split(":");

		timeStr = time[0] + "_" + time[1] + "_" + time[2];

		return timeStr;
	}


	private static HSSFCell createCellHeader(HSSFWorkbook wb, HSSFCell cell, String cellText) 
	{
		cell.setCellValue(new HSSFRichTextString(cellText));
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
		cell.setCellStyle(cellStyle);
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 10);
		//cellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setFont(font);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THICK);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setWrapText(true);
		cell.setCellStyle(cellStyle);
		return cell;
	}

	public static String giveMeTimeDiff(Calendar calendar1 ,Calendar calendar2 )
	{
		calendar1.set(Calendar.MILLISECOND, 0);
		calendar2.set(Calendar.MILLISECOND, 0);

		long milliseconds1 = calendar1.getTimeInMillis();
		long milliseconds2 = calendar2.getTimeInMillis();

		long diff = milliseconds2 - milliseconds1;

		SimpleDateFormat dateFormat =  	new SimpleDateFormat("HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

		return dateFormat.format(new Date(diff)).toString();
	}

	public static String giveMeTimeDiffStr(String strt ,String stop )
	{
		Calendar calendar1 ;Calendar calendar2;
		calendar1=Calendar.getInstance();
		calendar2=Calendar.getInstance();

		//2:2:2
		String h1=strt.substring(0,strt.indexOf(":"));
		String m1=strt.substring(strt.indexOf(":")+1, strt.lastIndexOf(":"));
		String s1=strt.substring(strt.lastIndexOf(":")+1,strt.length());

		String h2=stop.substring(0,stop.indexOf(":"));
		String m2=stop.substring(stop.indexOf(":")+1, stop.lastIndexOf(":"));
		String s2=stop.substring(stop.lastIndexOf(":")+1,stop.length());


		int hh1=Integer.parseInt(h1);
		int mm1=Integer.parseInt(m1);
		int ss1=Integer.parseInt(s1);

		int hh2=Integer.parseInt(h2);
		int mm2=Integer.parseInt(m2);
		int ss2=Integer.parseInt(s2);


		calendar1.set(Calendar.MILLISECOND, 0);
		calendar1.set(Calendar.HOUR_OF_DAY,hh1 );
		calendar1.set(Calendar.MINUTE,mm1 );
		calendar1.set(Calendar.SECOND,ss1 );


		calendar2.set(Calendar.HOUR_OF_DAY,hh2 );
		calendar2.set(Calendar.MINUTE,mm2 );
		calendar2.set(Calendar.SECOND,ss2 );

		calendar2.set(Calendar.MILLISECOND, 0);

		long milliseconds1 = calendar1.getTimeInMillis();
		long milliseconds2 = calendar2.getTimeInMillis();

		long diff = milliseconds2 - milliseconds1;

		SimpleDateFormat dateFormat =  	new SimpleDateFormat("HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

		return dateFormat.format(new Date(diff)).toString();
	}

	public static String getTimeStamp()
	{

		SimpleDateFormat sdfDate = new SimpleDateFormat("dd_MM_yyyy");
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH_mm_ss");

		Date now = new Date();

		String date = sdfDate.format(now);
		String time = sdfTime.format(now);

		return date+"_"+time;
	}
	
	
	public static String calculateTotalTime(List<BusinessProcessVO> businessProcesses) 
	{
		String totalTimeStr = RTCPConstants.EMPTY_STRING;

		try 
		{
			BusinessProcessVO businessProcessFirst = businessProcesses.get(0);
			BusinessProcessVO businessProcessLast = businessProcesses.get(businessProcesses.size() - 1);

			totalTimeStr = giveMeTimeDiff(businessProcessFirst.getStartCalendar(), businessProcessLast.getStopCalendar());

		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		return totalTimeStr;
	}

}
