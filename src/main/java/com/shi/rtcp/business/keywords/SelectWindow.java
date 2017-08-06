package com.shi.rtcp.business.keywords;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.shi.rtcp.business.KeywordInterface;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

public class SelectWindow implements KeywordInterface {

	@Override
	public TestStepExecutionResultVO execute(WebDriver webDriver, String... params) {

		TestStepExecutionResultVO result =new TestStepExecutionResultVO();
		
		int  index = Integer.parseInt(params[0]);
		
		try {

			if(index==1)
			{
				webDriver.switchTo().window(LaunchBrowser.mainWindowHandle);
			}
			else
			{
				Set<String> set = webDriver.getWindowHandles();

				int changeCount = 0;

				for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
					String handle = (String) iterator.next();
					webDriver.switchTo().window(handle);

					changeCount++;

					if(changeCount==index)
						break;
				}
			}
		} catch (Exception ex) {
			result.setDefectDesc(ex.getMessage());
			return result;
		}

		finally {
		}

	
		result.setStatus(1);
		return result;
	}

}
