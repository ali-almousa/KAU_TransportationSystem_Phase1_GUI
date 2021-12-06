
package application;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ali + Ahmed
 *
 */
public class Bus implements Cloneable{
	
	//--------------------Bus Variables----------------------
	private int numberTrips;		// Number of trips the bus made
	private double distanceKm;		// Distance the bus has traveled
	private double fuelConsumption;	// Fuel the bus has consumed
	private boolean available;		// Indicate if the bus is available to make a trip
	private int capacity;			// Seat capacity of the bus
	private int avalAt;				// Time bus is available at
	private int ID;					// Bus ID
	public ArrayList<Flight> tripsArray = new ArrayList<>();		  // Array of trips made by bus
	public ArrayList<Student> flightStudents = new ArrayList<>();	  // Array of student in the flight
	private int campusArrival;		// Time the bus Arrived at campus
	private int dormDeparture;		// Time the bus left the dorms
	private int scheduledDormDeparture;								  // Time the bus will leave dorms 
	private ArrayList<Student> studentsDelivered = new ArrayList<>(); // Array of students delivered to campus
	
	//--------------------Bus Constructors-------------------
	public Bus(int scheduledDormDeparture, int ID) {
		this.setAvailable(true);
		this.setCapacity(10);
		this.setID(ID);
		this.setScheduledDormDeparture(scheduledDormDeparture);
	}
	
	//--------------Setters & Getters-------------------
	public ArrayList<Student> getStudentsDelivered() {
		return studentsDelivered;
	}

	public void setStudentsDelivered(ArrayList<Student> studentsDelivered) {

		this.studentsDelivered = studentsDelivered;
	}
	
	public void addStudentsDelivered(Student S) {
		this.studentsDelivered.add(S);
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public int getScheduledDormDeparture() {
		return scheduledDormDeparture;
	}

	// Setter with condition
	public void setScheduledDormDeparture(int scheduledDormDeparture) {
		//if scheduledDormDeparture equals to or greater than 10:00pm then set this bus as unavailable  
		if(scheduledDormDeparture >= 960) {
			this.setAvailable(false);
		}
		this.scheduledDormDeparture = scheduledDormDeparture;
	}

	public int getNumberTrips() {
		return numberTrips;
	}

	public void setNumberTrips(int numberTrips) {
		this.numberTrips = numberTrips;
	}

	public double getDistanceKm() {
		return distanceKm;
	}

	public void setDistanceKm(double distanceKm) {
		this.distanceKm = distanceKm;
	}

	public double getFuelConsumption() {
		return fuelConsumption;
	}

	public void setFuelConsumption(double fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean aval) {
		this.available = aval;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public ArrayList<Flight> getTripsArray() {
		return tripsArray;
	}

	// will return the time the bus will arrive to the campus in minutes form
	public int getCampusArrival() {
		return campusArrival;
	}

	public void setCampusArrival(int mins) {
		this.campusArrival = mins;
	}

	public int getDormDeparture() {
		return dormDeparture;
	}

	public void setDormDeparture(int mins) {
		this.dormDeparture = mins;
	}

	public ArrayList<Student> getFlightStudents() {
		return flightStudents;
	}
	
	public int getAvalAt() {
		return avalAt;
	}

	public void setAvalAt(int avalAt) {
		this.avalAt = avalAt;
	}
	
	//-------------------Methods------------------------
	
	// Add a trip to the array of trips & update attribute of the bus (Polymorphism)
	public void addTripsArray( Flight trip ) {
		this.tripsArray.add(trip);
		// setting cumulative attributes
		this.setDistanceKm(this.getDistanceKm() + 2 * trip.getDISTANCE_TO_KAU());
		this.setFuelConsumption(trip.getFUEL_TO_KAU() + this.getFuelConsumption());
		this.setNumberTrips(this.getNumberTrips() + 1);
	}

	// Add a student to the flight array holding student currently delivered to campus
	public void addFlightStudents(Student student) {
		this.flightStudents.add(student);
	}
	
	// Load the student into flight and adjust number of available seats 
	public void loadStudent(Student s) {
		// reduce available seats by one
		this.setCapacity(this.getCapacity() - 1);
		//update the availability after decrementing the capacity of the bus
		if (this.getCapacity() == 0) {
			this.setAvailable(false);
		}
		// add the student to the current flightStudents 
		// this array will be cleared when the bus is sent
		this.addFlightStudents(s);
	}
	
	// Check & update available time of the bus
	public void checkAval(int mins) {
		// if there is a time set for available then update the availability accordingly
		if(this.getAvalAt() != 0)
		this.setAvailable(mins >= this.getAvalAt());
	}
	
	// Send the bus to campus and conclude the flight data
	public void sendBus(int days, ArrayList<Student> tempStudents, ArrayList<ArrayList<Flight>> flightReport, ArrayList<Student> studentsALl, ArrayList<Flight> flightsAll) {
		//create an object of the calling bus
		Bus callingBus = this;
		
		//create a new flight to be launched and add it to tripsArray
		Flight currentFlight = new RegularFlight();
		
		//set the busUsed attribute of the current flight
		currentFlight.setBusUsed(callingBus);
		
		//add the current flight to the trip array
		callingBus.addTripsArray(currentFlight);
		
		//update the bus data
		Updater.updateBusData(callingBus, currentFlight);
		
		//update the data of the new flight that about to be launched
		Updater.updateFlightData(callingBus, currentFlight);
		
		//make students in this flight miss or catch
		Updater.updateMissCatch(callingBus, currentFlight, tempStudents, studentsALl);
		
		//print the launched flight data along with the students in it
		Updater.updateFlightReport(days, currentFlight, flightReport, flightsAll);
		
		// clear the attribute flightStudents to fill it with the students of the next flight
		callingBus.getFlightStudents().clear();
	}
	
	// Clone bus object...
	@Override
    public Object clone() throws CloneNotSupportedException{
        // Assign the shallow copy to new reference variable
        Bus CB = (Bus)super.clone();
  
        CB.setStudentsDelivered(new ArrayList<>());
        for(Student s : this.getStudentsDelivered()) {
        	CB.addStudentsDelivered((Student)s.clone());
        }
        
        CB.tripsArray = new ArrayList<>();
        for(Flight f : this.tripsArray) {
        	CB.tripsArray.add(f);
        }
  
        // Create a new object for the field c
        // and assign it to shallow copy obtained,
        // to make it a deep copy
        return CB;
    }
    
	// String representation of bus moving to campus at declared time
	public String toString() {
		return "{Bus Moving at: " + this.getScheduledDormDeparture() + "mins}";
	}
	
	//---------------------Inner Classes---------------------------
	
	// Class Updater is used to update data after concluding the flight
	class Updater {
		// Updating the departing bus' data
		public static void updateBusData(Bus callingBus, Flight currentFlight) {
			//setting the bus as unavailable
			callingBus.setAvailable(false);
			//setting the bus available time to current time + commute time to campus
			callingBus.setAvalAt(Time.clock  + 2*currentFlight.getMINUTES_TO_KAU());
			//setting the new schedule for the bus to make a new trip
			callingBus.setScheduledDormDeparture(Time.clock  + 2*currentFlight.getMINUTES_TO_KAU() + 30);
			//updating the time bus departed the dorms
			callingBus.setDormDeparture(Time.clock);
			//updating the time the bus arrived at campus
			callingBus.setCampusArrival(Time.clock + currentFlight.getMINUTES_TO_KAU());
			//updating the capacity of the bus (all seats are available)
			callingBus.setCapacity(10);
		}
		
		// update the data of the flight that about to be launched
		public static void updateFlightData(Bus callingBuss, Flight currentFlight) {
			//setting the time that the flight has departed the dorms
			currentFlight.setTimeOfDeparture(callingBuss.getDormDeparture());
			//setting the time that the flight has arrived at campus
			currentFlight.setTimeOfArrival(callingBuss.getCampusArrival());
		}
		
		// Update the condition of students in the flight to whether they caught or missed their lecture
		public static void updateMissCatch(Bus callingBus, Flight currentFlight, ArrayList<Student> tempStudents, ArrayList<Student> studentsALl) {
			int numStudetns = callingBus.getFlightStudents().size(); // number of students in the flight
			ArrayList<Student> S = new ArrayList<>();
			// Loop for adding students in the flight to a clone array...
			for(Student stu : callingBus.getFlightStudents()) {
				try {
				S.add((Student)stu.clone());
				}catch(CloneNotSupportedException e) {
					System.out.println(e.getMessage());
				}
			}
			// Loop for updating the catch condition...
			for (int i = 0; i < numStudetns; i++) {
				Student student = callingBus.getFlightStudents().get(i);
				student.setIsCatch(callingBus.getCampusArrival() <= student.getIntendedArrivalTime());
				if (callingBus.getCampusArrival() <= student.getIntendedArrivalTime()) currentFlight.incCatches();
				tempStudents.add(student);
				studentsALl.add(student);
				callingBus.getStudentsDelivered().add(student);
				currentFlight.studentsInTrip.add(student);
			}
		}
		
		// Update the launched flight data
		public static void updateFlightReport(int days, Flight currentFlight, ArrayList<ArrayList<Flight>> flightReport, ArrayList<Flight> flightsAll) {
			int catches = currentFlight.getCatches(); // number of student caught their lecture
			int misses = currentFlight.studentsInTrip.size() - currentFlight.getCatches(); // number of student missed their lecture
			//string representation of percentage of students caught their lecture in the flight
			String perCC = String.format("%.2f", 100*((double)(catches) / (catches+misses))) + "%";
			currentFlight.setCatchesPer(perCC);				//setting the catch %
			flightsAll.add(currentFlight);					//adding the departed flight to all flight array
			flightReport.get(days - 1).add(currentFlight);	//adding the flight to reports array
		}
		
	}
	
}


