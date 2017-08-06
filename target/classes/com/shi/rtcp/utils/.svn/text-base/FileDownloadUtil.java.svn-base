package com.shi.rtcp.utils;

import java.io.File;
import java.util.Properties;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;

public class FileDownloadUtil {

	public static boolean saveAsFile(String FileName)
	{

		try {
			File f= new File(FileName);
			if(f.exists());
			f.delete();
		} catch (Exception e1) {

			System.out.println("Deleted old file...");
		}

		final String EMPTY_STRING=RTCPConstants.EMPTY_STRING;
		final User32 user32 = User32.INSTANCE;

		int Hwnd, comboBox32win,ComboBoxwin,EditBox,hwndButton,SaveAsDialog,Window6,Window7,Window1,Window2,Window3,Window4,Window5,Window5EditBox;

		Hwnd=user32.FindWindowA("#32770", "Save As");

		if(Hwnd!=0)
		{
			Properties p = System.getProperties();

			String Os_Ver=p.getProperty("os.name");

			SaveAsDialog = user32.FindWindowA("#32770", "Save As");

			if(Os_Ver.toUpperCase().equals("WINDOWS XP"))
			{
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				System.out.println(p.getProperty("os.name"));
				comboBox32win = user32.FindWindowExA(SaveAsDialog, 0, "ComboBoxEx32", EMPTY_STRING);
				ComboBoxwin = user32.FindWindowExA(comboBox32win, 0, "ComboBox", EMPTY_STRING);
				EditBox = user32.FindWindowExA(ComboBoxwin, 0, "Edit", EMPTY_STRING);
				Window6 = user32.FindWindowExA(SaveAsDialog, 0, "ComboBox", EMPTY_STRING);
				Window7 = user32.FindWindowExA(SaveAsDialog, Window6, "ComboBox", EMPTY_STRING);
				user32.SendMessageA(Window7, User32.CB_SETCURSEL, 1,0);
				user32.SendMessageA(EditBox, User32.WM_SETTEXT, 0, FileName);
				hwndButton = user32.FindWindowExA(SaveAsDialog, 0, "Button", "&Save");
				user32.SendMessageA(hwndButton, User32.BM_CLICK, 0, 0);
			}
			else
			{
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				System.out.println(p.getProperty("os.name"));
				Window1 = user32.FindWindowExA(SaveAsDialog, 0, "DUIViewWndClassName",EMPTY_STRING);
				Window2 = user32.FindWindowExA(Window1, 0, "DirectUIHWND", EMPTY_STRING);
				Window3 = user32.FindWindowExA(Window2, 0, "FloatNotifySink", EMPTY_STRING);
				Window4 = user32.FindWindowExA(Window3, 0, "ComboBox", EMPTY_STRING);
				Window5EditBox = user32.FindWindowExA(Window4, 0, "Edit", EMPTY_STRING);
				Window5 = user32.FindWindowExA(Window2, Window3, "FloatNotifySink", EMPTY_STRING);
				Window6 = user32.FindWindowExA(Window5, 0, "ComboBox", EMPTY_STRING);
				user32.SendMessageA(Window6, User32.CB_SETCURSEL, 1,0);
				user32.SendMessageA(Window5EditBox, User32.WM_SETTEXT, 0, FileName);
				hwndButton = user32.FindWindowExA(SaveAsDialog, 0, "Button", "&Save");
				user32.SendMessageA(hwndButton, User32.BM_CLICK, 0, 0);

			
			}

			System.out.println("Done");


		}
		else
		{
			System.out.println("No Download Window Found");
			return false;
		}

		return true;
	}

	public interface User32 extends StdCallLibrary
	{
		User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
		interface WNDENUMPROC extends StdCallCallback {boolean callback(Pointer hWnd, Pointer arg);}
		int BM_CLICK = 0x00F5;
		int WM_SETTEXT=0x000C;
		int CB_SETCURSEL=0x14E;
		int SC_CLOSE=0xF060;
		long MF_BYCOMMAND=0x00000000L;
		int FindWindowA (String WindowName, String WindowClassName);
		boolean SetForegroundWindow (int hwnd);
		int FindWindowExA (int hwnd,int i,String WindowClassName, String WindowName);
		int SendMessageA (int hwnd,int Msg, int WParam , int LParam);
		int SendMessageA (int hwnd,int Msg, int WParam , String LParam);
		int DeleteMenu (int hMenu , int nPosition, long wFlags);
		int GetSystemMenu (int hwnd , int bRevert);
	}

	public static void main(String arg[])
	{
		saveAsFile("C:\\Users\\ddaphal\\Desktop\\d.csv");
	}
}
