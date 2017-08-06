package com.shi.rtcp.utils;

public class RTCPException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public RTCPException(String message){
		super(message);
	}
	
	public RTCPException(String message, Throwable cause){
		super(message, cause);
	}
}
