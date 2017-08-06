package com.shi.rtcp.utils;

public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testMethod("1", "2", "3");
		
	}

	
	public static void testMethod(String... param)
	{
		System.out.println(param[0]);
		
		System.out.println(param[1]);
		
		System.out.println(param[2]);
		
		
		if(param.length>=4)
		{
			System.out.println(param[4]);
		}
	}
}
