package CallableStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SensitiveResultSetApp {

	public static void main(String[] args) {
	Connection con=null;
	Statement st=null;
	ResultSet rs=null;
		 try {
			 //Register the JDBc object
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 //Established the Connection 
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince", "prince");
			 //Create Statemet object
			 st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			 //Create ResultSet object
			 rs=st.executeQuery("select sid,sname,sadd from student");
			 //Gather the ResultSet
			 if(rs!=null)
			 {
				 Thread.sleep(3000);
				 while(rs.next())
				 {
					
					 System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
				 }
			 }
		 }
		 catch(SQLException se){
			 se.printStackTrace();
		 }
		 catch(ClassNotFoundException cnf) {
			 cnf.printStackTrace();
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 finally {
			 //close the jdbc object
			  try {
				 if(rs!=null) 
					 rs.close();
				 }
				 catch(SQLException se) {
					 se.printStackTrace();
				 }
			  try {
					 if(st!=null) 
						 st.close();
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
			 }
		 }
		 

	}


