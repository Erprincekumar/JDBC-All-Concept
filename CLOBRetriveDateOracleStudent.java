package PreparedStatemetCommand;

import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CLOBRetriveDateOracleStudent {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Reader reader=null;
		Writer writer=null;
		char[]buffer=null;
		int charRead=0;
		try {
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("Enter Student no:");
				no=sc.nextInt();
				
			}
			//Register JDBC oboject
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Established the Connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			//Create PreparedStatemet objerct
			if(con!=null)
				ps=con.prepareStatement("select *from studentAll where sno=?");
			//Set param values
			if(ps!=null) {
				ps.setInt(1, no);
				//Execute the Sql query
				rs=ps.executeQuery();
			}
			//Process the ResultSet object(Read the CLOB values)
			if(rs!=null) {
				if(rs.next())
				{
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
				reader=rs.getCharacterStream(4);
				}
			}
			//Create output Stream from best file
			writer=new FileWriter("E:\\pk.txt");
			//Write Buffer based logic to copy file Contant
			buffer=new char[2048];
			while((charRead=reader.read(buffer))!=-1)
			{
				writer.write(buffer,0,charRead);
				System.out.println("CLOB retrived :");
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
