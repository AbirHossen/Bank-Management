import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

public class Transfer extends JFrame{

	// declaration of all the components
	
	JFrame frmMoneyTransfer = new JFrame();
	JLabel labalAmount = new JLabel("Amount");
	JLabel labelDstAmount = new JLabel("Destination Account");
	JLabel labelName = new JLabel("Name");
	JLabel labelCurrentAmount = new JLabel("Current Account Balance");
	JTextArea txtCurrentAccBalance = new JTextArea();
	JTextArea txtName = new JTextArea();
	JTextField txtAccNum = new JTextField();
	JTextField txtDstAcc = new JTextField();
	JTextField txtAmount = new JTextField();
	JButton btTransferAmount = new JButton("Transfer Amount");
	JButton btDetails = new JButton("Details");
	JButton btCancel = new JButton("Cancel");
	//JLabel labelDate = new JLabel("Date:");
	JLabel ShowDate = new JLabel("");
	Calendar cal =new GregorianCalendar ();
	
	double finalBalanceFromAcc;
	double finalBalanceToAcc;
	String date;
	Connection connection=null;
	
	/**
	 * here is the constructor and all the declarations 
	 * here the frame is fixed and not resizable
	 * the background image path is declared
	 */
	
	public Transfer() {
	
		
		setTitle("Money Transfer");
		setBounds(100, 100, 800, 600);
		setVisible(true);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		setContentPane(new JLabel(new ImageIcon("C:\\Users\\Uchchhwash\\workspace\\Java Project\\Images\\Transfer.jpg")));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JLabel labelFromAccNo = new JLabel("From Account Number");
		labelFromAccNo.setFont(new Font("Agency FB", Font.BOLD, 24));
		labelFromAccNo.setBounds(36, 82, 222, 44);
		getContentPane().add(labelFromAccNo);
		
		
		labalAmount.setFont(new Font("Agency FB", Font.BOLD, 24));
		labalAmount.setBounds(36, 399, 117, 44);
		getContentPane().add(labalAmount);
				
		labelDstAmount.setFont(new Font("Agency FB", Font.BOLD, 24));
		labelDstAmount.setBounds(36, 323, 215, 44);
		getContentPane().add(labelDstAmount);
				
		labelName.setFont(new Font("Agency FB", Font.BOLD, 24));
		labelName.setBounds(36, 246, 86, 44);
		getContentPane().add(labelName);
		
		labelCurrentAmount.setFont(new Font("Agency FB", Font.BOLD, 24));
		labelCurrentAmount.setBounds(36, 178, 230, 22);
		getContentPane().add(labelCurrentAmount);
		txtCurrentAccBalance.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		
		txtCurrentAccBalance.setBounds(273, 166, 245, 42);
	    getContentPane().add(txtCurrentAccBalance);
	    txtCurrentAccBalance.setEditable(false);
		txtName.setFont(new Font("Agency FB", Font.PLAIN, 20));
				
		txtName.setBounds(273, 246, 245, 42);
		getContentPane().add(txtName);
		txtName.setEditable(false);
		txtAccNum.setFont(new Font("Agency FB", Font.PLAIN, 20));
				
		txtAccNum.setBounds(273, 86, 245, 42);
		getContentPane().add(txtAccNum);
		txtAccNum.setColumns(10);
		txtDstAcc.setFont(new Font("Agency FB", Font.PLAIN, 20));
			
		txtDstAcc.setColumns(10);
		txtDstAcc.setBounds(273, 323, 245, 42);
		getContentPane().add(txtDstAcc);
		txtAmount.setFont(new Font("Agency FB", Font.PLAIN, 20));
				
		txtAmount.setColumns(10);
		txtAmount.setBounds(273, 401, 245, 42);
		getContentPane().add(txtAmount);
		btDetails.setFont(new Font("Agency FB", Font.BOLD, 24));
				
		btDetails.setBounds(557, 85, 130, 44);
		getContentPane().add(btDetails);
		btTransferAmount.setFont(new Font("Agency FB", Font.BOLD, 24));
			
		btTransferAmount.setBounds(432, 478, 180, 44);
		getContentPane().add(btTransferAmount);
		btCancel.setFont(new Font("Agency FB", Font.BOLD, 24));
				
		btCancel.setBounds(249, 478, 130, 44);
	    getContentPane().add(btCancel);
		
		int day=cal.get(Calendar.DAY_OF_MONTH);
		int month=cal.get(Calendar.MONTH);
		int year=cal.get(Calendar.YEAR);
		month+=1;
		
		SetDate();
		System.out.println(date);
		
		ShowDate.setBackground(Color.WHITE);
		ShowDate.setFont(new Font("Agency FB", Font.BOLD, 30));
		ShowDate.setBounds(390, 24, 178, 30);
		getContentPane().add(ShowDate);
		ShowDate.setText(""+day+"/"+month+"/"+year);
		
		
		
		btCancel.addActionListener(new ActionListener() 
		 {
				public void actionPerformed(ActionEvent e) 
				{
					dispose();
				}
		 });
		 
		 
		
		 connection= DatabaseConnection.dbConnector();
		 btDetails.addActionListener(new ActionListener() 
		 {
				public void actionPerformed(ActionEvent e) 
				{
					 try
						{	String balance;			 
						 	ResultSet rs = null;
					    	String query = "select Name ,Balance from customer_info where Account_No = ?";
							PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
							ps.setString(1,txtAccNum.getText());
							rs = ps.executeQuery();
						    if(rs.next())
						    {
						        
						    	String name=rs.getString("Name");
						    	txtName.setText(name);
						    	
						    	balance  = rs.getString("Balance");
						    	txtCurrentAccBalance.setText(balance);
						    	String  a=txtCurrentAccBalance.getText();
						    	finalBalanceFromAcc = Double.parseDouble(a);
						    	finalBalanceToAcc   = Double.parseDouble(a);
						    	
						    							    	
						    }
						    
						    
						}
					 
					catch(Exception e1)
						{
							JOptionPane.showMessageDialog(null,e1);
						}
					}
				}
		 
		 );
		 
		 
		 btTransferAmount.addActionListener(new ActionListener() 
		 {
				public void actionPerformed(ActionEvent e) 
				{	
					
					if(Double.parseDouble(txtAmount.getText())>0)
					{
						
				    	try
				    	{	
				    					 
						 	ResultSet rs1 = null;
					    	String query = "select Balance from customer_info where Account_No = ?";
							PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
							ps.setString(1,txtDstAcc.getText());
							rs1 = ps.executeQuery();
							    if(rs1.next())
							    {
							        
							    	String dstbalance  = rs1.getString("Balance");
							    	double DstAccBl =Double.parseDouble(dstbalance);
							    	
							  
							    	
							    	finalBalanceToAcc   = DstAccBl;
							    	
					    		
					    		
						    		String  b=txtAmount.getText();
							    	double balance=Double.parseDouble(b);
							    	String query2 =" insert into transfer (Payee_Account,Recv_Account,Transfer_Amount,CDate) values ("+Integer.parseInt(txtAccNum.getText())+","+Integer.parseInt(txtDstAcc.getText())+","+balance+",'"+date+"')";
							    	PreparedStatement ps1 = (PreparedStatement) connection.prepareStatement(query2);
							    	ps1.execute();
							    	
							    	finalBalanceFromAcc -= balance;
							    	finalBalanceToAcc   += balance;
							    	String query3 ="update customer_info set Balance= "+finalBalanceFromAcc+" where Account_No="+Integer.parseInt(txtAccNum.getText());
							    	PreparedStatement ps2 = (PreparedStatement) connection.prepareStatement(query3);
									ps2.execute();
							    	
									
							    	String query4 ="update customer_info set Balance= "+finalBalanceToAcc+" where Account_No="+Integer.parseInt(txtDstAcc.getText());
							    	PreparedStatement ps3 = (PreparedStatement) connection.prepareStatement(query4);
							    	ps3.execute();
							    	
							    	/*
							    	String query5 ="insert into transaction (Account_No,TransferFrom,TransferTo,Amount,CDate) values (?,?,?,?,?) ";
							    	PreparedStatement ps4 = (PreparedStatement) connection.prepareStatement(query5);
							    	ps4.setString(1,txtAccNum.getText());
							    	ps4.setDouble(2,Double.parseDouble(txtAccNum.getText()));
							    	ps4.setDouble(3,Double.parseDouble(txtDstAcc.getText()));	
							    	ps4.setDouble(4,Double.parseDouble(txtAmount.getText()));
							    	ps4.setString(5,date);
							    	ps4.execute();
							    	*/
							    	
						    	
							    }
				    
				    	}
				    	
				    	catch(Exception e1)
						{
							JOptionPane.showMessageDialog(null,e1);
						}
				    	
				    	
				    	JOptionPane.showMessageDialog(null,"Successfully Transfered");
				    	dispose();
					}
					
					else
					{
						JOptionPane.showMessageDialog(null,"Transfer Amount Value Can not be negetive!");
					}
			}
		 }
	 
		);
		
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
