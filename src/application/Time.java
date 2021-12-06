package application;
// convention: everything is in minutes with 6:00Am as
// the starting reference an 10:00Pm as the last reference
/**
 * @author Azeez + Ali + Ahmed
 *
 */

public class Time {
	
	//-----------------Time Variables-------------------
	public static int clock;   // Used as a global clock
	public int minutesElapsed; // Minutes passed according to global clock
	public int startingHour;   // Start of working hours
	public int endingHour;     // End of working hours
	
	//----------------Time Constructors-----------------
	public Time() {
		this.setMinutesElapsed(0);
		this.setStartingHour(0);
		this.setEndingHour(960);
		Time.clock = this.getStartingHour();
	}
	
	public Time(int mins) {
		this.setMinutesElapsed(mins);
		this.setStartingHour(6);
		this.setEndingHour(22);
		Time.clock = this.getStartingHour();
	}
	
	//--------------Setters & Getters-------------------
	public int getMinutesElapsed() {
		return minutesElapsed;
	}

	public void setMinutesElapsed(int minutesElapsed) {
		this.minutesElapsed = minutesElapsed;
	}

	public int getStartingHour() {
		return startingHour;
	}

	public void setStartingHour(int startingHour) {
		this.startingHour = startingHour;
	}

	public int getEndingHour() {
		return endingHour;
	}

	public void setEndingHour(int endingHour) {

		this.endingHour = endingHour;
	}
	
	// Static setter for the global clock given minutes as parameter
	public static void setClock(int mins) {
			Time.clock = mins;
	}
	
	//-------------------Methods------------------------
	
	// Increment global clock by 1 minute
	public static void incrementClock() {
		Time.clock++;
	}
	
	// Increment global clock by given minutes elapsed
	public static void incrementClock(int mins) {
		Time.clock = Time.clock + mins;
	}
	
	// Reset the global clock to Starting Hour
	public void resetTime() {
		Time.clock = this.getStartingHour();
	}
	
	// Convert the value of minutes elapsed of this object to a proper time representation (HH:MM AM/PM)
	public String MinutesToTime() {
		String amPm = "AM";
		int hours = (this.getMinutesElapsed() / 60)  + 6; // represent minutes in terms of hours
		int minutes = this.getMinutesElapsed() % 60;	  // add reminding minutes
		// check whether AM or PM
		if (hours > 12) {
			hours -= 12;
			amPm = amPm.replace('A', 'P');
		}
		// create a string representation of proper time display
		String time = String.format("%d:%02d %s", hours, minutes, amPm);
		return time;
	}
		
	// Convert the value of minutes GIVEN to a proper time representation (HH:MM AM/PM)
	public static String MinutesToTime(int mins) {
		String amPm = "AM";
		int hours = (mins / 60)  + 6; // represent minutes in terms of hours
		int minutes = mins % 60;	  // add reminding minutes
		// check whether AM or PM
		if (hours > 12) {
			hours -= 12;
			amPm = amPm.replace('A', 'P');
		}
		// create a string representation of proper time display
		String time = String.format("%d:%02d %s", hours, minutes, amPm);
		return time;
	}
		
}
