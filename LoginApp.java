package ProjectJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp{ 
/*  1. Enter  UseName:
   prince' --              :your userid is correct but your password is incorrect than login successful
   Enter Password:         : it is a SQL Enjection problam   
    12345
   Successful login! 
      Welcome to my Website!
      -----------------------------------------------------------------------------------------------
    2.Enter  UseName:
    Ahit' or 1=1 --
    Enter Password:  :this way i can creak the password and userid wrong in the website
    2345
     Successful login! 
    Welcome to my Website!
  
	  
	 */
	public static void main(String[] args) {
		Scanner sc=null;
		String uname=null;
		String password=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		int count=0;
		try {
			//Read the from user
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter  UseName:");
				uname=sc.nextLine();
				System.out.println("Enter Password:");
				password=sc.nextLine();
				
			}
			//Convert the Query and Condition
			uname="'"+uname+"'";
			password="'"+password+"'";
			
			// Register the  JDBC Driver
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //Established the Connection 
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
		  
		  //Create the Statement object
		   if(con!=null)
		   {
			   st=con.createStatement();
		   }
		   //prepare the Query of the JDBC
		    //select count(*) from userlist where uname='prince' and password='pk12345';
		   query="select count(*) from userlist where uname="+uname+" and password="+password;
		   
		   //Send and Execute the Query of the DB
		   if(st!=null) {
			  rs=st.executeQuery(query) ;
		   }
		   //processing the Result of ResultSet object
		   if(rs!=null)
		   {
			   rs.next();
			   count=rs.getInt(1);
		   }
		   if(count==0)
			   System.out.println("Invalid UserId and Password!\n Plese enter the Correct!");
		  else
			  System.out.println("Successful login! \n Welcome to my Website!");
			  
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		  
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			//close the Connection of object
			try {
				if(rs!=null) 
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null) 
				  st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null) 
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(sc!=null) 
					sc.close();
			}
			catch(Exception se) {
				se.printStackTrace();
			}
		}

	}

}
