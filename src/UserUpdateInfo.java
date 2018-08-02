import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.sql.*;

public class UserUpdateInfo extends JFrame
{
	// declaration of all thr components  
	
	JButton update = new JButton("Update");
	JButton cancel = new JButton("Cancel");
	JButton details = new JButton("Details");
	
	JLabel labelUpdateUser = new JLabel("Update User");
	
	JLabel labelUname = new JLabel("User Name");
	JLabel labelName = new JLabel("Name");
	JLabel labelGender = new JLabel("Gender");
	JRadioButton MaleRadioButton = new JRadioButton("Male");
	JRadioButton FemaleRadioButton = new JRadioButton("Female");
	JLabel labelDateBirth = new JLabel("Date of Birth");
	JLabel labelPhone = new JLabel("Phone");
	JLabel labelAddress = new JLabel("Address");
	
	JTextField txtUname = new JTextField();
	JTextField txtName = new JTextField();
	JTextField txtPhone = new JTextField();
	JTextField txtAddress = new JTextField();		
	
	JComboBox DateComboBox = new JComboBox();
	JComboBox MonthComboBox = new JComboBox();
	JComboBox YearComboBox = new JComboBox();

	
		Calendar cal =new GregorianCalendar ();
	
	JLabel showDate = new JLabel ();
	
	Connection connection=null;
	
	String dateOfBirth;
	
	ButtonGroup gp= new ButtonGroup();
	
	/**
	 * here is the constructor and all the declarations 
	 * here the frame is fixed and not resizable
	 * the background image path is declared
	 */
	
	
	public UserUpdateInfo()
	{
		super("Update User Form");
		setSize(800,800);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		JLabel label = new JLabel(new ImageIcon("C:\\Users\\Uchchhwash\\workspace\\Java Project\\Images\\UserUpdateInfo.jpg"));
		label.setFont(new Font("Agency FB", Font.BOLD, 24));
		setContentPane(label);
		
		
		JFrame frame = new JFrame();
		
		labelUpdateUser.setFont(new Font("Agency FB", Font.BOLD, 35));
		labelUpdateUser.setBounds(320, 10, 350, 62);
		getContentPane().add(labelUpdateUser);
		labelUname.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		getContentPane().add(labelUname);
		labelUname.setBounds(150,86,100,80);
		labelName.setFont(new Font("Agency FB", Font.BOLD, 24));
		getContentPane().add(labelName);
		labelName.setBounds(150,167,80,80);
		labelGender.setFont(new Font("Agency FB", Font.BOLD, 24));
		getContentPane().add(labelGender);
		labelGender.setBounds(150,250,80,80);
		labelDateBirth.setFont(new Font("Agency FB", Font.BOLD, 24));
		getContentPane().add(labelDateBirth);
		labelDateBirth.setBounds(150,335,100,80);
		labelPhone.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		getContentPane().add(labelPhone);
		labelPhone.setBounds(150,410,80,80);
		labelAddress.setFont(new Font("Agency FB", Font.BOLD, 24));
		getContentPane().add(labelAddress);
		labelAddress.setBounds(150,510,80,80);
		txtUname.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		getContentPane().add(txtUname);
		txtUname.setBounds(300,110,240,35);
		txtName.setFont(new Font("Agency FB", Font.PLAIN, 20));
		getContentPane().add(txtName);
		txtName.setBounds(300,190,240,35);
		txtPhone.setFont(new Font("Agency FB", Font.PLAIN, 20));
		getContentPane().add(txtPhone);
		txtPhone.setBounds(300,430,240,35);
		txtAddress.setFont(new Font("Agency FB", Font.PLAIN, 20));
		getContentPane().add(txtAddress);
		txtAddress.setBounds(300,510,270,95);
		cancel.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		getContentPane().add(cancel);
		cancel.setBounds(260,650,110,40); 
		update.setFont(new Font("Agency FB", Font.BOLD, 24));
		getContentPane().add(update);
		update.setBounds(450,650,110,40);
		details.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		details.setBounds(600, 110, 110, 40);
		getContentPane().add(details);
		
		getContentPane().setLayout(null);
		MaleRadioButton.setFont(new Font("Agency FB", Font.BOLD, 20));
		
		
		MaleRadioButton.setBounds(300,250,80,80);
		MaleRadioButton.setContentAreaFilled(false);
		gp.add(MaleRadioButton);
		getContentPane().add(MaleRadioButton);
		MaleRadioButton.setActionCommand("Male");
		FemaleRadioButton.setFont(new Font("Agency FB", Font.BOLD, 20));
		
		

		FemaleRadioButton.setBounds(380,250,80,80);
		FemaleRadioButton.setContentAreaFilled(false);
		gp.add(FemaleRadioButton);
		getContentPane().add(FemaleRadioButton);
		FemaleRadioButton.setActionCommand("Female");
		DateComboBox.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		DateComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		DateComboBox.setBounds(300, 360, 46, 32);
		getContentPane().add(DateComboBox);
		MonthComboBox.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
	
		MonthComboBox.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "Sepetember", "October", "November", "December"}));
		MonthComboBox.setBounds(370, 360, 83, 32);
		getContentPane().add(MonthComboBox);
		
		YearComboBox.setFont(new Font("Agency FB", Font.PLAIN, 20));		
		YearComboBox.setModel(new DefaultComboBoxModel(new String[] {"1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050"}));
		YearComboBox.setBounds(470, 360, 70, 32);
		getContentPane().add(YearComboBox);
		
		int day=cal.get(Calendar.DAY_OF_MONTH);
		int month=cal.get(Calendar.MONTH);
		int year=cal.get(Calendar.YEAR);
		month+=1;
		
		showDate.setFont(new Font("Agency FB", Font.BOLD, 22));
		showDate.setBounds(350,20, 130, 90);		
		getContentPane().add(showDate);
		showDate.setText(""+day+"/"+month+"/"+year);
		
		// here is the actions of button click and database uml operation against click
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				dispose();
			}
		});
		
		connection= DatabaseConnection.dbConnector();
		details.addActionListener(new ActionListener() 
		{
				public void actionPerformed(ActionEvent e1) 
					{
						    try{
					
							    ResultSet rs = null;
						    	String query = "select * from user_info where Username = ?";
								PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
								ps.setString(1,txtUname.getText());
								rs = ps.executeQuery();
							    if(rs.next()){
							          
							    	String name=rs.getString("Name");
							    	txtName.setText(name);
							    	
		               				String phn_no 	  = rs.getString("Phone");
		               				txtPhone.setText(phn_no);
		               				
		               										        
							        String address   = rs.getString("Address");
							        txtAddress.setText(address);
							        
							        
							        
							        String gender	  = rs.getString("Gender");
							        setGender (gender);
							        
							        

							        String bdate	  = rs.getString("DOB");
							        setDate (bdate);							        
							        
							      } 
							    
							    else
							    {
							          JOptionPane.showMessageDialog(null, "User ID do not exist!");
							     }
							    }
						    catch(Exception ex)
						    {
							           JOptionPane.showMessageDialog(null, ex.getMessage());
						    }
								  
								
							}
			
		});
		
		update.addActionListener(new ActionListener() 
		{
				public void actionPerformed(ActionEvent e1) 
					{
						    try{
					
							    
						        
							    	 String value1=txtName.getText();
							         String value2=txtPhone.getText();
							         String value3=txtAddress.getText();
							         String value4=gp.getSelection().getActionCommand();
							         dateOfBirth=getDate();
							    								         
							         String query2 ="update user_info set Name='"+value1+"', Phone='"+value2+"',Address='"+value3+"',Gender='"+value4+"',DOB='"+dateOfBirth+"' where Username='"+txtUname.getText()+"'";
									 PreparedStatement ps3 = (PreparedStatement) connection.prepareStatement(query2);   
									 ps3.executeUpdate();
									 JOptionPane.showMessageDialog(null,"Successfully Updated");
									 dispose();
				    }
						    catch(Exception ex)
						    {
							           JOptionPane.showMessageDialog(null, ex.getMessage());
						    }
								  
								
					}
			
		});
				
	}
	
	
	 public void setGender (String x)
	 {	
		 
		    
			if(x.equals("Male"))
	        {
	        	
	        	 gp.setSelected(MaleRadioButton.getModel(), true);
	        }
	        else
	        {
	        	
	        	gp.setSelected(FemaleRadioButton.getModel(), true);
	        }
		  	        
	        
	 }
	 
	 public void setDate (String x)
	 {	
		 String temp = x;
		 String[] output = temp.split("-");
		 String year = output[0];
		 String month = output[1];
		 String date = output[2];
		 
		
		 if(month.equals("01"))
         {
       	  month="January";
         }
		 else if(month.equals("02"))
         {
       	  month="February";
         }
		 else if(month.equals("03"))
         {
       	  month="March";
         }
		 else if(month.equals("04"))
         {
       	  month="April";
         }
		 else if(month.equals("05"))
         {
       	  month="May";
         }
		 else if(month.equals("06"))
         {
       	  month="June";
         }
		 else if(month.equals("07"))
         {
       	  month="July";
         }
		 else if(month.equals("08"))
         {
       	  month="August";
         }
		 else if(month.equals("09"))
         {
       	  month="Sepember";
         }
         
		 else if(month.equals("10"))
         {
       	  month="October";
         }
		 else if(month.equals("11"))
         {
       	  month="November";
         }
		 else if(month.equals("12"))
         {
       	  month="December";
         }
		 DateComboBox.setSelectedItem(date);
		 MonthComboBox.setSelectedItem(month);
		 YearComboBox.setSelectedItem(year);
		 
		 
	 }
	 
	  public String getDate()
      {
          String date  =DateComboBox.getSelectedItem().toString();
          String month =MonthComboBox.getSelectedItem().toString();
          String year  = YearComboBox.getSelectedItem().toString();
          String dob;
          
          if(month=="January")
          {
        	  month="JAN";
          }
          else if(month=="February")
          {
        	  month="FEB";
          }
          else if(month=="March")
          {
        	  month="MAR";
          }
          else if(month=="April")
          {
        	  month="APR";
          }
          else if(month=="May")
          {
        	  month="MAY";
          }
          else if(month=="June")
          {
        	  month="JUN";
          }
          else if(month=="July")
          {
        	  month="JUL";
          }
          else if(month=="August")
          {
        	  month="AUG";
          }
          else if(month=="September")
          {
        	  month="SEP";
          }
          else if(month=="October")
          {
        	  month="OCT";
          }
          else if(month=="November")
          {
        	  month="NOV";
          }
          else if(month=="December")
          {
        	  month="DEC";
          }
          
          
          dob = date+"-"+month+"-"+year ;
          
          return dob;
          
      }
	  
	  
	 
}
	
	
	