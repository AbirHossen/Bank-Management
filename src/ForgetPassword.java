/**
 * this class contain the frame of change password
 * one can chage his/her password from it
 */
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.JOptionPane;
import java.sql.*;

public class ForgetPassword extends JFrame 
{
	
	//declarations of all components
	
	JButton change=new JButton("Change");
	JButton cancel=new JButton("Cancel");
	
	JTextField txtUsername=new JTextField();
	JPasswordField txtOldpass = new JPasswordField(20);
	JPasswordField txtNewPass = new JPasswordField(20);
	JPasswordField txtConfirmPass = new JPasswordField(20);
	
	JLabel labelUsername=new JLabel("Username");
	JLabel labelOldPassword=new JLabel("Old Password");	
	JLabel labelNewPassword=new JLabel("New Password");	
	JLabel labelConfirmPass=new JLabel("Confirm Password");	
	JLabel labelChangePass=new JLabel("Change Password");
	Connection connection=null;
	
	/**
	 * here is the constructor and all the declarations 
	 * here the frame is fixed and not resizable
	 * the background image path is declared
	 */
	
	public ForgetPassword()
	{
			 
		super("Forget Password");
		setSize(600,600);
		
		
		setVisible(true);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		JLabel label = new JLabel(new ImageIcon("C:\\Users\\Uchchhwash\\workspace\\Java Project\\Images\\ForgetPassword.jpg"));
		label.setFont(new Font("Agency FB", Font.PLAIN, 13));
		setContentPane(label);
		change.setFont(new Font("Agency FB", Font.BOLD, 22));
		
		change.setBounds(355,390,95,30);
		getContentPane().add(change);
		cancel.setFont(new Font("Agency FB", Font.BOLD, 22));
		cancel.setBounds(200,390,95,30);
		getContentPane().add(cancel);
		
		getContentPane().setLayout(null);
		txtUsername.setFont(new Font("Agency FB", Font.PLAIN, 20));
		txtUsername.setBounds(270,110,180,30);
		getContentPane().add(txtUsername);
		txtOldpass.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		txtOldpass.setBounds(270,170,180,30);
		getContentPane().add(txtOldpass);
		txtNewPass.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		txtNewPass.setBounds(270,230,180,30);
		getContentPane().add(txtNewPass);
		txtConfirmPass.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		txtConfirmPass.setBounds(270,290,180,30);
		getContentPane().add(txtConfirmPass);
		
		
		labelChangePass.setBounds(210,40,200,30);
		getContentPane().add(labelChangePass);
		
		labelChangePass.setFont(new Font("Agency FB", Font.BOLD, 30));
		labelUsername.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		
		labelUsername.setBounds(110,110,200,30);
		getContentPane().add(labelUsername);
		labelOldPassword.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		labelOldPassword.setBounds(110,170,200,30);
		getContentPane().add(labelOldPassword);
		labelNewPassword.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		labelNewPassword.setBounds(110,230,200,30);
		getContentPane().add(labelNewPassword);
		labelConfirmPass.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		labelConfirmPass.setBounds(110,290,200,30);
		getContentPane().add(labelConfirmPass);	
	
		
		// button actions here
		
		cancel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e1) {
			dispose();
			
		}
	});
		
	
	change.addActionListener(new ActionListener() 
	{
			public void actionPerformed(ActionEvent e1) 
			{
				connection= DatabaseConnection.dbConnector();
				
				String oldPass=new String (txtOldpass.getPassword());
				String confirmPass= new String (txtConfirmPass.getPassword());
				String newPass = new String (txtNewPass.getPassword());
				String user=txtUsername.getText();
				boolean result = false;
				
				if( confirmPass.equals(newPass))
				{	
					
					try {
						
						String query ="select * from user_info where Username=? AND Password=?";
						PreparedStatement ps1 = (PreparedStatement) connection.prepareStatement(query);
						ps1.setString(1, user);
						ps1.setString(2, oldPass);
						ResultSet rs=ps1.executeQuery();
						if(rs.next())
							{
		                        result = true;
							}
		                else
		                 	{
		                        result = false;
		                    }
	
	                
					    String userName = rs.getString("Username");
					
						String query1 ="update user_login set Password=? where Username= '"+userName+"'";
				    	PreparedStatement ps2 = (PreparedStatement) connection.prepareStatement(query1);
						ps2.setString(1,newPass);
						ps2.execute();
						
						JOptionPane.showMessageDialog(null,"Successfully Changed");
						dispose();
						
					} 	
					
					catch(Exception e2)
					{
						JOptionPane.showMessageDialog(null,"Username Don't Exist!");
					}
					
				}
				else
				{
				
					JOptionPane.showMessageDialog(null,"New Password and Confirm Password Password Miss-Match");
				
				}
				
				
			}
		});
		
	
	}
		
		
		
}
