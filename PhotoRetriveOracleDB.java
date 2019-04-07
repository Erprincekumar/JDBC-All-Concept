package PreparedStatemetCommand;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PhotoRetriveOracleDB {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		OutputStream os=null;
		InputStream is=null;
		byte[]buffer=null;
		int byteRead=0;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Employee no:");
				no=sc.nextInt();
			}
			//Register the JDBC Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Established the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "prince", "prince");
			
			//Create PrepareStatement object
			if(con!=null)
			{
				ps=con.prepareStatement("select *from empall where empno=?");
			}
			//set parameter values
			if(ps!=null)
			{
				ps.setInt(1, no);
				
			// Execute the SqlQuery
				rs=ps.executeQuery();
			}
			if(rs!=null)
			{
				if(rs.next())
				{
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3));
					
				}
			}
			//Create OutputStream object Dist file
			os=new FileOutputStream("E:\\photo\\photos.gif");
			//Write Buffered Based logic to copy the file
			buffer=new byte[4096];
			while((byteRead=is.read(buffer))!=-1)
			{
			os.write(buffer,0,byteRead);
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
			catch(Exception e) {
				e.printStackTrace();
			}
		}
     
	}

}
