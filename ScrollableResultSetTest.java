package CallableStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableResultSetTest {

	public static void main(String[] args) {
		  Connection con=null;
		  Statement st=null;
		  ResultSet rs=null;
		try {
			//Register the JDBc Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Established the Connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			//Create the Statement object
			if(con!=null)
			{
				st=con.createStatement(1005,1008);
			}
			//Create ResultSet object
			if(st!=null)
				rs=st.executeQuery("select *from student");
			if(rs!=null)
			{
				while(rs.next())
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
			}
			 rs.afterLast();//Its is a same used in the class
			System.out.println("==================================");
			while(rs.previous())
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
			rs.first();//its allocate the First record
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
			
			rs.last();//its allocate the First record
			System.out.println(rs.getRow()+"\t"+rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
			
			 rs.absolute(-3);//its allocate the First record
			System.out.println(rs.getRow()+"\t"+rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
			
			
			 rs.absolute(3);//its allocate the First record
				System.out.println(rs.getRow()+"\t"+rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
		
				 rs.relative(3);//its allocate the First record
					System.out.println(rs.getRow()+"\t"+rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
					
					 rs.relative(-3);//its allocate the First record
						System.out.println(rs.getRow()+"\t"+rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
								
				
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
		}
        catch(Exception e) {
        	e.printStackTrace();
        }
		finally {
			//Close the jdbc object
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
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
		}
	}

}
