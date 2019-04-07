package PreparedStatemetCommand;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ClobInsertStudentRecord {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String name=null,addrs=null,resumepath=null;
		Connection con=null;
		PreparedStatement ps=null;
		File file=null;
		Reader reader=null;
		int result=0;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter no:");
				no=sc.nextInt();
				System.out.println("Enter Name:");
				name=sc.next();
				System.out.println("Enter addrss:");
				addrs=sc.next();
				System.out.println("Enter resume path:");//you musttype this code
				resumepath=sc.next();
			}
			//Register the JDBC Drver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Established the Connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			//Create preparedStatemet object
			if(con!=null) 
				ps=con.prepareStatement("insert into studentAll values(?,?,?,?)");
			//Create Reader object hold resume (CLOB values);
			file=new File(resumepath);
			reader=new FileReader(file);
			//Set values to Query parems
			if(ps!=null) {
				ps.setInt(1, no);
				ps.setString(2,name);
				ps.setString(3, addrs);
				ps.setCharacterStream(4, reader,(int)file.length());
			}
			//Execute the Query
			if(ps!=null)
				result=ps.executeUpdate();
			//Process the Result
			if(result==0)
				System.out.println("Record not insert:");
			else
				System.out.println("Record Inserted Successfully");
		
			
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
			//close the jdbc object
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
		}

	}

}
