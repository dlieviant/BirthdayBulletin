
/**
 * Stores members' birthday information by first name, last name, month, and day of birth
 * @author dlieviant
 *
 */
public class Birthday {
	private String firstName;
	private String lastName;
	private String month;
	private String day;
		
	public Birthday(String fn, String ln, String bd) {
		firstName = fn;
		lastName = ln;
		String[] date = bd.split("/");
		month = date[0];
		day = date[1];
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getMonth() {
		return month;
	}
	
	public String getDay() {
		return day;
	}
	
	public void setMonth(String m) {
		month = m;
	}
	
	public void setDay(String d) {
		day = d;
	}
	
	/**
	 * Returns the String representative of the Birthday object
	 */
	public String toString() {
		return firstName + " " + lastName + " " + "(" + BulletinBirthdayList.monthName(month, false) + " " + day + ")";
	}

}
