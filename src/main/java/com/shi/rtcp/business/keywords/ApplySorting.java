package com.shi.rtcp.business.keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.business.keywords.util.CommonAutomationTasks;
import com.shi.rtcp.library.ActionLibrary;
import com.shi.rtcp.utils.AutomationUtil;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

public class ApplySorting implements KeywordInterface {

	@Override
	public TestStepExecutionResultVO execute(WebDriver webDriver,
			String... params) {

		TestStepExecutionResultVO  testCaseExecutionResult = new TestStepExecutionResultVO();

		int iTryCount  = 2;

		try {
			int index = CommonAutomationTasks.getColumnNumber(webDriver,AutomationUtil.getTableLocatorByName(params[0]+" HEADER"), params[1]);

			if(index==-1)
			{
				System.out.println(" Header not found  :  "+params[1]  );
			}
			else
			{
				WebElement elementTable = AutomationUtil.getElement(webDriver,AutomationUtil.getTableLocatorByName(params[0]+" HEADER"));

				WebElement elementHeaderRow = elementTable.findElements(By.tagName("tr")).get(0);

				WebElement header = elementHeaderRow.findElements(By.tagName("th")).get(index);

				WebElement div = header.findElement(By.tagName("div"));

				int clickCount=0;

				if(params[2].equalsIgnoreCase("asc"))
				{
					WebElement ascArrow  = div.findElement(By.tagName("span")).findElements(By.tagName("span")).get(0);

					String classAsc = ascArrow.getAttribute("class");

					while(classAsc.contains("disabled"))
					{
						ActionLibrary.click(webDriver,div);

						classAsc = ascArrow.getAttribute("class");

						clickCount++;

						if(clickCount==iTryCount) 
							break;
					}
				}
				else
				{
					WebElement descArrow  = div.findElement(By.tagName("span")).findElements(By.tagName("span")).get(1);

					String classDesc = descArrow.getAttribute("class");

					while(classDesc.contains("disabled"))
					{
						ActionLibrary.click(webDriver,div);

						classDesc = descArrow.getAttribute("class");

						clickCount++;

						if(clickCount==iTryCount) 
							break;
					}
				}
			}
		} catch (Exception e) {

			return AutomationUtil.returnExceptionResult(testCaseExecutionResult, e);
		}
	
		
		testCaseExecutionResult.setStatus(1);
		return testCaseExecutionResult;
	}

	
}
