package SelectCommand;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest5 {
	public static void main(String args[]) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;	
		try {
			
			
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "prince", "prince");
	     
		st=con.createStatement();
		rs=st.executeQuery("select ename,sal from emp");
		while(rs.next())
		{
			System.out.println(rs.getString(1)+"     "+rs.getInt(2));
		}
		
	       
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			try {
			rs.close();
			st.close();
			con.close();
			}
			catch(SQLException s)
			{
				s.printStackTrace();
			}
		}
		
	}
 }


