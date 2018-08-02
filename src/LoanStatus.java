
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.JOptionPane;
import java.sql.*;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;

public class LoanStatus extends JFrame{

	// here is the declaration of all components
	
	JTable table= new JTable();
	JLabel labelLoans = new JLabel("Loan Status");
	Connection connection=null;
	JButton btShowDetails = new JButton("Show All Loan's");
	JButton btCancel = new JButton("Cancel");
	JButton btSearch = new JButton("Search");
	JButton btApprove = new JButton("Approve");
	JButton btDecline = new JButton("Decline");
	JTextField txtSearch = new JTextField();
	JTextField txtAccLoan = new JTextField();
	
	/**
	 * here is the constructor and all the declarations 
	 * here the frame is fixed and not resizable
	 * the background image path is declared
	 */

	public LoanStatus() {

		
		setBounds(100, 100, 1360, 768);
		setTitle("Loan Status");
		setVisible(true);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		setContentPane(new JLabel(new ImageIcon("C:\\Users\\Uchchhwash\\workspace\\Java Project\\Images\\LoanStatus.jpg")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 71, 1194, 450);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		btShowDetails.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		btShowDetails.setBounds(600, 670, 170,43);
	    getContentPane().add(btShowDetails);
		btCancel.setFont(new Font("Agency FB", Font.BOLD, 24));
	    
		btCancel.setBounds(410 ,670, 170, 43);
		getContentPane().add(btCancel);
		btSearch.setFont(new Font("Agency FB", Font.BOLD, 24));
	    
		btSearch.setBounds(1150 ,670, 120, 43);
		getContentPane().add(btSearch);
		btApprove.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		btApprove.setBounds(600, 580, 170,43);
	    getContentPane().add(btApprove);
	    btDecline.setFont(new Font("Agency FB", Font.BOLD, 24));
	    
	    btDecline.setBounds(800, 580, 170,43);
	    getContentPane().add(btDecline);
		txtSearch.setFont(new Font("Agency FB", Font.PLAIN, 20));
	    
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setColumns(10);
		txtSearch.setBounds(1000, 670, 140, 43);
		getContentPane().add(txtSearch);
		txtAccLoan.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		txtAccLoan.setHorizontalAlignment(SwingConstants.CENTER);
		txtAccLoan.setBounds(410 ,580, 170, 43);
		getContentPane().add(txtAccLoan);
	    
		
		
		labelLoans.setForeground(Color.BLACK);
		labelLoans.setFont(new Font("Agency FB", Font.BOLD, 50));
		labelLoans.setBounds(580, -13, 527, 108);
		getContentPane().add(labelLoans);
		

		 connection= DatabaseConnection.dbConnector();
		 showDetails();
		 
	// here is the action of buttons and fetch data or perform uml operations in database
		 
		 btShowDetails.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e1) {
					showDetails();
				}
			});
		 
		 btSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e1) {
					 try
						{
						 	String query ="select * from loan where Account_No=?";
						 	PreparedStatement pst = (PreparedStatement) connection.prepareStatement(query);
						 	pst.setString(1,txtSearch.getText());
						 	ResultSet rs=pst.executeQuery();
						 	table.setModel(DbUtils.resultSetToTableModel(rs));
						
						}
					catch(Exception e)
						{
							JOptionPane.showMessageDialog(null,e);
						}
				}
			});
		 
		 	btApprove.addActionListener(new ActionListener() 
			 {
					public void actionPerformed(ActionEvent e) 
					{
						 try
							{	
							 	String query ="update loan set Loan_Status=? where Account_No='"+txtAccLoan.getText()+"'";
							 	PreparedStatement pst = (PreparedStatement) connection.prepareStatement(query);
							 	pst.setString(1,"Approved");
							 	pst.executeUpdate();
							 	showDetails();
							 	
							
							}
						catch(Exception a)
							{
								JOptionPane.showMessageDialog(null,"Enter an Account No on the Textfield!");
							}
					}
			 });
			
			btDecline.addActionListener(new ActionListener() 
			 {
					public void actionPerformed(ActionEvent e) 
					{
						 try
							{	
							 	String query ="delete from loan where Account_No=?";
							 	PreparedStatement pst = (PreparedStatement) connection.prepareStatement(query);
							 	pst.setString(1,txtAccLoan.getText());
							 	pst.execute();		
							 	showDetails();
							
							}
						catch(Exception a)
							{
								JOptionPane.showMessageDialog(null,"Enter an Account No on the Textfield!");
							}
					}
			 });
		 
			btCancel.addActionListener(new ActionListener() 
			 {
					public void actionPerformed(ActionEvent e) 
					{
						dispose();
					}
			 });
			
			
			
		
	}
	public void showDetails()
	{	
		
		try
			{
				
				String query ="select * from loan";
			 	PreparedStatement pst = (PreparedStatement) connection.prepareStatement(query);
			 	ResultSet rs=pst.executeQuery();
			 	table.setModel(DbUtils.resultSetToTableModel(rs));
			}
		
		catch(Exception a)
		{
			JOptionPane.showMessageDialog(null,a);
		}
	}
	
}
