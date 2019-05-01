public  class Depositor {
	private Name name;
	private String socSecNum;
	
	//copy constructor 
	public Depositor(Depositor d) {
		name=d.name;
		socSecNum=d.socSecNum;
	}
	//no-arg constructor
	public Depositor(){
		name=new Name();
		socSecNum="00000000";
	}
	//paramatized constructor
	public Depositor(Name n, String social){
		name=n;
		socSecNum=social;
	}
	//setters/mutators
	private void setName(Name person) {
		name=person;
	}
	private void setSocSecNum(String social){
		socSecNum=social;
	}
	//accessors/getters
	public Name getName() {
		return new Name(name);
	}
	public String getSocSecNum() {
		return socSecNum;
	}
	
	//toString 
	public String toString () {
		String str;
		str=String.format("%10s%9s%9s", socSecNum, name.getFirst(), name.getLast());
		return str; 
	}
	public String toString (String soc, Name n) {
		String str;
		str=String.format("%10s%9s%9s", soc, n.getFirst(), n.getLast());
		return str; 
	}
	public String toString (String soc, String f, String l) {
		String str;
		str=String.format("%10s%9s%9s", soc, f, l);
		return str; 
	}
	//equals 
	public boolean equals (String s, Name n) {
		boolean flag =false;
		
		if (socSecNum.equals(s)&& name.getFirst().equals(n.getFirst())
				&&name.getLast().equals(n.getLast())) {
			flag =true;
		}
			
		
		return flag;
	}
	public boolean equals (String s, String f, String l) {
		boolean flag =false;
		
		if (socSecNum.equals(s)&& name.getFirst().equals(f)
				&&name.getLast().equals(l)) {
			flag =true;
		}
		return flag;
	}
	public boolean equals (Depositor d ) {
		boolean flag =false;
		String f=d.getName().getFirst();
		String l=d.getName().getLast();
		
		if (socSecNum.equals(d.getSocSecNum())&& name.getFirst().equals(f)
				&&name.getLast().equals(l)) {
			flag =true;
		}
		return flag;
	}
	
	
}
