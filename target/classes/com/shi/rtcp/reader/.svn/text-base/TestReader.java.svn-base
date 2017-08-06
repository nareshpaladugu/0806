package com.shi.rtcp.reader;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.shi.rtcp.utils.RTCPConstants;
import com.shi.rtcp.utils.RTCPException;
import com.shi.rtcp.vos.BusinessProcessVO;
import com.shi.rtcp.vos.SingleTestStepVO;
import com.shi.rtcp.vos.TestVO;

public class TestReader {


	public static TestVO readTest(String sFileName) throws RTCPException
	{
		Workbook wb=null;

		TestVO testVO=new TestVO();

		LinkedList<BusinessProcessVO> driverRows=new LinkedList<BusinessProcessVO>();

		try {
			wb = WorkbookFactory.create(new File(sFileName));

			Sheet sheet = wb.getSheet(RTCPConstants.DRIVER_TAB_NAME);

			int rows = sheet.getPhysicalNumberOfRows();

			Row row= null;

			for (int i =1; i <=rows; i++) 
			{
				row = sheet.getRow(i);

				if(row==null)
				{
					continue;
				}

				int colNum=0;
				BusinessProcessVO driverVO=new BusinessProcessVO(getCellValue(row.getCell(colNum++)).toString(),getCellValue(row.getCell(colNum++)));

				driverVO.setTestDataFile(getCellValue(row.getCell(colNum++)).toString());
				driverVO.setRowNumber(getCellValue(row.getCell(colNum++)).toString());
				driverVO.setNotes(getCellValue(row.getCell(colNum++)).toString());
				
				try {
					driverVO.setStartIt(Integer.parseInt(getCellValue(row.getCell(colNum++)).toString()));
				} catch (Exception e) {
					driverVO.setStartIt(-1);
				}
				
				try {
					driverVO.setStopIt(Integer.parseInt(getCellValue(row.getCell(colNum++)).toString()));
				} catch (Exception e) {
					driverVO.setStopIt(-1);
				}

				driverVO.setTestSteps(readBusniessProcess(wb, getCellValue(row.getCell(0))));

				driverRows.add(driverVO);

			}

		} catch (Exception e) {

			throw new RTCPException(e.getMessage());
		}

		finally
		{
			//TODO closing file
		}
		testVO.setDriverRows(driverRows);

		return testVO;
	}

	public static LinkedList<SingleTestStepVO> readBusniessProcess(Workbook wb,String tabName)
	{
		LinkedList<SingleTestStepVO> testSteps=new LinkedList<SingleTestStepVO>();
		Sheet sheet = wb.getSheet(tabName);

		int rows = sheet.getPhysicalNumberOfRows();

		Row row= null;

		for (int i =1; i <=rows; i++) 
		{
			row = sheet.getRow(i);

			if(row==null)
			{
				continue;
			}

			//Row Num Test Step ID	ToBeExecuted?	Keyword	Param1	Param2	Param3	Param4	Param5	Param6	Param7	Param8	Step Description

			SingleTestStepVO singleTestStepVO=new SingleTestStepVO();

			int cellInd=0;

			singleTestStepVO.setRow(getCellValue(row.getCell(cellInd++)));
			singleTestStepVO.setTeststep(getCellValue(row.getCell(cellInd++)));
			singleTestStepVO.setToBeExecuted(getCellValue(row.getCell(cellInd++)));
			singleTestStepVO.setAction(getCellValue(row.getCell(cellInd++)));

			singleTestStepVO.setsWorkSheet(getCellValue(row.getCell(cellInd++)));
			singleTestStepVO.setsRowNumber(getCellValue(row.getCell(cellInd++)));


			singleTestStepVO.setParam1(getCellValue(row.getCell(cellInd++)));
			singleTestStepVO.setParam2(getCellValue(row.getCell(cellInd++)));
			singleTestStepVO.setParam3(getCellValue(row.getCell(cellInd++)));
			singleTestStepVO.setParam4(getCellValue(row.getCell(cellInd++)));
			singleTestStepVO.setParam5(getCellValue(row.getCell(cellInd++)));
			singleTestStepVO.setParam6(getCellValue(row.getCell(cellInd++)));
			singleTestStepVO.setParam7(getCellValue(row.getCell(cellInd++)));
			singleTestStepVO.setParam8(getCellValue(row.getCell(cellInd++)));
			singleTestStepVO.setStepDescription(getCellValue(row.getCell(cellInd++)));
			
			singleTestStepVO.setActionOnFail(getCellValue(row.getCell(cellInd++)));
			singleTestStepVO.setActionOnFailParam(getCellValue(row.getCell(cellInd++)));


			testSteps.add(singleTestStepVO);
		}


		return testSteps;
	}


	public static String getCellValue(org.apache.poi.ss.usermodel.Cell cell) 
	{

		String value = RTCPConstants.EMPTY_STRING;

		if (cell != null) 
		{
			int cellType = cell.getCellType();

			switch (cellType) 
			{

			case Cell.CELL_TYPE_STRING:

				value = cell.getRichStringCellValue().toString();

				if (value.contains("password")) 
				{
					//no need to show password in test data
				}

				break;

			case Cell.CELL_TYPE_NUMERIC:

				if (HSSFDateUtil.isCellDateFormatted(cell))
				{
					Date date2 = cell.getDateCellValue();
					String dateFmt = cell.getCellStyle().getDataFormatString();
					value = new CellDateFormatter(dateFmt).format(date2);
				}else
				{
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					value = cell.getStringCellValue();
				}

				break;

			case Cell.CELL_TYPE_BOOLEAN:

				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				value = cell.getStringCellValue();
				break;

			case Cell.CELL_TYPE_BLANK:

				value = RTCPConstants.EMPTY_STRING;
				break;

			default:

				return value;
			}
		} else 
		{
			return value;
		}

		return value;
	}

	public static String handleStringCase(String cellString) {


		return cellString;
	}


}
