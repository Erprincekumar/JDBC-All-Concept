package CallableStatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/*
  ed
Wrote file afiedt.buf

  1  create or replace function GET_FX_STUDNET_FOR_DELETION(no in number
  2                                                       ,cnt out number)return sys_refcursor as
  3  details sys_refcursor;
  4  begin
  5  open details for
  6               select *from student where sid=no;
  7             delete from student where sid=no;
  8       cnt:=SQL%ROWCOUNT;
  9         return details;
 10*  end;
SQL> /

Function created.  */
public class CsFxTest2 {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		int result=0;
		try {
			//Read the values from the enduser
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter the Student no:");
				no=sc.nextInt();
			}
			//Register the JDBC
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Established the Connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			//Create a CallableObject
			if(con!=null) {
				cs=con.prepareCall("{?=call GET_FX_STUDNET_FOR_DELETION(?,?)}");
			}
			//Register out parameter with JDBC types
			if(cs!=null)
			{
		       cs.registerOutParameter(1,OracleTypes.CURSOR);
		       cs.registerOutParameter(3, Types.INTEGER);
			}
			//Set the Values IN parameter
			if(cs!=null)
			{
				cs.setInt(2,no);
			}
			//Executer the PL/SQL Query of function 
			if(cs!=null) {
				cs.execute();
			}
			//Gather the Results of the OUt
			if(cs!=null)
			{
				rs=(ResultSet)cs.getObject(1);
			}
			//process the ResultSet object
			if(rs!=null)
			{
				while(rs.next())
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
			}
			//Result Deleted record are Effected
			result=cs.getInt(3);
			if(result==0)
			{
				System.out.println("Record are not Deleted:"+result);
			}
			else
				System.out.println("Record are Deleted:"+result);
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
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
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
