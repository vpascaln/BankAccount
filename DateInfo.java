import java.util.Calendar;
public class DateInfo {
	private int year;
	private int month;
	private int dayOfMonth;

	// no-arg constructor
	public DateInfo() {
		Calendar temp = Calendar.getInstance();
		year = temp.get(Calendar.YEAR);
		month =temp.get(Calendar.MONTH);
		dayOfMonth = temp.get(Calendar.DAY_OF_MONTH);
	}
	// paramatized constructor
	public DateInfo(int m, int doM, int y) {
		year = y;
		month = m;
		dayOfMonth = doM;
	}
	//copy contructor
	public DateInfo(DateInfo d) {
		year = d.year;
		month = d.month;
		dayOfMonth =d.dayOfMonth;
	}
	// setters/mutators
	private void setYear(int y) {
		year = y;
	}
	private void setMonth(int m) {
		month = m;
	}
	private void setdayOfMonth(int dom) {
		dayOfMonth = dom;
	}
	// getters/acessors
	public int getYear() {
		return year;
	}
	public int getMonth() {
		return month;
	}
	public int getDayOfMonth() {
		return dayOfMonth;
	}
	 /*compareDateToday()
	* Input 
	*  	nothing
	* Process 
	*   compares two dates
	*    (today & check date)
	* Output 
	*   bob- signal
	*/
	public int compareDateToday() {
		int bob = 0;
		Calendar temp = Calendar.getInstance();
		Calendar today= Calendar.getInstance();
		Calendar cdate= Calendar.getInstance();

		cdate.clear();
		cdate.set(Calendar.YEAR, year);
		cdate.set(Calendar.MONTH, month-1);
		cdate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		today.clear();
		today.set(Calendar.YEAR, temp.get(Calendar.YEAR));
		today.set(Calendar.MONTH, temp.get(Calendar.MONTH));
		today.set(Calendar.DAY_OF_MONTH, temp.get(Calendar.DAY_OF_MONTH));

		if (cdate.before(today))
			bob = -1;
		else if (cdate.equals(today))
			bob = -2;
		else if (cdate.after(today))
			bob = -3;

		return bob;
	}
	/*compareDate()
	* Input 
	* 	nothing
	* Process 
	* compares two dates (6 ms 
	* after check date & check date)
	* Output 
	*  bob-signal
	*/
	public int compareDate() {
		int bob = 0;
		Calendar range = Calendar.getInstance();
		Calendar temp = Calendar.getInstance();
		Calendar today= Calendar.getInstance();
		range.clear();
		range.set(Calendar.YEAR, year);
		range.set(Calendar.MONTH, month-1);
		range.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		range.add(Calendar.MONTH, 6);
		today.clear();
		today.set(Calendar.YEAR, temp.get(Calendar.YEAR));
		today.set(Calendar.MONTH, temp.get(Calendar.MONTH));
		today.set(Calendar.DAY_OF_MONTH,
				temp.get(Calendar.DAY_OF_MONTH));
		if (today.before(range))
			bob = -1;
		else if (today.equals(range))
			bob = -2;
		else if (today.after(range))
			bob = -3;
		return bob;
	}
	public void updateMD(int a) {
		Calendar range = Calendar.getInstance();
		range.set(Calendar.YEAR, year);
		range.set(Calendar.MONTH, month-1);
		range.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		range.add(Calendar.MONTH, a);
		year =range.get(Calendar.YEAR);
		month =range.get(Calendar.MONTH);
		dayOfMonth = range.get(Calendar.DAY_OF_MONTH);
		
	}
}
