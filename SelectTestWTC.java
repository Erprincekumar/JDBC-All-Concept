package SelectCommand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTestWTC {

	public static void main(String[] args) {
		
		try{
			//Register the JDBC Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince", "prince")){
				if(con!=null) {
					try(Statement st=con.createStatement()){
						if(st!=null) {
							try(ResultSet rs=st.executeQuery("SELECT *FROM STUDENT")){
								if(rs!=null) {
									boolean flag=false;
									while(rs.next()) {
										flag=true;
										System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
									}
									if(flag==false)System.out.print("Record not found!");
								}
								
								
							}
						}
						
					}
				}
			}
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
			
		 

	}

}
