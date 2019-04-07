package CallableStatement;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MetaDataCapability {

	public static void main(String[] args) {
		Connection con=null;
		DatabaseMetaData dbmd=null;
		try {
			//Register the JDBC object
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Established the Connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			//Creata DatabaseMetaData object object
			dbmd=con.getMetaData();
			//Get the Databsae bject
			System.out.println("DataBase name:"+dbmd.getDatabaseProductName());
			System.out.println("DataBase Driver Name:"+dbmd.getDriverName());
			System.out.println(" maximum row sixe"+dbmd.getMaxRowSize());
			System.out.println("Catalog name:"+dbmd.getCatalogSeparator());
			System.out.println("Major version name"+dbmd.getDatabaseMajorVersion());
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		finally{
			//Close jDBC object
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
