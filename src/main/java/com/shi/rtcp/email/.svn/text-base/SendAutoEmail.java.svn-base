package com.shi.rtcp.email;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.shi.rtcp.utils.GenericUtil;


/**
 * @author ddaphal
 *
 */
public class SendAutoEmail {


	/**
	 * @param host SMTP host
	 * @param from From 
	 * @param to To list
	 * @param cc CC list
	 * @param subject Subject of email
	 * @param body Body text
	 * @param file File to attach
	 * @return
	 */
	public static int sendMailUsingJavaMail(String host, String from,  String[] to, String[] cc,  String subject, String body, File file)
	{
		final Properties props = new Properties();

		props.put("mail.smtp.host", host);
		props.put("mail.debug", "false");

		final Session session = Session.getInstance(props);

		try 
		{
			final Message msg = new MimeMessage(session);

			if(from.endsWith(";") || from.endsWith(","))
			{
				from = from.substring(0, from.length() - 1);
			}

			msg.setFrom(new InternetAddress(from));

			final InternetAddress[] toAddress = new InternetAddress[to.length];

			for (int count = 0; count < to.length; count++) 
			{
				if(to[count] != null && !to[count].trim().equals(""))
				{
					toAddress[count] = new InternetAddress(to[count]);
				}
			}

			final InternetAddress[] ccAddress = new InternetAddress[cc.length];

			for (int count = 0; count < cc.length; count++) 
			{
				if(cc[count] != null && !cc[count].trim().equals(""))
				{
					ccAddress[count] = new InternetAddress(cc[count]);
				}
			}

			if(toAddress.length != 0 && toAddress[0] != null)
			{
				msg.setRecipients(Message.RecipientType.TO, toAddress);
			}

			if(ccAddress.length != 0 && ccAddress[0] != null)
			{
				msg.setRecipients(Message.RecipientType.CC, ccAddress);
			}

			msg.setSubject(subject);
			msg.setSentDate(new Date());

			Multipart multipart = new MimeMultipart("related");

			BodyPart mbp1 = new MimeBodyPart();
			mbp1.setContent(body, "text/html");			
			multipart.addBodyPart(mbp1);

			if(file != null && file.exists())
			{
				long fileLen = GenericUtil.getFileSizeInMb(file);
				System.out.println("Result file size... : "+fileLen);
				if(fileLen<=30)
				{
					MimeBodyPart mbp2 = new MimeBodyPart();
					FileDataSource fds = new FileDataSource(file);
					mbp2.setDataHandler(new DataHandler(fds));
					mbp2.setFileName(fds.getName());

					multipart.addBodyPart(mbp2);
				}
			}

			msg.setContent(multipart);

			Transport.send(msg);

			System.out.println("Mail sent succussfully.");

			return 1;

		} catch (Exception ex) 
		{			
			ex.printStackTrace();
			return 0;
		}
	}
	
	
}