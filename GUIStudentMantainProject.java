package CallableStatement;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*
 SQL> create table all_student(sno number(5),sname varchar2(20),sadd varchar2(20));

Table created.

SQL> desc all_student;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 SNO                                                NUMBER(5)
 SNAME                                              VARCHAR2(20)
 SADD                                               VARCHAR2(20)
 
 */
public class GUIStudentMantainProject extends JFrame implements ActionListener{
	//All the Components are Decleared is inside the JFrame class
	   JLabel lno,lname,laddr;
       JTextField tno,tname,taddr;
       JButton binsert,bupdate,bview,bdelete;
       Connection con;
       PreparedStatement ps1,ps2,ps3,ps4;
       ResultSet rs;
	public GUIStudentMantainProject() {
		//Set component only for view
		System.out.println("GUIStudentMantainProject.0 Construceert");
		setTitle("Student Details Maintain");
		setSize(200,300);
		setLayout(new FlowLayout());
		lno=new JLabel("Sno");
		add(lno);
		tno=new JTextField(10);
		add(tno);
		
		lname=new JLabel("Sname");
		add(lname);
		tname=new JTextField(10);
		add(tname);

		laddr=new JLabel("Saddr");
		add(laddr);
		taddr=new JTextField(10);
		add(taddr);
		//Add the Component of button
		 binsert=new JButton("Insert");
		 binsert.addActionListener(this);
		 add(binsert);
		 bupdate=new JButton("Update");
		 bupdate.addActionListener(this);
		 add(bupdate);
		 bview=new JButton("View");
		 bview.addActionListener(this);
		 add(bview);
		 bdelete=new JButton("Delete");
		 bdelete.addActionListener(this);
		 add(bdelete);
		
		//Set visibility
		setVisible(true);
		//Call the helper method initilized
		initilized();
	}
	
      private void initilized() {
    	  System.out.println("GUIStudentMantainProject.initilized()");
    	  try {
    		//Register the JDBC Driver
    		  Class.forName("oracle.jdbc.driver.OracleDriver");
    		  //Established the Connection
    		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
    		  //Create the preparedStatement object
    		  if(con!=null)
    			  ps1=con.prepareStatement("Insert into all_student values(?,?,?)");
    		  if(con!=null)
    		  {//SQL> update all_student set sname='Deepak',sadd='Bihar' where sno=101;
    			  ps2=con.prepareStatement("update all_student set sname=? and sadd=? where sno=?");
    		  }
    		  if(con!=null)
    			  ps3=con.prepareStatement("select sname,sadd from all_student where sno=?");
    		  if(con!=null)
    		  {
    			 ps4=con.prepareStatement("delete all_student where sno=?"); 
    		  }
    	  }
    	  catch(Exception e) {
    		  e.printStackTrace();
    	  }
    	  
    	  
      }
	public static void main(String[] args) {
	   System.out.println("GUIStudentMantainProject.main() start"); 
         new GUIStudentMantainProject();
         System.out.println("GUIStudentMantainProject.main() end");
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		int no=0;
		String name=null,addr=null;
		int result=0;
		System.out.println("GUIStudentMantainProject.actionPerformed()");
		if(binsert==ae.getSource())
		{
			System.out.println("Insert button is clicked!");
			try {
			//Read the values inside the text
			no=Integer.parseInt(tno.getText());
			name=tname.getText();
			addr=taddr.getText();
			//Set the  values in the prepared Query 1
			 ps1.setInt(1, no);
			 ps1.setString(2,name);
			 ps1.setString(3,addr);
			 //Execute the Query
			 result=ps1.executeUpdate();
			 if(result!=0)
			 {
				 System.out.println("Record Inserted Successfully");
			 }
			 else
			 {
				 System.out.println("Record insert Failed!");
			 }
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		else if(bupdate==ae.getSource())
		{
			System.out.println("Update button is clicked");
			try {
				//read the values from the Textbox
				no=Integer.parseInt(tno.getText());
				name=tname.getText();
				addr=taddr.getText();
				//Set the Values in the querty parameter
				ps2.setInt(3,no);
				ps2.setString(2, addr);
				ps2.setString(1,name);
				//Execute the Query
				result=ps2.executeUpdate();
				//Gather the Result
				if(result!=0)
					System.out.println("Record is Updated Successfully");
				else
					System.out.println("Record is Update Failed!");
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		else if(bview==ae.getSource()) {
			System.out.println("View Button is clicked!");
			try {
				//Read the Input from sno textbox
				no=Integer.parseInt(tno.getText());
				//Set the no in the Query parameter
				ps3.setInt(1,no);
				//Create the ResultSet object and execute the Query
				if(ps3!=null)
				{
				  rs=ps3.executeQuery();	
				}
				if(rs!=null) {
					if(rs.next())
					{
						tname.setText(rs.getString(1));
						taddr.setText(rs.getString(2));
						System.out.println("Record is Display");
					}
					else
						System.out.println("Record is not Found!");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Delete Button is clicked!");
			try {
				//Read the input from Sno text box
				no=Integer.parseInt(tno.getText());
				//Set the Query parameter
				ps4.setInt(1, no);
				//Execute the Query
				result=ps4.executeUpdate();
				if(result==0)
					System.out.println("Record Deletion Failed!");
				else
					System.out.println("Record Delete Successfully");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
