import java.io.*;
import java.util.Scanner;

public class Bank6 {
	public static void main(String[] args) throws IOException {
		File myfile = new File("tests");
		Scanner input = new Scanner(myfile);
		PrintWriter output = new PrintWriter("out66");
		boolean not_done = true;
		String line;
		char choice;
		int sig;
		Bank bank = new Bank();

		output.println("Vannisa Pascal-Nelson");
		output.println();

		sig = readAccts(bank);
		printAccts(bank, output);
		if (sig != -1) {
			do {
				menu();
				line = input.next();
				choice = line.charAt(0);
				// gets the user selection
				switch (choice) {
				case 'B':
				case 'b':
					balance(bank, output, input);
					output.println();
					break;
				case 'i':
				case 'I':
					acctInfo(bank, output, input);
					output.println();
					break;
				case 'H':
				case 'h':
					acctInfoATH(bank, output, input);
					output.println();
					break;
				case 's':
				case 'S':
					closeAcct(bank, output, input);
					output.println();
					break;
				case 'r':
				case 'R':
					reopenAcct(bank, output, input);
					output.println();
					break;
				case 'n':
				case 'N':
					newAcct(bank, output, input);
					output.println();
					break;
				case 'w':
				case 'W':
					withdraw(bank, output, input);
					output.println();
					break;
				case 'd':
				case 'D':
					deposit(bank, output, input);
					output.println();
					break;
				case 'x':
				case 'X':
					deleteAcct(bank, output, input);
					output.println();
					break;
				case 'c':
				case 'C':
					clearCheck(bank, output, input);
					output.println();
					break;
				case 'Q':
				case 'q':
					printAccts(bank, output);
					printTrans( bank, output);
					output.flush();
					not_done = false;
					break;
				default:
					output.println(choice + " is an Invalid selection!");
					output.println();
				}
			} while (not_done);
		} else
			output.println("Error! Database was not updated!");
		
	output.close();
	}
	/*menu()
	* Input 
	*  nothing 
	* Process 
	*  prints the menu
	* Output 
	*   prints the menu
	*/
	public static void menu() {
		System.out.println("\nSelect one of the following: " 
				+ "\n\tW-Withdrawal\n\tD-Deposit\n\t"
				+ "N-New Account\n\tB-Balance\n\tX-Delete Account\n\t"
				+ "C-Clear Check\n\tI-Account Info\n\tH-Account Info "
				+ "+ Account Transaction History\n\tS-Close Account"
				+ "\n\tR-Reopen a closed account\n\tQ-Quit\n");
	}
	/*readAccts()
	* Input 
	*  b- bank object
	* Process 
	*  reads in info into arraylist of objects
	* Output 
	*   bank object full of info
	*/
	public static int readAccts(Bank b) throws IOException {
		File myfile = new File("database4");
		Scanner input = new Scanner(myfile);
		int acctNum, acctStat, sig = -1, cm, cd, cy;
		String first, last, soc, acTyp;
		double bal;

		while (input.hasNext()) {
			sig = 1;
			acctNum = input.nextInt();
			bal = input.nextDouble();
			acTyp = input.next();
			soc = input.next();
			first = input.next();
			last = input.next();
			acctStat = input.nextInt();
			//to read in data for CD
			if (acTyp.equals("CD")) {
				cm = input.nextInt();
				cd = input.nextInt();
				cy = input.nextInt();
				Name person = new Name(first, last);
				Depositor depo = new Depositor(person, soc);
				Account at = new Account(depo, acctNum, acTyp,
						bal, cm, cd, cy, acctStat);
				b.addAcct(at);
			} else {
				Name person = new Name(first, last);
				Depositor depo = new Depositor(person, soc);
				Account at = new Account(depo, acctNum, acTyp,
						bal, acctStat);
				b.addAcct(at);
			}
		}
		input.close();
		return sig;
	}
	/*printAccts()
	* Input 
	*  b- bank object
	*  out-reference to output file 
	* Process 
	*  prints the arraylist of objects (account)
	* Output 
	*  prints the arraylist of objects (account)
	*/
	public static void printAccts(Bank b, PrintWriter out) {
		Name myName = new Name();
		Depositor myDepositor = new Depositor();
		Account myAccount = new Account();

		out.println();
		out.printf("%9s", "Account #");
		out.printf("%10s", "Balance");
		out.printf("%10s", "Type");
		out.printf("%10s", "Social");
		out.printf("%9s", "Name");
		out.printf("%10s", "Surname");
		out.printf("%8s", "Status");
		out.printf("%15s", "Maturity Date");
		out.println();
		// prints neat table
	
		out.println(b);
		out.println();
		
		out.println("Total Amount In Checkings Accounts"+Bank.getTotalAmountInCheckingsAccts());
		out.println("Total Amount In Savings Accounts"+Bank.getTotalAmountInSavingsAccts());
		out.println("Total Amount In CD Accounts"+Bank.getTotalAmountInCDAccts());
		out.println("Total Amount In All Accounts"+Bank.getTotalAmountInAllAccts());
		
		out.println();
	}
	
	
	
	
	
	public static void printTrans(Bank b, PrintWriter out) {
		
			
				for (int i = 0; i < b.getNumAccts(); i++) {
					Account a = b.getAccount(i);
					out.println("Account #" + a.getAccountNum());
					out.println("Account Type: " + a.getAccountType());
					out.println();
					for (int j = 0; j < a.getTransSize(); j++) {
						Transactions t = a.getTrans(j);
						out.println("Transaction #" + (j + 1));
						out.println("Date: " + t.getTransDate());
						out.println("Type:" + t.getTransTyp());

						if (t.getTransTyp().equals("Delete Account")
							||t.getTransTyp().equals("Reopen Account")||
							t.getTransTyp().equals("Close Account")) {}
						else {
							out.println("Amount: " + t.getTransAmount());
						}
						if ((t.getTransTyp().equals("Deposit")
								||t.getTransTyp().equals("Withdraw"))
									&& a.getAccountType().equals("CD") &&t.getSucess()==true) {
								out.println("New Maturtiy Date: "
										+ t.getNewMD());
								}
								
						if (t.getSucess()) {
							out.println("Transaction Successful");
						} else {
							out.println("Transaction Failed");
						}
						out.println(t.getReason());
						out.println();
						}
					out.println();
						
					}
				out.println();
					
				

}	
	/*balance()
	* Input 
	*  b- bank object
	*  out-reference to output file 
	*  infile-reference to input file
	* Process 
	*  calls account class method 
	*  	to search through arraylist
	* Output 
	*  finds the balance of an account
	*/
	public static void balance(Bank bank, PrintWriter out, Scanner infile) {
		int acct, index;
		double amount;
		boolean flag = false;
		String reason, transDate;
		String ttyp = "Balance";
		DateInfo mD = new DateInfo();

		transDate = (mD.getMonth()+1) + "/" + mD.getDayOfMonth()
			+ "/" + mD.getYear();
		System.out.println("Please enter the account number");
		acct = infile.nextInt();
		out.println("Transcation Type: " + ttyp);
		index = bank.findAcct(acct);
		out.println("Account Number: " + acct);

		if (index != -1) {
			Account temp = bank.getAccount(index);
			if (temp.getStatus() == 1) {
				flag = true;
				out.println("Account Type: " + temp.getAccountType());
				amount = temp.getBalance();
				out.println("Balance: " + amount);
				reason = "Account exist & is open.";
				//add transaction
				Transactions tran = new Transactions(ttyp, amount, 
						transDate, flag, reason);
				temp.addTrans(tran);
			} else if (temp.getStatus() == 0) {
				reason = "Error! The account is closed!";
				out.println(reason);
				//adds transaction
				Transactions tran = new Transactions(ttyp, 
						transDate, flag, reason);
				temp.addTrans(tran);
			}
		} else if (index == -1) {
			out.println("Error! The account  does not exist!");
		}
		out.println();
	}
	/*acctInfo()
	 * Input 
	 *  b- bank object
	 *  out-reference to output file 
	 *  infile-reference to input file
	 * Process 
	 *  search through arraylist for req social
	 *  calls account class method get acount info
	 * Output 
	 *  a account info transaction
	 */
	public static void acctInfo(Bank bank, PrintWriter out, 
			Scanner infile) {
		int acct, m , dayy, y;
		String social, f, l;
		boolean found = false, flag = false;
		double amount;
		String reason, temp;
		String ttyp = "Account Info";
		DateInfo mD = new DateInfo();
		String transDate =( mD.getMonth()+1) + "/" + mD.getDayOfMonth()
			+ "/" + mD.getYear();

		System.out.println("Please enter your social");
		social = infile.next();
		out.println("\nTranscation Type: " + ttyp);
		out.println("Social: " + social);
		for (int i = 0; i < bank.getNumAccts(); i++) {
			Account a = bank.getAccount(i);
			if (social.equals(a.getDepositor().getSocSecNum())) {
				out.println("\nAccount Number: " + a.getAccountNum());
				if (a.getStatus() == 1) {
					found = flag = true;
					
					amount = a.getBalance();
					found = flag = true;
					acct=a.getAccountNum();
					f=a.getDepositor().getName().getFirst();
					l=a.getDepositor().getName().getLast();
					temp=a.getAccountType();
					int stat=a.getStatus();
					
					if (temp.equals("CD")) {
						m=a.getDate().getMonth();
						dayy=a.getDate().getDayOfMonth();
						y=a.getDate().getYear();
						out.println(a.toString(acct, amount,temp,social, f, l,stat, m,dayy, y));	
					}
					else {
						out.println(a.toString(acct, amount,temp,social, f, l,stat));
					}
					reason = "Account exit & is open";
					out.println();
					Transactions tran = new Transactions(ttyp, amount, 
							transDate, flag, reason);
					a.addTrans(tran);
				} else if (a.getStatus() == 0) {
					found = true;
					reason = "Error! The account is closed!";
					out.println(reason);
					Transactions tran = new Transactions(ttyp, transDate,
							flag, reason);
					a.addTrans(tran);
				}
			}
		}
		if (found == false) {
			out.println("Error! The account with the social was not found!");
		}
		out.println();
	}
	/*acctInfoATH()
	 * Input 
	 *  b- bank object
	 *  out-reference to output file 
	 *  infile-reference to input file
	 * Process 
	 *  search through arraylist for req social
	 *  	calls account class method get acount info
	 *  	& prints each account trans history 
	 * Output 
	 *  a account info w/ trans history transaction
	 */
	public static void acctInfoATH(Bank bank, PrintWriter out, 
			Scanner infile) {
		String social;
		boolean found = false, flag = false;
		double amount;
		String reason, temp;
		String ttyp = "Account Info with Account Transaction History";
		DateInfo mD = new DateInfo();
		String transDate =( mD.getMonth()+1) + "/" + mD.getDayOfMonth()
		+ "/" + mD.getYear();
		int acct;
		String f, l;
		int m,d,y;

		System.out.println("Please enter your social");
		social = infile.next();
		out.println("\nTranscation Type: " + ttyp);
		out.println("Social: " + social);
		for (int i = 0; i < bank.getNumAccts(); i++) {
			Account a = bank.getAccount(i);
			if (social.equals(a.getDepositor().getSocSecNum())) {
				out.println("Account Number: " + a.getAccountNum());
				if (a.getStatus() == 1) {
					found = flag = true;
					amount = a.getBalance();
					acct=a.getAccountNum();
					f=a.getDepositor().getName().getFirst();
					l=a.getDepositor().getName().getLast();
					temp=a.getAccountType();
					int stat=a.getStatus();
					
					if (temp.equals("CD")) {
						m=a.getDate().getMonth();
						d=a.getDate().getDayOfMonth();
						y=a.getDate().getYear();
						out.println(a.toString(acct, amount,temp,social, f, l,stat, m, d, y));	
					}
					else {
						out.println(a.toString(acct, amount,temp,social, f, l, stat));
					}
					
					reason = "Account exit & is open";
					out.println();
					out.println();
					Transactions tran = new Transactions(ttyp, amount, 
							transDate, flag, reason);
					a.addTrans(tran);
					
					for (int j = 0; j < a.getTransSize(); j++) {
						Transactions t = a.getTrans(j);
						out.println("Transaction #" + (j + 1));
						out.println("Date: " + t.getTransDate());
						out.println("Type:" + t.getTransTyp());

						if (t.getTransTyp().equals("Delete Account")
								||t.getTransTyp().equals("Reopen Account")||
								t.getTransTyp().equals("Close Account")) {
						}
						else {
							out.println("Amount: " + t.getTransAmount());
						}
						if ((t.getTransTyp().equals("Deposit")
								||t.getTransTyp().equals("Withdraw"))
									&& a.getAccountType().equals("CD")
									&&t.getSucess()==true) {
								out.println("New Maturtiy Date: "
										+ t.getNewMD());
								}
						
						if (t.getSucess()) {
							out.println("Transaction Successful");
						} else {
							out.println("Transaction Failed");
						}
						out.println(t.getReason());
						out.println();
					}
				} else if (a.getStatus() == 0) {
					found = true;
					reason = "Error! The account is closed!";
					out.println(reason);
					Transactions tran = new Transactions(ttyp, 
							transDate, flag, reason);
					a.addTrans(tran);
					out.println();
				}
			}
		} // end of for loop
		if (found == false) {
			out.println("Error! The account with the social "
					+ "was not found!");
		}
		out.println();
	}
	/*closeAcct()
	 * Input 
	 *  b- bank object
	 *  out-reference to output file 
	 *  infile-reference to input file
	 * Process 
	 *	 calls method to find account 
	 *		then closes acct if its 
	 *		open
	 * Output 
	 *  a close account transaction
	 */
	public static void closeAcct(Bank bank, PrintWriter out, 
			Scanner infile) {
		int acct, index;
		String reason;
		String ttyp = "Close Account";
		DateInfo mD = new DateInfo();
		String transDate =  (mD.getMonth() +1)+ "/" + mD.getDayOfMonth()
		+ "/" + mD.getYear();
		boolean flag = false;

		System.out.println("Please enter the account number");
		acct = infile.nextInt();
		out.println("\nTranscation Type: " + ttyp);
		out.println("Account: " + acct);

		index = bank.findAcct(acct);

		if (index != -1) {
			Account a = bank.getAccount(index);
			out.println("Account Type: " + a.getAccountType());

			flag = a.closeAcct();

			out.print("Status:");
			if (flag)
				out.println(" Open");
			else
				out.println(" Closed");

			if (flag) {
				out.print("New Status: Closed");
				reason = "Accoutn was previously open";
				Transactions tran = new Transactions(ttyp, 
						transDate, flag, reason);
				a.addTrans(tran);
			} else {
				reason = "Error! The account is already closed!";
				out.println(reason);
				Transactions tran = new Transactions(ttyp, 
						transDate, flag, reason);
				a.addTrans(tran);
			}

		} else
			out.println("Error! The account does not exist!");

		out.println();
	}
	/*reopenAcct()
	 * Input 
	 *  b- bank object
	 *  out-reference to output file 
	 *  infile-reference to input file
	 * Process 
	 *	 calls method to find account 
	 *		then opens acct if its 
	 *		closed
	 * Output 
	 *  an open account transaction
	 */
	public static void reopenAcct(Bank bank, PrintWriter out, 
			Scanner infile) {
		int acct, index;
		String reason;
		String ttyp = "Reopen Account";
		DateInfo mD = new DateInfo();
		String transDate =   (mD.getMonth()+1) + "/" + mD.getDayOfMonth()
		+ "/" + mD.getYear();
		boolean flag = false;

		System.out.println("Please enter the account number");
		acct = infile.nextInt();
		out.println("\nTranscation Type: " + ttyp);
		out.println("Account: " + acct);
		index = bank.findAcct(acct);

		if (index != -1) {
			Account a = bank.getAccount(index);
			out.println("Account Type: " + a.getAccountType());
			flag = a.reopenAcct();
			out.print("Status:");
			
			if (flag)
				out.println(" Closed");
			else
				out.println(" Open");

			if (flag) {
				out.print("New Status: Open");
				reason = "Accout was previously closed";
				Transactions tran = new Transactions(ttyp, 
						transDate, flag, reason);
				a.addTrans(tran);
			} else {
				reason = "Error! The account is already open!";
				out.println(reason);
				Transactions tran = new Transactions(ttyp,
						transDate, flag, reason);
				a.addTrans(tran);
			}
		} else
			out.println("Error! The account does not exist!");
		out.println();
	}
	/*newAcct()
	* Input 
	*  b- bank object
	*  out-reference to output file 
	*  infile-reference to input file
	* Process 
	*  calls account class method to create acct
	*  	if account does not already exist
	* Output 
	*  a new account transaction
	*/
	public static void newAcct(Bank bank, PrintWriter out, Scanner infile) {
		int newacct, cm, cd, cy, signal, actStat;
		String first, last, soc, actyp, reason;
		String ttyp = "New Account";
		double openAmount;
		boolean flag = false;
		Account at;
		DateInfo mD = new DateInfo();
		String transDate =  (mD.getMonth()+1) + "/" + mD.getDayOfMonth()
		+ "/" + mD.getYear();

		System.out.println("Please enter the new" + " account number: ");
		newacct = infile.nextInt();
		out.println("Transcation Type: " + ttyp);
		signal = bank.openNewAcct(newacct);

		if (signal == -1) {
			flag = true;
			System.out.println("Please enter your" + " first name");
			first = infile.next();
			System.out.println("Please enter your" + " last name.");
			last = infile.next();
			System.out.println("Please enter your" + " social");
			soc = infile.next();
			System.out.println("Please enter the " + "account type");
			actyp = infile.next();
			System.out.println("Please enter the " + "starting balance ");
			openAmount = infile.nextDouble();
			actStat = 1;
			Name individual = new Name(first, last);
			Depositor depo = new Depositor(individual, soc);

			if (actyp.equals("CD")) {
				System.out.println("Please enter " + "the maturity month");
				cm = infile.nextInt();
				System.out.println("Please enter" + " the maturity day");
				cd = infile.nextInt();
				System.out.println("Please enter" + " the maturity year");
				cy = infile.nextInt();
				reason = "Account did not already exist.";
				at = new Account(depo, newacct, actyp, openAmount, cm, cd,
						cy,  actStat);
				bank.addAcct(at);
				Transactions tran = new Transactions(ttyp, openAmount,
						transDate, flag, reason);
				at.addTrans(tran);
			} else {
				reason = "Account did not already exist.";
				at = new Account(depo, newacct, actyp, openAmount,
						actStat);
				bank.addAcct(at);
				Transactions tran = new Transactions(ttyp, openAmount,
						transDate, flag, reason);
				at.addTrans(tran);
			}
			Account b = bank.getAccount(bank.getNumAccts() - 1);
			out.println("New Account Number: " + b.getAccountNum());
			out.println("Name: " + individual.getFirst() + " " 
					+ individual.getLast());
			out.println("Social: " + depo.getSocSecNum());
			out.println("Account Type: " + b.getAccountType());
			out.println("Start Balance: " + b.getBalance());

			if (actyp.equals("CD")) {
				DateInfo da=b.getDate();
				out.print("Maturity Date: " + da.getMonth());
				out.print("/" + da.getDayOfMonth());
				out.print("/" + da.getYear());
				out.println();
			}
		
			out.println("Account "+newacct + " has been added!");
		} else
			out.println("Error! " + newacct + " already exist!");
	}
	/*deposit()
	* Input 
	*  b- bank object
	*  out-reference to output file 
	*  infile-reference to input file
	* Process 
	*  calls account class method to search 
	*  	through arraylist calls account class 
	*  	method to make deposit
	* Output 
	*  a deposit transaction
	*/
	public static void deposit(Bank bank, PrintWriter out, Scanner infile) {
		int  index, acct, signal, nMD;
		double deposit, tempBalance;
		String ttyp = "Deposit", reason;
		DateInfo mD= new DateInfo();
		String transDate =  ( mD.getMonth()+1) + "/" + mD.getDayOfMonth()
			+ "/" + mD.getYear();
		String matDate;
		boolean flag = false;
		

		System.out.println("Please enter your account number: ");
		acct = infile.nextInt();
		index = bank.findAcct(acct);
		out.println("Transcation Type: " + ttyp);
		out.println("Account Number: " + acct);
		
		
		if (index != -1) {
			Account a = bank.getAccount(index);
			String temp = a.getAccountType();
			if (a.getStatus() == 1) {
				if (temp.equals("CD")) {
					DateInfo di=a.getDate();
					System.out.println("Please enter the maturity month");
					System.out.println("Please enter the maturity day");
					System.out.println("Please enter the maturity year");
				
					signal = di.compareDateToday();
					System.out.println("Please enter the amount you want"
							+ " to deposit: ");
					tempBalance = a.getBalance();
					deposit = infile.nextDouble();
					
					

					if (signal == -2 || signal == -1) {
						signal = a.makeDeposit(deposit);
						if (signal == 1) {
							reason = "Error! Invalid Deposit Amount: "+ deposit;
							out.println("Deposit Failure!");
							out.println(reason);
							Transactions tran = new Transactions(ttyp,
									transDate, flag, reason);
							a.addTrans(tran);
						} else if (signal == 2) {
							flag = true;
							bank.addTotalAmountInCDAccts(deposit);
							out.println("Deposit " + "Sucessful!");
							out.printf("Old Balance: " + "%.2f", tempBalance);
							out.println("\nDeposit: "  + deposit);
							out.printf("New Balance: " + "%.2f", a.getBalance());
							reason = "Account exist & had valid deposit";
							
							System.out.println("Please enter the"
									+ " 6, 12, 18 or 24 to set a new maturity ");
							nMD= infile.nextInt();
							di.updateMD(nMD);
							
							matDate =(di.getMonth()+1) + "/" + di.getDayOfMonth()
								+ "/" + di.getYear();
							out.println("\nNew Maturity: "+ matDate);
							Transactions tran = new Transactions(ttyp, 
									deposit, transDate, flag, reason, matDate);
							a.addTrans(tran);
						}
					} else if (signal == -3) {
						out.println("Deposit Failure!");
						out.print("Maturity Date: " + di.getMonth()
							+ "/" + di.getDayOfMonth());
						out.print("/" + di.getYear());
						out.print("\nToday's Date: " +( mD.getMonth() +1)
							+ "/" + mD.getDayOfMonth());
						out.print("/" + mD.getYear());
						reason = "Error! Maturity Date has not passed.";
						out.println("\n"+reason );
						Transactions tran = new Transactions(ttyp, 
								deposit, transDate, flag, reason);
						a.addTrans(tran);
					}
				} else {
					System.out.println("Please enter the"
							+ " amount you want to deposit: ");
					tempBalance = a.getBalance();
					deposit = infile.nextDouble();
					signal = a.makeDeposit(deposit);
					if (signal == 1) {
						out.println("Deposit Failure!");
						reason = "Error! Invalid Deposit Amount: "+deposit;
						out.println(reason );
						Transactions tran = new Transactions(ttyp, 
								deposit, transDate, flag, reason);
						a.addTrans(tran);
					} else if (signal == 2) {
						flag = true;
						if(temp.equals("Savings")){
							bank.addTotalAmountInSavingsAccts(deposit);
						}
						else if(temp.equals("Checkings")){
							bank.addTotalAmountInCheckingsAccts(deposit);
						}
						out.println("Deposit Sucessful!");
						out.printf("Old Balance: %.2f", tempBalance);
						out.println("\nDeposit: " + deposit);
						out.printf("New Balance: %.2f", a.getBalance());
						reason = "Account exist & had valid deposit";
						Transactions tran = new Transactions(ttyp,
								deposit, transDate, flag, reason);
						a.addTrans(tran);
						
					}
				}
			} else if (a.getStatus() == 0) {
				reason = "The account is closed!";
				out.println(reason);
				Transactions tran = new Transactions(ttyp, 
						transDate, flag, reason);
				a.addTrans(tran);
			}
		} else
			out.println("Error! The account does not exist!");
		out.println();
	}
	/*withdraw()
	* Input 
	*  b- bank object
	*  out-reference to output file 
	*  infile-reference to input file
	* Process 
	*  calls account class method to
	*   search through arraylist calls account 
	*   class method to make withdraw
	* Output 
	*  a withdraw transaction
	*/
	public static void withdraw(Bank bank, PrintWriter out, Scanner infile) {

		int acct, index, signal, nMD;
		double withdraw, tempBalance;
		String ttyp = "Withdraw", reason, matDate;
		DateInfo mD= new DateInfo();
		String transDate =( mD.getMonth() +1) + "/" + mD.getDayOfMonth()
			+ "/" + mD.getYear();
		boolean flag = false;

		System.out.println("Please enter your account number: ");
		acct = infile.nextInt();
		out.println("Transcation Type: " + ttyp);
		index = bank.findAcct(acct);
		out.println("Account Number: " + acct);

		if (index != -1) {
			Account a = bank.getAccount(index);
			String temp = a.getAccountType();
			if (temp.equals("CD")) {
				DateInfo di=a.getDate();
				System.out.println("Please enter " + "the maturity month");
				System.out.println("Please enter" + " the maturity day");
				System.out.println("Please enter " + "the maturity year");
			
				signal = di.compareDateToday();
				if (signal == -2 || signal == -1) {
					System.out.println("Please enter " + "the amount you want "
							+ "to withdraw: ");
					tempBalance = a.getBalance();
					withdraw = infile.nextDouble();
					signal = a.makeWithdraw(withdraw);
					if (signal == 1) {
						reason = "Error! Invalid Withdraw Amount: "+ withdraw;
						out.println("Withdraw Failure!");
						out.println(reason );
						Transactions tran = new Transactions(ttyp, 
								withdraw, transDate, flag, reason);
						a.addTrans(tran);
					} else if (signal == 2) {
						out.println("Withdraw Failure!");
						out.printf("Withdraw: %.2f", withdraw);
						reason = "Error! Insufficient Funds!";
						out.println("\n" + reason);
						out.printf("Balance: %.2f", tempBalance);
						Transactions tran = new Transactions(ttyp,
								withdraw, transDate, flag, reason);
						a.addTrans(tran);
					} else if (signal == 3) {
						flag = true;
						bank.subTotalAmountInCDAccts(withdraw);
						out.println("Withdraw " + "Success!");
						out.printf("Old Balance" + ": %.2f", tempBalance);
						out.printf("\nWithdraw:" + " %.2f", withdraw);
						out.printf("\nNew Balance: " + "%.2f", a.getBalance());
						out.println();
						reason = "Account exist & valid witdraw & "
								+ "sufficient funds.";
						System.out.println("Please enter the"
								+ " 6, 12, 18 or 24 to set a new maturity ");
						nMD= infile.nextInt();
						di.updateMD(nMD);
						
						matDate =(di.getMonth()+1) + "/" + di.getDayOfMonth()
							+ "/" + di.getYear();
						out.println("\nNew Maturity: "+ matDate);
						Transactions tran = new Transactions(ttyp, 
								withdraw, transDate, flag, reason, matDate);
						a.addTrans(tran);
					} else if (signal == -98) {
						out.println("Withdraw Failure!");
						reason = "The account is closed!";
						out.println(reason);
						Transactions tran = new Transactions(ttyp, 
								transDate, flag, reason);
						a.addTrans(tran);
					}
				} else if (signal == -3) {
					out.println("Withdraw Failure!");
					out.print("Maturity Date:"+ di.getMonth()
					+ "/" + di.getDayOfMonth());
					out.print("/" + di.getYear());
					out.print("\nToday's Date:" +( mD.getMonth() +1)
						+ "/" + mD.getDayOfMonth());
					out.print("/" + mD.getYear());
					reason = "Error! Maturity Date has not passed.";
					out.println("\n" + reason);
					Transactions tran = new Transactions(ttyp, 
							transDate, flag, reason);
					a.addTrans(tran);
				}
			} else {
				System.out.println("Please enter the amount you "
						+ "want to withdraw: ");
				tempBalance = a.getBalance();
				withdraw = infile.nextDouble();
				signal = a.makeWithdraw(withdraw);
				if (signal == 1) {
					reason = "Error! Invalid Withdraw Amount: "+ withdraw;
					out.println("Withdraw Failure!");
					out.println(reason );
					Transactions tran = new Transactions(ttyp, 
							withdraw, transDate, flag, reason);
					a.addTrans(tran);
				} else if (signal == 2) {
					out.println("Withdraw Failure!");
					out.printf("Withdraw: %.2f", withdraw);
					reason = "Error! Insufficient Funds!";
					out.println("\n" + reason);
					out.printf("Balance: %.2f", tempBalance);
					Transactions tran = new Transactions(ttyp, 
							withdraw, transDate, flag, reason);
					a.addTrans(tran);
				} else if (signal == 3) {
					flag = true;
					if(temp.equals("Savings")){
						bank.subTotalAmountInSavingsAccts(withdraw);
					}
					else if(temp.equals("Checkings")){
						bank.addTotalAmountInCheckingsAccts(withdraw);
					}
					out.println("Withdraw " + "Success!");
					out.printf("Old Balance" + ": %.2f", tempBalance);
					out.printf("\nWithdraw:" + " %.2f", withdraw);
					out.printf("\nNew Balance: " + "%.2f", a.getBalance());
					out.println();
					reason = "Account exist & valid witdraw & sufficient funds.";
					Transactions tran = new Transactions(ttyp, 
							withdraw, transDate, flag, reason);
					a.addTrans(tran);
				} else if (signal == -98) {
					out.println("Withdraw Failure!");
					reason = "The account is closed!";
					out.println(reason);
					Transactions tran = new Transactions(ttyp,
							transDate, flag, reason);
					a.addTrans(tran);
				}
			}
		} else {
			out.println("Withdraw Failure!");
			out.println("Error! The account does not exist!");
			out.println();
		}
		out.println();
	}
	 /*clearCheck()
	* Input 
	*  bank- bank object
	*  out-reference to output file 
	*  infile-reference to input file
	* Process 
	*  clears a check or not
	* Output 
	*  a clear check transaction
	*/
	public static void clearCheck(Bank bank, PrintWriter out, 
			Scanner infile) {
		int acct, signal, index, ccM, ccD, ccY;
		double amount, temp;
		String ttyp = "Clear Check", reason;
		DateInfo mD= new DateInfo();
		String transDate =  ( mD.getMonth()+1) + "/" + mD.getDayOfMonth()
			+ "/" + mD.getYear();
		boolean flag = false;

		out.println("Transcation Type:" + ttyp);
		System.out.println("Please enter the account you would " 
				+ "like to clear the check");
		acct = infile.nextInt();
		out.println("Account Number: " + acct);
		index = bank.findAcct(acct);

		if (index != -1) {
			Account a = bank.getAccount(index);
			temp = a.getBalance();
			if (a.getAccountType().equals("Checkings")) {
				System.out.println("Please enter the month on"
						+ " the check");
				ccM = infile.nextInt();
				System.out.println("Please enter the day of month"
						+ " on the check");
				ccD = infile.nextInt();
				System.out.println("Please enter the year" 
						+ " on the check");
				ccY = infile.nextInt();
				System.out.println("Please enter the amount" 	
						+ " on the check");
				amount = infile.nextDouble();
				signal = a.clearCheck(ccM, ccD, ccY, amount);
				if (signal == -625) {
					reason = "The account is closed!";
					out.println(reason);
					Transactions tran = new Transactions(ttyp,
							amount, transDate, flag, reason);
					a.addTrans(tran);
				} else if (signal == -1234) {
					flag = true;
					bank.subTotalAmountInCheckingsAccts(amount);
					out.println("Old Balance: " + a.getBalance());
					out.println("Check: " + amount);
					out.println("Sucess! The check cleared!");
					out.println("New Balance: " + a.getBalance());
					reason = "Accoun exist & check was valid";
					Transactions tran = new Transactions(ttyp, 
							amount, transDate, flag, reason);
					a.addTrans(tran);
				} else if (signal == -4444) {
					out.println("Check: " + amount);
					out.printf("Old Balance %.2f", temp);
					reason = "ERROR! The check bounced!\nA $2.50 "
							+ "service fee has been charged.";
					out.println("\n" + reason);
					out.printf("New Balance %.2f", a.getBalance());
					Transactions tran = new Transactions(ttyp, amount, 
							transDate, flag, reason);
					a.addTrans(tran);
				} else if (signal == -99) {
					out.print("Check Date:" + ccM + "/" + ccD);
					out.print("/" + ccY);
					reason = "The check has expired.\nMore than"
							+ " 6 months have passed.";
					out.println("\n" + reason);
					Transactions tran = new Transactions(ttyp, amount,
							transDate, flag, reason);
					a.addTrans(tran);
				} else if (signal == -50) {
					reason = "ERROR! Post-dated checks are not allowed.";
					out.println(reason);
					out.print("Check Date:" + ccM + "/" + ccD);
					out.print("/" + ccY + "\n");
					Transactions tran = new Transactions(ttyp, amount, 
							transDate, flag, reason);
					a.addTrans(tran);
				}
			} else {
				out.println("Account Type: " + a.getAccountType());
				reason = "Error the account is not a checkings account!";
				out.println(reason);
				Transactions tran = new Transactions(ttyp, transDate, 
						flag, reason);
				a.addTrans(tran);
			}
		} else if (index == -1) {
			out.println("The account does not exist!");
		}
		out.println();
	}
	/*deleteAcct()
	* Input 
	*  b- bank object
	*  out-reference to output file 
	*  infile-reference to input file
	* Process 
	*  deletes an account
	* Output 
	*  a delete account transaction
	*/
	public static void deleteAcct(Bank bank, PrintWriter out, 
			Scanner infile) {
		int acct, signal;
		double amount;
		String reason,  ttyp = "Delete Account";
		DateInfo mD= new DateInfo();
		String transDate =  ( mD.getMonth()+1) + "/" + mD.getDayOfMonth()
			+ "/" + mD.getYear();
		boolean flag = false;

		System.out.println("Please enter the account you would"
				+ " like to delete");
		acct = infile.nextInt();
		out.println("Transcation Type: " + ttyp);
		out.println("Account Number: " + acct);
		signal = bank.deleteAcct(acct);
	
		if (signal!=-1) {
			Account at = bank.getAccount(signal);
			if (at.getStatus() == 0) {
				reason = "Error! The account is closed!";
				out.println(reason);
				Transactions tran = new Transactions(ttyp, 
						transDate, flag, reason);
				at.addTrans(tran);
			}else if (at.getBalance() >= 0) {
				amount= at.getBalance();
				reason = "Error! The account  still has a balance "+amount;
				out.println(reason);
				
				Transactions tran = new Transactions(ttyp, amount,
						transDate, flag, reason);
				at.addTrans(tran);
			}else  if(signal == 1) {
				flag = true;
				out.println("The account has been deleted!");
			} 
		}else if (signal == -1)
			out.println("Error! The account does not exist!");
	}
}
