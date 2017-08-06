
package com.shi.rtcp.business.keywords.util;

import java.io.IOException;
import java.util.Properties;

import com.shi.rtcp.business.KeywordFactory;
import com.shi.rtcp.business.KeywordInterface;

public class KeywordFactoryImpl implements KeywordFactory {


	private static Properties properties = new Properties();

	private static KeywordFactoryImpl KEYWORD_MAP = null;

	private KeywordFactoryImpl() {

		init();
	}

	public static synchronized KeywordFactoryImpl getInstance() {

		if (KEYWORD_MAP == null) {
			KEYWORD_MAP = new KeywordFactoryImpl();

		}
		return KEYWORD_MAP;
	}

	private void init() {

		properties = new Properties();
		try {

			properties.load(getClass().getResourceAsStream(com.shi.rtcp.utils.RTCPConstants.KEYWORD_PROPERTY_FILE_PATH));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public KeywordInterface get(String actualKeyword) {


		String className = properties.getProperty(actualKeyword);
		KeywordInterface keyword = null;
		try {
			keyword = (KeywordInterface) Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			return null;
		}
		return keyword;
	}
}
