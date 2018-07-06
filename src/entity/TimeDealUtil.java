package entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;

public class TimeDealUtil {
	
	private static String[] Months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	private static String[] DaysOfWeek = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
	
	public static String getMonth(String str){
		
		return Months[Integer.parseInt(str.substring(5, 7)) - 1];
	}
	public static String getDate(String str){
		
		return Integer.parseInt(str.substring(8, 10)) + "";
	}
	
	public static String getDay(String str){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		
		try {
			date = df.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int week = ca.get(Calendar.DAY_OF_WEEK);
		return DaysOfWeek[week - 1];
	}

}
