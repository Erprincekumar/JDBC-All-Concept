package SelectCommand;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteTest {  //Write a query to delete the Record of the table

	public static void main(String[] args) {
		 Scanner sc=null;
		 int no=0;
		 Connection con=null;
		 Statement st=null;
		 String query=null;
		 int count=0;
      try {
    	  // Read the Values of end user 
    	  sc=new Scanner(System.in);
    	  if(sc!=null)
    	     {
    		  System.out.println("Enter student no:");
    		  no=sc.nextInt();
    	    }  
    		  //Register the Driver
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  
    		  //Established the Conection:
    		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
    		  
    		  //Create the Statement object
    		  if(con!=null)
    			  st=con.createStatement();
    		  //preared the SQL Query
    		  //delete from student where sid=101;
    		  query="DELETE FROM STUDENT WHERE SID="+no;
    		  //send and Execute the Executer query
    		  if(st!=null)
    		  {
    			  count=st.executeUpdate(query);
    		  }
    		  //processing the Result of the And display
    		  if(count==0) {
    			  System.out.println("Not Record delete in the DB:");
    		  }
    		  else
    		  {
    			  System.out.println(count+" Record delete update in the Database:");
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
        
	}	//main 

	}//class


