package controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import service.ServiceFactory;
import dao.DaoFactory;

/**
 * Application Lifecycle Listener implementation class FactoryLinstener
 *
 */
@WebListener
public class FactoryLinstener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public FactoryLinstener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	System.out.println("服务器启动时时调用该方法");
        try {
       	 	DaoFactory.getInstance();
       	 	ServiceFactory.getInstance();
        } catch (Exception e) {
       	 // TODO Auto-generated catch block
       	 e.printStackTrace();
        }
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	 System.out.println("服务器关闭时时调用该方法");
         try {
        	 
         } catch (Exception e) {
        	 // TODO Auto-generated catch block
        	 e.printStackTrace();
         }
    }
	
}
