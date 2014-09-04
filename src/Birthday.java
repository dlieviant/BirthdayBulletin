
/**
 * Stores members' birthday information by first name, last name, month, and day of birth
 * @author dlieviant
 *
 */
public class Birthday extends BirthdayAnniversary{
	private String firstName;
	private String lastName;
		
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
	
	/**
	 * Returns the String representative of the Birthday object
	 */
	public String toString() {
		return firstName + " " + lastName + " " + super.toString();
	}

}
