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

public class Deposit extends JFrame {
		
	// here is the declaration for all components used and variables
	
	JLabel accNo = new JLabel("Enter Account Number");
	JLabel name = new JLabel("Name");
	JLabel currentBalance = new JLabel("Current Balance");
	JLabel depositAmount = new JLabel("Deposit Amount");
	JButton details = new JButton("Details");
	JButton update = new JButton("Update");
	JButton btCancel = new JButton("Cancel");
	JTextField accNumTxt = new JTextField();
	JTextField depositAmountTxt = new JTextField();
	JTextArea nameTxt = new JTextArea();
	JTextArea currentBalanceTxt = new JTextArea();
	Calendar cal =new GregorianCalendar ();
	JLabel showDate = new JLabel("");
	String date;

	
	double finalBalance;
	
	Connection connection=null;
	
	/**
	 * here is the constructor and all the declarations 
	 * here the frame is fixed and not resizable
	 * the background image path is declared
	 */
	
	public Deposit() {

		
		setBounds(100, 100, 800, 600);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Deposit");
		setResizable(false);
		setContentPane(new JLabel(new ImageIcon("C:\\Users\\Uchchhwash\\workspace\\Java Project\\Images\\Deposit.jpg")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		getContentPane().setLayout(null);
		
	
		accNo.setFont(new Font("Agency FB", Font.BOLD, 24));
		accNo.setBounds(48, 99, 214, 37);
		getContentPane().add(accNo);
		
		
		name.setFont(new Font("Agency FB", Font.BOLD, 24));
		name.setBounds(47, 165, 141, 41);
		getContentPane().add(name);
		
		
		currentBalance.setFont(new Font("Agency FB", Font.BOLD, 24));
		currentBalance.setBounds(48, 236, 150, 37);//
		getContentPane().add(currentBalance);
		
		
		depositAmount.setFont(new Font("Agency FB", Font.BOLD, 24));
		depositAmount.setBounds(48, 306, 151, 37);
		getContentPane().add(depositAmount);
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
		depositAmountTxt.setFont(new Font("Agency FB", Font.PLAIN, 20));
			
		depositAmountTxt.setBounds(274, 305, 245, 42);
		getContentPane().add(depositAmountTxt);
		nameTxt.setFont(new Font("Agency FB", Font.PLAIN, 20));
				
		nameTxt.setForeground(new Color(0, 0, 0));
		nameTxt.setBounds(274, 165, 245, 42);
		getContentPane().add(nameTxt);
		nameTxt.setEditable(false);
		currentBalanceTxt.setFont(new Font("Agency FB", Font.PLAIN, 20));
				
		currentBalanceTxt.setBounds(274, 236, 245, 42);
		getContentPane().add(currentBalanceTxt);
		currentBalanceTxt.setEditable(false);
		
		int day=cal.get(Calendar.DAY_OF_MONTH);
		int month=cal.get(Calendar.MONTH);
		month+=1;
		int year=cal.get(Calendar.YEAR);
		
		
		showDate.setFont(new Font("Agency FB", Font.BOLD, 28));
		showDate.setBounds(350,0, 120, 100);
		getContentPane().add(showDate);
		showDate.setText(""+day+"/"+month+"/"+year);
		
		SetDate();
		

		// performed UML operation against button click
		
		 connection= DatabaseConnection.dbConnector();
		 details.addActionListener(new ActionListener() 
		 {
				public void actionPerformed(ActionEvent e) 
				{
					 try
						{	String balance;			 
						 	ResultSet rs = null;
					    	String query = "select Name ,Balance from customer_info where Account_No = ?";
							PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
							ps.setString(1,accNumTxt.getText());
							rs = ps.executeQuery();
						    if(rs.next())
						    {
						        
						    	String name=rs.getString("Name");
						    	nameTxt.setText(name);
						    	
						    	balance  = rs.getString("Balance");
						    	currentBalanceTxt.setText(balance);
						    	String  a=currentBalanceTxt.getText();
						    	finalBalance = Double.parseDouble(a);
	
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
					if(Double.parseDouble(depositAmountTxt.getText())>0)
					{
				    	try
				    	{
				    		
				    		String  b=depositAmountTxt.getText();
					    	double balance=Double.parseDouble(b);
					    	
					    	finalBalance += balance;
					    	
					    	CallableStatement ps1 = connection.prepareCall
					    		     ("{call dp(?,?,?,?)}");
					    	
					    	ps1.setString(1,accNumTxt.getText());
					    	ps1.setDouble(2,balance);							 
					    	ps1.setString(3,date);
					    	ps1.setDouble(4,finalBalance);
					  
					    	ps1.executeUpdate();
					    	ps1.close();
					    	
					    	/*String query2 ="insert into deposit (Account_No,Deposit_Amount,CDate) values (?,?,?) ";
					    	PreparedStatement ps1 = (PreparedStatement) connection.prepareStatement(query2);
					    	
					    	
					    	
					    	ps1.setString(1,accNumTxt.getText());
					    	ps1.setDouble(2,balance);							 
					    	ps1.setString(3,date);
					  
					    	ps1.execute();
					    	
					    	
					    	
					    	String query3 ="update customer_info set Balance= "+finalBalance+" where Account_No=?";
					    	PreparedStatement ps2 = (PreparedStatement) connection.prepareStatement(query3);
							ps2.setString(1,accNumTxt.getText());
							ps2.execute();
							
							
							
					    	String query4 ="insert into transaction (Account_No,Credit,CDate) values (?,?,?) ";
					    	PreparedStatement ps3 = (PreparedStatement) connection.prepareStatement(query4);
					    	b=depositAmountTxt.getText();
					    	ps3.setString(1,accNumTxt.getText());
					    	ps3.setDouble(2,balance);							 
					    	ps3.setString(3,date);
					    	ps3.execute();*/
					    	
					    	
					    	
					    	
				    	}
				    	catch(Exception e1)
						{
							JOptionPane.showMessageDialog(null,"Something wrong");
						}
				    	
			    	
				    	JOptionPane.showMessageDialog(null,"Successfully Deposited");
				    	dispose();
					
					}
					
					else
					{
						JOptionPane.showMessageDialog(null,"Deposit Value Can not be negetive!");
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
