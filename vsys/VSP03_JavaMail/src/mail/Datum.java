package mail;

import java.util.*;
public class Datum
{
	Date datum = new Date();
	private int zeit_SS_Month=5;
	private int zeit_WS_Month=1;
	boolean bezahlZeit()
	{
		int todayMonth=datum.getMonth()+1;
		int todayDay=datum.getDate();
		//wenn 01.05 (Sommer Semester) oder 01.01 (Winter Senester) dann TRUE
		if((todayMonth==zeit_SS_Month && todayDay==1) || (todayMonth==zeit_WS_Month && todayDay==1))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	int getTodayMonth()
	{
		return datum.getMonth();
	}
}
