package com.shi.rtcp.locators;

public class LocatorVO {

	private String locator;
	private String type;

	public LocatorVO(String locator) {
		super();
		

		if(locator.startsWith("xpath="))
			this.type = "xpath";
		else if(locator.startsWith("id="))
			this.type = "id";
		else if(locator.startsWith("link="))
			this.type = "link";
		else if(locator.startsWith("name="))
			this.type = "name";
		else if(locator.startsWith("tagname="))
			this.type = "tagname";
		
		this.locator = locator.substring(locator.indexOf("=")+1);
	}

	public String getLocator() {
		return locator;
	}
	public void setLocator(String locator) {
		this.locator = locator;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


}
