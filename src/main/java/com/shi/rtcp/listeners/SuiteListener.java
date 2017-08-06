package com.shi.rtcp.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import com.shi.rtcp.utils.LoadProperties;

public class SuiteListener implements ISuiteListener{

	Long start, stop;

	public void onStart(ISuite suite) {

		start = System.currentTimeMillis();
		LoadProperties.loadProperties();
		//ExcelReportListener.openResultFileForWriting();


	}

	public void onFinish(ISuite suite) {
		stop = System.currentTimeMillis();
		System.out.println( "Total time taken in secs:"+(stop - start)/1000);
		System.out.println( "Total time taken in mins:"+(stop - start)/60000);
		suite.setAttribute("TotalTimeSecs", (stop - start)/1000);
		suite.setAttribute("TotalTimeMins", new Double((stop - start)/60000));
	}



}
