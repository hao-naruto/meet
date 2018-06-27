package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DealSmallTime;
import service.ServiceFactory;
import entity.SmallTimeSlots;
import entity.User;
import service.DealUser;

@WebServlet("/SavaTimes")
public class SavaTimes extends HttpServlet {

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
		
		String UserID = request.getParameter("person");
		String adates = request.getParameter("adates");
		String eventID = request.getParameter("event");
		String slots = request.getParameter("slots");
		String availability = request.getParameter("availability");
		String ChangeToAvailable = request.getParameter("ChangeToAvailable");
		
		System.out.println(UserID + "  " + eventID + "  " + slots + "   " + availability+"  " + ChangeToAvailable);
		// 有几天
		System.out.println(adates);
		
		//存储 到 数据库
		
		// 天
		String[] dates = adates.split(",");
		int sizeofdates = dates.length;
		// 小时间段
		String[] smallt = new String[sizeofdates];
		int timeslots = availability.length();
		for(int i = 0; i < sizeofdates; i++){
			smallt[i] = new String(availability.substring(i*timeslots/sizeofdates, (i+1)*timeslots/sizeofdates));
			System.out.println(smallt[i]);
		}
		
		System.out.println(dates.length);
		for(int i = 0; i < sizeofdates; i++){
			System.out.println(dates[i]);
		}
		System.out.println(availability.length());
		
		//查用户 名称
		DealUser du = ServiceFactory.getDealUser();
		
		User u = du.getUsers(eventID, UserID);
		System.out.println(u.getUserID());
		String userName = u.getUserName();
		
		// 用于存储
		SmallTimeSlots[] slts = new SmallTimeSlots[sizeofdates];
		for(int i = 0; i < sizeofdates; i++){
			slts[i] = new SmallTimeSlots();
			slts[i].setEventID(eventID);
			slts[i].setUserName(userName);
			slts[i].setDate(dates[i]);
			slts[i].setSlot(smallt[i]);
		}
		DealSmallTime dst = ServiceFactory.getDealSmallTime();
		System.out.println("---------------------------");
		dst.updateSmall(slts);
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		ServiceFactory.closeDealUser(du);
		ServiceFactory.closeDealSmallTime(dst);
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
