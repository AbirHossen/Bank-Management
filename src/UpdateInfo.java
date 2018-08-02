import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.*;

import net.proteanit.sql.DbUtils;


public class UpdateInfo extends JFrame {
	
	// declaration of all thr components 
	
		JLabel labelCustomeInfo             = new JLabel("Customer Information Update");		
        JLabel 		labelCurrentDate        = new JLabel("Current Date :");
		JLabel 		labelDate               = new JLabel();
		JLabel 		labelAccName            = new JLabel("Account Number");
		JLabel 		labelName               = new JLabel("Name");
		JLabel 		labelDateBirth          = new JLabel("Date of Birth");
		JLabel 		labelPhnNo              = new JLabel("Phone No.");
		JLabel 		labelAddress            = new JLabel("Address");
		JLabel 		labelDistrict           = new JLabel("District");
		JLabel 		labelState              = new JLabel("State");
		JLabel 		labelMstatus 			= new JLabel("Marital Status");
		JLabel 		labelGender 			= new JLabel("Gender");
		JLabel      labelFather  			= new JLabel("Father's Name");
		JLabel 		labelMother 			= new JLabel("Mother's Name");
		JLabel		labelBalance			= new JLabel("Balance");
		JLabel 		labelPhoto 			    = new JLabel();
		JLabel      labelMail 				= new JLabel("E-mail");
		JTextField  txtAccNum               = new JTextField();
		JTextField  txtName                 = new JTextField();
		JTextField  txtPhone                = new JTextField();
		JTextField  txtAddress              = new JTextField();
		JTextField  txtDistrict             = new JTextField();
		JTextField  txtState                = new JTextField();
		JTextField  txtMother               = new JTextField();
		JTextField  txtFather   			= new JTextField();
		JTextField  txtBalance   			= new JTextField();
		JTextField 	txtMail    		        = new JTextField();
	    
	    JRadioButton MaleRadioButton 		= new JRadioButton("Male");
		JRadioButton FemaleRadioButton 		= new JRadioButton("Female");
	    JRadioButton MarriedRadioButton 	= new JRadioButton("Married");
		JRadioButton UnmarriedRadioButton   = new JRadioButton("Unmarried");
		
	   
		JButton btPhotoUp 					= new JButton("Upload \r\n\r\nPhoto");
		JButton btSubmit 					= new JButton("Submit");
		JButton btCancel 					= new JButton("Cancel");
		JButton btDetails 					= new JButton("Details");
		JButton btSearch 					= new JButton("Search");
		
		JComboBox MonthComboBox				= new JComboBox();
		JComboBox YearComboBox 				= new JComboBox();
		JComboBox DateComboBox 				= new JComboBox();
		JTable tableA 						= new JTable();
		
		
		ButtonGroup gp						= new ButtonGroup();
		ButtonGroup mp						= new ButtonGroup();
		Calendar 	cal                     =new GregorianCalendar ();
		
		
	
		Connection connection=null;
		String s;
		String photoPath ;
		
		String dateOfBirth;

		/**
		 * here is the constructor and all the declarations 
		 * here the frame is fixed and not resizable
		 * the background image path is declared
		 */
		
	public UpdateInfo() {
	
		

		setBounds(100, 100, 1360, 768);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		JLabel label = new JLabel(new ImageIcon("C:\\Users\\Uchchhwash\\workspace\\Java Project\\Images\\UpdateInfo.jpg"));
		label.setFont(new Font("Agency FB", Font.BOLD, 24));
		setContentPane(label);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		labelCustomeInfo.setFont(new Font("Tahoma", Font.BOLD, 30));
		labelCustomeInfo.setBounds(10, 0, 475, 62);
		getContentPane().add(labelCustomeInfo);
		
		
		int day=cal.get(Calendar.DAY_OF_MONTH);
		int month=cal.get(Calendar.MONTH);
		month+=1;
		int year=cal.get(Calendar.YEAR);
		
		
		labelCurrentDate.setFont(new Font("Agency FB", Font.BOLD, 30));
		labelCurrentDate.setBounds(1042, 0, 150, 43);
		getContentPane().add(labelCurrentDate);
		

		labelDate.setFont(new Font("Agency FB", Font.BOLD, 30));
		labelDate.setBounds(1192, 0, 152, 43);
		getContentPane().add(labelDate);
		labelDate.setText(""+day+"/"+month+"/"+year);
		
	
		labelAccName.setFont(new Font("Agency FB", Font.BOLD, 24));
		labelAccName.setBounds(20, 70, 160, 35);
		getContentPane().add(labelAccName);
		

		labelName.setFont(new Font("Agency FB", Font.BOLD, 24));
		labelName.setBounds(20, 120, 96, 35);
		getContentPane().add(labelName);
		
	
		labelDateBirth.setFont(new Font("Agency FB", Font.BOLD, 24));
		labelDateBirth.setBounds(20, 170, 120, 40);
		getContentPane().add(labelDateBirth);
		

		labelPhnNo.setFont(new Font("Agency FB", Font.BOLD, 24));
		labelPhnNo.setBounds(20, 400, 96, 45);
		getContentPane().add(labelPhnNo);
		
		
		labelAddress.setFont(new Font("Agency FB", Font.BOLD, 24));
		labelAddress.setBounds(20, 527, 96, 30);
        getContentPane().add(labelAddress);
		

		labelDistrict.setFont(new Font("Agency FB", Font.BOLD, 24));
		labelDistrict.setBounds(20, 596, 96, 30);
		getContentPane().add(labelDistrict);
		
		
		labelState.setFont(new Font("Agency FB", Font.BOLD, 24));
		labelState.setBounds(20, 647, 46, 30);
		getContentPane().add(labelState);
		txtAccNum.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
	
		txtAccNum.setColumns(10);
		txtAccNum.setBounds(167, 73, 240, 31);
		getContentPane().add(txtAccNum);
		txtName.setFont(new Font("Agency FB", Font.PLAIN, 20));
		

		txtName.setColumns(10);
		txtName.setBounds(167, 123, 240, 31);
		getContentPane().add(txtName);
		txtPhone.setFont(new Font("Agency FB", Font.PLAIN, 20));

		txtPhone.setColumns(10);
		txtPhone.setBounds(164, 408, 250, 31);
		getContentPane().add(txtPhone);
		txtAddress.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
	
		txtAddress.setName("Date");
		txtAddress.setColumns(10);
		txtAddress.setBounds(164, 505, 250, 74);
		getContentPane().add(txtAddress);
		txtDistrict.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		
		txtDistrict.setColumns(10);
		txtDistrict.setBounds(164, 597, 250, 31);
		getContentPane().add(txtDistrict);
		txtState.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		
		txtState.setColumns(10);
		txtState.setBounds(164, 647, 250, 31);
		getContentPane().add(txtState);
		
		
		labelMstatus.setFont(new Font("Agency FB", Font.BOLD, 24));
		labelMstatus.setBounds(20, 363, 150, 25);
		getContentPane().add(labelMstatus);
		
		labelGender.setFont(new Font("Agency FB", Font.BOLD, 24));
		labelGender.setBounds(20, 306, 96, 25);
		getContentPane().add(labelGender);
		MaleRadioButton.setFont(new Font("Agency FB", Font.BOLD, 20));
		
	
		MaleRadioButton.setBounds(171, 301, 58, 33);
		MaleRadioButton.setContentAreaFilled(false);
		getContentPane().add(MaleRadioButton);
		gp.add(MaleRadioButton);
		MaleRadioButton.setActionCommand("Male");
		FemaleRadioButton.setFont(new Font("Agency FB", Font.BOLD, 20));
		

		FemaleRadioButton.setBounds(282, 301, 88, 33);
		FemaleRadioButton.setContentAreaFilled(false);
		getContentPane().add(FemaleRadioButton);
		gp.add(FemaleRadioButton);
		FemaleRadioButton.setActionCommand("Female");
		MarriedRadioButton.setFont(new Font("Agency FB", Font.BOLD, 20));
		
		

		MarriedRadioButton.setBounds(171, 367, 109, 23);
		MarriedRadioButton.setContentAreaFilled(false);
		getContentPane().add(MarriedRadioButton);
		mp.add(MarriedRadioButton);
		MarriedRadioButton.setActionCommand("Married");
		UnmarriedRadioButton.setFont(new Font("Agency FB", Font.BOLD, 20));
		

		UnmarriedRadioButton.setBounds(282, 367, 109, 23);
		UnmarriedRadioButton.setContentAreaFilled(false);
		getContentPane().add(UnmarriedRadioButton);
		mp.add(UnmarriedRadioButton);
		UnmarriedRadioButton.setActionCommand("Unmarried");
		txtFather.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		
		
		txtFather.setColumns(10);
		txtFather.setBounds(167, 221, 247, 31);
	    getContentPane().add(txtFather);
		txtMother.setFont(new Font("Agency FB", Font.PLAIN, 20));
		

		txtMother.setColumns(10);
		txtMother.setBounds(167, 263, 247, 31);
		getContentPane().add(txtMother);
		txtBalance.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		
		txtBalance.setHorizontalAlignment(SwingConstants.CENTER);
		txtBalance.setColumns(10);
		txtBalance.setBounds(551, 332, 120, 31);
		txtBalance.setEnabled(false);
		getContentPane().add(txtBalance);
		

		labelFather.setFont(new Font("Agency FB", Font.BOLD, 24));
		labelFather.setBounds(20, 220, 429, 30);
		getContentPane().add(labelFather);
		labelMother.setVerticalAlignment(SwingConstants.TOP);
		

		labelMother.setFont(new Font("Agency FB", Font.BOLD, 24));
		labelMother.setBounds(20, 264, 428, 25);
		getContentPane().add(labelMother);
		
	
		labelBalance.setFont(new Font("Agency FB", Font.BOLD, 24));
		labelBalance.setBounds(473, 334, 75, 25);
		getContentPane().add(labelBalance);
		
	
		labelPhoto.setLayout(null);
		labelPhoto.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelPhoto.setBounds(585, 73, 160, 150);
		getContentPane().add(labelPhoto);
		
		btPhotoUp.setFont(new Font("Agency FB", Font.BOLD, 24));
		btPhotoUp.setBounds(590, 239, 150, 43);
		getContentPane().add(btPhotoUp);
		btSubmit.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		
		btSubmit.setBounds(490 ,670, 120, 43);
		getContentPane().add(btSubmit);
		btCancel.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		btCancel.setBounds(650 ,670, 120, 43);
		getContentPane().add(btCancel);
		btDetails.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		btDetails.setBounds(426, 69, 112, 34);
		getContentPane().add(btDetails);
		btSearch.setFont(new Font("Agency FB", Font.BOLD, 24));
		
		btSearch.setBounds(426, 122, 112, 34);
		getContentPane().add(btSearch);
			
		labelMail.setFont(new Font("Agency FB", Font.BOLD, 24));
		labelMail.setBounds(20, 462, 96, 30);
		getContentPane().add(labelMail);
		txtMail.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		
		txtMail.setColumns(10);
		txtMail.setBounds(164, 463, 250, 31);
		getContentPane().add(txtMail);
		DateComboBox.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		DateComboBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		DateComboBox.setBounds(167, 173, 46, 32);
		getContentPane().add(DateComboBox);
		MonthComboBox.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		
		MonthComboBox.setModel(new DefaultComboBoxModel(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		MonthComboBox.setBounds(233, 173, 83, 32);
		getContentPane().add(MonthComboBox);
		YearComboBox.setFont(new Font("Agency FB", Font.PLAIN, 20));
		
		
		YearComboBox.setModel(new DefaultComboBoxModel(new String[] {"1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050"}));
		YearComboBox.setBounds(337, 173, 70, 32);
		getContentPane().add(YearComboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(789, 78, 475, 602);
		getContentPane().add(scrollPane);
		
		tableA = new JTable();
		scrollPane.setViewportView(tableA);
		tableA.setEnabled(false);
		
		
		// here is the actions of button click and database uml operation against click
		
		btCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				dispose();
			}
		});
		
		connection= DatabaseConnection.dbConnector();
		
		btDetails.addActionListener(new ActionListener() 
		{
				public void actionPerformed(ActionEvent e1) 
					{
						    try{
					
							    ResultSet rs = null;
						    	String query = "select * from customer_info where Account_No = ?";
								PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
								ps.setString(1,txtAccNum.getText());
								rs = ps.executeQuery();
							    if(rs.next())
							    {
							          
							    	String name=rs.getString("Name");
							    	txtName.setText(name);
							    	
							    	String date      = rs.getString("DOB");
								    setDate(date);
							    	
		               				String phn_no 	  = rs.getString("Phone_No");
		               				txtPhone.setText(phn_no);
		               				
		               				String email      = rs.getString("Email");
							        txtMail.setText(email);
		               				
		               										        
							        String address   = rs.getString("Address");
							        txtAddress.setText(address);
							        
							        String district   = rs.getString("District");
							        txtDistrict.setText(district);
							        
							        String state 	  = rs.getString("State");
							        txtState.setText(state);
							        
							        String gender	  = rs.getString("Gender");
							        setGender (gender);
							        
							        String marital = rs.getString("Marital_Status");
							        setMarital(marital);	
							        
							        String father 	  = rs.getString("Father_Name");
							        txtFather.setText(father);
							        
							        String mother     = rs.getString("Mother_Name");
							        txtMother.setText(mother);
							        
							        String balance    = rs.getString("Balance");
							        txtBalance.setText(balance);
							        
							        photoPath    = rs.getString("Photo_Path");
							        setImage();
							        			        			       						        							          
							      }  
							    else
							    {
							          JOptionPane.showMessageDialog(null, "No Data found for the Account ID Given!");
							    }
							   }
						    catch(Exception ex)
						    {
							           JOptionPane.showMessageDialog(null, ex.getMessage());
						    }
								  								
					}
			
		});
		
		btSubmit.addActionListener(new ActionListener() 
		{
				public void actionPerformed(ActionEvent e1) 
					{
					 try
						{
						 dateOfBirth=getDate();
						 System.out.println(photoPath);
						 saveImage();
					
						 String query2="update customer_info set Name='"+txtName.getText()+"',DOB='"+dateOfBirth+"',Phone_No='"+txtPhone.getText()+"',Email='"+txtMail.getText()+"',Address='"+txtAddress.getText()+"',District='"+txtDistrict.getText()+"',State='"+txtState.getText()+"',Gender='"+gp.getSelection().getActionCommand()+"',Marital_Status='"+mp.getSelection().getActionCommand()+"',Father_Name='"+txtFather.getText()+"',Mother_Name='"+txtMother.getText()+"',Balance="+Double.parseDouble(txtBalance.getText())+"where Account_No="+Integer.parseInt(txtAccNum.getText());
						 PreparedStatement ps3 = (PreparedStatement) connection.prepareStatement(query2);
					
						 
						 ps3.execute();
						 
						 String q3="update customer_info set photo_path='"+photoPath.toString()+"' where account_no="+Integer.parseInt(txtAccNum.getText());
						 PreparedStatement ps4 = (PreparedStatement) connection.prepareStatement(q3);
						 ps4.execute();
						 
						 JOptionPane.showMessageDialog(null,"Successfully Updated");
						 dispose();
						
						}
					catch(Exception e)
						{
							JOptionPane.showMessageDialog(null,e);
						}
				}
			});
	
		
		btPhotoUp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e1) {
						getPic();
					}
		});
		
		
		btSearch.addActionListener(new ActionListener() 
		{
				public void actionPerformed(ActionEvent e1) 
					{
					 try
						{

						 	String query ="select Account_No,Name,DOB,Phone_No,Email,Address from customer_info where  Name=?";
						 	PreparedStatement pst = (PreparedStatement) connection.prepareStatement(query);
						 	pst.setString(1, txtName.getText());
						 	ResultSet rs=pst.executeQuery();
						 	tableA.setModel(DbUtils.resultSetToTableModel(rs));
						
						 
						}
					 catch(Exception e)
						{
							JOptionPane.showMessageDialog(null,e);
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
	 
	 public void setMarital(String x)
	 {	
		 
		    if(x.equals("Married"))
	        {
	        	
	        	 mp.setSelected(MarriedRadioButton.getModel(), true);
	        }
	        else
	        {
	        	
	        	mp.setSelected(UnmarriedRadioButton.getModel(), true);
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
	 
	 public void setImage()
	 {	
				labelPhoto.setIcon(ResizeImage(photoPath));
				s=photoPath;
	 }
	 
		public void getPic()
		{
				JFileChooser filechooser= new JFileChooser();
				filechooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE","jpg","gif","png");
				int result = filechooser.showSaveDialog(null);
				if(result== JFileChooser.APPROVE_OPTION){
					File selectedFile=filechooser.getSelectedFile();
					String path=selectedFile.getAbsolutePath();
					labelPhoto.setIcon(ResizeImage(path));
					s=path;
					System.out.println(s);

				}
				
				else if(result==JFileChooser.CANCEL_OPTION){

				}
				
		}
		
		  public ImageIcon ResizeImage(String imgPath)
		  {
		        ImageIcon MyImage = new ImageIcon(imgPath);
		        Image img = MyImage.getImage();
		        Image newImage = img.getScaledInstance(labelPhoto.getWidth(), labelPhoto.getHeight(),Image.SCALE_SMOOTH);
		        ImageIcon image = new ImageIcon(newImage);
		        return image;
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
		  
		  public void saveImage(){
			  
				 BufferedImage image = null;
				    File f = null;

				    try
				    {
				      f = new File(s);
				      image = ImageIO.read(f);
				      				    		  
				    }
				    catch(IOException e)
				    {
				      System.out.println("Error: "+e);
				    }
				    photoPath="C:\\Users\\Uchchhwash\\workspace\\Java Project\\Customer Pictures\\"+txtAccNum.getText().toString()+".jpg";
			
				    try
				    {
				      f = new File(photoPath);
				      ImageIO.write(image, "jpg", f);
				    }
				    catch(IOException e){
				      System.out.println("Error: "+e);
				    }
		  }
		  
}
