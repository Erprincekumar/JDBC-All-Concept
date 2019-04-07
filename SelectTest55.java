//Write a JDBC code 
package SelectCommand;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**(Write a JDBC App that gives employee details who has haviing the nth highest salery in the emplyee);
 * @prince
 * version :1.8
 */
public class SelectTest55 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String query=null;
		boolean flag=false;
		 int nth=0;
		
		try {
			
			//Take input details from the user
			sc=new Scanner(System.in);
			System.out.println("Enter the Nth Highest salery:");
			 nth=sc.nextInt();
			System.out.println("prince");
			//Register the Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Established the connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "prince","prince");
			
			//Create the Statement object
			if(con!=null)
				st=con.createStatement();
			
			//Prepared the Query
			//select deptno,ename,sal,job from emp e1 where 2=(select count(distinct(sal)) from emp e2 where e2.sal>=e1.sal)
		          query="select deptno,ename,sal,job from emp e1 where "+nth+"=(select count(distinct(sal)) from emp e2 where e2.sal>=e1.sal)";
			  System.out.println(query);
            
			//Send and execute the Query
			if(st!=null)
				
				rs=st.executeQuery(query);
			  System.out.println("prince");
			//processing the ResultSet object
			if(rs!=null) {
				
				while(rs.next())
				{
					flag=true;
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getString(4));
				}
				if(flag==false)
					System.out.println("No Record found!");
			}
			
			
		}
		//Handle the Exception explecitly
		catch(SQLException s) {
			s.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
			//close the connection of the object
			try {
				if(rs!=null)
				{
					rs.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(st!=null)
				{
					st.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con!=null)
				{
					con.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(sc!=null)
				{
					sc.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

}
