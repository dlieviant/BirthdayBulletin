import java.util.*;
import java.io.*;

public class BulletinBirthdayList extends BirthdayAnniversary{
	
	private static String month; //the month the lists are generated for
	private static ArrayList<BirthdayAnniversary> birthdayList; //list of members' birthday
	private static ArrayList<BirthdayAnniversary> anniversaryList; //list of members' anniversary
	private static ArrayList<Birthday> tempList; //temporary list for members' anniversary
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		calendar.setTime(date);
		month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		
		birthdayList = new ArrayList<BirthdayAnniversary>();
		anniversaryList = new ArrayList<BirthdayAnniversary>();
		tempList = new ArrayList<Birthday>();
		
		readFile();
		sortList(birthdayList);
		sortList(anniversaryList);
		
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
	 * Sorts the list in ascending order based on the date
	 */
	private static void sortList(ArrayList<BirthdayAnniversary> l) {
		for(int i = 0; i < l.size(); i++) {
			int min = i;
			for(int j = i+1; j < l.size(); j++) {
				if(l.get(j).getDay().compareTo(l.get(min).getDay()) < 0) min = j;
			}
			BirthdayAnniversary temp = l.get(i);
			l.set(i, l.get(min));
			l.set(min, temp);
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
}
