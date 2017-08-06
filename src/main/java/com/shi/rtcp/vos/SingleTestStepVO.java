package com.shi.rtcp.vos;

import java.util.Map;

import com.shi.rtcp.business.testdata.DataUtil;
import com.shi.rtcp.utils.RTCPConstants;
import com.shi.rtcp.utils.RTCPException;

public class SingleTestStepVO {

	private String actionOnFail;
	private String actionOnFailParam;
	private String expectedResult;
	
	public String getActionOnFail() {
		if(actionOnFail==null)
		{
			actionOnFail=RTCPConstants.EMPTY_STRING;
		}
		return actionOnFail;
	}

	public void setActionOnFail(String actionOnFail) {
		this.actionOnFail = actionOnFail;
	}

	public String getActionOnFailParam() {
		
		if(actionOnFailParam==null)
		{
			actionOnFailParam=RTCPConstants.EMPTY_STRING;
		}
		return actionOnFailParam;
	}

	public void setActionOnFailParam(String actionOnFailParam) {
		this.actionOnFailParam = actionOnFailParam;
	}

	public String getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}
	private String param1;
	private String param2;
	private String param3;
	private String param4;
	private String param5;
	private String param6;
	private String param7;
	private String param8;

	private String teststep;

	public String getTeststep() {
		return teststep;
	}

	public void setTeststep(String teststep) {
		this.teststep = teststep;
	}
	private String row;
	private String toBeExecuted;

	private String action;
	private String stepDescription;

	private String paramForTestData;

	public String getParamForTestData() {
		return paramForTestData;
	}

	public void setParamForTestData(String paramForTestData) {
		this.paramForTestData = paramForTestData;
	}

	public String[] getParamsList(Map<String, Map<String, Map<String, String>>>dataMap,Map<String, String> rowNumberConfigurationMap) throws RTCPException
	{
		paramForTestData = null;

		String params[]=new String[8];

		params[0]=param1;
		params[1]=param2;
		params[2]=param3;
		params[3]=param4;
		params[4]=param5;
		params[5]=param6;
		params[6]=param7;
		params[7]=param8;

		for (int i = 0; i < params.length; i++) {

			if(params[i].contains(DataUtil.COL_TAG))
			{
				try {
					params[i]=DataUtil.parseColValues(dataMap, sWorkSheet, params[i], sRowNumber, rowNumberConfigurationMap);
				} catch (RTCPException e) {
					throw e;
				}
			}
			
			if(action.equalsIgnoreCase("LoginToRTCPApp"))
			{
				paramForTestData = params[0];
			}
			else
			{
				if(!params[i].isEmpty())
				if(paramForTestData==null)
					paramForTestData=params[i];
				else
					paramForTestData=paramForTestData+","+params[i];
			}
		}

		return params;
	}
	public String getParam1() {

		param1 = param1==null?"":param1.trim();

		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	public String getParam2() {

		param2 = param2==null?"":param2.trim();

		if(param2.isEmpty())
		{
			//default browser is chrome
			param2="chrome";
		}
		return param2;
	}
	public void setParam2(String param2) {
		this.param2 = param2;
	}
	public String getParam3() {

		param3 = param3==null?"":param3.trim();
		return param3;
	}
	public void setParam3(String param3) {
		this.param3 = param3;
	}
	public String getParam4() {

		param4 = param4==null?"":param4.trim();

		return param4;
	}
	public void setParam4(String param4) {
		this.param4 = param4;
	}
	public String getParam5() {

		param5 = param5==null?"":param5.trim();
		return param5;
	}
	public void setParam5(String param5) {
		this.param5 = param5;
	}
	public String getParam6() {

		param6 = param6==null?"":param6.trim();
		return param6;
	}
	public void setParam6(String param6) {
		this.param6 = param6;
	}
	public String getParam7() {
		return param7;
	}
	public void setParam7(String param7) {
		this.param7 = param7;
	}
	public String getParam8() {
		return param8;
	}
	public void setParam8(String param8) {
		this.param8 = param8;
	}
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public String getToBeExecuted() {
		return toBeExecuted;
	}
	public void setToBeExecuted(String toBeExecuted) {
		this.toBeExecuted = toBeExecuted;
	}
	public String getAction() {
		
		if(action==null)
		{
			action=RTCPConstants.EMPTY_STRING;
		}
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getStepDescription() {
		if(stepDescription==null || stepDescription.isEmpty())
		{
			stepDescription = action;
		}
		return stepDescription;
	}
	public void setStepDescription(String stepDescription) {
		this.stepDescription = stepDescription;
	}

	private String sWorkSheet;
	private String sRowNumber;

	public String getsWorkSheet() {

		sWorkSheet = sWorkSheet==null?"":sWorkSheet.trim();

		return sWorkSheet;
	}

	public void setsWorkSheet(String sWorkSheet) {

		this.sWorkSheet = sWorkSheet;
	}

	public String getsRowNumber() {

		sRowNumber = sRowNumber==null?"":sRowNumber.trim();
		return sRowNumber;
	}

	public void setsRowNumber(String sRowNumber) {
		this.sRowNumber = sRowNumber;
	}

}
