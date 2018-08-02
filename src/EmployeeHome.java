/**
 * this class contains the homepage of an Employee
 * when an employee will login this interface will come 
 */

import javax.swing.*;
import java.awt.EventQueue;
import java.awt.Font;
import java.time.DayOfWeek;
import java.awt.Component;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class EmployeeHome extends JFrame
{			
	JLabel txtHomeInfo = new JLabel("USN BANK LTD");
	JLabel showDate = new JLabel ();
	Calendar cal=new GregorianCalendar();
	
	/**
	 * here is the constructor and all the declarations 
	 * here the frame is fixed and not resizable
	 * the background image path is declared
	 */
	
	public EmployeeHome()
	 	{

			 setVisible(true);
			 setTitle("USN BANK LTD");
			 setSize(1360,768);
			 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 setFocusable(true);
			 setContentPane(new JLabel(new ImageIcon("C:\\Users\\Uchchhwash\\workspace\\Java Project\\Images\\EmployeeHome.jpg")));
			 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			 this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
			 setResizable(false);
			
			 txtHomeInfo.setForeground(new Color(10, 18, 145));
			 txtHomeInfo.setFont(new Font("Agency FB", Font.BOLD, 70));
			 txtHomeInfo.setBounds(444,11, 500, 66);
			 
			 getContentPane().add(txtHomeInfo);
			 
			 JMenuBar menubar= new JMenuBar();
			 setJMenuBar(menubar);
			 
			 JMenu account = new JMenu ("Account");
			 account.setFont(new Font("Agency FB", Font.BOLD, 26));
			 menubar.add(account);
			 
			 JMenu transaction = new JMenu ("Transaction");
			 transaction.setFont(new Font("Agency FB", Font.BOLD, 26));
			 menubar.add(transaction);
			 
			 JMenu view = new JMenu ("View");
			 view.setFont(new Font("Agency FB", Font.BOLD, 26));
			 menubar.add(view);
			 
			 JMenu other = new JMenu ("Others");
			 other.setFont(new Font("Agency FB", Font.BOLD, 26));
			 menubar.add(other);
			 
			 JMenuItem accNew= new JMenuItem("New Account");
			 accNew.setFont(new Font("Agency FB", Font.PLAIN, 24));
			 account.add(accNew);
			
			 JMenuItem accSU= new JMenuItem("Search/Update Account");
			 accSU.setFont(new Font("Agency FB", Font.PLAIN, 24));
			 account.add(accSU);
			 
			 JMenuItem allCustomers= new JMenuItem("All Customers");
			 allCustomers.setFont(new Font("Agency FB", Font.PLAIN, 24));
			 account.add(allCustomers);
			 
			
			 JMenuItem deposit = new JMenuItem("Deposit");
			 deposit.setFont(new Font("Agency FB", Font.PLAIN, 24));
			 	transaction.add(deposit);
			 
			 JMenuItem withdraw = new JMenuItem("Withdraw");
			 withdraw.setFont(new Font("Agency FB", Font.PLAIN, 24));
			 	transaction.add(withdraw);
			 
			 JMenuItem transfer = new JMenuItem("Transfer");
			 transfer.setFont(new Font("Agency FB", Font.PLAIN, 24));
				transaction.add(transfer);
			 
		     JMenuItem fdForm = new JMenuItem("FD Form");
		     fdForm.setFont(new Font("Agency FB", Font.PLAIN, 24));
		    	transaction.add(fdForm);
			 
			 JMenuItem loan = new JMenuItem("Loan");
			 loan.setFont(new Font("Agency FB", Font.PLAIN, 24));
				transaction.add(loan);
			 
		   	 JMenuItem loanPayment = new JMenuItem("Loan Payment");
		   	 loanPayment.setFont(new Font("Agency FB", Font.PLAIN, 24));
				transaction.add(loanPayment);
					
			 JMenuItem tHistory = new JMenuItem("Transaction History");
			 tHistory.setFont(new Font("Agency FB", Font.PLAIN, 24));
				view.add(tHistory);
					
			 JMenuItem chkFd = new JMenuItem("FD(s)");
			 chkFd.setFont(new Font("Agency FB", Font.PLAIN, 24));
				view.add(chkFd);
					
			 JMenuItem chkLoan = new JMenuItem("Loan(s)");
			 chkLoan.setFont(new Font("Agency FB", Font.PLAIN, 24));
				view.add(chkLoan);

					
			 JMenuItem logOut = new JMenuItem("Log Out");
			 logOut.setFont(new Font("Agency FB", Font.PLAIN, 24));
				other.add(logOut);
					
			 JMenuItem exit = new JMenuItem("Exit");
			 exit.setFont(new Font("Agency FB", Font.PLAIN, 24));
				other.add(exit);
					
			 JMenuItem showLoanPayments = new JMenuItem("Show Loan Payment");
			 showLoanPayments.setFont(new Font("Agency FB", Font.PLAIN, 24));
				view.add(showLoanPayments);
				
					
					// there are the actions of mouse click on the items of Jmenu
					
					accNew.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							CreateNewAcc ac1 = new CreateNewAcc();
							
						}
					});
					
					accSU.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							UpdateInfo ac2 = new UpdateInfo();
						}
					});
					
					allCustomers.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e2) {
							AllCustomerDetails asd = new AllCustomerDetails();
						}
					});
					
					deposit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							Deposit ac3 = new Deposit();
						}
					});
					
					withdraw.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							Withdraw ac4 = new Withdraw();
						}
					});
					
					transfer.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							Transfer ac5 = new Transfer();
						}
					});
					
					fdForm.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							FdForm ac6 = new FdForm();
						}
					});
					
					loan.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							LoanForm ac7 = new LoanForm();
						}
					});
					
					loanPayment.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							LoanPayment ac8 = new LoanPayment();
						}
					});
					
					
					
					
					exit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							System.exit(0);
						}
					});
					

					
					logOut.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							dispose(); 
							Login l1 = new Login ();
						}
					});
					
					
					tHistory.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							TransactionHistory d1 = new TransactionHistory();
							
						}
					});
					chkLoan.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							ShowLoan d1 = new ShowLoan();
							
						}
					});
					
					chkFd.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							ShowFd d1 = new ShowFd();
							
						}
					});
					
					showLoanPayments.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e1) {
							ShowLoanPayment d1 = new ShowLoanPayment();
							
						}
					});
					
					
					int day=cal.get(Calendar.DAY_OF_MONTH);
					int month=cal.get(Calendar.MONTH);
					month+=1;
					int year=cal.get(Calendar.YEAR);
					
					
					showDate.setFont(new Font("Agency FB", Font.BOLD, 70));
					showDate.setForeground(new Color(10, 18, 145));
					showDate.setBounds(480,112, 456, 66);
					
					
					showDate.setText(day+"/"+month+"/"+year);
					getContentPane().add(showDate);
					
					

	 	}
					
}