package com.shi.rtcp.email;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.shi.rtcp.utils.GenericUtil;
import com.shi.rtcp.utils.LoadProperties;

public class EmailUtils {

	public static void sendAutoEmail(String sTestName,String resultFile,int passedCount, int failedCount,String timeOfExecution)
	{
		String host = "mailhost.prod.ch3.s.com";
		String from = "rtcp.automation@searshc.com";
		String[] cc = {""};
		try {

			String emailRecipients =  LoadProperties.EMAIL_RECEPIENTS;
			String emailRecipientsCC =  LoadProperties.EMAIL_RECEPIENTS_CC;

			String[] toList = emailRecipients.split(",");

			if(emailRecipientsCC!=null)
			{
				cc = emailRecipientsCC.split(",");
			}

			if(passedCount+failedCount==0)
			{
				SendAutoEmail.sendMailUsingJavaMail(host, from, toList, cc, "Test results "+ sTestName,
						GenericUtil.getActualBodyTextForNoData(),
						null);
			}
			else
			{
				SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, ''yy 'at' HH:mm z");
				String dateAndTimeOfReportCreation =  sdf.format(new Date());

				SendAutoEmail.sendMailUsingJavaMail(host, from, toList, cc, "Test results "+ sTestName,
						GenericUtil.getActualBodyText(passedCount,failedCount,timeOfExecution,dateAndTimeOfReportCreation),
						new File(resultFile));
			}


		} catch (Exception e) {
		}
	}


	public static void sendEmail(String customSubject, String customBody, File file)
	{
		String host = "mailhost.prod.ch3.s.com";
		String from = "rtcp.automation@searshc.com";
		String[] cc = {""};
		try {

			String emailRecipients =  LoadProperties.EMAIL_RECEPIENTS;
			String emailRecipientsCC =  LoadProperties.EMAIL_RECEPIENTS_CC;

			String[] toList = emailRecipients.split(",");

			if(emailRecipientsCC!=null)
			{
				cc = emailRecipientsCC.split(",");
			}

			String sMailBodyTextSummary =  "<font face='Calibri' color='black' size='2'><B>***NOTE: This is an auto generated mail.***" +
					"<br><span style='font-family:Calibri, Arial; font-size:1em; font-weight:bold;'>";

			String regards = "<font face='Calibri' color='black' size='2'><B></br>Regards, <br>RTCP Automation Team<br>";

			SendAutoEmail.sendMailUsingJavaMail(host, from, toList, cc,customSubject , sMailBodyTextSummary+customBody+ regards, file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}