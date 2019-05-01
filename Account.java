import java.util.ArrayList;

public class Account {

	private Depositor depositor;
	private int account_num;
	private String account_type;
	private int account_stat;
	private double balance;
	private DateInfo date;
	private ArrayList<Transactions> transactions;
	//copy constructor 
	public Account(Account a) {
		depositor = a.depositor;
		account_num = a.account_num;
		account_type = a.account_type;
		account_stat = a.account_stat;
		balance = a.balance;
		date =a.date;
		transactions = a.transactions;
	}

	// no-arg constructor
	public Account() {
		depositor = new Depositor();
		account_num = -111;
		account_type = " NONE";
		account_stat = 0;
		balance = -0.00;
		date =new DateInfo();
		transactions = new ArrayList<>();
	}

	// paramatized constructor
	public Account(Depositor d, int accountNum, String 
			accountType, double bal, int actStat) {
		depositor = d;
		account_num = accountNum;
		account_type = accountType;
		account_stat = actStat;
		balance = bal;
		transactions = new ArrayList<>();
	}

	// constructor for cd accounts
	public Account(Depositor depo, int accountNum, String 
			accountType, double bal, int m, int d, int y, int actStat) {
		depositor = depo;
		account_num = accountNum;
		account_type = accountType;
		account_stat = actStat;
		balance = bal;
		date =new DateInfo(m, d, y);
		transactions = new ArrayList<>();
	}
	// setters or mutators
	private void setDepositor(Depositor deposit) {
		depositor = deposit;
	}
	private void setAccountNum(int account) {
		account_num = account;
	}
	private void setAccountType(String type) {
		account_type = type;
	}
	public void setStatus(int flag) {
		account_stat = flag;
	}
	private void setBalance(double bal) {
		balance = bal;
	}
	private void setDate(DateInfo d) {
		date=d;
	}
	// getters or accessors
	public Depositor getDepositor() {
		return new Depositor(depositor);
	}
	public int getAccountNum() {
		return account_num;
	}
	public String getAccountType() {
		return account_type;
	}
	public int getStatus() {
		return account_stat;
	}
	public double getBalance() {
		return balance;
	}
	public DateInfo getDate() {
		return new DateInfo(date);
	}
	// to set & get transactions
	public void addTrans(Transactions trans) {
		transactions.add(trans);
	}
	public Transactions getTrans(int index) {
		return new Transactions(transactions.get(index));
	}
    //Get transaction array size
	public int getTransSize() {
		return transactions.size();
	}
	//equals 
	public boolean  equals (int ac, String soc) {
		boolean flag=false;
		if (account_num==ac && soc.equals(depositor.getSocSecNum())) {
			flag=true;
		}
			
		return flag;
	}
	public boolean  equals (int ac, String f, String l) {
		boolean flag=false;
		if (account_num==ac && f.equals(depositor.getName().getFirst())&&
				l.equals(depositor.getName().getLast())) {
			flag=true;
		}
			
		return flag;
	}
	public String toString () {
		String str;
		String l=depositor.getName().getLast();
		String f=depositor.getName().getFirst();
		String soc=depositor.getSocSecNum();
		String stat="blahh";
		
		
		
		
		if (account_stat == 1) {
			 stat="Open";
		} else if (account_stat== 0) {
			stat= "Closed";
		}
		
		if (account_type.equals("CD")) {
			int m=date.getMonth();
			int day=date.getDayOfMonth();
			int year=date.getYear();
			str=String.format("%d%14.2f%10s%10s%9s%9s%8s%3d/%2d/%4d", account_num, balance, account_type,soc,f, l,stat, m, day, year);
		}else {
		
			str=String.format("%d%14.2f%10s%10s%9s%9s%8s", account_num, balance, account_type,soc,f, l,stat);
		}
		return str;
	}
	public String toString (int a, double b, String at, String soc, String f, String l, DateInfo d) {
		String str;
		int m=d.getMonth();
		int day=d.getDayOfMonth();
		int year=d.getYear();
		String stat="aaaa";
		
		if (account_stat == 1) {
			 stat="Open";
		} else if (account_stat== 0) {
			stat= "Closed";
		}

		str=String.format("\nBalance %14.2f\nAccount Type%10s\nSocial%10s\n Name%9s%9s\nStatus%8s\n Maturity Date%3d/%2d/%4d", b, at,soc,f, l,stat, m, day, year);
	
		
		return str;
	}
	                                                  
	public String toString (int a, double b, String at, String soc, String f, String l, int statt, int m, int d, int y) {
		String str;
		String stat="aaaa";
		
		if (statt == 1) {
			 stat="Open";
		} else if (statt== 0) {
			stat= "Closed";
		}

		str=String.format("\nBalance %9.2f\nAccount Type%10s\nSocial%10s\n Name%5s%5s\nStatus%5s\n "
				+ "Maturity Date%3d/%2d/%4d",  b, at,soc,f, l,stat, m, d, y);
	
		
		return str;
	}
	                           
	public String toString (int a, double b, String at, String soc, String f, String l, int st) {
		String str;
		String stat="xxxx";
		
		if (st == 1) {
			 stat="Open";
		} else if (st== 0) {
			stat= "Closed";
		}
	
		str=String.format("\nBalance %9.2f\nAccount Type%10s\nSocial%10s\n Name%5s%5s\nStatus%5s\n ", b, at,soc,f, l,st);
	
		return str;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*closeAcct()
	* Input 
	* 	nothing
	* Process 
	*  sends signals if close was
	*  	successful or not
	* Output 
	*  s the signal
	*/
	public boolean closeAcct() {
		boolean s = false;
		if (account_stat == 1) {
			account_stat = 0;
			s = true;
		}
		return s;
	}
	/*reopenAcct()
	* Input 
	* 	nothing
	* Process 
	*  sends signals if reopen was
	*  	successful or not
	* Output 
	*  s the signal
	*/
	public boolean reopenAcct() {
		boolean s = false;
		if (account_stat == 0) {
			account_stat = 1;
			s = true;
		}
		return s;
	}
	/*makeDeposit()
	* Input 
	* 	money- deposit
	* Process 
	*  sends signals if deposit was
	*  	successful or not
	* Output 
	*  sig- the signal
	*/
	public int makeDeposit(double money) {
		int sig = 2;
		if (money <= 0)
			sig = 1;
		else if (money > 0) {
			balance += money;
		}
		return sig;
	}
	 /*makeWithdraw()
	* Input 
	* 	withdraw- amount
	* Process 
	*  sends signals if withdraw was
	*  	successful or not
	* Output 
	*  sig- the signal
	*/
	public int makeWithdraw(double withdraw) {
		int sig = 0;
		if (withdraw <= 0)
			sig = 1;
		else if (withdraw > 0) {
			if (account_stat == 1) {
				if (withdraw <= balance) {
					balance -= withdraw;
					sig = 3;
				} else
					sig = 2;
			} else if (account_stat == 0) {
				sig = -98;
			}
		}
		return sig;
	}
	
	/*clearCheck()
	* Input 
	* 	month- check month
	* 	day- check day 
	* 	year- check year 
	* 	withdraw- amount
	* Process 
	*  sends signals if clear check was
	*  	successful or not
	* Output 
	*  sig- the signal
	*/
	public int clearCheck(int month, int day, int year,
			double withdraw) {
		int blah, sig, signal = 0;
		
		if (account_stat == 1) {
			date =new DateInfo((month-1), day, year);
			sig = date.compareDateToday();
			if (sig == -1 || sig == -2) {
				blah = date.compareDate();
				if (blah== -1 || blah == -2) {
					// check clears & calls
					//withdrawal
					blah = makeWithdraw(withdraw);
					if (blah == 3)
						// check cleared
						signal = -1234;
					if (blah == 1)
						// check did not clear 
						//invalid amount
						signal = -7777;
					if (blah == 2) {
						// check did not clear
						//insufficient funds
						if (balance > 2.50)
							balance -= 2.50;
						signal = -4444;
					}
				} else if (blah == -3){
					// check expired
					signal = -99;
				}
			} else if (sig == -3)
				// post-dated check
				signal = -50;
			} else
			signal = -625;
		return signal;
	}

}