public class Name {
	private String last;
	private String first;
	//copy constructor
	public Name (Name n) {
		last=n.last;
		first=n.first;
	}
	
	
	//no-arg constructor 
	public Name() {
		last="Doe";
		first="John";
	}
	//paramatized constructor 
	public Name (String f, String l) {
		last=l;
		first=f;
	}
	//setters/mutators
	private void setLast(String surname) {
		last=surname;
	}
	private void setFirst(String forename) {
		first= forename;
	}
	//getter/accessors
	public String getLast() {
		return last;
	}
	public String getFirst() {
		return first;
	}
	
	//to string 
	public String toString () {
		String str; 
		str=String.format("%9s%9s", first, last);
		return str;
	}

	//paramatized to string 
	public String toString (Name n ) {
		String str; 
		str=String.format("%9s%9s", n.first, n.last);
		return str;
	}
	public String toString (String f, String l) {
		String str; 
		str=String.format("%9s%9s",f,l);
		return str;
	}
	//equals 
	public boolean equals (Name n ) {
		boolean flag=false;
		if (last.equals(n.last)&&first.equals(n.first)) {
			flag=true;
		}	
		return flag;
	}
	public boolean equals (String f, String l) {
		boolean flag=false;
		if (last.equals(l)&&first.equals(f)) {
			flag=true;
		}
		return flag;
	}

}
