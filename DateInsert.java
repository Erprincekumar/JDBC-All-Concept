package DateFormateInser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateInsert {
   public static final String Date_Insert="INSERT INTO PERSON VALUES(?,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		int pid=0;
		String pname=null;
		String dob=null,doj=null,dom=null;
		SimpleDateFormat sdf1=null,sdf2=null;
		java.util.Date udob=null,udoj=null;
		java.sql.Date sqdob=null,sqdoj=null,sqdom=null;
		long ms1=0L, ms2=0l;
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		try {
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter no:");
				pid=sc.nextInt();
				System.out.println("Enter Person name:");
				pname=sc.next();
				System.out.println("Enter DOB(DD-MM-YYYY):");
				dob=sc.next();
				System.out.println("Enter DOJ(MM-DD-YYYY):");
				doj=sc.next();
				System.out.println("Enter DOM(yyyy-MM-DD):");
				dom=sc.next();
			}
			//Converting String Date values to java.SQL.Date class object
			// for DOB and DOJ in java.util.Date object
			sdf1=new SimpleDateFormat("DD-MM-YYYY");
			if(sdf1!=null)
			 udob=sdf1.parse(dob);//Gives java.util.Date object
			sdf2=new SimpleDateFormat("MM-DD-YYYY");
			if(sdf2!=null)
			 udoj=sdf1.parse(doj);//Gives java.util.Date object
          
			 //Converting java.util.Date to java.sql.Date Class oject
			if(udob!=null)
				ms1=udob.getTime();
			    sqdob=new java.sql.Date(ms1); //Gives java.sql.Date class object
			    
		  if(udoj!=null)
		  ms2=udoj.getTime();
		  sqdoj=new java.sql.Date(ms2); //Gives java.sql.Date class object
      
		   //For DOM
		    // It is formated way so it cannot be converted into the Other util formate it is Direct convert
		  // into the java.sql.Date Formate
		  sqdom=java.sql.Date.valueOf(dom);
		  
		 //Register the JDBC Driver
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  //Established the Connection
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","princce");
		  
		  //create prepareStatement object
		   if(con!=null)
			   ps=con.prepareStatement(Date_Insert);
		   //set Query values perpams
		   if(ps!=null) {
			   ps.setInt(1, pid);
			   ps.setString(2,pname);
			   ps.setDate(3,sqdob);
			   ps.setDate(4,sqdoj);
			   ps.setDate(5, sqdom);
		   }
		   //Excecute the Query
		   if(ps!=null) 
			   result=ps.executeUpdate();
		   
		   //process the Result
		   if(result==0)
			   System.out.println("Record  not inserted!");
		   else
			   System.out.println("Record Successfully Inserted!");
		}
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Record not inserted!");
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
