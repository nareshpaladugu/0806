package com.shi.rtcp.utils;

import java.io.File;

public class GenericUtil {

	/**
	 * generates html mail body content when no data found for selected filter
	 * @return
	 */
	public static String getActualBodyTextForNoData()
	{
		String sMailBodyTextSummary =  "<font face='Calibri' color='black' size='2'><B>***NOTE: This is an auto generated mail.***<br><br>" +
				"<span style='font-family:Calibri, Arial; font-size:1em; font-weight:bold;'>"+"No data found for selected filter";

		return sMailBodyTextSummary + "</font><font face='Calibri' color='black' size='2'><B></br></br>Regards, <br><br>Content Automation<br>";
	}

	/**
	 * Generates html mail body content for pass/fail case
	 * @param passcount
	 * @param failcount
	 * @return
	 */
	public static String getActualBodyText(int passcount,int failcount,String timeinMin,String dateAndTime)
	{


		String sMailBodyTextSummary =  "<font face='Calibri' color='black' size='2'><B>***NOTE: This is an auto generated mail.***<br>" +
				"<span style='font-family:Calibri, Arial; font-size:1em; font-weight:bold;'>" 
				/*+"<br>"+lstFiles+"</br><br>"+sGreenvip+"</br><br>"+sWCSDB+"</br>" +*/
				+"<br>Please refer attached file containing test execution report.</font>";



		sMailBodyTextSummary +=
				"<font face='Calibri' color='black' size='3'>"+
						"<BR><BR><TABLE>" +
						"	<TR align='center'>" +
						"		<TD rowspan='2'>" +
						"			<TABLE cellspacing='2' cellpadding='1' border='1' BORDERCOLOR='#4f81bd' align='left' ><col width='160px'><col width='160px'>" +
						"			<TR>" +
						"				<TD><DIV style='text-align:left'><font face='Calibri' color='green' size='2'><B>Total PASSED Tests</DIV>" +
						"				<TD><DIV style='text-align:left'><font face='Calibri' color='green' size='2'><B>"	+ passcount+ "</DIV>" +
						"			</TR>" +
						"			<TR>" +
						"				<TD><DIV style='text-align:left'><font face='Calibri' color='red' size='2'><B>Total FAILED Tests</DIV>" +
						"				<TD><DIV style='text-align:left'><font face='Calibri' color='red' size='2'><B>"+ failcount+ "</DIV>" +
						"			</TR>"+ 

	"			<TR>" +
	"				<TD><DIV style='text-align:left'><font face='Calibri' color='black' size='2'><B>Total Execution time</DIV>" +
	"				<TD><DIV style='text-align:left'><font face='Calibri' color='black' size='2'><B>"	+ timeinMin+ "</DIV>" +
	"			</TR>" +
	"			<TR>" +
	"				<TD><DIV style='text-align:left'><font face='Calibri' color='black' size='2'><B>Date-Time of report creation</DIV>" +
	"				<TD><DIV style='text-align:left'><font face='Calibri' color='black' size='2'><B>"+ dateAndTime+ "</DIV>" +
	"			</TR>"+ 


						"			</TABLE>" +
						"		</TD></TR></TABLE></BR>" ;



		return sMailBodyTextSummary + "<font face='Calibri' color='black' size='2'><B></br>Regards, <br>RTCP Automation Team<br>";
	}

	/**
	 * Returns file size in MB
	 * @param fileName
	 * @return
	 */
	public static long getFileSizeInMb(String fileName)
	{
		try {
			return (new File(fileName).length()/(1024*1024));
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Returns file size in MB
	 * @param fileName
	 * @return
	 */
	public static long getFileSizeInMb(File file)
	{
		try {
			return (file.length()/(1024*1024));
		} catch (Exception e) {
			return 0;
		}
	}

}
