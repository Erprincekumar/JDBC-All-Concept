package DateFormateInser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DateRetrieve {
    private static final String DATE_SELECT_QUERY="select *from person_tab";
	public static void main(String[] args) {
		  Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  int no=0;
		  String name=null;
		  
		  java.sql.Date sqdob=null,sqdoj=null;
		  java.util.Date udob=null,udoj=null;
		  String dob=null,doj=null;
		  boolean flag=false;
		  SimpleDateFormat sdf=null;
		  
		try {
			
			//Register the JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Established the Connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			
	      //Create the PreparedStatement object
			if(con!=null)
			{
				ps=con.prepareStatement(DATE_SELECT_QUERY);
			}
			//execute the Query
			if(ps!=null)
			  rs=ps.executeQuery();
			//process the Result set object
			if(rs!=null)
			{
				while(rs.next())
				{
					no=rs.getInt(1);
					name=rs.getString(2);
					sqdob=rs.getDate(3);
					sqdoj=rs.getDate(4);
				  //Converting the java.sql.Date class object to java.util.Date class object
					//class objects
					udob=(java.util.Date)sqdob;
					udoj=(java.util.Date)sqdoj;
					//Convert java.util.Date object to String 
					//String Date values
					sdf=new SimpleDateFormat("dd-MMM-yyyy");
					dob=sdf.format(udob);
					doj=sdf.format(udoj);
					
					
					System.out.println(no+"\t"+name+"\t"+dob+"\t"+doj);
					flag=true;
					
				}
				if(flag==false)
					System.out.println("Record Is not Found !");
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Record not inserted!");
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
				if(ps!=null)
					ps.close();
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
