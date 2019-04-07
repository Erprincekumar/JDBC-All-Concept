package CallableStatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/* SQL> ed
Wrote file afiedt.buf

1  create or replace procedure p_get_EmpDetails(no in number,name out varchar,salery out number,
2   job out varchar)as
3  begin
4    select ename,sal,job into name,salery,job from emp where empno=no;
5* end;
SQL> /

Procedure created.*/
public class Cs_EmployeeDetailsTest {

   private final static String Employee_Details= "{call p_get_EmpDetails(?,?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		try {
			//read the input values
			sc=new Scanner(System.in);
			if(sc!=null)
			{
			System.out.println("Enter the Empno:");
			no=sc.nextInt();
			}
			//Register the JDBC obj
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establihed the Connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			//Create CallableStatement object
			if(con!=null)
				cs=con.prepareCall(Employee_Details);
			//Register OUT parameter with JDBC types
			if(cs!=null) {
				cs.registerOutParameter(2,Types.VARCHAR);
				cs.registerOutParameter(3,Types.NUMERIC);
				cs.registerOutParameter(4,Types.VARCHAR);
			}
			//Set the IN parameter values in the Query
			if(cs!=null) {
				cs.setInt(1, no);
			}
			//Exceut the Query & call the pl/sql procedure
			if(cs!=null)
				cs.execute();
			//Gather result form out params
			if(cs!=null) {
				System.out.println("Empno:"+no);
				System.out.println("ename:"+cs.getString(2));
				System.out.println("Salery:"+cs.getDouble(3));
				System.out.println("Job:"+cs.getString(4));
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
