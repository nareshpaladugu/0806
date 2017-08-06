package com.shi.rtcp.business.keywords;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.business.keywords.util.CommonAutomationTasks;
import com.shi.rtcp.library.ActionLibrary;
import com.shi.rtcp.utils.AutomationUtil;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

public class SelectTableRow implements KeywordInterface
{

	@Override
	public TestStepExecutionResultVO execute(WebDriver webDriver,
			String... params) {

		TestStepExecutionResultVO  testStepExecutionResultVO = new TestStepExecutionResultVO();

		boolean passFlag = false;

		try {

			List<Integer> indices = CommonAutomationTasks.getColumnNumbers(webDriver,AutomationUtil.getTableLocatorByName(params[0]+" HEADER"), params[1]);

			WebElement dataTable = AutomationUtil.getElement(webDriver, AutomationUtil.getTableLocatorByName(params[0]));

			List<WebElement> allRows = dataTable.findElements(By.tagName("tr"));


			for (WebElement singleRow : allRows) {

				String ActaulValRow = null;

				List<WebElement> allCells = singleRow.findElements(By.tagName("td"));

				for (Integer ind : indices) {

					try {
						if(ActaulValRow==null)
							ActaulValRow = allCells.get(ind).getText().trim();
						else
							ActaulValRow = ActaulValRow+"|"+allCells.get(ind).getText().trim();
					} catch (StaleElementReferenceException e) {

						//skip current row, try next
						break;
					}
				}

				if(ActaulValRow.equals(params[2]))
				{
					passFlag=true;
					ActionLibrary.click(webDriver,allCells.get(1));

					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
					}
					//waitForLoadingStage is present...
					CommonAutomationTasks.waitForLoadingStage(webDriver);

					System.out.println("Got row................");
				}
			}


		} catch (Exception e) {

			testStepExecutionResultVO.setDefectDesc("Row not found/visible with Data "+params[2]);
		}

		if(passFlag)
		{
			testStepExecutionResultVO.setStatus(1);
		}
		else
		{
			testStepExecutionResultVO.setDefectDesc("Row not found with Data "+params[2]);
		}
		return testStepExecutionResultVO;

	}
}
