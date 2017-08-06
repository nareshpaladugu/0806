package com.shi.rtcp.vos;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class BusinessProcessVO {

	private int passedTests;
	private int skippedTests;
	private int failedTest;

	private int startIt;
	private int stopIt;
	
	public int getStartIt() {
		return startIt;
	}
	public void setStartIt(int startIt) {
		this.startIt = startIt;
	}
	public int getStopIt() {
		return stopIt;
	}
	public void setStopIt(int stopIt) {
		this.stopIt = stopIt;
	}

	private String notes;
	
	private String messages;
	
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getPassedTests() {
		return passedTests;
	}
	public void setPassedTests(int passedTests) {
		this.passedTests = passedTests;
	}
	public int getSkippedTests() {
		return skippedTests;
	}
	public void setSkippedTests(int skippedTests) {
		this.skippedTests = skippedTests;
	}
	public int getFailedTest() {
		return failedTest;
	}
	public void setFailedTest(int failedTest) {
		this.failedTest = failedTest;
	}
	public BusinessProcessVO(String scenariosName, String toBeExecuted) {
		super();
		this.name = scenariosName;
		this.toBeExecuted = toBeExecuted;
	}

	private List<TestStepExecutionResultVO> testCaseExecutionList;

	public List<TestStepExecutionResultVO> getTestCaseExecutionList() {
		return testCaseExecutionList;
	}
	public void setTestCaseExecutionList(
			List<TestStepExecutionResultVO> testCaseExecutionList) {
		this.testCaseExecutionList = testCaseExecutionList;
	}

	private boolean executed=false;
	private Calendar startCalendar, stopCalendar;

	public boolean isExecuted() {
		return executed;
	}
	public void setExecuted(boolean executed) {
		this.executed = executed;
	}
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

	private String name;
	private String toBeExecuted;
	private String testDataFile;
	private String rowNumber;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getToBeExecuted() {
		return toBeExecuted;
	}
	public void setToBeExecuted(String toBeExecuted) {
		this.toBeExecuted = toBeExecuted;
	}
	public String getTestDataFile() {
		return testDataFile;
	}
	public void setTestDataFile(String testDataFile) {
		this.testDataFile = testDataFile;
	}
	public String getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}


	private LinkedList<SingleTestStepVO> testSteps;
	public LinkedList<SingleTestStepVO> getTestSteps() {
		return testSteps;
	}
	public void setTestSteps(LinkedList<SingleTestStepVO> testSteps) {
		this.testSteps = testSteps;
	}
}
