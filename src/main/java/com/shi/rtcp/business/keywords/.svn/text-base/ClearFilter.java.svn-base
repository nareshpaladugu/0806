package com.shi.rtcp.business.keywords;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.business.keywords.util.CommonAutomationTasks;
import com.shi.rtcp.library.ActionLibrary;
import com.shi.rtcp.utils.AutomationUtil;
import com.shi.rtcp.utils.RTCPConstants;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

public class ClearFilter implements KeywordInterface {

	@Override
	public TestStepExecutionResultVO execute(WebDriver webDriver,
			String... params) {


		TestStepExecutionResultVO testStepExecutionResultVO=new TestStepExecutionResultVO();

		WebElement elementTable=null;
		try {

			CommonAutomationTasks.waitForLoadingStage(webDriver);

			if(params[0].isEmpty())
			{
				//default it main table
				params[0] = RTCPConstants.PRICE_MAIN_DATA_TABLE_VARIABLE;
			}
			elementTable = AutomationUtil.getElement(webDriver,AutomationUtil.getTableLocatorByName(params[0]+" HEADER"));

			WebElement elementHeaderRow = elementTable.findElements(By.tagName("tr")).get(1);

			int columnCount = elementHeaderRow.findElements(By.tagName("th")).size();

			for (int i = 0; i < columnCount; i++) {

				WebElement header = elementHeaderRow.findElements(By.tagName("th")).get(i);

				WebElement td=null;
				try {

					webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

					td = header.findElement(By.className("ui-search-input"));

					if(td!=null)
					{
						WebElement input = td.findElement(By.tagName("input"));

						// Select '==' condition
						td = header.findElement(By.className("ui-search-oper"));

						WebElement link = td.findElement(By.tagName("a"));

						//no need to select equals condition is already selected
						String oper = link.getAttribute("soper");

						if(oper!=null && (oper.equalsIgnoreCase("eq") || oper.equalsIgnoreCase("bw")))
						{
							oper ="==";
						}

						oper = oper==null?link.getText():oper;

						if(!oper.contains("=="))
						{
							AutomationUtil.scrollToElement(webDriver, td);

							ActionLibrary.click(webDriver,link);

							WebElement menu = webDriver.findElement(By.id("sopt_menu"));

							ActionLibrary.click(webDriver,menu.findElement(By.xpath("//a[@oper='==']")));

							System.out.println("selected condition '=='  ");
						}

						//empty the input box if not already
						if(!input.getAttribute("value").isEmpty())
						{
							/*input.clear();

							input.sendKeys(Keys.TAB);*/
							
							String getLengthfromTextBox = input.getAttribute("value").toString();
							int slength=getLengthfromTextBox.length();
							for (int o=0;o<slength;o++)
							{
								input.sendKeys(Keys.BACK_SPACE);
							}
							input.clear();
							
							//System.out.println("Cleared text and sent TAB ...");

							Thread.sleep(1500);
							
							CommonAutomationTasks.waitForLoadingStage(webDriver);
						}

					}
				} catch (Exception e) {

					//not so imp to handle
					//System.out.println("ui-search-input not found @ "+i);
				}
			}

			Thread.sleep(2000);
			CommonAutomationTasks.waitForLoadingStage(webDriver);

		}catch (Exception e1) {

			e1.printStackTrace();
			return AutomationUtil.returnExceptionResult(testStepExecutionResultVO, e1);
		}
		finally
		{
			webDriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		}
		testStepExecutionResultVO.setStatus(1);
		return testStepExecutionResultVO;
	}

}
