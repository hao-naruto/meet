package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Slots;
import entity.SmallTime;
import entity.Time;
import entity.User;
import entity.SmallTimeSlots;
import service.DealSlots;
import service.DealTimeUtil;
import service.QueryEvent;
import service.DealUser;
import service.DealSmallTime;
import service.ServiceFactory;
@WebServlet("*.event")
@SuppressWarnings("unused")
public class JoinAEvent extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		System.out.println(path);
		System.out.println("abc!");
		
		
		//处理 path
		if(path.length() != 16){
			response.sendRedirect("index.html");
			return ;
		}
		String eventID = path.substring(1, 10);
		String eventName = "";
		
		// 查询 事件
		
		// 工厂
		QueryEvent qe = ServiceFactory.getQueryEvent();
		String res[] = qe.getEvent(eventID);
		if(res != null){
			for(int i = 0; i < res.length; i++){
				System.out.println(res[i]);
			}
			eventName = res[1];
		}else{
			System.out.println("no");
			response.sendRedirect("index.html");
			return ;
		}
		
		//查询  以天为单位的 时间段
		Time[] tm = qe.getTime(eventID);
		if(tm != null){
			for(int i = 0; i < tm.length; i++){
				System.out.println(tm[i].getDate());
				System.out.println(tm[i].getStartT());
				System.out.println(tm[i].getEndT());
			}
		}
		String NoEarlierThan = null;
		String NoLaterThan = null;
		// 控制器 处理
		if(tm.length != 0){
			NoEarlierThan = tm[0].getStartT();
			NoLaterThan = tm[0].getEndT();
		}
		
		SmallTime[] slt = DealTimeUtil.SmallInterval(tm);
		DealTimeUtil.PrintSmallTime(slt);
		
		//时间处理
		int st = Integer.parseInt(NoEarlierThan);
		int et = Integer.parseInt(NoLaterThan);
		int count = et - st + 1;
		String[] hours = new String[count];
		for(int i = 0; i < count; i++){
			hours[i] = new String(st + "");
			st ++;
		}
		
		// 获取 用户
		DealUser dlu = ServiceFactory.getDealUser();
		// 所有的用户
		ArrayList<User> alu = dlu.getUsers(eventID);
		for(int i = 0; i < alu.size(); i++){
			User us = alu.get(i);
			System.out.println(us.getUserID());
			System.out.println(us.getUserName());
			System.out.println(us.getPassword());
			System.out.println(us.getEventID());
		}
		
		//获取选择  通过  id
		DealSmallTime dslt = ServiceFactory.getDealSmallTime();
		ArrayList<SmallTimeSlots> alsts = new ArrayList<SmallTimeSlots>();
		for(int i = 0; i < alu.size(); i++){
			User us = alu.get(i);
			alsts.addAll(dslt.getSmallSlots(eventID, alu.get(i).getUserName()));
		}
		//ArrayList<SmallTimeSlots> alsts = dslt.getSmallSlots(eventID, "haohao");
		for(int i = 0; i < alsts.size(); i++){
			SmallTimeSlots ss = alsts.get(i);
			System.out.println(ss.getEventID());
			System.out.println(ss.getUserName());
			System.out.println(ss.getDate());
			System.out.println(ss.getSlot());
		}
		// 处理 选择
		ArrayList<Slots> alss = new ArrayList<Slots>();
		if(alsts.size() != 0){
			//使用 工具类 处理
			alss = DealSlots.getAllSlots(alsts, alu);
		}
		
		/*
		int row = alsts.size();
		int col = alsts.get(0).getSlot().length();
		String slotRes[][] = new String[row][col];
		for(int i = 0; i < row; i++){
			for(int j = 0; j < col; j++){
				if(alsts.get(j).getSlot().charAt(i) == '1'){
					slotRes[i][j] = alsts.get(j).getUserID();
				}
				else{
					slotRes[i][j] = "-1";
				}
			}
		}
		*/
		
		ServiceFactory.closeQueryEvent(qe);
		ServiceFactory.closeDealUser(dlu);
		ServiceFactory.closeDealSmallTime(dslt);
		
		request.setAttribute("eventID", eventID);
		request.setAttribute("NewEventName", eventName);
		request.setAttribute("bigtime", tm);
		request.setAttribute("smalltime", slt);
		request.setAttribute("smalltimecount", slt.length/tm.length);
		request.setAttribute("bigtimecount", tm.length);
		request.setAttribute("hours", hours);
		
		request.setAttribute("users", alu);
		request.setAttribute("alss", alss);
		
		request.getRequestDispatcher("Group.jsp").forward(request, response);
		
		/*
		System.out.println(NewEventName);
		System.out.println(DateTypes);
		System.out.println(PossibleDates);
		System.out.println(NoEarlierThan);
		System.out.println(NoLaterThan);
		System.out.println(TimeZone);
		
		//处理 时间
		Time tm[] = DealTimeUtil.DealTime(PossibleDates, NoEarlierThan, NoLaterThan);
		DealTimeUtil.PrintTime(tm);
		//转为 时间戳
		SmallTime[] slt = DealTimeUtil.SmallInterval(tm, NoEarlierThan, NoLaterThan);
		DealTimeUtil.PrintSmallTime(slt);
		
		//时间处理
		int st = Integer.parseInt(NoEarlierThan);
		int et = Integer.parseInt(NoLaterThan);
		int count = et - st + 1;
		String[] hours = new String[count];
		for(int i = 0; i < count; i++){
			hours[i] = new String(st + "");
			st ++;
		}
		//时间处理
		
		PrintWriter pw = response.getWriter();
		
		request.setAttribute("bigtime", tm);
		request.setAttribute("smalltime", slt);
		request.setAttribute("smalltimecount", slt.length/tm.length);
		request.setAttribute("bigtimecount", tm.length);
		request.setAttribute("hours", hours);
		
		request.getRequestDispatcher("/ds.jsp").forward(request, response);
		
		*/
		
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
