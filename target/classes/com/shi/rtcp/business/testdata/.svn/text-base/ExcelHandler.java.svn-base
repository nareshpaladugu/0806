
package com.shi.rtcp.business.testdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.shi.rtcp.utils.RTCPException;

public class ExcelHandler 
{
	FileInputStream fileInputStream = null;
	org.apache.poi.ss.usermodel.Workbook workBook = null;

	public Workbook getWorkbookSheet(File file) throws RTCPException 
	{
		try
		{
			if (file.getName().endsWith(".xls")) 
			{
				fileInputStream = new FileInputStream(file);
				workBook = new HSSFWorkbook(fileInputStream);

			} else if (file.getName().endsWith(".xlsx")) 
			{
				fileInputStream = new FileInputStream(file);
				workBook = new XSSFWorkbook(fileInputStream);
			}
		} catch (Exception e) 
		{
			throw new RTCPException(e.getMessage());

		}

		return workBook;
	}

	public void closeWorkBook()
	{
		try 
		{
			if(fileInputStream != null)
			{
				fileInputStream.close();
			}

			workBook = null;

		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}