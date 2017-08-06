package com.shi.rtcp.business.testdata;

import java.io.File;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.usermodel.Cell;

import com.shi.rtcp.utils.RTCPConstants;
import com.shi.rtcp.utils.RTCPException;

public class DataSheetReader {

	public static 	Map<String, Map<String, Map<String, String>>> readDataSheets(String sWorkBook) throws RTCPException
	{
		Map<String, Map<String, Map<String, String>>>workBookMap = new LinkedHashMap<String, Map<String,Map<String,String>>>();
		
		Map<String, String> rowMap = null;

		Map<String, Map<String, String>> sheetMap =	null;

		File file =new File(sWorkBook);

		String header[]=null;

		int iNoOfSheets=0;

		ExcelHandler excelHandler = new ExcelHandler();

		try 
		{
			org.apache.poi.ss.usermodel.Workbook workbook = excelHandler.getWorkbookSheet(file);

			int actualDataCols[];

			iNoOfSheets = workbook.getNumberOfSheets();

			for(int s=0;s<iNoOfSheets;s++)
			{
				org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(s);

				sheetMap =	new LinkedHashMap<String,  Map<String, String>>();

				if (sheet != null) 
				{
					org.apache.poi.ss.usermodel.Row row = sheet.getRow(0);

					if (row != null) 
					{						
						int totalColums= row.getLastCellNum();
						int actualDataColsCount=-1;

						header=new String[totalColums];
						actualDataCols=new int[totalColums];

						for(int i=0;i<totalColums;i++)
						{
							if(row.getCell(i)!=null && !(row.getCell(i).toString().trim().equals("")))
							{
								actualDataCols[++actualDataColsCount]=i;
							}
						}

						actualDataColsCount++;

						header=new String[actualDataColsCount];

						for(int i=0;i<actualDataColsCount;i++)
						{
							String cellValue = getCellValue(row.getCell(actualDataCols[i]));
							header[i] = cellValue.trim();
						}

						int iRowNumbers = sheet.getLastRowNum();

						for(int i=1;i<=iRowNumbers;i++)
						{
							rowMap=new LinkedHashMap<String, String>();
							row = sheet.getRow(i);

							for(int col=0;col<actualDataColsCount;col++)
							{
								try 
								{
									String cellValue = getCellValue(row.getCell(actualDataCols[col]));

									rowMap.put(header[col], cellValue);

								} catch (Exception e) 
								{
									rowMap.put(header[col],"");
								}
							}

							sheetMap.put(String.valueOf(i), rowMap);
						}

						workBookMap.put(sheet.getSheetName(), sheetMap);
					}
				}
			}
		} catch (RTCPException e) 
		{
			//e.printStackTrace();
			throw e;
		}
		finally
		{
			excelHandler.closeWorkBook();
		}

		return workBookMap;
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
					// value = encode(value);
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

}
