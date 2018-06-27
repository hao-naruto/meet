package service;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 生成唯一 ID
 * @author wanghao
 *
 */
public class CreateOnlyID {

	public String onlyUserID(){
		Date d = new Date();
		String ss = d.getTime() + "";
		ss = ss.substring(4, 7) + ss.subSequence(10, 13);
		ss = "1" + ss;
		return ss;
	}
	
	/**
	 * 
	 * @param eventName
	 * @return
	 */
	public String onlyEventID(String eventName){
		Date d = new Date();
		String ss = d.getTime() + "";
		ss = ss.substring(4, 7) + ss.subSequence(10, 13);
		ss = "e-" + ss + eventName.charAt(0);
		return ss;
	}
	
	public static String getNowDate(){
		SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String dstr = adf.format(d);
		return dstr;
	}
	/**
	 * 获取 后一个月
	 * @return
	 */
	public static String getNextDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		// 操作 时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.MONTH, 1);
		d = calendar.getTime();
		String dstr = sdf.format(d);
		return dstr;

	}
	
	public static void main(String[] args) {
		System.out.println(getNextDate());
	}
}
