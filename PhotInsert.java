package ProjectJDBC;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PhotInsert {
 private static final String INSERt_EMP_RECORD="insert into empall values(?,?,?,?)";
	public static void main(String[] args) {
	   Scanner sc=null;
	   int empno=0;
	   String ename=null;
	   double esalery=0.0;
	   String photopath=null;
	   File file=null;
	   InputStream is=null;
	   
	   Connection con=null;
	   PreparedStatement ps=null;
	   int count=0;
		try {
			sc=new Scanner(System.in);
			//Read the input values from the End user
			if(sc!=null)
			{	
			System.out.println("Enter no:");
			empno=sc.nextInt();
			System.out.println("Enter Name:");
			ename=sc.next();
			System.out.println("Enter salery:");
			esalery=sc.nextDouble();
			System.out.println("Enter Employee photo path:");
			photopath=sc.next();
			}
			//Create input stream by locating File based on photo path it means conversion of the byte code
			file=new File(photopath);
			System.out.println("Hello");
			if(file!=null)
			is=FileInputStream(file);
			System.out.println("Hello prince");
			//Register the JDBC Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Stablished the Connection
	        con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
	        //Create preparedStatement object
	        if(con!=null)
	        	ps=con.prepareStatement(INSERt_EMP_RECORD);
			if(ps!=null)
			{
				ps.setInt(1, empno);
				ps.setString(2, ename);
				ps.setDouble(3,esalery);
				ps.setBinaryStream(4,is,file.length());
			}
			//Excecute the Query of the prepared Statement object
			if(ps!=null)
				count=ps.executeUpdate();
			//Processing the Result
			if(count==0)
				System.out.println("Record is not Inserted:");
			else
				System.out.println("Record is Successfully Inserted!");
				
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
			//Close the jDBc connection 
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
			try {
				if(is!=null)
					is.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}//Finally

	}//main
	private static InputStream FileInputStream(File file) {
		// TODO Auto-generated method stub
		return null;
	}
	

}//Class
