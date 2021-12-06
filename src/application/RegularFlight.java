package application;
import java.util.ArrayList;

/**
 * @author Azeez + Ahmed + Turki
 *
 */
public class RegularFlight extends Flight {
	
	//--------------------RegularFlight Variables----------------------
	public static final double DISTANCE_TO_KAU = 18.2; // distance in KM to campus from dorms
	public static final int MINUTES_TO_KAU = 30;	   // Time in minutes to campus from dorms
	public static final int FUEL_TO_KAU = 10;		   // Fuel required to get from campus to dorms
	
	//--------------------RegularFlight Constructors-------------------
	public RegularFlight() {
		setTypeOfFilght("Regular Flight");
		Flight.incNumFlights();
	}
	
	//--------------Setters & Getters-------------------
	public ArrayList<Student> getStudentsInTrip() {
		return studentsInTrip;
	}

	public void setStudentsInTrip(ArrayList<Student> studentsInTrip) {
		this.studentsInTrip = studentsInTrip;
	}
	
	@Override
	public double getDISTANCE_TO_KAU() {
		return DISTANCE_TO_KAU;
	}
	
	@Override
	public int getMINUTES_TO_KAU() {
		return MINUTES_TO_KAU;
	}
	@Override
	public int getFUEL_TO_KAU() {
		return FUEL_TO_KAU;
	}
	
	@Override
	public int getTimeOfDeparture() {
		return timeOfDeparture;
	}
	@Override
	public void setTimeOfDeparture(int timeOfDeparture) {
		this.timeOfDeparture = timeOfDeparture;
	}
	@Override
	public int getTimeOfArrival() {
		return timeOfArrival;
	}
	@Override
	public void setTimeOfArrival(int timeOfArrival) {
		this.timeOfArrival = timeOfArrival;
	}

}