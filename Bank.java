import java.util.ArrayList;

public class Bank {
	final int MAX = 100;
	private ArrayList<Account> accounts;
	
	private static double totalAmountInSavingsAccts;
	private static double totalAmountInCheckingsAccts;
	private static double totalAmountInCDAccts;
	private static double totalAmountInAllAccts;

	// no-arg constructor
	public Bank() {
		accounts = new ArrayList<>();
	}
	public Account getAccount(int index) {
		return new Account( accounts.get(index));
	}
	//gets array list size
	public int getNumAccts() {
		return accounts.size();
	}
	public boolean equals (Account a, int i) {
		boolean flag=false; 
		Account b=accounts.get(i);
		
		if (a.getAccountNum()==b.getAccountNum())
			flag=true;
		
		return flag;
	}
	public String toString() {
		String str="";
		for (int i=0;i< accounts.size(); i++) {
			Account a=accounts.get(i);
			str+=a.toString()+"\n";
		}
		return str;
	}
	public void addTotalAmountInSavingsAccts(double amo) {
		
		totalAmountInSavingsAccts+=amo;
		totalAmountInAllAccts+=amo;
	}
	public void addTotalAmountInCheckingsAccts(double amo) {
		totalAmountInCheckingsAccts+=amo;
		totalAmountInAllAccts+=amo;
	}
	public void addTotalAmountInCDAccts(double amo) {
		totalAmountInCDAccts+=amo;
		totalAmountInAllAccts+=amo;
		
	}
	public void addTotalAmountInAllAccts(double amo) {
		totalAmountInAllAccts+=amo;
	}
	public void subTotalAmountInSavingsAccts(double amo) {
		totalAmountInSavingsAccts-=amo;
		totalAmountInAllAccts-=amo;
	}
	public void subTotalAmountInCheckingsAccts(double amo) {
		totalAmountInCheckingsAccts-=amo;
		totalAmountInAllAccts-=amo;
	}
	public void subTotalAmountInCDAccts(double amo) {
		totalAmountInCDAccts-=amo;
		totalAmountInAllAccts-=amo;
	}
	public void subTotalAmountInAllAccts(double amo) {
		totalAmountInAllAccts-=amo;
	}
	public static double getTotalAmountInSavingsAccts() {
		return totalAmountInSavingsAccts;
		
	}
	public static double getTotalAmountInCheckingsAccts() {
		return totalAmountInCheckingsAccts;
		
	}
	public static double getTotalAmountInCDAccts() {
		return totalAmountInCDAccts;
		
	}
	public static double getTotalAmountInAllAccts() {
		return totalAmountInAllAccts;
	}

	/*addAccount()
	* Input 
	* 	acct-account object
	* Process 
	*  adds an account object
	* Output 
	*  increments the number of accts dynamically
	*/
	public void addAcct(Account acct) {
		accounts.add(acct);
	}
	/* findAcct()
	 * Input 
	 *   reqAccount- the requested account
	 * Process 
	 *   searches the object of arraylist for account info
	 * Output
	 *  index- the index of the requested account
	 */
	public int findAcct(int reqAccount) {
		int index = -1;
		Account a;
		for (int i = 0; i < accounts.size(); i++) {
			a = getAccount(i);
			if (reqAccount == a.getAccountNum())
				index = i;
		}
		return index;
	}
	 /*openNewAcct()
	* Input 
	* 	act-account number
	* Process 
	*  sends signals if open new acct was
	*  	successful or not
	* Output 
	*  increments the number of accts dynamically
	*/
	public int openNewAcct(int act) {
		int sig = 2, red = 9;
		red = findAcct(act);
		if (red == -1)
			sig = -1;
		return sig;
	}
	 /*deleteAcct()
	* Input 
	* 	act-account number
	* Process 
	*  sends signals if delete acct was
	*  	successful or not
	* Output 
	*  decrements the number of accts dynamically
	*/
	public int deleteAcct(int acct) {
		int sig = -000, yellow = 0;
		yellow = findAcct(acct);
		if (yellow != -1) {
			Account a = getAccount(yellow);
			if (a.getStatus() == 0) {
				sig = yellow;
			} else if (a.getBalance() >= 0)
				sig = yellow;
			else {
				sig = 1;
				accounts.remove(yellow);
			}
		}
		else 
			sig=-1;
		return sig;
	}
}
