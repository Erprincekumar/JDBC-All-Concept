package CallableStatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
/*
 SQL> create procedure first_pro(x in number,y out number)as
  2  begin
  3  y:=x*x;
  4  end;
  5  /

Procedure created.

SQL> commit;

Commit complete.
*/

public class CsTest1 {
  public static final String query="{call first_pro(?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		int result=0;
		try {
			//Read the input from end user
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("Enter a Values:");
				no=sc.nextInt();
			}
			//Register the JDBC Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Established rthe Connecrtion 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			//prepare the SqlQuery of the SQL procedure
			//  String query="{call first_pro(?,?)}";
			//Create the Callable Statement object
			if(con!=null)
			cs=con.prepareCall(query);
			//Register out perms with JDBC type
			if(cs!=null)
			  cs.registerOutParameter(2,Types.INTEGER);	
			//Set the IN Params values
			if(cs!=null)
				cs.setInt(1, no);
			//Executer the sQL Query Procedure
			if(cs!=null)
				cs.execute();
			//Process the Result of the OUT params
			if(cs!=null) {
				result=cs.getInt(2);
			}
			System.out.println("SQUARE Values:"+result);
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//Close the JDBC object Connecrtion 
			try {
				if(cs!=null)
					cs.close();
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
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception se) {
				se.printStackTrace();
			}
		}

	}

}
