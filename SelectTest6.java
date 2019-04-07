package SelectCommand;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectTest6 {

	public static void main(String[] args) {// Write a App to get details of Student on the basic of student no
		
		  Scanner sc=null;
		  Connection con=null;
		  Statement st=null;
		  ResultSet rs=null;
		  int sid=0;
		  String query=null;
		  boolean flag=false;
		try {
			// Read the input from end user
			sc=new Scanner(System.in);
			System.out.println("Enter the Student no:");
			sid=sc.nextInt();
			
			//Register the driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Established the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			
			//Create the ststement object
			if(con!=null)
			{
				st=con.createStatement();
			}
			//Preapare the SQL  Query
			//   select *from student where sid=&n;
			query="select sid,sname,sadd from student where sid=101";
			// Sending and execute the Query
			if(st!=null)
				rs=st.executeQuery(query);
			
			//Processing the Result in the processing the ResultSet object
			if(rs!=null)
			{
				while(rs.next())
				{   
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
					flag=true;
				}
			}
			if(flag==false)
				System.out.println("No record found!");
			
	  }// try
		//Handle the Exception
	  catch(SQLException e) {
		  e.printStackTrace();
	  }
		
	 catch(ClassNotFoundException e) {
			e.printStackTrace();
			
		}
		
      catch(Exception e) {
    	  e.printStackTrace();
      }
    finally {
    	//close the object
    	try {
    		if(rs!=null)
    			rs.close();
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	//close the object
    	try {
    		if(st!=null)
    			st.close();
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	//close the object
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
    }//finally

	}//main

}//class
