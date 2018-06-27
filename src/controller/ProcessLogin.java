package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import service.CreateOnlyID;
import service.DealUser;
import service.ServiceFactory;

@WebServlet("/ProcessLogin")
public class ProcessLogin extends HttpServlet {

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
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		
		System.out.println("ProcessLogin");
		String eventID = request.getParameter("id");
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		
		CreateOnlyID cd = new CreateOnlyID();
		String userID = cd.onlyUserID();
		
		DealUser du = ServiceFactory.getDealUser();
		if(du.userLogin(userID, username, password, eventID)){
			System.out.println("ok");
		}else{
			//登录 失败
			pw.println("no");
			return ;
		}
		
		ArrayList<User> alu = new ArrayList<User>();
		alu = du.getUsers(eventID);
		for(int i = 0; i < alu.size(); i++){
			if(alu.get(i).getUserName().equals(username)){
				userID = alu.get(i).getUserID();
				break;
			}
		}
		
		System.out.println(eventID);
		System.out.println(userID);
		System.out.println(username);
		System.out.println(password);
		
		
		
		pw.println(userID);
		ServiceFactory.closeDealUser(du);
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
