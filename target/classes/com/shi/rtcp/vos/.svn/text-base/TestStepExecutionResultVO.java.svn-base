package com.shi.rtcp.vos;

import java.util.Calendar;

import com.shi.rtcp.utils.RTCPConstants;

public class TestStepExecutionResultVO {

	private Calendar startCalendar, stopCalendar;
	
	public Calendar getStartCalendar() {
		return startCalendar;
	}
	public void setStartCalendar(Calendar startCalendar) {
		this.startCalendar = startCalendar;
	}
	public Calendar getStopCalendar() {
		return stopCalendar;
	}
	public void setStopCalendar(Calendar stopCalendar) {
		this.stopCalendar = stopCalendar;
	}
	private String executionTimeStr;
	private String totalTimeStr = RTCPConstants.EMPTY_STRING;
	
	private String stepDescription;
	public String getStepDescription() {
		return stepDescription;
	}
	public void setStepDescription(String stepDescription) {
		this.stepDescription = stepDescription;
	}
	private String testCaseID;
	
	private String action;
	private String testData;
	private String defectDesc;
	private int status;

	public String getTestCaseID() {
		return testCaseID;
	}
	public void setTestCaseID(String testCaseID) {
		this.testCaseID = testCaseID;
	}
	private String strStatus;

	public String getStrStatus() {

		int intStatus = getStatus();
		if (intStatus != -1) {
			switch (intStatus) {
			case RTCPConstants.PASS:
				strStatus = RTCPConstants.PASS_STEP_STATUS;
				break;
			case RTCPConstants.FAIL:
				strStatus = RTCPConstants.FAIL_STEP_STATUS;
				break;
			case RTCPConstants.SKIPPED:
				strStatus = RTCPConstants.SKIPPED_STEP_STATUS;
				break;
			default:
				break;
			}
		}

		return strStatus;
	}
	public TestStepExecutionResultVO()
	{
		status = RTCPConstants.FAIL;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getTestData() {
		
		testData=testData==null?"":testData.trim();
		
		if(testData.startsWith(","))
		{
			testData=testData.replaceFirst(",","");
		}
		return testData;
	}
	public void setTestData(String testData) {
		this.testData = testData;
	}
	public String getDefectDesc() {
		return defectDesc;
	}
	public void setDefectDesc(String defectDesc) {
		this.defectDesc = defectDesc;
	}
	
	public String getExecutionTimeStr() {
		return executionTimeStr;
	}
	public void setExecutionTimeStr(String executionTimeStr) {
		this.executionTimeStr = executionTimeStr;
	}
	public String getTotalTimeStr() {
		return totalTimeStr;
	}
	public void setTotalTimeStr(String totalTimeStr) {
		this.totalTimeStr = totalTimeStr;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}

}
