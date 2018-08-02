import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.*;

import javax.swing.JOptionPane;
import java.sql.*;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class LoanForm extends JFrame //implements ActionListener
{
	// all the declaration of components
	
	JButton btSubmit = new JButton("Submit");
	JButton btCancel = new JButton("Cancel");
	
	
	JLabel labelAccNo= new JLabel("Account No.");
	JLabel labelLoanAmount= new JLabel("Loan Amount");
	JLabel labelMonthlyPay= new JLabel("Monthly Pay");
	JLabel labelPeriod= new JLabel("Period(Month)");
	JLabel label_Interest= new JLabel("Interest %");
	JLabel showDate = new JLabel ();
	

	JTextField txtAccountNo = new JTextField();
	JTextField txtAmount = new JTextField();
	JTextField txtMonthlyPay = new JTextField();
	JTextField txtPeriod = new JTextField();		
	JTextField txtInterest = new JTextField();
	
	Calendar cal =new GregorianCalendar ();
	
	Connection connection=null;
	
	String date;
	
	/**
	 * here is the constructor and all the declarations 
	 * here the frame is fixed and not resizable
	 * the background image path is declared
	 */
	
	public LoanForm()
	{
		super("Loan Form");
		setSize(800,800);
		setVisible(true);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		 setContentPane(new JLabel(new ImageIcon("C:\\Users\\Uchchhwash\\workspace\\Java Project\\Images\\LoanForm.jpg")));
		
		
		
		getContentPane().add(labelAccNo);
		labelAccNo.setBounds(150,170,120,80);
		labelAccNo.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		getContentPane().add(labelLoanAmount);
		labelLoanAmount.setBounds(150,250,120,80);
		labelLoanAmount.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		getContentPane().add(labelMonthlyPay);
		labelMonthlyPay.setBounds(150,330,120,80);
		labelMonthlyPay.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		getContentPane().add(labelPeriod);
		labelPeriod.setBounds(150,410,140,80);
		labelPeriod.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		getContentPane().add(label_Interest);
		label_Interest.setBounds(150,490,120,80);
		label_Interest.setFont(new Font("Agency FB", Font.BOLD, 24));
		txtAccountNo.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		getContentPane().add(txtAccountNo);
		txtAccountNo.setBounds(300,190,240,35);
		txtAmount.setFont(new Font("Agency FB", Font.PLAIN, 20));
		getContentPane().add(txtAmount);
		txtAmount.setBounds(300,270,240,35);
		txtMonthlyPay.setFont(new Font("Agency FB", Font.PLAIN, 20));
		getContentPane().add(txtMonthlyPay);
		txtMonthlyPay.setBounds(300,350,240,35);
		txtPeriod.setFont(new Font("Agency FB", Font.PLAIN, 20));
		getContentPane().add(txtPeriod);
		txtPeriod.setBounds(300,430,240,35);
		txtInterest.setFont(new Font("Agency FB", Font.PLAIN, 20));
		getContentPane().add(txtInterest);
		txtInterest.setBounds(300,510,240,35);
		btSubmit.setFont(new Font("Agency FB", Font.BOLD, 22));
		
	
		getContentPane().add(btSubmit);
		btSubmit.setBounds(430,650,110,40);
		btCancel.setFont(new Font("Agency FB", Font.BOLD, 22));
		
		getContentPane().add(btCancel);
		btCancel.setBounds(270,650,110,40);
		
		getContentPane().setLayout(null);
		

		int day=cal.get(Calendar.DAY_OF_MONTH);
		int month=cal.get(Calendar.MONTH);
		int year=cal.get(Calendar.YEAR);
		month+=1;
		
		SetDate();
		
		
		showDate.setFont(new Font("Agency FB", Font.BOLD, 30));
		showDate.setBounds(350,20, 120, 100);		
		getContentPane().add(showDate);
		showDate.setText(""+day+"/"+month+"/"+year);


		connection= DatabaseConnection.dbConnector();
		
		// Button Actions
		
		btSubmit.addActionListener(new ActionListener() 
		{
				public void actionPerformed(ActionEvent e2) 
				{	
					checkAccount();
				}
	
		});
		
		 btCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e1) 
				{
					dispose();
				}
			});
		
	}
	
	//method for checking account
	
	public void checkAccount()
	{	
		try
		{
			connection= DatabaseConnection.dbConnector();
			String query ="select * from customer_info where Account_No=?";
			PreparedStatement pst = (PreparedStatement) connection.prepareStatement(query);
			pst.setString(1, txtAccountNo.getText());
			ResultSet rs=pst.executeQuery();

			int count =0;
			while(rs.next())
				{
					count=count+1; 
				}
			if(count==1)
				{
					loanSubmit();
				}
			
			else
			{
				JOptionPane.showMessageDialog(null,"Account No Do not Exist");
			}
		}	
	
		catch(Exception e3)
		{
			JOptionPane.showMessageDialog(null,e3);
		}
	}
	
	// method for submission of loan
	
		public void loanSubmit()
		{
			if(Double.parseDouble(txtAmount.getText())>0)
			{
				try
					{
					     int acc = Integer.parseInt(txtAccountNo.getText()) ;
					     double am =Double.parseDouble(txtAmount.getText());
					     double monthlyPay  =Double.parseDouble(txtMonthlyPay.getText());
					     int period =Integer.parseInt(txtPeriod.getText());
					     double interest =Double.parseDouble(txtInterest.getText());
					 	 String query ="insert into loan (Account_No,Loan_Amount,Monthly_Pay,Period,Interest,Loan_Status,CDate) values(?,?,?,?,?,?,?)";
					 	 PreparedStatement st1 = (PreparedStatement) connection.prepareStatement(query);
					 
					    
						 st1.setInt	(1,acc);
						 st1.setDouble(2,am);
						 st1.setDouble(3,monthlyPay);						 
						 st1.setInt(4,period);												
						 st1.setDouble(5,interest);
						 st1.setString(6,"Pending");
						 st1.setString(7,date);
						 st1.execute();
						 
						 JOptionPane.showMessageDialog(null,"Successfully Created");
						 dispose();
						
					}
				 
				catch(Exception e3)
					{
						JOptionPane.showMessageDialog(null,e3);
					}
				}
			
			else
			{
				JOptionPane.showMessageDialog(null,"Amount Value Can not be negetive!");
			}
		}
	
		 public void SetDate()
	     {
				int day=cal.get(Calendar.DAY_OF_MONTH);
				int month=cal.get(Calendar.MONTH);
				month+=1;
				int year=cal.get(Calendar.YEAR);
				
				
				String strDay = Integer.toString(day);
				String strMonth="";
				String strYear = Integer.toString(year);
				
				
	         
	         if(month==1)
	         {
	        	 strMonth="JAN";
	         }
	         else if(month==2)
	         {
	        	 strMonth="FEB";
	         }
	         else if(month==3)
	         {
	        	 strMonth="MAR";
	         }
	         else if(month==4)
	         {
	        	 strMonth="APR";
	         }
	         else if(month==5)
	         {
	        	 strMonth="MAY";
	         }
	         else if(month==6)
	         {
	        	 strMonth="JUN";
	         }
	         else if(month==7)
	         {
	        	 strMonth="JUL";
	         }
	         else if(month==8)
	         {
	        	 strMonth="AUG";
	         }
	         else if(month==9)
	         {
	        	 strMonth="SEP";
	         }
	         else if(month==10)
	         {
	        	 strMonth="OCT";
	         }
	         else if(month==11)
	         {
	        	 strMonth="NOV";
	         }
	         else if(month==12)
	         {
	        	 strMonth="DEC";
	         }
	         
	         date=strDay+"-"+strMonth+"-"+strYear;
	         
	     }
}	






