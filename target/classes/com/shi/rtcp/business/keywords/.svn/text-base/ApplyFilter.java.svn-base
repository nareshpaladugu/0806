package com.shi.rtcp.business.keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.business.keywords.util.CommonAutomationTasks;
import com.shi.rtcp.library.ActionLibrary;
import com.shi.rtcp.utils.AutomationUtil;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

/**
 * @author ddaphal
 *
 */
public class ApplyFilter implements KeywordInterface {

	@Override
	public TestStepExecutionResultVO execute(WebDriver webDriver,
			String... params) {

		TestStepExecutionResultVO  testCaseExecutionResult = new TestStepExecutionResultVO();

		try {

			CommonAutomationTasks.waitForLoadingStage(webDriver);

			int index = CommonAutomationTasks.getColumnNumber(webDriver,AutomationUtil.getTableLocatorByName(params[0]+" HEADER"), params[1]);

			if(index==-1)
			{
				testCaseExecutionResult.setDefectDesc(" Table column not found "+params[1]  );
				return testCaseExecutionResult;
			}
			else
			{
				WebElement elementTable = AutomationUtil.getElement(webDriver,AutomationUtil.getTableLocatorByName(params[0]+" HEADER"));

				WebElement elementHeaderRow = elementTable.findElements(By.tagName("tr")).get(1);

				WebElement header = elementHeaderRow.findElements(By.tagName("th")).get(index);

				WebElement td = header.findElement(By.className("ui-search-input"));

				WebElement input = td.findElement(By.tagName("input"));

				AutomationUtil.clearText(input);

				Thread.sleep(500);

				CommonAutomationTasks.waitForLoadingStage(webDriver);

				if(!params[2].isEmpty())
				{
					td = header.findElement(By.className("ui-search-oper"));

					WebElement link = td.findElement(By.tagName("a"));

					ActionLibrary.click(webDriver,link);

					WebElement menu = webDriver.findElement(By.id("sopt_menu"));

					ActionLibrary.click(webDriver,menu.findElement(By.xpath("//a[@oper='"+AutomationUtil.getConditionSymbol(params[2])+"']")));
				}

				input.sendKeys(params[3]);

				Thread.sleep(1000);

				CommonAutomationTasks.waitForLoadingStage(webDriver);


			}
		} catch (Exception e) {

			return AutomationUtil.returnExceptionResult(testCaseExecutionResult, e);
		}

		testCaseExecutionResult.setStatus(1);
		return testCaseExecutionResult;
	}
}
