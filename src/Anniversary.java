
/**
 * Stores members' anniversary information by date
 * The object stores the husband's name, wife's name, last name, the day and month of anniversary
 * @author dlieviant
 *
 */
public class Anniversary extends BirthdayAnniversary{
	private String husbandName;
	private String wifeName;
	private String lastName;
	
	
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
	
	/**
	 * Returns the String representative of the Anniversary object
	 */
	public String toString() {
		return wifeName + " and " + husbandName + " " + lastName + " " + super.toString();
	}
}
