package com.shi.rtcp.business.handlefailure;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.shi.rtcp.business.keywords.util.RTCPWait;
import com.shi.rtcp.utils.RTCPConstants;
import com.shi.rtcp.vos.BusinessProcessVO;
import com.shi.rtcp.vos.SingleTestStepVO;
import com.shi.rtcp.vos.TestStepExecutionResultVO;

public class HandlerFailures {

	public static List< LinkedHashMap<String, HashMap<String, String>>> outputList = new LinkedList<LinkedHashMap<String,HashMap<String,String>>>();

	public static void captureBitmapHandler(WebDriver webDriver, String scriptName, 
			BusinessProcessVO businessProcess,	SingleTestStepVO testCase, TestStepExecutionResultVO executionResult) 
	{
		try 
		{

			String sImageName = captureScreenPrint(webDriver);
			HashMap<String, String> details = new HashMap<String, String>();
			details.put("ScriptName", scriptName);
			details.put("BusinessProcess", businessProcess.getName());
			details.put("TestCaseId", testCase.getTeststep());
			details.put("StepDescription", testCase.getStepDescription());
			details.put("DefectDescription", executionResult.getDefectDesc());

			String outputFileName = RTCPConstants.RESULT_DIR_PATH + scriptName;

			if(outputFileName.contains("\\"))
			{
				outputFileName = outputFileName.substring(outputFileName.lastIndexOf("\\")+1);
				outputFileName = outputFileName.replace(".xlsx","");
			}

			if(outputFileName.contains("/"))
			{
				outputFileName = outputFileName.substring(outputFileName.lastIndexOf("/")+1);
				outputFileName = outputFileName.replace(".xlsx","");
			}


			LinkedHashMap<String, HashMap<String, String>> newe=new LinkedHashMap<String, HashMap<String, String>>();
			newe.put(sImageName,details);

			outputList.add(newe);


		} catch(Exception e)
		{
			executionResult.setDefectDesc(executionResult.getDefectDesc()+" Capture screen error : " + e.getMessage());
		}
	}		


	/**
	 * Captures screen
	 * @param webDriver
	 * @return
	 */
	public static String captureScreenPrint(WebDriver webDriver)
	{
		String ImageName = RTCPConstants.TEMP_FOLDER+Thread.currentThread().getName()+Math.random() +".png";

		try
		{
			
			RTCPWait myWait=new RTCPWait();

			if(webDriver != null){

				try {
					webDriver.switchTo().window(webDriver.getWindowHandle());
					myWait.waitFor(300);

					webDriver.manage().window().maximize();
					myWait.waitFor(300);

					webDriver.switchTo().window(webDriver.getWindowHandle());
					myWait.waitFor(300);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}

			myWait.waitFor(2000);

			File snapshort_file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);

			System.out.println(ImageName);

			FileUtils.copyFile(snapshort_file, new File(ImageName));

			myWait.waitFor(2000);
		} catch (Exception e) {

		}
		return ImageName;
	}

	public static void generatFailureReport(String scriptName){

		if(outputList!=null)
		{
			String sFileName=RTCPConstants.RESULT_DIR_PATH+scriptName;

			try 
			{
				OutputStream file = new FileOutputStream(new File(sFileName + ".pdf"));
				Document document = new Document();
				PdfWriter.getInstance(document, file);

				document.open();

				for(int i=0;i<outputList.size();i++)
				{
					LinkedHashMap<String, HashMap<String, String>> newe = outputList.get(i);
					Set<String> keySet1 = newe.keySet();
					Iterator<String> keySetIterator1 = keySet1.iterator();

					while(keySetIterator1.hasNext())
					{
						Object ImageKey = keySetIterator1.next();
						HashMap<String, String> details = newe.get(ImageKey);

						addDetailFailure(document, ImageKey.toString(),details);				
					}
				}

				document.close();
				file.close();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			outputList.clear();
		}
	}


	public static void addDetailFailure(Document document ,String sImageName,HashMap<String, String> details) throws DocumentException, MalformedURLException, IOException
	{
		Image image = Image.getInstance (sImageName);
		image.scaleAbsolute(image.getWidth() / 3, image.getHeight() / 3);

		document.add(new Paragraph("Script Name : "+details.get("ScriptName")));
		document.add(new Paragraph("Business Process : "+details.get("BusinessProcess")));
		document.add(new Paragraph("Test Case Id : "+details.get("TestCaseId")));
		document.add(new Paragraph("Step Description : "+details.get("StepDescription")));
		document.add(new Paragraph("Defect Description : "+details.get("DefectDescription")));
		document.add(new Paragraph(""));
		document.add(image);
		document.add(new Paragraph(""));
	}
}
