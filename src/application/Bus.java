
package application;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ali
 *
 */
public class Bus implements Cloneable{
	private int numberTrips;
	private double distanceKm;
	private double fuelConsumption;
	private boolean available;
	private int capacity;
	private int avalAt;
	private int ID;
	public ArrayList<Flight> tripsArray = new ArrayList<>();
	public ArrayList<Student> flightStudents = new ArrayList<>();
	private int campusArrival;
	private int dormDeparture;
	private int scheduledDormDeparture;
	private ArrayList<Student> studentsDelivered = new ArrayList<>(); 
	
	public Bus(int scheduledDormDeparture, int ID) {
		this.setAvailable(true);
		this.setCapacity(10);
		this.setID(ID);
		this.setScheduledDormDeparture(scheduledDormDeparture);
	}
	
	
	/**
	 * @return the studentsDelivered
	 */
	public ArrayList<Student> getStudentsDelivered() {
		return studentsDelivered;
	}


	/**
	 * @param studentsDelivered the studentsDelivered to set
	 */
	public void setStudentsDelivered(ArrayList<Student> studentsDelivered) {

		this.studentsDelivered = studentsDelivered;
	}
	
	public void addStudentsDelivered(Student S) {
		this.studentsDelivered.add(S);
	}


	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(int ID) {
		this.ID = ID;
	}

	/**
	 * @return the scheduledDormDeparture
	 */
	public int getScheduledDormDeparture() {
		return scheduledDormDeparture;
	}

	/**
	 * @param scheduledDormDeparture the scheduledDormDeparture to set
	 */
	public void setScheduledDormDeparture(int scheduledDormDeparture) {
		//if scheduledDormDeparture equals to or greater than 10:00pm then set this bus as unavailable  
		if(scheduledDormDeparture >= 960) {
			this.setAvailable(false);
		}
		this.scheduledDormDeparture = scheduledDormDeparture;
	}

	/**
	 * @return the numberTrips
	 */
	public int getNumberTrips() {
		return numberTrips;
	}

	/**
	 * @param numberTrips the numberTrips to set
	 */
	public void setNumberTrips(int numberTrips) {
		this.numberTrips = numberTrips;
	}

	/**
	 * @return the distanceKm
	 */
	public double getDistanceKm() {
		return distanceKm;
	}

	/**
	 * @param distanceKm the distanceKm to set
	 */
	public void setDistanceKm(double distanceKm) {
		this.distanceKm = distanceKm;
	}

	/**
	 * @return the fuelConsumption
	 */
	public double getFuelConsumption() {
		return fuelConsumption;
	}

	/**
	 * @param fuelConsumption the fuelConsumption to set
	 */
	public void setFuelConsumption(double fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}

	/**
	 * @return the available
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(boolean aval) {
		this.available = aval;
	}
	
	public void checkAval(int mins) {
		// if there is a time set for available then update the availability accordingly
		if(this.getAvalAt() != 0)
		this.setAvailable(mins >= this.getAvalAt());
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * @return the tripsArray
	 */
	public ArrayList<Flight> getTripsArray() {
		return tripsArray;
	}

	/**
	 * @param tripsArray the tripsArray to set
	 */
	//poly
	public void addTripsArray( Flight trip ) {
		this.tripsArray.add(trip);
		// setting cumulative attributes
		this.setDistanceKm(this.getDistanceKm() + 2 * trip.getDISTANCE_TO_KAU());
		this.setFuelConsumption(trip.getFUEL_TO_KAU() + this.getFuelConsumption());
		this.setNumberTrips(this.getNumberTrips() + 1);
	}

	/**
	 * @return the campusArrival
	 */
	// will return the time the bus will arrive to the campus in mins form
	public int getCampusArrival() {
		return campusArrival;
	}

	/**
	 * @param campusArrival the campusArrival to set
	 */
	public void setCampusArrival(int mins) {
		this.campusArrival = mins;
	}

	/**
	 * @return the dormDeparture
	 */
	public int getDormDeparture() {
		return dormDeparture;
	}

	/**
	 * @param dormDeparture the dormDeparture to set
	 */
	public void setDormDeparture(int mins) {
		this.dormDeparture = mins;
	}

	/**
	 * @return the flightStudents
	 */
	public ArrayList<Student> getFlightStudents() {
		return flightStudents;
	}

	/**
	 * @param the student to add to the flightStudents
	 */
	public void addFlightStudents(Student student) {
		this.flightStudents.add(student);
	}

	public void loadStudent(Student s) {
		this.setCapacity(this.getCapacity() - 1);
		//update the availability after decrementing the capacity of the bus
		if (this.getCapacity() == 0) {
			this.setAvailable(false);
		}
		// add the student to the current flightStudents 
		// this array will be cleared when the bus is sent
		this.addFlightStudents(s);
	}
	
	/**
	 * @return the avalAt
	 */
	public int getAvalAt() {
		return avalAt;
	}

	/**
	 * @param avalAt the avalAt to set
	 */
	public void setAvalAt(int avalAt) {
		this.avalAt = avalAt;
	}

	public void sendBus(int days, ArrayList<Student> tempStudents, ArrayList<ArrayList<Flight>> flightReport, ArrayList<Student> studentsALl, ArrayList<Flight> flightsAll) {
		//create a new flight to be launched and add it to tripsArray
		Flight currentFlight = new RegularFlight();
		//set the busUsed attribute of the current flight
		currentFlight.setBusUsed(this);
		
		this.addTripsArray(currentFlight);
		//update the bus data
		this.setAvailable(false);
		this.setAvalAt(Time.clock  + 2*currentFlight.getMINUTES_TO_KAU());
		this.setScheduledDormDeparture(Time.clock  + 2*currentFlight.getMINUTES_TO_KAU() + 30);
		this.setDormDeparture(Time.clock); 
		this.setCampusArrival(Time.clock + currentFlight.getMINUTES_TO_KAU());
		this.setCapacity(10);		
		//update the data of the new flight that about to be launched
		currentFlight.setTimeOfArrival(this.getCampusArrival());
		currentFlight.setTimeOfDeparture(this.getDormDeparture());
		//make students in this flight miss or catch
		int numStudetns = this.getFlightStudents().size();
		
		ArrayList<Student> S = new ArrayList<>();
		for(Student stu : this.getFlightStudents()) {
			try {
			S.add((Student)stu.clone());
			}catch(CloneNotSupportedException e) {
				System.out.println(e.getMessage());
			}
		}

		
		for (int i = 0; i < numStudetns; i++) {
			Student student = this.getFlightStudents().get(i);
			student.setIsCatch(this.getCampusArrival() <= student.getIntendedArrivalTime());
			if (this.getCampusArrival() <= student.getIntendedArrivalTime()) currentFlight.incCatches();
			tempStudents.add(student);
			studentsALl.add(student);
			this.getStudentsDelivered().add(student);
			currentFlight.studentsInTrip.add(student);
		}
		//print the launched flight data along with the students in it
//		System.out.println(currentFlight);
		int catches = currentFlight.getCatches();
		int misses = currentFlight.studentsInTrip.size() - currentFlight.getCatches();
		String perCC = String.format("%.2f", 100*((double)(catches) / (catches+misses))) + "%";
		currentFlight.setCatchesPer(perCC);
		flightsAll.add(currentFlight);
		flightReport.get(days - 1).add(currentFlight);
		
//		System.out.println("Bus ID: " + this.ID);

		for(int i = 0; i < numStudetns; i++) {
//			System.out.println(currentFlight.studentsInTrip.get(i));
		}
		//clear the attribute flightStudents to fill it with the
		//students of the next flight 
		
		this.getFlightStudents().clear();
		
	}
	
    
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }
	
	@Override
    public Object clone() throws CloneNotSupportedException{
        // Assign the shallow copy to new reference variable
        // t
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
    
    
    
	public String toString() {
		return "{Bus Moving at: " + this.getScheduledDormDeparture() + "mins}";
	}
	

	
	

}
