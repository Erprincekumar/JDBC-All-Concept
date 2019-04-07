package CallableStatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/* SQL> ed
Wrote file afiedt.buf
  1  create or replace procedure p_AllStudnetDetails(initchars in varchar,details out sys_refcursor)as
  2  begin
  3     open details for
  4    select *from student where sname like initchars;
  5* end;
SQL> /
Procedure created.
 */
public class CsCursorStudentDetials {
 
	public static void main(String[] args) {
		Scanner sc=null;
		String initchars=null;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		boolean flag=false;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.print("Enter Student name initCharacter:");
				initchars=sc.next();
			}
			//Register the JDBC object
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Established the Connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			//create the CallableStatement object
			if(con!=null)
			{
				cs=con.prepareCall("{call p_AllStudnetDetails(?,?)}");
			}
			//register OUT parmas with JDBC type
			   cs.registerOutParameter(2,OracleTypes.CURSOR);
			//Set IN param values
			   cs.setString(1, initchars+"%");
			 //Execute the PL/SQL procedure
			   cs.execute();
			 //Gather result from UOT param
			   rs=(ResultSet)cs.getObject(2);
			 //process the ResultSet of the query
			   if(rs!=null) {
				   while(rs.next())
				   {
					   System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
					   flag=true;
				   }
			   }
			  //Result is
			   if(flag==false) {
				   System.out.println("Record no found!");
			   }
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
		}

	}

}
