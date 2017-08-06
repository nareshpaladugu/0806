package com.shi.rtcp.business.testdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.shi.rtcp.utils.RTCPException;

public class DataUtil {

	public static String COL_TAG="<COL>";
	public static String COL_TAG_END = "</COL>";

	public static String parseColValues(Map<String, Map<String, Map<String, String>>>dataMap, String sWorkSheet,
			String sColumnNameParam,String sRowNumber,Map<String, String> rowNumberConfigurationMap)  throws RTCPException  
	{

		try {
			String[] sWorkSheetArray = sWorkSheet.split(";");

			List<String> columns = new ArrayList<String>();
			List<String> envs = new ArrayList<String>();

			for (int i = 0; i < sWorkSheetArray.length; i++) {

				Map<String, Map<String, String>> sheetMapSingle =dataMap.get(sWorkSheetArray[i]);

				if(sheetMapSingle!=null)
				{
					Map<String, String> actualSheetMap = sheetMapSingle.get("1");

					Iterator<Map.Entry<String, String>> itr = actualSheetMap.entrySet().iterator();
					while (itr.hasNext()) {
						Map.Entry<String, String> entry = (Map.Entry<String, String>) itr.next();

						columns.add(entry.getKey());
					}
				}
			}

			Iterator<Map.Entry<String, String>> itr = rowNumberConfigurationMap.entrySet().iterator();
			while (itr.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) itr.next();

				envs.add(entry.getKey());
			}

			int counter = countSubStrings(sColumnNameParam, COL_TAG);

			if(counter==1)
			{
				if(sColumnNameParam.contains(COL_TAG) && !sColumnNameParam.contains(COL_TAG_END))
				{
					sColumnNameParam = sColumnNameParam +COL_TAG_END;
				}
			}

			if(counter == 0)
			{
				return sColumnNameParam;
			}

			int iCOLTAG_counter = countSubStrings(sColumnNameParam, COL_TAG);
			int iCOLENDTAG_counter = countSubStrings(sColumnNameParam, COL_TAG_END);

			int iCOLTAGDiff = iCOLTAG_counter - iCOLENDTAG_counter;

			if(iCOLTAGDiff > 1)
			{
				throw new RTCPException("Could not evaluate param value, please check <COL> syntax: " + sColumnNameParam);
			}

			if(iCOLTAGDiff > 0)
			{
				throw new RTCPException("Could not evaluate param value, please check syntax: " + sColumnNameParam);
			}

			if(iCOLTAG_counter > iCOLENDTAG_counter)
			{
				sColumnNameParam = sColumnNameParam + COL_TAG_END;
			}

			int lastCounter = counter;

			do
			{

				lastCounter = counter;

				for (Iterator<String> iterator = columns.iterator(); iterator.hasNext();) {
					String string = (String) iterator.next();

					if(sColumnNameParam.contains(COL_TAG+string+COL_TAG_END))
					{
						String sValue= getValueFromMap(dataMap, sWorkSheet, string, sRowNumber,rowNumberConfigurationMap);
						sColumnNameParam = sColumnNameParam.replace(COL_TAG+string+COL_TAG_END, sValue);
					}
				}

				counter = countSubStrings(sColumnNameParam, COL_TAG);

				if(counter == 0)
				{
					break; 
				}

				if(lastCounter-counter==0)
				{
					throw new RTCPException("Could not evaluate param value, please check value or syntax ");
				}

			}while(true);


			return sColumnNameParam;


		} catch (Exception e) {

			throw new RTCPException("Could not evaluate param value, please check value or syntax ");
		}

	}

	public static int countSubStrings(String str,String subStr)
	{
		int counter = 0;

		int ind = str.indexOf(subStr);

		while(ind !=-1)
		{
			ind  = str.indexOf(subStr, ind+1);
			counter++;
		}
		return counter;

	}


	/**
	 * Return value from test data sheet
	 * @param dataMap
	 * @param sWorkSheet
	 * @param sColumnName
	 * @param sRowNumber
	 * @param rowNumberConfigurationMap
	 * @return
	 * @throws RTCPException
	 */
	public static String getValueFromMap(Map<String, Map<String, Map<String, String>>>dataMap, 	String sWorkSheet,String sColumnName,String sRowNumber,		Map<String, String> rowNumberConfigurationMap)  throws RTCPException
	{

		Map<String, Map<String, String>> sheetMap = dataMap.get(sWorkSheet);

		if(null == sheetMap)
		{
			throw new RTCPException("Input sheet \""+sWorkSheet+"\" not found");
		}

		if(sRowNumber==null || "".equals(sRowNumber))
		{
			sRowNumber = rowNumberConfigurationMap.get(sWorkSheet);
		}

		if(null == sRowNumber)
		{
			throw new RTCPException("Rownumber not defined");
		}

		Map<String, String> rowMap= sheetMap.get(sRowNumber.trim());

		if(null == rowMap)
		{
			throw new RTCPException("Row not found");
		}
		
		if(null == rowMap.get(sColumnName.trim()))
		{
			throw new RTCPException("Column not found");
		}

		return rowMap.get(sColumnName.trim()).toString();
	}
}
