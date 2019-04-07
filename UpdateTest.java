//Write A JDBC Application that update student details with given new Address based on the student no
package SelectCommand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {
	
	public static void main(String ars[]) {
		 Scanner sc=null;
		 int sid=0;
		 String newName=null;
		 String newAddrs=null;
		 Connection con=null;
		 Statement st=null;
		 String query=null;
		 int count=0;
		try {
			 //read inputs
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("Enter Existing student no to update:");
				sid=sc.nextInt(); // given student no
				System.out.print("Enter new Name of student:");
				newName=sc.next();//given new name raja
				System.out.println("Enter new Address of Student:");
				newAddrs=sc.next();// given new address bpl
			
			}
			//Conver input values as for required for SQL query
			newName="'"+newName+"'";  //given 'newraja'
			newAddrs="'"+newAddrs+"'";// given 'newbpl'
			
			//Register the JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Established the Connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			
			//Create Ststement object
			if(con!=null) {
				
				st=con.createStatement();
				
			}
			//preapre the SQL query
			// update student set  sname='Rahul', sadd='Bha' where sid=105;
			
            query="update student set  sname="+newName+",sadd="+newAddrs+" where sid="+sid; 
            System.out.println(query);
            
            //Send and execute the SQL query into the DAtabase
            if(st!=null) {
            	count=st.executeUpdate(query);
            }
            if(count==0)
            {
            	System.out.println("Record is not update:");
            	
            }
            else
            {
            	System.out.println("Record is Update in the Database:");
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
			
			try {
				if(sc!=null) {
					sc.close();
				}
			}
			catch(Exception s) {
				s.printStackTrace();
			}
		}
		
	}

}
