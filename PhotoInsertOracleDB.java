package PreparedStatemetCommand;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PhotoInsertOracleDB {

	public static void main(String[] args) {
		    Scanner sc=null;
		    int no=0;
		    String name=null,photopath=null;
		    float salery=0.0f;
		    File file=null;
		    InputStream is=null;
		    Connection con=null;
		    PreparedStatement ps=null;
		    int result=0;
		    long length=0;
         try {
			//Read the Input values from the user
			  sc=new Scanner(System.in);
			  if(sc!=null)
			  {
			  System.out.println("Enter eid::");
			  no=sc.nextInt();
			  System.out.println("Enter Name:");
			  name=sc.next();
			  System.out.println("Enter Salery:");
			  salery=sc.nextFloat();
			  System.out.println("ENter Photopath:");
			  photopath=sc.next();
			  }//if
			 //Creating InputStream by locating file based on photopath
		      file=new File(photopath);
			   length = file.length();
			   is=new FileInputStream(file);
			  //E:\DBImages\2.jpg
			  
			  
			 //Register JDBC Driver
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 //Established the Connection 
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			 //Create PreparedStatement
			 if(con!=null)
			 {
				ps=con.prepareStatement("insert into empall values(?,?,?,?)"); 
				//Set values from Query parameter
				ps.setInt(1, no);
				ps.setString(2, name);
				ps.setFloat(3, salery);
				ps.setBinaryStream(4, is, (int)length);
				}
			//execute the SQL query
			 if(ps!=null)
			 {
				result=ps.executeUpdate(); 
			 }
			 if(result==0)
				 System.out.println("Record not inserted!");
			 else
				 System.out.println("Record is Inserted Successfully");
			 
		}//try
         catch(SQLException se) {
        	 se.printStackTrace();
         }
         catch(ClassNotFoundException cnf) {
        	 cnf.printStackTrace();
         }
		catch(Exception e)
		{
			e.printStackTrace();
		}
         finally {
        	 //close jdbc object
        	 try {
        		 if(ps!=null)
            		 ps.close(); 
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
        	 try {
        		 if(sc!=null)
            		 sc.close(); 
        	 }
        	catch(Exception se)
         {
        	 se.printStackTrace();
         }
	}

	}

}
