
/**
 * Stores the month and date information for members' Birthday and Anniversary
 * @author dlieviant
 * TODO replace this in favor of composition instead.
 */
public abstract class BirthdayAnniversary {
	protected String month;
	protected String day;
	
	public String getMonth() {
		return month;
	}
	
	public String getDay() {
		return day;
	}
	
	/**
	 * Gives an English name of the month based on its numerical representation
	 * @param month the numerical representation of the month as a String
	 * @param full whether to give the full name of the month (e.g. January) or its acronym (e.g. Jan)
	 * @return the English name of the month
	 */
	public static String monthName(String month, boolean full) {
		String ans = "";
		switch(month) {
			case "1": 
				if(full) {
					ans = "January";
				} else {
					ans = "Jan";
				}
				break;
			case "2":
				if(full) {
					ans = "February";
				} else {
					ans = "Feb";
				}
				break;
			case "3":
				if(full) {
					ans = "March";
				} else {
					ans = "Mar";
				}
				break;
			case "4":
				if(full) {
					ans = "April";
				} else {
					ans = "Apr";
				}
				break;
			case "5":
				ans = "May";
				break;
			case "6":
				if(full) {
					ans = "June";
				} else {
					ans = "Jun";
				}
				break;
			case "7":
				if(full) {
					ans = "July";
				} else {
					ans = "Jul";
				}
				break;
			case "8":
				if(full) {
					ans = "August";
				} else {
					ans = "Aug";
				}
				break;
			case "9":
				if(full) {
					ans = "September";
				} else {
					ans = "Sep";
				}
				break;
			case "10":
				if(full) {
					ans = "October";
				} else {
					ans = "Oct";
				}
				break;
			case "11":
				if(full) {
					ans = "November";
				} else {
					ans = "Nov";
				}
				break;
			case "12":
				if(full) {
					ans = "December";
				} else {
					ans = "Dec";
				}
				break;
			default:
				ans = "N/A";
				break;
		}
		return ans;
	}
	
	public String toString() {
		return "(" + monthName(month, false) + " " + day + ")";
	}
	
	public void setMonth(String m) {
		month = m;
	}
	
	public void setDay(String d) {
		day = d;
	}
}
