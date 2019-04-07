package PreparedStatemetCommand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectTest1 {

	public static void main(String[] args) {
		Scanner sc=null;
		int dept=0;
		Connection con=null;
		String query=null;
		PreparedStatement ps=null;
		  ResultSet rs=null;
		  int count =0;
		try {
			sc=new Scanner(System.in); 
			
			//Register the JDBC Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Established the Connectin 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			// prepare the Qury
			query=" select ename,job,sal from emp WHERE DEPTNO= ?";
			//Create the PreparedStatement
			 ps=con.prepareStatement(query);
			
			if(sc!=null && ps!=null) {
				System.out.println("Enter Departmentno:");
				dept=sc.nextInt();
				
				//set the Department no in the Query
				ps.setInt(1, dept);
				//Execute the SQL query to update
				rs=ps.executeQuery();
				
			}
			if(rs!=null) {
				while(rs.next()) {
					count=1;
					System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3));
				}
			}
			if(count==0)
				System.out.println("Record is not Found  in the Table!");
			
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
        	catch(SQLException se) {
        		se.printStackTrace();
        	}
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

		


	}

}
