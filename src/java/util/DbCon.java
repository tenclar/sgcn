package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {
	private static Connection con = null;
	
	public static Connection getConnection()
	{
		if(con == null){
			try {
				String url = "jdbc:mysql://localhost:3306/sgcn";
				String usuario = "sgcn";
				String senha = "sgcn";
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url,usuario,senha);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return con;
	}
	
	public static void closeConnection()
	{
		if (con != null)
		{
			try {
				con.close();
			}catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
