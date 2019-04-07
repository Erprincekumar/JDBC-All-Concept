package PreparedStatemetCommand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTest_GroupRegistation {
	
    //You can plase the This method any where either inside the main method and and in the class also
	static String query="insert into student values(?,?,?)";	   
	public static void main(String[] args) {
		Scanner sc=null;
		int norecord=0;
		Connection con=null;
		PreparedStatement ps=null;
		int sno=0;
		String sname=null;
		String sadd=null;
		int i=1;
		int result=0;
		
		try {
			sc=new Scanner(System.in);
			 //Read the Input from user!
			 System.out.println("Enter How many Record! you can Register:");
			 norecord=sc.nextInt();
			//Register the JDBC Driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Established the Connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince", "prince");
			
			//Create the Prepared Statement object
			 
			if(con!=null) {
				ps=con.prepareStatement(query);
			}
			if(ps!=null) {
				//Read the All information of the EndUser
				for(i=1;i<=norecord;i++)
				{
				System.out.println("Enter "+i+" Student Details:");
				System.out.println("Enter Student SNO:");
				sno=sc.nextInt();
				System.out.println("Enter Student Name:");
				sname=sc.nextLine();
		        sc.nextLine();
				System.out.println("Enter Student Address:");
				sadd=sc.nextLine();
				
				//Set the Recored of the Query
				ps.setInt(1,sno);
				ps.setString(2, sname);
				ps.setString(3, sadd);
				
				//invoked the Excecute the Query of the JDBC
				//Execute the SQL Query
				 result=ps.executeUpdate();
				 //Check the Query is inserted and not
			    if(result==0)
			    	System.out.println(i+".Student Details are not Inserted!");
			    else
			    	System.out.println(i+".Student Details are Inserted!");
				
				}
				
			}
			//You also check all the Record Inserted or not
			if(norecord==(--i))
				System.out.println("Successfully All Record are Inserted!");
			
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
        		if(ps!=null)
        			ps.close();
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
        }//finally
	}//main

}//class
