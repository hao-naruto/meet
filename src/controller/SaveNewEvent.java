package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AddEvent;
import service.CreateOnlyID;
import service.ServiceFactory;

@SuppressWarnings("unused")
@WebServlet("/SaveNewEvent")
public class SaveNewEvent extends HttpServlet {

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
		// 接收 请求
		String NewEventName = request.getParameter("NewEventName");
		String DateTypes = request.getParameter("DateTypes");
		String PossibleDates = request.getParameter("PossibleDates");
		String NoEarlierThan = request.getParameter("NoEarlierThan");
		String NoLaterThan = request.getParameter("NoLaterThan");
		String TimeZone = request.getParameter("TimeZone");
		
		//DateTypes 处理
		
		
		// 产生 唯一 ID
		CreateOnlyID co = new CreateOnlyID();
		String eventID = co.onlyEventID(NewEventName);
		
		// 存储 到 数据库
		AddEvent ae = ServiceFactory.getAddEvent();
		// 添加 事件
		ae.addNewEvent(eventID, NewEventName, TimeZone);
		// 添加每一天
		ae.addEventTime(eventID, PossibleDates, NoEarlierThan, NoLaterThan);
		
		
		response.sendRedirect(eventID + ".event");
		
		ServiceFactory.closeAddEvent(ae);
		
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
		
		
		//mes=new String(mes.getBytes("iso-8859-1"),"utf-8");//浏览器默认支持的"iso8859-1",是不支持中文的
		
		//response.setContentType("text/html");
		//response.setCharacterEncoding("utf-8");
		
		/*
		ServletContext application = request.getServletContext();
		
		String hours=request.getParameter("h");
		String minutes=request.getParameter("m");
		String seconds=request.getParameter("s");
		String user=request.getParameter("user");
		String mes=request.getParameter("mes");
		String type=request.getParameter("type");
		mes=new String(mes.getBytes("iso-8859-1"),"utf-8");//浏览器默认支持的"iso8859-1",是不支持中文的
		//System.out.println(mes);
		if(type.equals("send")){
			Vector<OnUser> ou=(Vector<OnUser>)application.getAttribute("user");//添加 增加 自己的 消息 数;
			for(int i=0;i<ou.size();i++){
				if(ou.get(i).getName().equals(user)){
					ou.get(i).setMesId(ou.get(i).getMesId()+1);  // 自身 消息数 +1;  避免重复消息
				}
			}
			String[] str={hours,minutes,seconds,user,mes};
			NotSql mess=new NotSql();
			mess.InsertData(str);
		}
		String allmes=hours+"-"+minutes+"-"+seconds+"\n"+user+":"+mes+"\n";
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		response.getWriter().println(allmes);
		*/
		/*
		 * 显示 所有 请求
		try{
		    boolean first = true;
		    StringBuffer strbuf = new StringBuffer("");
		    Enumeration emParams = request.getParameterNames();
		    do{
		    	if(!emParams.hasMoreElements()) {
		    		break;
		    	}
		    	String sParam = (String) emParams.nextElement();
		    	String[] sValues = request.getParameterValues(sParam);
		    	String sValue = "";
		    	for (int i = 0; i < sValues.length; i++) {
		    		sValue = sValues[i];
		    		if (sValue != null && sValue.trim().length() != 0 && first == true) {
		    			first = false;
		    			strbuf.append(sParam).append("=").append(URLEncoder.encode(sValue, "GBK"));
		    		}else if(sValue != null && sValue.trim().length() != 0 && first == false) {
		    			strbuf.append("&").append(sParam).append("=").append(URLEncoder.encode(sValue, "GBK"));
		    		}
		    		System.out.println(strbuf);
		    		strbuf = new StringBuffer("");
		    	}
		    }while (true);
		}catch(UnsupportedEncodingException e){
		   e.printStackTrace();
		}
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
