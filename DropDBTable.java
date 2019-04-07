package SelectCommand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DropDBTable {
	public static void main(String args[]) {
		Scanner sc=null;
		String table=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		int count=0;
		try {
			//read the input from end user
			sc=new Scanner(System.in);
			if(sc!=null)
			{
			System.out.print("Enter table Name:");
			table=sc.next();
			}
			//convert the Condition
			
			//Register the Driver
             Class.forName("oracle.jdbc.driver.OracleDriver");
    		  
    		  //Established the Conection:
    		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
    		  
    		  //Create the Statement object
    		  if(con!=null)
    			  st=con.createStatement();
    		  //preared the SQL Query
    		  //drop table test;
    		  query="drop table "+table+"";
    		  System.out.print(query);
    		  //send and Execute the Executer query
    		  if(st!=null)
    		  {
    			  count=st.executeUpdate(query);
    		  }
    		  //processing the Result of the And display
    		  if(count==0) {
    			  System.out.println("Table is not drop:");
    		  }
    		  else
    		  {
    			  System.out.println("Table is droped Successfully:");
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
    	  
    	  //close the Connection in the Result
    	      try {
    		      if(st!=null)
    			  st.close();
    	       }
    		  catch(SQLException e)
    		  {
    			  e.printStackTrace();
    		  }
    	      try {
    		      if(con!=null)
    			  con.close();
    	       }
    		  catch(SQLException e)
    		  {
    			  e.printStackTrace();
    		  }
    	      try {
    		      if(sc!=null)
    			  sc.close();
    	       }
    		  catch(Exception e)
    		  {
    			  e.printStackTrace();
    		  }
    	 }//finally

		}

}
