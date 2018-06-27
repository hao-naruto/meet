package db;
import java.sql.Connection;  
import java.sql.SQLException;  
  
import javax.sql.DataSource;  
  
import com.mchange.v2.c3p0.ComboPooledDataSource;  
  
public class JDBCUtil {
    private static DataSource dataSource=null;  
    static{
        dataSource=new ComboPooledDataSource("mysql");  
    }
      
    /** 
     * 
     * @return 
     */  
    public static synchronized Connection getConnection(){  
        Connection conn=null;  
        try {
             conn=dataSource.getConnection();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return conn;  
    }  
  
      
    /** 
     * 
     * @param conn 
     */  
    public static synchronized void closeConn(Connection conn){
        try {  
        	// 傻逼 感叹号
            if(conn != null && !conn.isClosed()){  
                conn.close();
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }
}
