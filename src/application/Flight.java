/**
 * @author Azeez + Ahmed 
 *
 *
 */
package application;
import java.util.ArrayList;

public abstract class Flight {
	
	//--------------------Flight Variables----------------------
	public static int numFlights; // Number of flights
	public String typeOfFilght;	  // Type of flight (Phase2)
	public int timeOfDeparture;   // Time of flight departure
	public int timeOfArrival;	  // Time of flight arrival
	public Bus busUsed;			  // Object of bus being used
	public int catches;			  // Number of students caught their lecture in flight
	public String catchesPer;     // Percentage of students caught their lecture in flight
	ArrayList<Student> studentsInTrip = new ArrayList<Student>(); // Array of students in flight
	
	//--------------Setters & Getters-------------------
	
	public int getCatches() {
		return catches;
	}

	public void incCatches() {
		this.catches++;
	}

	public String getCatchesPer() {
		return catchesPer;
	}

	public void setCatchesPer(String catchesPer) {
		this.catchesPer = catchesPer;
	}

	public Bus getBusUsed() {
		return busUsed;
	}

	public void setBusUsed(Bus busUsed) {
		this.busUsed = busUsed;
	}

	public static int getNumFlights() {
		return numFlights;
	}

	public static void incNumFlights() {
		Flight.numFlights++;
	}
	
	public String getTypeOfFilght() {
		return typeOfFilght;
	}
		
	public void setTypeOfFilght(String typeOfFilght) {
		this.typeOfFilght = typeOfFilght; 
	};
	
	// Abstract Setters & Getters
	public abstract int getTimeOfDeparture();
	
	public abstract void setTimeOfDeparture(int timeOfDeparture);
	
	public abstract int getTimeOfArrival();
	
	public abstract void setTimeOfArrival(int timeOfArrival);

	public abstract double getDISTANCE_TO_KAU();

	public abstract int getMINUTES_TO_KAU();
	
	public abstract int getFUEL_TO_KAU();
	
	//-------------------Methods------------------------
	
	// Print flight information
	public String toString() {
		// get the flight number
		int numFlight = Flight.getNumFlights();
		// get the time of departure and arrival of the flight
		String TimeDep = Time.MinutesToTime(this.getTimeOfDeparture());
		String TimeArr = Time.MinutesToTime(this.getTimeOfArrival());
		
		// get the type of flight
		String type = this.getTypeOfFilght();
		// get number of students in the flight
		int numS = this.studentsInTrip.size();
		boolean isFull = numS==10; // check if flight is at max capacity
		
		// loop for getting catch state
		int c = 0;
		for(Student S : this.studentsInTrip) {
			if(S.isCatch) c++;
		}
		// number of students missed the lecture (reminder of people didn't catch of total) 
		int misses = numS - c;
		// percentage of student caught their lecture
		String perCC = String.format("%.2f", 100*((double)(c) / (c+misses))) + "%";
		// All data of flight
		String a = String.format("Students: %d\t Moved At: %s\t\t Arrived At: %s\t Catches: %d\t Misses: %d\t\t Catch%%: %s\t Flight Type: %s\t BusId: %d\t", 
				numS, TimeDep, TimeArr, c, misses, perCC, type, busUsed.getID());
		return a;
	}
}