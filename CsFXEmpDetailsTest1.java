package CallableStatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
/* SQL> ed
Wrote file afiedt.buf

  1  create or replace function FX_GET_EMP_DETAILS(no in number,name out
  2     varchar,salery out number)return varchar as
  3      desg varchar(10);
  4      begin
  5      select ename,sal,job into name,salery,desg from emp where empno=no;
  6     return desg;
  7*    end;
SQL> /

Function created.

SQL> commit;

Commit complete.
  */
public class CsFXEmpDetailsTest1 {
    private static final String call_fax="{?=call FX_GET_EMP_DETAILS(?,?,?)}";
	public static void main(String[] args) {
	      Scanner sc=null;
	      Connection con=null;
	       int no=0;
	       CallableStatement cs=null;
	       String desg=null;
	       String name=null;
	       try {
			sc=new Scanner(System.in);
			//read the inputes from end userd
			if(sc!=null)
			{
				System.out.println("Enter Emp no:");
				no=sc.nextInt();
			}//if
			//Register the JDBC Drover
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Established the Connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			//Create the CallabeStatement object
			if(sc!=null) {
				cs=con.prepareCall(call_fax);
			}
			//Register the outparams,and return
			if(cs!=null)
			{
				cs.registerOutParameter(1,Types.VARCHAR);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4,Types.VARCHAR);
			}
			//Set the IN parameters values in the PL/Sql
			if(cs!=null) {
				cs.setInt(2, no);
			}
			//execute the Pl/sql query
			if(cs!=null) {
				cs.execute();
			}
			//Gather the result From the out parameter
			if(cs!=null)
			{
				System.out.println(cs.getString(1)+"\t"+cs.getString(3)+"\t"+cs.getString(4));
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
		}//finally

	}

}
