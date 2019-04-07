package PreparedStatemetCommand;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIFrontEndStudentRegistrationApp extends JFrame implements ActionListener,WindowListener{
	private static final String INSERT_GUISTUDENTRECORD_QUERY="insert into student values(seq_student.nextval,?,?)";
	JLabel lname,laddr,lresult;
	JTextField tname,taddr;
	JButton btn;
	Connection con;
	PreparedStatement ps;

	public GUIFrontEndStudentRegistrationApp() {
		System.out.println("GUIFrontEndStudentRegistrationApp.GUIFrontEndStudentRegistrationApp()");
		setTitle("Student Registation App");
		setSize(300,300);//How many row and column requirement;
		setLayout(new FlowLayout());
		//Add Component to Required to show in the GUI Applcation
	   lname=new JLabel("Student name:");
	  add(lname);
	  tname=new JTextField(10);
	  add(tname);
	  laddr=new JLabel("Student Address:");
	  add(laddr);
	  taddr=new JTextField(10);
	   add(taddr);
	   btn=new JButton("Register");
	   add(btn);
	   btn.addActionListener(this);//Action performet the Regidter thr button click;
	   System.out.println(); 
	   lresult=new JLabel("Result is:");
	   add(lresult);
	   
	  //Enable Visibility
	   setVisible(true);
	 //close the App when frame window is closed
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  //add WindowListener to FRame Window
	   this.addWindowListener( this);
		
	   //Helper method the Stablishe the Connecrion of oracle datbase S/W;
	   initilized();
	}//Constructer
	
	private void initilized() {
		System.out.println("GUIFrontEndStudentRegistrationApp.initilized()");
		try {
			//Register the JDBC Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Established the Connection 
			  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince", "prince");
			  //PreparedStatement object create
			  if(con!=null)
				  ps=con.prepareStatement(INSERT_GUISTUDENTRECORD_QUERY);
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
		
	}//initilized


public static void main(String args[]) {
	System.out.println("GUIFrontEndStudentRegistrationApp.main()");
	  GUIFrontEndStudentRegistrationApp gui=new GUIFrontEndStudentRegistrationApp();
	  
  }//main
@Override
public void actionPerformed(ActionEvent arg0) {
	String name=null,addrs=null;
	int count=0;
	System.out.println("GUIFrontEndStudentRegistrationApp.actionPerformed()");
	try {
	//Read the input values fronm the text box
	 name=tname.getText();
	 addrs=taddr.getText();
	//set the Query param
	 ps.setString(1, name);
	 ps.setString(2,addrs);
	//Execute the SQLquery
	  count=ps.executeUpdate();
	//processing the Result
	  if(count==0)
	  {   lresult.setForeground(Color.RED);
		  lresult.setText("Registation Failed!");
	  }
	 else
	  { 
		  lresult.setForeground(Color.GREEN); 
		  lresult.setText("Registation Succissed!");
	  }
	}
	catch(SQLException se) {
		 lresult.setForeground(Color.RED);
		  lresult.setText("Registation Failed!");
		se.printStackTrace();
	}
	catch(Exception e) {
		 lresult.setForeground(Color.RED);
		  lresult.setText("Registation Failed!");
		e.printStackTrace();
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
	System.out.println("GUIFrontEndStudentRegistrationApp.windowClosing()");
	try {
		if(ps!=null)
			ps.close();
	}
	catch(SQLException se) {
		 lresult.setForeground(Color.RED);
		  lresult.setText("Registation Failed!");
		se.printStackTrace();
	}
	try {
		 lresult.setForeground(Color.RED);
		  lresult.setText("Registation Failed!");
		if(con!=null)
			con.close();
	}
	catch(SQLException se) {
		se.printStackTrace();
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
