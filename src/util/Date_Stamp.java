package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 时间戳转为 字符串/日期    日期转为时间戳
 * @author wanghao
 *
 */
public class Date_Stamp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int Stamp = getTimes(2018, 6, 8, 13, 40);
		System.out.println(Stamp);
		System.out.println(transForDateToStr(Stamp));
		System.out.println(transForDate(Stamp));
		System.out.println(StrToStamp("2018-6-8-13-40"));
	}
	/**
	 * 字符串 to Stamp
	 * @param str
	 * @return
	 */
	public static int StrToStamp(String str){
		String[] arrStr = str.split("-");
		int[] arrInt = new int[5];
		for(int i = 0; i< arrStr.length; i++){
			arrInt[i] = Integer.parseInt(arrStr[i]);
		}
		return getTimes(arrInt[0], arrInt[1], arrInt[2], arrInt[3], arrInt[4]);
	}
	/**
	 * 时间转为时间戳
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @return
	 */
	public static int getTimes(int year, int month, int day, int hour, int minute) {  
        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, day, hour, minute);
        cal.set(Calendar.SECOND, 0);  
        cal.set(Calendar.MILLISECOND, 0);
        
//        cal.add(Calendar.YEAR, year);
//        cal.add(Calendar.MONTH, month);
//        cal.add(Calendar.DAY_OF_MONTH, day);
//        cal.set(Calendar.HOUR_OF_DAY, hour);  
//        cal.set(Calendar.MINUTE, minute);
        return (int) (cal.getTimeInMillis() / 1000);  
    } 
	/** 
     * 时间戳转日期 
     * @param ms 
     * @return 
     */  
    public static Date transForDate(Integer ms){
        if(ms==null){  
            ms=0;  
        }  
        long msl=(long)ms*1000;  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");  
        Date temp=null;  
        if(ms!=null){  
            try {  
                String str=sdf.format(msl);
                temp=sdf.parse(str);  
            } catch (ParseException e) {  
                e.printStackTrace();  
            }  
        }  
        return temp;  
    }
    /**
     * 时间戳转 字符串
     * @param ms
     * @return
     */
    public static String transForDateToStr(Integer ms){
        if(ms==null){  
            ms=0;  
        } 
        long msl=(long)ms*1000;  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");  
        String str = null;
        if(ms!=null){
            str=sdf.format(msl);  
        }  
        return str;  
    }
	/** 
     * 字符串转日期，格式为："yyyy-MM-dd HH:mm:ss" 
     * @param dateStr 
     * @return 
     */  
    public Date formatDate(String dateStr){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        Date result=null;  
        try {  
            result = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();  
        }  
        return result;  
    }

}
