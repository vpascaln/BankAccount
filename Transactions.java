
public class Transactions {
	private String transTyp;
	private double transAmount;
	private String transDate;
	private boolean sucess; 
	private String reason;
	private String newMD;

	
	//copy constructor 
	public Transactions(Transactions t) {
		transTyp=t.transTyp;
		transAmount=t.transAmount;
		transDate=t.transDate;//CHANGE?
		sucess=t.sucess;
		reason=t.reason;
		newMD=t.newMD;
	}
	//no-arg constructor
	public Transactions() {
		transTyp="NOTHING";
		transAmount=-0.00;
		transDate="00/00/00";//CHANGE?
		sucess=false;
		reason="NO REASON";
		newMD="b";
	}
	//paramatized constructors
	public Transactions(String tType, String tDate, boolean s, String r) {
		transTyp=tType;
		transDate=tDate;
		sucess=s;
		reason=r;
	}
	public Transactions(String tType, double a, 
			String tDate, boolean s, String r) {
		transTyp=tType;
		transAmount=a;
		transDate=tDate;
		sucess=s;
		reason=r;
	
	}
	public Transactions(String tType, double a, 
			String tDate, boolean s, String r, String nM) {
		transTyp=tType;
		transAmount=a;
		transDate=tDate;
		sucess=s;
		reason=r;
		newMD=nM;
	}
	//setters
	private void  setTransTyp(String tType) {
		transTyp=tType;
	}
	private void  setTransAmount(double a) {
		transAmount=a;
	}
	private void  setTransDate(String tDate) {
		transDate=tDate;
	}
	private void  setSucess(boolean s) {
		sucess=s;
	}
	private void  setReason(String r) {
		reason=r;
	}
	//getters
	public String getTransTyp() {
		return transTyp;
	}
	public double  getTransAmount() {
		return transAmount;
	}
	public String getTransDate() {
		return transDate;
	}
	public boolean  getSucess() {
		return sucess;
	}
	public String getReason() {
		return reason;
	}	
	public String getNewMD() {
		return newMD;
	}	

}
