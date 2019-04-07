package DateFormateInser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateInsert1 {

	public static void main(String[] args) {
		  Scanner sc=null;
		  int no=0;
		  String name=null,dob=null,doj=null;
		  SimpleDateFormat sdf=null;
		  java.util.Date udob=null;
		  java.sql.Date sqdob=null,sqdoj=null;
		  long ms=0;
		  Connection con=null;
		  PreparedStatement ps=null;
		  int result=0;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter no:");
				no=sc.nextInt();
				
				System.out.println("Enter name:");
				name=sc.next();
			
				System.out.println("Enter DOB(dd-MM-yyyy)");
				dob=sc.next();
		
				System.out.println("Enter DOJ(yyyy-MM-dd)");
				doj=sc.next();
			}
			//Converting String date values to java.sql.Date class object
				//FOR DOB
				sdf=new SimpleDateFormat("dd-MM-yyyy");
				if(sdf!=null)
					udob=sdf.parse(dob);
				if(udob!=null)
					ms=udob.getTime();
			  sqdob=new java.sql.Date(ms);
			  
			  //FOR DOJ
			  sqdoj=java.sql.Date.valueOf(doj);
			  
			  //Register the JDBC Driver
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  
			  //Established the Connection 
			  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			  
			 //Create the PreparedStatement object
			  if(con!=null)
				  ps=con.prepareStatement("insert into person_tab values(?,?,?,?)");
			  //Set values of Query parameters
			  if(ps!=null)
			  {
				  ps.setInt(1,no);
				  ps.setString(2, name);
				  ps.setDate(3, sqdob);
				  ps.setDate(4, sqdoj);
			  }
			  //Execut the Query of the program
			  if(ps!=null)
				  result=ps.executeUpdate();
			  //process the Result
			  if(result==0)
				  System.out.println("Record not inserted!");
			  else
				  System.out.println("Record Inserted Successfully");
			 
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
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

	}

}
