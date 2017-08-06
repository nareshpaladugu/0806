package com.shi.rtcp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class LoadProperties {

	public static String LST_FILES = getProperty("lstFiles","");
	public static String LST_FILES_FOLDER = getProperty("fileFolder","");
	public static Integer CONSUMER_THREADCOUNT = Integer.parseInt(getProperty("consumerThreads","10"));

	public static boolean EMAIL_FLAG = Boolean.parseBoolean(getProperty("emailflag", "false"));
	public static String EMAIL_RECEPIENTS = getProperty("emailreceipents", "dnyaneshwar.daphal@searshc.com");
	public static String EMAIL_RECEPIENTS_CC = getProperty("emailreceipentsCC", "");

	public static boolean CaptureBitmapOnFailure = Boolean.parseBoolean(getProperty("captureBitmapOnFailure", "false"));


	public static boolean HIGHLIGHT_OBJECT = Boolean.parseBoolean(getProperty("highlightobject", "true"));


	private static Properties prop;


	public static Properties getProp() {
		return prop;
	}

	public static void setProp(Properties prop) {
		LoadProperties.prop = prop;
	}

	public static String getProperty(String key, String defaultValue) {
		// Read properties file.
		if (null == prop) {
			prop = new Properties();

			try {
				File propFile = new File("src/main/resources/properties.properties");
				prop.load(new FileInputStream(propFile));
			} catch (IOException e) {
				e.printStackTrace();
			}

			Enumeration<Object> keys = prop.keys();

			System.out.println("Configured properties:");
			while (keys.hasMoreElements()) {
				String elt = (String) keys.nextElement();
				System.out.println(String.format(
						"\t\tProperty: %s, value: '%s'", elt, prop
						.getProperty(elt)));
			}
			for (Object syskey : prop.keySet()) {
				String value = System.getProperty(syskey.toString());
				if ((value != null) && (!value.equals(""))) {
					prop.setProperty(syskey.toString(), value);
					System.out.println("Overriding "+ syskey+" to  "+value);
				}
			}
		}


		return prop.getProperty(key, defaultValue);
	}

	public static void loadProperties()
	{
		if (null == prop) {
			prop = new Properties();

			try {
				File propFile = new File("test.properties");
				prop.load(new FileInputStream(propFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}





}
