/**
 * this class is about the details of bank statement!
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BankDetails extends JFrame{
	
	// declaration of Labels
	
	JLabel labelBankDetails = new JLabel("Bank Detail's");
	JLabel labelTotalLoan = new JLabel("Total Loan Amount(TK)   :");
	JLabel labelTotalFd = new JLabel("Number of FD's                 :");
	JLabel labelTotalEmp = new JLabel("Total Employee's              :");
	JLabel labelTotalCs = new JLabel("Number of Customer's    :");
	JLabel LabelTotalLoan = new JLabel("Number of Loan's             :");
	JLabel labelBankDeposit = new JLabel("Total Bank Deposit's(TK):");
	JLabel bankDeposit = new JLabel("");
	JLabel loanAmount = new JLabel("");
	JLabel totalEmp = new JLabel("");
	JLabel totalCs = new JLabel("");
	JLabel totalLoan = new JLabel("");
	JLabel totalFd = new JLabel("");
	JLabel labelDate = new JLabel("Date:");
	JButton btCancel = new JButton("Cancel");
	JLabel labelShowDate = new JLabel("");

	// declaration of calendar
	
	Calendar cal=new GregorianCalendar();
	Connection connection=null;

	/**
	 * here is the constructor and all the declarations 
	 * here the frame is fixed and not resizable
	 * the background image path is declared
	 */

	
	public BankDetails() {
	
		
		setBounds(100, 100, 1360, 768);
		setTitle("Bank Detail's");
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		setContentPane(new JLabel(new ImageIcon("C:\\Users\\Uchchhwash\\workspace\\Java Project\\Images\\BankDetails.jpg")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		

		
		labelBankDetails.setFont(new Font("Agency FB", Font.BOLD, 50));
		labelBankDetails.setBounds(525, 0, 328, 61);
		getContentPane().add(labelBankDetails);
		
		
		labelTotalLoan.setFont(new Font("Agency FB", Font.BOLD, 30));
		labelTotalLoan.setBounds(35, 223, 300, 49);
		getContentPane().add(labelTotalLoan);
		
		
		labelTotalFd.setFont(new Font("Agency FB", Font.BOLD, 30));
		labelTotalFd.setBounds(35, 557, 287, 49);
		getContentPane().add(labelTotalFd);
		
		
		labelTotalEmp.setFont(new Font("Agency FB", Font.BOLD, 30));
		labelTotalEmp.setBounds(35, 304, 287, 49);
		getContentPane().add(labelTotalEmp);
		
		
		labelTotalCs.setFont(new Font("Agency FB", Font.BOLD, 30));
		labelTotalCs.setBounds(35, 387, 287, 49);
		getContentPane().add(labelTotalCs);
		
		
		LabelTotalLoan.setFont(new Font("Agency FB", Font.BOLD, 30));
		LabelTotalLoan.setBounds(35, 470, 287, 49);
		getContentPane().add(LabelTotalLoan);
		
		
		labelBankDeposit.setFont(new Font("Agency FB", Font.BOLD, 30));
		labelBankDeposit.setBounds(35, 146, 300, 49);
		getContentPane().add(labelBankDeposit);
		
		
		bankDeposit.setFont(new Font("Agency FB", Font.BOLD, 30));
		bankDeposit.setBounds(334, 156, 131, 29);
		getContentPane().add(bankDeposit);
		
	
	
		loanAmount.setFont(new Font("Agency FB", Font.BOLD, 30));
		loanAmount.setBounds(334, 233, 131, 29);
		getContentPane().add(loanAmount);
		
		
		totalEmp.setFont(new Font("Agency FB", Font.BOLD, 30));
		totalEmp.setBounds(334, 314, 131, 29);
		getContentPane().add(totalEmp);
		
		
		totalCs.setFont(new Font("Agency FB", Font.BOLD, 30));
		totalCs.setBounds(334, 397, 131, 29);
		getContentPane().add(totalCs);
		
		
		totalLoan.setFont(new Font("Agency FB", Font.BOLD, 30));
		totalLoan.setBounds(334, 480, 131, 29);
		getContentPane().add(totalLoan);
		
		
		totalFd.setFont(new Font("Agency FB", Font.BOLD, 30));
		totalFd.setBounds(334, 567, 131, 29);
		getContentPane().add(totalFd);
		
	
		labelDate.setFont(new Font("Agency FB", Font.BOLD, 26));
		labelDate.setBounds(569, 74, 92, 29);
		getContentPane().add(labelDate);
		
		
		btCancel.setFont(new Font("Agency FB", Font.BOLD, 24));
		btCancel.setBounds(623, 658, 159, 36);
		getContentPane().add(btCancel);
		
		
		labelShowDate.setFont(new Font("Agency FB", Font.BOLD, 26));
		labelShowDate.setBounds(655, 74, 209, 29);
		getContentPane().add(labelShowDate);
		
	
		
	// button action	
		connection= DatabaseConnection.dbConnector();
		showCustomers();
		showEmployee();
		showBankDeposit();
		showBankLoan();
		showTotalLoans();
		showTotalFd();
		showDate();
		btCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				dispose();
				
				
			}
		});
	}
	
	// all the methods for showing details about bank
	
	public void showCustomers()
	{
		try
		{
			connection= DatabaseConnection.dbConnector();
			String query ="SELECT count(Account_No) FROM Customer_info";
			PreparedStatement pst = (PreparedStatement) connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			if(rs.next())
			{
				String sum=rs.getString("count(Account_No)");
				totalCs.setText(sum);
			}
			
			rs.close();
			pst.close();
		}
			
		catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
	}
	
	public void showEmployee()
	{
		try
		{
			connection= DatabaseConnection.dbConnector();
			String query ="SELECT count(Username) FROM user_info";
			PreparedStatement pst = (PreparedStatement) connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			if(rs.next())
			{
				String sum=rs.getString("count(Username)");
				totalEmp.setText(sum);
			}
			
			rs.close();
			pst.close();
		}
			
		catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
	}
	
	public void showBankDeposit()
	{
		try
		{
			connection= DatabaseConnection.dbConnector();
			String query ="SELECT sum(Balance) FROM Customer_info";
			PreparedStatement pst = (PreparedStatement) connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			if(rs.next())
			{
				String sum=rs.getString("sum(Balance)");

				bankDeposit.setText(sum);
			}
			
			rs.close();
			pst.close();
		}
			
		catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
	}
	
	public void showBankLoan()
	{
		try
		{
			connection= DatabaseConnection.dbConnector();
			String ap="Approved";
			String query ="SELECT sum(Loan_Amount) FROM loan where Loan_Status='"+ap+"'";
			PreparedStatement pst = (PreparedStatement) connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			if(rs.next())
			{
				String sum=rs.getString("sum(Loan_Amount)");
				loanAmount.setText(sum);
			}
			
			rs.close();
			pst.close();
		}
			
		catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
	}
	
	public void showTotalLoans()
	{
		try
		{
			connection= DatabaseConnection.dbConnector();
			String ap="Approved";
			String query ="SELECT count(Account_No) FROM loan where Loan_Status='"+ap+"'";
			PreparedStatement pst = (PreparedStatement) connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			if(rs.next())
			{
				String sum=rs.getString("count(Account_No)");
				totalLoan.setText(sum);
			}
			
			rs.close();
			pst.close();
		}
			
		catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
	}
	
	public void showTotalFd()
	{
		try
		{
			connection= DatabaseConnection.dbConnector();
			String ap="Approved";
			String query ="SELECT count(Account_No) FROM fd ";
			PreparedStatement pst = (PreparedStatement) connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			if(rs.next())
			{
				String sum=rs.getString("count(Account_No)");
				totalFd.setText(sum);
			}
			
			rs.close();
			pst.close();
		}
			
		catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
	}
	
	public void showDate()
	{

		int day=cal.get(Calendar.DAY_OF_MONTH);
		int month=cal.get(Calendar.MONTH);
		month+=1;
		int year=cal.get(Calendar.YEAR);
		
		labelShowDate.setText(day+"/"+month+"/"+year);
		getContentPane().add(labelShowDate);
		
	}
	
}
