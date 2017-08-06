package com.shi.rtcp.business.keywords.util;

import com.thoughtworks.selenium.Wait;

/**
 * @author ddaphal
 *
 */
public class RTCPWait
{
	public RTCPWait()
	{
	}

	public void waitFor(long milliSeconds)
	{
		Wait forceWait=new Wait() {

			@Override
			public boolean until() {

				return false;
			}

		};
		try {
			forceWait.wait("Waited for "+milliSeconds , milliSeconds);
		} catch (Exception e) {

		}

	}


}
