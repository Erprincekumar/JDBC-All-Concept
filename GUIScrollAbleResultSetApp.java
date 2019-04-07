package CallableStatement;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIScrollAbleResultSetApp {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
    Connection con=null;
    PreparedStatement ps;
    ResultSet rs=null;
    boolean flag=false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println("GUIScrollAbleResultSetApp.main()");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIScrollAbleResultSetApp window = new GUIScrollAbleResultSetApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIScrollAbleResultSetApp() {
		System.out.println("GUIScrollAbleResultSetApp.Constructer is called");
		initialize();
		initilizedJDBC();
		
	}
	public void initilizedJDBC() {
		System.out.println("GUIScrollAbleResultSetApp.initilizedJDBC()");
		try {
			//Register the JDBC Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//established the Connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prince","prince");
			//Create PreparedStatement object
			ps=con.prepareStatement("select *from student",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			//Create ResultSet object
			rs=ps.executeQuery();
		}
		catch(Exception se) {
			se.printStackTrace();
		}
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		System.out.println("GUIScrollAbleResultSetApp.initialize()");
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(253, 13, 152, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(253, 65, 152, 21);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(253, 118, 152, 21);
		frame.getContentPane().add(textField_2);
		
		JLabel lblNewLabel = new JLabel("STUDENT ID:");
		lblNewLabel.setBounds(43, 16, 89, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblStudentName = new JLabel("STUDENT NAME:");
		lblStudentName.setBounds(43, 68, 105, 16);
		frame.getContentPane().add(lblStudentName);
		
		JLabel lblStudentAddrs = new JLabel("STUDENT ADDRS:");
		lblStudentAddrs.setBounds(43, 121, 105, 16);
		frame.getContentPane().add(lblStudentAddrs);
		
		JButton btnNewButton = new JButton("FIRST");
		btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println("First button clicked");
					rs.first();
					textField.setText(rs.getString(1));
					textField_1.setText(rs.getString(2));
					textField_2.setText(rs.getString(3));
					flag=true;
					
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(12, 167, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNext = new JButton("NEXT");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				  if(!rs.isLast())
					   rs.next();
					System.out.println("Next button clicked");
				      textField.setText(rs.getString(1));
					textField_1.setText(rs.getString(2));
					textField_2.setText(rs.getString(3));
					flag=true;
				  }
				catch(SQLException et) {
					et.printStackTrace();
				}
				catch(Exception er) {
					er.printStackTrace();
				}
				
			}
		});
		btnNext.setBounds(121, 167, 97, 25);
		frame.getContentPane().add(btnNext);
		
		JButton btnPrevious = new JButton("PREVIOUS");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					  if(!rs.isFirst())
						rs.previous();
						System.out.println("previous button clicked");
					    textField.setText(rs.getString(1));
						textField_1.setText(rs.getString(2));
						textField_2.setText(rs.getString(3));
						flag=true;
					  }
					catch(SQLException et) {
						et.printStackTrace();
					}
					catch(Exception er) {
						er.printStackTrace();
					}
					
			}
		});
		btnPrevious.setBounds(229, 167, 97, 25);
		frame.getContentPane().add(btnPrevious);
		
		JButton btnLast = new JButton("LAST");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				rs.last();
				System.out.println("Last button clicked");
				flag=true;
				textField.setText(rs.getString(1));
				textField_1.setText(rs.getString(2));
				textField_2.setText(rs.getString(3));
				flag=true;
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnLast.setBounds(337, 167, 83, 25);
		frame.getContentPane().add(btnLast);
	}
		
}
