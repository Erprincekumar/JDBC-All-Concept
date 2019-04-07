package SelectCommand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelectQuery {

	public static void main(String[] args) {
		Scanner sc=null;
		String query=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		boolean flag=false;
		int count=0;
		
		
		try {
			//Read input of end user
			sc=new Scanner(System.in);
			if(sc!=null)
			{
			System.out.print("Enter the Query:");
			query=sc.nextLine();
			} 
			//Register the Jdbc Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Established the connection
              con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
              
              //Create the Statement object
              if(con!=null) {
            	  st=con.createStatement();
              }
              //Send and execute sql query
              flag=st.execute(query);
              if(flag==true) {
            	  System.out.println("Seelct query is executed!");
            	  rs=st.getResultSet();
            	  //process the ResultSet
            	  while(rs.next()) {
            		  System.out.println(rs.getInt(1)+" \t"+rs.getString(2)+"\t"+rs.getString(3));
            	  }
              }
              else
              {
            	  System.out.println("Non-select Query is executed!");
            	  count=st.getUpdateCount();
            	  System.out.println("No of Record that are Affected"+count);
              }
               
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
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
			catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

	}//main

}//class
/*
      
      Simpaly write the query without any ""  double cote and ; semicolon
      Example=select *from student
      
      insert into student values(102,'prince','hyd')
      Enter
      
      
      
      
      
      */
