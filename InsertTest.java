package SelectCommand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
   // How to handle in the Error in the Insert query in the DB
public class InsertTest {// insert Student details in Database
	/*
	  version:1.5
	  Developer:prince
	  date:12/03/2019
	 */
  public static void main(String[] args) {
	    Scanner sc=null;
	    int sid=0;
	    String sname=null;
	    String sadd=null;
	    Connection con=null;
	    Statement st=null;
	    String query=null;
	    int count=0;
	  try {
		  //Read the Values of enduser
		  sc=new Scanner(System.in);
		  if(sc!=null) {
			  System.out.println("Student Details:\n");
			  System.out.println("Enter Student no:");
			  sid=sc.nextInt();
			  System.out.println("Enter Student name:");
			  sname=sc.next();
			  System.out.println("Enter the address:");
			  sadd=sc.next();
			 }
		     sname="'"+sname+"'";
		     sadd="'"+sadd+"'";
		   //Register the Driver Software
		     Class.forName("oracle.jdbc.driver.OracleDriver");
		     
		     //Established the Connection 
		     con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
		     //Create the Statement object
		     if(con!=null) {
		    	 st=con.createStatement();
		     }
		     //prepared the SQL Query fire in the Database
		     //insert into student values(105,'Jatin','Delhi');
		     query="insert into student values("+sid+","+sname+","+sadd+")";
		     System.out.println(query);
		     
		     //send the update the Query
		     if(st!=null) {
		    	 count=st.executeUpdate(query);
		     }
		     if(count==0)
		    	 System.out.print("Record is not inserted!");
		     else
		    	 System.out.println(count+"  record is inserted in the Student table in database!");
		     
	  }//try
	  catch(SQLException s) {
		  if(s.getErrorCode()==1)
			  System.out.println("Student is already Registered!");
		  else if(s.getErrorCode()==12899)
			  System.out.print("Values to large for colume!");
		  else if(s.getErrorCode()==912)
			  System.out.print("Invalid SQL Query!");
		  else
			  System.out.println("Unknow Internal problam!");
	  }
	  catch(ClassNotFoundException cnf) {
		  cnf.printStackTrace();
	  }
	  catch(Exception e) {
		  e.printStackTrace();
	  }
	  finally {
		  
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
		  try {
			  if(sc!=null)
				  sc.close();
		  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
		  
	  }

	}//main

}//class
