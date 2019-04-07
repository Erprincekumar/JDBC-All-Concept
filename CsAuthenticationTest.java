package CallableStatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
/*
 SQL> ed
Wrote file afiedt.buf

  1  create or replace procedure p_Authentation(user in varchar,pass in varchar,result out varchar)as
  2  cnt number(5);
  3  begin
  4   select count(*) into cnt from userlist where uname=user and password=pass;
  5   if(cnt<>0)then
  6   result:='Valid Credentils';
  7  else
  8   result:='Invalid Credentils';
  9  end if;
 10* end;
SQL> /

Procedure created.

 */

 public class CsAuthenticationTest {

	 public static void main(String[] args) {
		Scanner sc=null;
		String user=null;
		String pass=null;
		Connection con=null;
        CallableStatement cs=null;
       
		
		try {
			 sc=new Scanner(System.in);
			 if(sc!=null) {
				System.out.println("Enter Userid:");
				user=sc.next();
				System.out.println("Enter password:");
				pass=sc.next();
			}
			//Register the JDBC Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Established the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			//Create the CallebalStatement
			if(con!=null) {
				cs=con.prepareCall("{call p_Authentation(?,?,?)}");
			}
			//Register OUT Parameters  With jDBC parameters
			if(cs!=null) {
				cs.registerOutParameter(3,Types.VARCHAR);
			}
			//set the IN parameter of JDBC object
			if(cs!=null) {
				cs.setString(1,user);
				cs.setString(2,pass);
			}
			//Execute the PL/SQL procedure
			if(cs!=null) {
				cs.execute();
			}
			//Gather the Result from out parameter
			if(cs!=null)
				System.out.println(cs.getString(3));
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
			//close the jdbc object
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
		}//finally
	 }//main
	}//class
