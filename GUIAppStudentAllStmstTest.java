package CallableStatement;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/* SQL> create table AllStudent(sno number(10),sname varchar2(20),marks1 number(10)
 * ,marks2 number(10),marks3 number(10));      Table created.  
 * SQL> insert into allstudent values(105,'Pritee',34,26,75);    1 row created.*/
/*SQL> ed
Wrote file afiedt.buf

  1  create procedure find_pass_fail(m1 in number,m2 in number,m3 in number,result out varchar)as
  2  begin
  3  if(m1<35 or m2<35 or m3<35)then
  4   result:='Fali';
  5  else
  6  result:=' pass';
  7  end if;
  8* end;
SQL> /

Procedure created.

 */
public class GUIAppStudentAllStmstTest extends JFrame implements ActionListener,WindowListener{
    private JLabel  lno,lname,lm1,lm2,lm3,lresult;
    private JComboBox tno;
    private JTextField tname,tm1,tm2,tm3,tresult;
    private JButton bdetails,bresult;
    Connection con=null;
    Statement st=null;
    PreparedStatement ps=null;
    ResultSet rs1,rs2;
    CallableStatement cs=null;
    
	public GUIAppStudentAllStmstTest() {
	 System.out.println("GUIAppStudentAllStmstTest.0 parameter Constructer");
	 setTitle("Student Management Details Marks:");
	 setSize(500,600);
	 setLayout(new FlowLayout());
	 setBackground(Color.green);
	 //Add components in the project
	 lno=new JLabel("Student ID:");
	 add(lno);
	 tno=new JComboBox();
	 add(tno);
	 bdetails=new JButton("Details");
	 bdetails.addActionListener(this);
	 add(bdetails);
	 lname=new JLabel("Name");
	 add(lname);
	 tname=new JTextField(10);
	 add(tname);
	 
	 lm1=new JLabel("Marks1");
	 add(lm1);
	 tm1=new JTextField(10);
	 add(tm1);
	 lm2=new JLabel("Marks2");
	 add(lm2);
	 tm2=new JTextField(10);
	 add(tm2);
	 lm3=new JLabel("Marks3");
	 add(lm3);
	 tm3=new JTextField(10);
	 add(tm3);
	 bresult=new JButton("Result");
	 bresult.addActionListener(this);
	 add(bresult);
	 lresult=new JLabel("Result");
	
	 add(lresult);
	 tresult=new JTextField(10);
	 add(tresult);
	 //Disable All the TextField
	 tname.setEditable(false);
	 tm1.setEditable(false);
	 tm2.setEditable(false);
	 tm3.setEditable(false);
	 tresult.setEditable(false);
	 setVisible(true);
	 
	 initilized();	
	}
	//Helper method of constructer
	private void initilized() {
		System.out.println("GUIAppStudentAllStmstTest.initilized()");
		try {
			//Register the JDBC Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Established the Connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			//Create Statement object
			st=con.createStatement();
			//Execute the SQL query
			rs1=st.executeQuery("select  sno from allstudent");
			//Gather the Result From ResultSet object and paste the Combobox 
			if(rs1!=null)
			{
			 while(rs1.next())
			   tno.addItem(rs1.getInt(1));
			}
			//Create preparedStatement object
			ps=con.prepareStatement("select sname,marks1,marks2,marks3 from allstudent where sno=?");
			//Create CallableStatement object
			cs=con.prepareCall("{call find_pass_fail(?,?,?,?)}");
			//Register the OUT parameter of the Procedure
			cs.registerOutParameter(4,Types.VARCHAR);
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

	public static void main(String[] args) {
	System.out.println("GUIAppStudentAllStmstTest.Start main Method()");
	new GUIAppStudentAllStmstTest();
	System.out.println("GUIAppStudentAllStmstTest.ENd main()");

	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		int no=0;
		int m1=0,m2=0,m3=0;
		String result=null;
		System.out.println("GUIAppStudentAllStmstTest.actionPerformed()");
		if(ae.getSource()==bdetails)
		{
			System.out.println("Details button is clicked!");
			try {
				//Get the Selected item in the Combobox
			     no=(Integer)tno.getSelectedItem();	
			     //Set values to the Query param
			      ps.setInt(1, no);
			      //Execute the Quert
			       rs2=ps.executeQuery();
			      //Set the All recored in the ResultSet to Textbox
			       if(rs2!=null) {
			    	   if(rs2.next())
			    	   {
			    		   tname.setText(rs2.getString(1));
			    		    tm1.setText(rs2.getString(2));
			    		    tm2.setText(rs2.getString(3));
			    		    tm3.setText(rs2.getString(4));
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
		else
		{
			
			System.out.println("Result Button is clicked");
			try {
				//Read the Values form the TestBox marks1, marks2,marks3 also and pass the values
				m1=Integer.parseInt(tm1.getText());
				m2=Integer.parseInt(tm2.getText());
				m3=Integer.parseInt(tm1.getText());
				//Set the values the Callable Statement parameter of procedure
				 cs.setInt(1, m1);
				 cs.setInt(2, m2);
				 cs.setInt(3,m3);
				 //Execute the Query
				 cs.execute();
				 //Gather the Result from out parameter
				 result=cs.getString(4);
				 //Set the Result in the Textbox of Result pass and fail
				 tresult.setText(result);
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		try {
			if(rs2!=null)
				rs2.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			if(rs1!=null)
				rs1.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			if(cs!=null)
				cs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			if(ps!=null)
				ps.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			if(st!=null)
				st.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			if(con!=null)
				con.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
