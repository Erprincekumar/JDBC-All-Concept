package SelectCommand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateTableTest {
	
	public static void main(String args[]) {
	
		
		
		 Connection con=null;
		 Statement st=null;
		 String query=null;
		 int count=0;
		try {
		//Register the JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Established the Connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			
			//Create Ststement object
			if(con!=null) {
				
				st=con.createStatement();
				
			}
			//preapre the SQL query
			// create table student(sid number(10),sname varchar2(10),sadd varchar2(20));
			
            query="create table temp(sid number(10),sname varchar2(10),sadd varchar2(20))";
            System.out.println(query);
            
            //Send and execute the SQL query into the DAtabase
            
            if(st!=null) {
            	 count=st.executeUpdate(query);
            }
            if(count==0)
            {
            	System.out.println("Table created successfully");
            	
            }
            else
            {
            	System.out.println("Table is not created!:");
            }
            
		}//try
		catch(SQLException s) {
			s.printStackTrace();
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
				if(st!=null) {
					st.close();
				}
			}
			catch(SQLException s) {
				s.printStackTrace();
			}
			try {
				if(con!=null) {
					con.close();
				}
			}
			catch(SQLException s) {
				s.printStackTrace();
			}
			
			
			
		}
		
		
	}

}
