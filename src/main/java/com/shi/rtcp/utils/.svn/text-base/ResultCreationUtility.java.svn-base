package com.shi.rtcp.utils;

import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ResultCreationUtility {

	
	/**
	 * @param hssfWorkbook
	 * @param businessProcessName
	 * @return
	 */
	public static HSSFSheet createSheet(HSSFWorkbook hssfWorkbook, String businessProcessName) 
	{
		try 
		{
			businessProcessName = getSheetName(businessProcessName);

			HSSFSheet hssfSheet = hssfWorkbook.createSheet(businessProcessName);
			return hssfSheet;
		} catch (IllegalArgumentException e) 
		{	
			if (e.getMessage().contains("The workbook already contains a sheet of this name")) 
			{
				return null;
			}
		}
		
		return null;
	}
	

	/**
	 * @param businessProcessName
	 * @return
	 */
	public static  String getSheetName(String businessProcessName)
	{
		Integer instance = ResultUtil.trackerForHL1.get(businessProcessName);

		if( instance == null )
		{
			instance = 0; 
			ResultUtil.trackerForHL1.put(businessProcessName,instance);
			return businessProcessName;
		}
		else
		{
			
			/* ------------ increment instance -------------- */
			instance ++;
			
			/* ------------ map business process name = instance -------------- */
			
			ResultUtil.trackerForHL1.put(businessProcessName,instance);
			
			/* ------------ take first 28 characters if name greater than 28 chars -------------- */
			
			if(businessProcessName.length() >= 29)
			{
				businessProcessName = businessProcessName.substring(0,28);
			}
			
			/* ------------ return final business process name -------------- */
			
			businessProcessName = businessProcessName + "_"+instance;
		}

		return businessProcessName;
	}
	
	/**
	 * @param calendar calendar Instance
	 * @return
	 */
	public static String getExecutionTimingString(Calendar calendar) {

		if(calendar == null)
		{
			return RTCPConstants.EMPTY_STRING;
		}

		int hour;
		int minute;
		int second;
		String timeStr =RTCPConstants.EMPTY_STRING;
		
		try 
		{
			hour = calendar.get(Calendar.HOUR_OF_DAY);
			minute = calendar.get(Calendar.MINUTE);
			second = calendar.get(Calendar.SECOND);
			timeStr= hour + ":" + minute + ":" + second;
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		return timeStr;
	}

	
	
	/**
	 * @param businessProcessName
	 * @return
	 */
	public static String getSheetNameHL(String businessProcessName)
	{
		Integer instance = ResultUtil.trackerForHL2.get(businessProcessName);

	
		if( instance == null )
		{
			instance = 0; 
			ResultUtil.trackerForHL2.put(businessProcessName,instance);
			return businessProcessName;
		}
		else
		{
			

			
			/* ------------ increment instance -------------- */
			instance ++;
			
			/* ------------ map business process name = instance -------------- */
			
			ResultUtil.trackerForHL2.put(businessProcessName,instance);
			
			/* ------------ take first 28 characters if name greater than 28 chars -------------- */
			
			if(businessProcessName.length() >= 29)
			{
				businessProcessName = businessProcessName.substring(0,28);
			}
			
			/* ------------ return final business process name -------------- */
			
			businessProcessName = businessProcessName + "_"+instance;
		
			
		}

		return businessProcessName;
	}
	
}
