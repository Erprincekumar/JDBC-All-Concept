package DateFormateInser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TextTest {

	public static void main(String[] args) {
	       Connection con=null;
	       Statement st=null;
	       ResultSet rs=null;
		try {
			//Register JDBC Driver
			Class.forName("com.hxtt.sql.text.TextDriver");
			//Established the Connection
			con=DriverManager.getConnection("jdbc:text:///P:\\ORACLEJDBC\\TextDB");
			//Create Statement object
			if(con!=null)
			{
				st=con.createStatement();
			}
			//Send and execute the SQL Query
			if(st!=null)
				rs=st.executeQuery("select *from file.csv");
			//Processing the ResultSet object
			if(rs!=null)
			{
				while(rs.next())
				{
                  System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
				}
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close the JDBC object
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			
		}




	}

}
