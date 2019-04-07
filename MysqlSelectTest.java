package SelectCommand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlSelectTest {

	public static void main(String[] args) {
		
		  Connection con=null;
		  Statement st=null;
		  String query=null;
		  ResultSet rs=null;
		try {
			//Register the Driver
			Class.forName("org.gjt.mm.mysql.Driver");
			//Established the Conncetion 
			con=DriverManager.getConnection("jdbc:mysql:///mysqlprince","root","pk12345");
			
			 //Create Statement object
			if(con!=null) {
				st=con.createStatement();
			}
			//prepared the Query
			query="SELECT *FROM STUDENT";
			
			//Send and execute the SQL Query in DAtsabase
			if(st!=null) {
				rs=st.executeQuery(query);
			}
			boolean flag=false;
			if(rs!=null) {
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
				}
			}
			if(flag==false) {
				System.out.println("Record not found!");
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
			//close the Connection of object
			try {
				if(rs!=null) 
					rs.close();
			     }
				catch(SQLException e) {
					e.printStackTrace();
				 }
			try {
				if(st!=null) 
					st.close();
			     }
				catch(SQLException e) {
					e.printStackTrace();
				 }
			try {
				if(con!=null) 
					con.close();
			     }
				catch(SQLException e) {
					e.printStackTrace();
				 }
			
			
		}

	}

}
