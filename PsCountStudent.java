package PreparedStatemetCommand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PsCountStudent {//Count the Number of student Avilable n the Particular table
   //You can used the PreparedStatement Object Any Query AS will wish
	public static void main(String[] args) {
		 Connection con=null;
		 PreparedStatement ps=null;
		 String query=null;
		 int count=0;
		try {
			//Register the JDBC Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Established the Connectiin 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			
			//Prepared the Sql QUERY
			query="SELECT COUNT(*) FROM STUDENT";
			//Create the PreparedStatement object
			if(con!=null) {
				ps=con.prepareStatement(query);
			}
			
			if(ps!=null) {
				count=ps.executeUpdate();
				System.out.println("Number of Studnet in table="+count);
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
        
        	
        }//finally

		

	}

}
