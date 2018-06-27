package service;

import java.util.LinkedList;

public class ServiceFactory {
	private static LinkedList<AddEvent> llmu = null;
	private static LinkedList<DealSmallTime> llm = null;
	private static LinkedList<DealUser> llstd = null;
	private static LinkedList<QueryEvent> llqe = null;
	
	
	// 恶汉单例
	private static ServiceFactory serviceFactory = new ServiceFactory();
	private ServiceFactory(){
		llmu = new LinkedList<AddEvent>();
		llm = new LinkedList<DealSmallTime>();
		llstd = new LinkedList<DealUser>();
		llqe = new LinkedList<QueryEvent>();
		init();
	}
	public static ServiceFactory getInstance(){
		return serviceFactory;
	}
	
	
	
	private static void init(){
		for(int i = 0; i < 3; i++){
			llmu.add(new AddEvent());
			llm.add(new DealSmallTime());
			llstd.add(new DealUser());
			llqe.add(new QueryEvent());
		}
	}
	
	/**
	 * SmallTimeDeal
	 * @return
	 */
	public synchronized static DealUser getDealUser(){
		System.out.println("111111111");
		if(!llstd.isEmpty()){
			return llstd.removeLast();
		}else{
			System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
			return new DealUser();
		}
	}
	
	public synchronized static void closeDealUser(DealUser std){
		System.out.println("3333333333");
		if(std != null){
			llstd.add(std);
		}
	}
	/**
	 * MeetQuery
	 * @return
	 */
	public synchronized static AddEvent getAddEvent(){
		if(!llmu.isEmpty()){
			return llmu.removeLast();
		}else{
			return new AddEvent();
		}
	}
	
	public synchronized static void closeAddEvent(AddEvent std){
		if(std != null){
			llmu.add(std);
		}
	}
	
	/**
	 * MeetUpdate
	 * @return
	 */
	public synchronized static DealSmallTime getDealSmallTime(){
		if(!llm.isEmpty()){
			return llm.removeLast();
		}else{
			return new DealSmallTime();
		}
	}
	
	public synchronized static void closeDealSmallTime(DealSmallTime std){
		if(std != null){
			llm.add(std);
		}
	}
	
	
	
	public synchronized static QueryEvent getQueryEvent(){
		if(!llqe.isEmpty()){
			return llqe.removeLast();
		}else{
			return new QueryEvent();
		}
	}
	
	public synchronized static void closeQueryEvent(QueryEvent std){
		if(std != null){
			llqe.add(std);
		}
	}
}
