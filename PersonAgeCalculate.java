package DateFormateInser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonAgeCalculate {
	private static final String PERSON_AGE_CALCU="select (sysdate-dob)/365 from person_tab where no=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//Read the Input of the End user
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("Enter Person ID:");
				no=sc.nextInt();
			}
			
			//Register the JDBC Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Established the Connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			//Create the PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(PERSON_AGE_CALCU);
			 if(ps!=null)
				 ps.setInt(1, no);
			//Execute the Query
			if(ps!=null)
				rs=ps.executeQuery();
			//processing the ResultSet object in the our appaleiction
			if(rs!=null) {
				rs.next();
				System.out.println(rs.getInt(1));
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
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception se)
			{
				se.printStackTrace();
			}
			
		}//finally
    
	}//main

}//class
