package dao;

import java.util.LinkedList;

public class DaoFactory {
	
	private static LinkedList<MeetQuery> llmu = null;
	private static LinkedList<MeetUpdate> llm = null;
	private static LinkedList<UserOperator> lluo = null;
	private static LinkedList<SmallTimeDeal> llstd = null;
	
	// 恶汉单例
	private static DaoFactory daoFactory = new DaoFactory();
	private DaoFactory(){
		llmu = new LinkedList<MeetQuery>();
		llm = new LinkedList<MeetUpdate>();
		lluo = new LinkedList<UserOperator>();
		llstd = new LinkedList<SmallTimeDeal>();
		init();
	}
	public static DaoFactory getInstance(){
		return daoFactory;
	}
	
	
	
	private static void init(){
		for(int i = 0; i < 3; i++){
			llmu.add(new MeetQuery());
			llm.add(new MeetUpdate());
			lluo.add(new UserOperator());
			llstd.add(new SmallTimeDeal());
		}
	}
	
	/**
	 * SmallTimeDeal
	 * @return
	 */
	public synchronized static SmallTimeDeal getSmallTimeDeal(){
		System.out.println("111111111");
		if(!llstd.isEmpty()){
			return llstd.removeLast();
		}else{
			System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
			return new SmallTimeDeal();
		}
	}
	
	public synchronized static void closeSmallTimeDeal(SmallTimeDeal std){
		System.out.println("3333333333");
		if(std != null){
			llstd.add(std);
		}
	}
	/**
	 * MeetQuery
	 * @return
	 */
	public synchronized static MeetQuery getMeetQuery(){
		if(!llmu.isEmpty()){
			return llmu.removeLast();
		}else{
			return new MeetQuery();
		}
	}
	
	public synchronized static void closeMeetQuery(MeetQuery std){
		if(std != null){
			llmu.add(std);
		}
	}
	
	/**
	 * MeetUpdate
	 * @return
	 */
	public synchronized static MeetUpdate getMeetUpdate(){
		if(!llm.isEmpty()){
			return llm.removeLast();
		}else{
			return new MeetUpdate();
		}
	}
	
	public synchronized static void closeMeetUpdate(MeetUpdate std){
		if(std != null){
			llm.add(std);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public synchronized static UserOperator getUserOperator(){
		if(!lluo.isEmpty()){
			return lluo.removeLast();
		}else{
			return new UserOperator();
		}
	}
	
	public synchronized static void closeUserOperator(UserOperator std){
		if(std != null){
			lluo.add(std);
		}
	}
	
}
