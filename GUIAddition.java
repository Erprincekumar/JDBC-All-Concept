package CallableStatement;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIAddition extends JFrame implements ActionListener{
	JLabel lfno,lsno,lsum;
	JButton badd;
	JTextField tfno,tsno,tresult;
	public GUIAddition() {
		setTitle("Addition");
		setSize(300,300);
		setLayout(new FlowLayout());
		lfno=new JLabel("Enter Firstno:");
		add(lfno);
		tfno=new JTextField(10);
		add(tfno);
		lsno=new JLabel("Enter Secondno:");
		add(lsno);
		tsno=new JTextField(10);
		add(tsno);
		badd=new JButton("Addition");
		badd.addActionListener(this);
		add(badd);
		lsum=new JLabel("Sum=");
		add(lsum);
		tsno=new JTextField(10);
		add(tsno);
		setVisible(true);
	}

	public static void main(String[] args) {
		System.out.println("Main method is executed");
		new GUIAddition();
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("GUIAddition.actionPerformed()");
		//Read the Values from text box
		int a=Integer.parseInt(tfno.getText());
		System.out.println(" "+a);
		int b=Integer.parseInt(tsno.getText());
		System.out.println(" "+b);
		int c=a+b;
		//Set the values from result text box
		tresult.setText("Hello");
	}
}
	
