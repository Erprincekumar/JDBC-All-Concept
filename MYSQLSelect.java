package PreparedStatemetCommand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MYSQLSelect {
	public static void main(String[] args) {
		
		int dept=0;
		Connection con=null;
		String query=null;
		PreparedStatement ps=null;
		  ResultSet rs=null;
		  int count =0;
		try {
			
			System.out.println("prince");
			//Register the JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			System.out.println("prince");

			System.out.println("prince");
			//Established the Connectin 
			con=DriverManager.getConnection("jdbc:mysql:///prince","root","pk12345");
			// prepare the Qury

			System.out.println("prince");
			query=" select *from student";
			//Create the PreparedStatement
			 ps=con.prepareStatement(query);
			
		 if( ps!=null) {
				
				
				//Execute the SQL query to update
				rs=ps.executeQuery();
				
			}
			if(rs!=null) {
				while(rs.next()) {
					count=1;
					System.out.println(rs.getString(1)+"\t"+rs.getInt(3));
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
        	
        }//finally

	}
}



