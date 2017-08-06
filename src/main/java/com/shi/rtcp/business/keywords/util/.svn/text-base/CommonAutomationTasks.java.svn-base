package com.shi.rtcp.business.keywords.util;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.shi.rtcp.locators.LocatorVO;
import com.shi.rtcp.utils.AutomationUtil;

public class CommonAutomationTasks {
	/**
	 * Return the column index by column name
	 * 
	 * @param tableLocator
	 * @param sColumnName
	 * @return
	 */
	public static int getColumnNumber(WebDriver webDriver,LocatorVO tableLocator,String sColumnName)
	{
		try 
		{
			WebElement elementTable = AutomationUtil.getElement(webDriver,tableLocator);

			WebElement elementHeaderRow = elementTable.findElements(By.tagName("tr")).get(0);

			List<WebElement> elementCells = elementHeaderRow.findElements(By.tagName("th"));
			
			int iIndex = 0 ;
			for (WebElement elementSingleCell : elementCells) 
			{
				if(elementSingleCell.getText().trim().equals(sColumnName))
				{
					return iIndex;
				}

				iIndex++;
			}

			return -1;

		} catch (Exception e) {
			return -1;
		}

	}


	/**
	 * Waits for loading stage to complete
	 */
	public static void waitForLoadingStage(WebDriver webDriver)
	{
		if(webDriver!=null)
		{
			//Both "Loading" should not be there

			WebDriverWait wait = new WebDriverWait(webDriver, 120);

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("load_realTimePriceMainData")));

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("load_realTimePriceData")));
		}
	}

	/**
	 * Waits for loading stage to complete
	 *//*
	public static void waitForLoadingStageForSure(WebDriver webDriver)
	{
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, 10);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("load_realTimePriceMainData")));

			//Both "Loading" should not be there

			wait = new WebDriverWait(webDriver, 120);

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("load_realTimePriceMainData")));

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("load_realTimePriceData")));
		} catch (Throwable e) {

			System.out.println(e.getMessage());
		}
	}*/


	public static List<Integer> getColumnNumbers(WebDriver webDriver,LocatorVO tableLocator,String sColumnNames)
	{
		List<Integer> indices  = new LinkedList<Integer>();

		try 
		{

			WebElement elementTable = AutomationUtil.getElement(webDriver,tableLocator);

			WebElement elementHeaderRow = elementTable.findElements(By.tagName("tr")).get(0);

			List<WebElement> elementCells = elementHeaderRow.findElements(By.tagName("th"));

			
			
			String sColumnNamesSp[] = sColumnNames.split("\\|");
			for (String sColumnName : sColumnNamesSp) {

				int iIndex = 0 ;

				for (WebElement elementSingleCell : elementCells) 
				{
					if(elementSingleCell.getText().trim().equals(sColumnName))
					{
						indices.add(iIndex);
					}

					iIndex++;
				}

			}
			
			
			return  indices;

		} catch (Exception e) {
			return indices;
		}

	}

}
