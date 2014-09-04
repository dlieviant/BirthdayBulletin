import java.util.*;
import java.io.*;

public class BulletinBirthdayList {
	
	private static String month; //the month the lists are generated for
	private static ArrayList<Birthday> birthdayList; //list of members' birthday
	private static ArrayList<Anniversary> anniversaryList; //list of members' anniversary
	private static ArrayList<Birthday> tempList; //temporary list for members' anniversary
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		calendar.setTime(date);
		month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		
		birthdayList = new ArrayList<Birthday>();
		anniversaryList = new ArrayList<Anniversary>();
		tempList = new ArrayList<Birthday>();
		
		readFile();
		sortBirthdayList();
		sortAnniversaryList();
		
		printHTML();
	}
	
	/**
	 * Reads in the list from a text file and pick out the birthdays for this month
	 */
	private static void readFile() {
		//opens the file containing the birthday list
		File f = new File("BirthdayList.txt");
		try {
			Scanner sc = new Scanner(f);
			String in = "";
			while(sc.hasNext()) {
				//reads in one line at a time
				in = sc.nextLine();
				String[] i = in.split(","); //separate the data from the text file by first name, last name, and birthday
				//separating the ones who have anniversary
				if(i.length > 3) {
					//temporarily stores the data as Birthday object, using the anniversary date for the date info
					Birthday temp = new Birthday(i[0], i[1], i[3]);
					if(temp.getMonth().equals(month)) { //only stores the ones whose anniversary are this month
						tempList.add(temp);
					}
				}
				Birthday birthday = new Birthday(i[0], i[1], i[2]);
				//save the data if it is this month's birthday
				if(birthday.getMonth().equals(month)) {
					birthdayList.add(birthday);
				}
			}
			sc.close();
			//if there are anniversaries this month, generate a proper Anniversary list
			if(tempList.size() != 0) {
				generateAnniversaryList();
			}
		} catch (FileNotFoundException e) {
			System.err.print("The file name does not exist.");
		}
	}
	
	/**
	 * prints the list to an HTML file
	 */
	private static void printHTML() {
		String nameOfMonth = monthName(month, true);
		File f = new File("GKIBirthday.html");
		try {
			PrintWriter w = new PrintWriter(f);
			w.println("<html>");
			w.println("\t<head></head>");
			w.println("\t<body>");
			w.println("\t\t<h1>" + nameOfMonth + " Birthdays / Anniversaries</h1>");
			w.println("\t\t<h2>GKI Seattle would like to wish happy birthday to:</h2>");
			w.println("\t\t<ul>");
			for(int i = 0; i < birthdayList.size(); i++) {
				w.println("\t\t\t<li>" + birthdayList.get(i).toString() + "</li>");
			}
			w.println("\t\t</ul>");
			//if there are anniversaries this month, print them out
			if(anniversaryList.size() != 0) {
				w.println("\t\t<h2>and Happy Anniversary to:</h2>");
				w.println("\t\t<ul>");
				for(int j = 0; j < anniversaryList.size(); j++) {
					w.println("\t\t\t<li>" + anniversaryList.get(j).toString() + "</li>");
				}
				w.println("\t\t</ul>");
			}
			w.println("\t</body>");
			w.println("</html>");
			w.close();
		} catch (FileNotFoundException e) {
			System.err.print("The file name does not exist.");
		}
	}
	
	/**
	 * Sorts the birthday list in ascending order based on the date
	 */
	private static void sortBirthdayList() {
		for(int i = 0; i < birthdayList.size(); i++) {
			int min = i;
			for(int j = i+1; j < birthdayList.size(); j++) {
				if(birthdayList.get(j).getDay().compareTo(birthdayList.get(min).getDay()) < 0) min = j;
			}
			Birthday temp = birthdayList.get(i);
			birthdayList.set(i, birthdayList.get(min));
			birthdayList.set(min, temp);
		}
	}	
	
	/**
	 * Sorts the anniversary list in ascending order based on the date
	 */
	private static void sortAnniversaryList() {
		for(int i = 0; i < anniversaryList.size(); i++) {
			int min = i;
			for(int j = i+1; j < anniversaryList.size(); j++) {
				if(anniversaryList.get(j).getDay().compareTo(anniversaryList.get(min).getDay()) < 0) min = j;
			}
			Anniversary temp = anniversaryList.get(i);
			anniversaryList.set(i, anniversaryList.get(min));
			anniversaryList.set(min, temp);
		}
	}	

	/**
	 * Generates a proper Anniversary list from the temporary list
	 * Pairs up couples with the same day of anniversary and last name and save as Anniversary object
	 */
	private static void generateAnniversaryList() {
		for(int i = 0; i < tempList.size(); i++) {
			Birthday temp1 = tempList.get(i);
			for(int j = i+1; j < tempList.size(); j++) {
				Birthday temp2 = tempList.get(j);
				if(temp2.getDay().equals(temp1.getDay()) && temp2.getLastName().equals(temp1.getLastName())) {
					Anniversary a = new Anniversary(temp1.getFirstName(), temp2.getFirstName(), temp1.getLastName(), month + "/1/1111");
					a.setDay(temp1.getDay());
					anniversaryList.add(a);
				}
			}
		}
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
}
