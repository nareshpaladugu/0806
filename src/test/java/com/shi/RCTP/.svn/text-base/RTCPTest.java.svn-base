package com.shi.RCTP;

import org.openqa.selenium.WebDriver;

import com.shi.rtcp.library.ActionLibrary;
import com.shi.rtcp.locators.Locators;
import com.shi.rtcp.utils.FileDownloadUtil;
import com.shi.rtcp.utils.RTCPException;

public class RTCPTest {

	public static WebDriver webDriver;

	public static void main(String[] args) {

		try 
		{
			webDriver = ActionLibrary.launchBrowser("http://wiwebcm301p.dev.ch3.s.com:5500/realtime-pricing-dashboard/realTimePrice/home","chrome");

			/*ScenariosLibrary.clearSelectionPriceMainDatATable(webDriver);*/
			/*ScenariosLibrary.selectTableRow(webDriver, Locators.RealTimePriceMainDataTableHeader, Locators.RealTimePriceMainDataTable, "Product Id|Deal", "09699960000|Restricted");
			
			ScenariosLibrary.selectTableRow(webDriver, Locators.RealTimePriceMainDataTableHeader, Locators.RealTimePriceMainDataTable, "Product Id|Deal", "09699919000|Coupon");
			
			ScenariosLibrary.selectTableRow(webDriver, Locators.RealTimePriceMainDataTableHeader, Locators.RealTimePriceMainDataTable, "Product Id", "09699253000");
			 
			ScenariosLibrary.verifyTableSingleColumnValues(webDriver,Locators.RealTimePriceDataTableHeader, 
					Locators.RealTimePriceDataTable, "Product Id","09699960000|09699919000|09699253000");*/
			
		} catch (RTCPException e) {
			e.printStackTrace();
		}

	}

	public static void testFileDownload()
	{
		//ScenariosLibrary.applyFilter(webDriver,Locators.RealTimePriceMainDataTableHeader,"Product Id","09699960000");
		
		ActionLibrary.click(webDriver, Locators.ExportToExcel_RealTimePriceMainDataTable);
		
		FileDownloadUtil.saveAsFile("C:\\Users\\ddaphal\\Desktop\\exported.xls");
	}

	public static void testApplyFilter()
	{
		// apply filter - main table

	/*	ScenariosLibrary.applyFilter(webDriver,Locators.RealTimePriceMainDataTableHeader,"PMI Price","0.00","less");

		ScenariosLibrary.applyFilter(webDriver,Locators.RealTimePriceMainDataTableHeader,"DP Price","0.00","!");

		ScenariosLibrary.applyFilter(webDriver,Locators.RealTimePriceMainDataTableHeader,"Before Price","0.00","less or equal");

		ScenariosLibrary.applyFilter(webDriver,Locators.RealTimePriceMainDataTableHeader,"Response Code","No_price");

		//aply filter - price table

		ScenariosLibrary.applyFilter(webDriver,Locators.RealTimePriceDataTableHeader,"Response Code","Price_Available","==");*/

		//ScenariosLibrary.applySorting(webDriver, Locators.RealTimePriceMainDataTableHeader, "Product Id", "asc");
	}

	public static void testApplySort()
	{
		/*ScenariosLibrary.applySorting(webDriver, Locators.RealTimePriceMainDataTableHeader, "ASIN", "desc");

		ScenariosLibrary.applySorting(webDriver, Locators.RealTimePriceMainDataTableHeader, "ASIN", "desc");

		ScenariosLibrary.applySorting(webDriver, Locators.RealTimePriceDataTableHeader, "Response Code", "desc");

		ScenariosLibrary.applySorting(webDriver, Locators.RealTimePriceDataTableHeader, "Sears Cost", "asc");*/
	}

}
