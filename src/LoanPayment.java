/**
 * this class is for deposit balance for specific account.
 */

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import javax.swing.JOptionPane;
import java.sql.*;

import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class LoanPayment extends JFrame {
		
	// here is the declaration for all components used and variables
	
	JLabel accNo = new JLabel("Enter Account Number");
	JLabel name = new JLabel("Name");
	JLabel paymentAmount = new JLabel("Payment Amount");
	JLabel paymenMonth = new JLabel("Payment Month");
	JButton details = new JButton("Details");
	JButton update = new JButton("Update");
	JButton btCancel = new JButton("Cancel");
	JTextField accNumTxt = new JTextField();
	JTextField paymentAmountTxt = new JTextField();
	JTextArea nameTxt = new JTextArea();
	JTextArea paymentMonthTxt = new JTextArea();
	Calendar cal =new GregorianCalendar ();
	JLabel showDate = new JLabel("");
	
	String date;
	

	Connection connection=null;
	
	/**
	 * here is the constructor and all the declarations 
	 * here the frame is fixed and not resizable
	 * the background image path is declared
	 */
	
	public LoanPayment() {

		
		setBounds(100, 100, 800, 600);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Loan Payment");
		setResizable(false);
		setContentPane(new JLabel(new ImageIcon("C:\\Users\\Uchchhwash\\workspace\\Java Project\\Images\\LoanPayment.jpg")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		getContentPane().setLayout(null);
		
	
		accNo.setFont(new Font("Agency FB", Font.BOLD, 24));
		accNo.setBounds(48, 99, 214, 37);
		getContentPane().add(accNo);
		
		
		name.setFont(new Font("Agency FB", Font.BOLD, 24));
		name.setBounds(47, 165, 141, 41);
		getContentPane().add(name);
		
		
		paymenMonth.setFont(new Font("Agency FB", Font.BOLD, 24));
		paymenMonth.setBounds(48, 236, 170, 37);//
		getContentPane().add(paymenMonth);
		
		
		paymentAmount.setFont(new Font("Agency FB", Font.BOLD, 24));
		paymentAmount.setBounds(48, 306, 170, 37);
		getContentPane().add(paymentAmount);
		details.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		
		details.setBounds(555, 96, 172, 46);
		getContentPane().add(details);
		update.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		
		update.setBounds(400, 402, 172, 46);
		getContentPane().add(update);
		btCancel.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		btCancel.setBounds(200, 402, 172, 46);
		getContentPane().add(btCancel);
		accNumTxt.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		
		
		accNumTxt.setBounds(274, 99, 245, 42);
		getContentPane().add(accNumTxt);
		paymentAmountTxt.setFont(new Font("Agency FB", Font.PLAIN, 20));
			
		paymentAmountTxt.setBounds(274, 305, 245, 42);
		getContentPane().add(paymentAmountTxt);
		nameTxt.setFont(new Font("Agency FB", Font.PLAIN, 20));
				
		nameTxt.setForeground(new Color(0, 0, 0));
		nameTxt.setBounds(274, 165, 245, 42);
		getContentPane().add(nameTxt);
		nameTxt.setEditable(false);
		paymentMonthTxt.setFont(new Font("Agency FB", Font.PLAIN, 20));
				
		paymentMonthTxt.setBounds(274, 236, 245, 42);
		getContentPane().add(paymentMonthTxt);
		
		
		int day=cal.get(Calendar.DAY_OF_MONTH);
		int month=cal.get(Calendar.MONTH);
		month+=1;
		int year=cal.get(Calendar.YEAR);
		SetDate();

	
		showDate.setFont(new Font("Agency FB", Font.BOLD, 30));
		showDate.setBounds(350,0, 120, 100);
		getContentPane().add(showDate);
		showDate.setText(""+day+"/"+month+"/"+year);
		

		// performed UML operation against button click
		
		 connection= DatabaseConnection.dbConnector();
		 details.addActionListener(new ActionListener() 
		 {
				public void actionPerformed(ActionEvent e) 
				{
					 try
						{			 
						    connection= DatabaseConnection.dbConnector();
							String query ="select * from loan where Account_No=?";
							PreparedStatement pst = (PreparedStatement) connection.prepareStatement(query);
							pst.setString(1, accNumTxt.getText());							
							ResultSet rs=pst.executeQuery();
							
							int count =0;
							while(rs.next())
								{
									count=count+1; 
								}
							if(count==1)
								{
									paymentSubmit();
								}
							
							else
							{
								JOptionPane.showMessageDialog(null,"Account No Do not Exist");
							}
						    
						    
						}
					 
					catch(Exception e1)
						{
							JOptionPane.showMessageDialog(null,"Something wrong");
						}
					}
				}
		 
		 );
		 
		 
		 update.addActionListener(new ActionListener() 
		 {
				public void actionPerformed(ActionEvent e) 
				{
					if(Double.parseDouble(paymentAmountTxt.getText())>0)
					{
				    	try
				    	{
				    		
					    	
					    	String query2 ="insert into loan_payment (Account_No,Name,Payment_Month,Payment_Amount,CDate) values (?,?,?,?,?) ";
					    	PreparedStatement ps1 = (PreparedStatement) connection.prepareStatement(query2);
					    	
					    	String  b=paymentAmountTxt.getText();
					    	double payAmount=Double.parseDouble(b);
					    	
					    	ps1.setString(1,accNumTxt.getText());
					    	ps1.setString(2,nameTxt.getText());
					    	ps1.setString(3,paymentMonthTxt.getText());
					    	ps1.setDouble(4,payAmount);
					    	ps1.setString(5,date);
					  
					    	ps1.execute();
					    	
					    	
				    	}
				    	catch(Exception e1)
						{
							JOptionPane.showMessageDialog(null,"Something wrong");
						}
				    	
			    	
				    	JOptionPane.showMessageDialog(null,"Payment Successed");
				    	dispose();
					
					}
					
					else
					{
						JOptionPane.showMessageDialog(null,"Amount Value Can not be negetive!");
					}
				}
			}
	 
		);
		 
		 btCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e1) 
				{
					dispose();
				}
			});
		
		
	}
	
	public void paymentSubmit()
	{	try
		{
			connection= DatabaseConnection.dbConnector();
			ResultSet rs = null;
	    	String query = "select Name from customer_info where Account_No = ? ";
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
			ps.setString(1,accNumTxt.getText());
			rs = ps.executeQuery();
		    if(rs.next())
		    {
		        
		    	String name=rs.getString("Name");
		    	nameTxt.setText(name);
			
		    }
		    
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null,"Something wrong");
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
