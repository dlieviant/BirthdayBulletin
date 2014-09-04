
/**
 * Stores members' anniversary information by date
 * The object stores the husband's name, wife's name, last name, the day and month of anniversary
 * @author dlieviant
 *
 */
public class Anniversary {
	private String husbandName;
	private String wifeName;
	private String lastName;
	private String month;
	private String day;
	
	public Anniversary(String hn, String wn, String ln, String bd) {
		husbandName = hn;
		wifeName = wn;
		lastName = ln;
		String[] date = bd.split("/");
		month = date[0];
		day = date[1];
	}
	
	public String getHusbandName() {
		return husbandName;
	}
	
	public String getWifeName() {
		return wifeName;
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
	 * Returns the String representative of the Anniversary object
	 */
	public String toString() {
		return wifeName + " and " + husbandName + " " + lastName + " " + "(" + BulletinBirthdayList.monthName(month, false) + " " + day + ")";
	}
}
