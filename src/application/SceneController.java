package application;



import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * This class is for Action and connection between FXML Buttons and the actions
 *
 */
public class SceneController implements Initializable {
	


	
	
	//TestFields
	@FXML
	TextField NumberOfDays;
	@FXML
	TextField NumberOfBusses;
	@FXML
	TextField NumberOfStudents;
	
	
	@FXML
	TextArea TextFieldPhase1Report;
	@FXML
	public void start(ActionEvent ae) {
		int KAUdays = Integer.parseInt(NumberOfDays.getText());
		int KAUbusses = Integer.parseInt(NumberOfBusses.getText());
		String KAUstudents = NumberOfStudents.getText();

		
		String[] parts = KAUstudents.split(",");
		int[] n1 = new int[parts.length];
		for(int n = 0; n < parts.length; n++) {
		   n1[n] = Integer.parseInt(parts[n]);
		}
		
		
		ArrayList<ArrayList<Flight>> report = GUI(KAUdays, KAUbusses, n1);
		String con = "";
		for(Flight s : report.get(0)) {
			con = con + " " + s;
		}
		TextFieldPhase1Report.setText(con);
		
		
		//+++++++++++++++++++++++++++++++++++++
		//testing
//		String[] cars = doo();
//		String con = "";
//		for(String s : cars) {
//			con = con + " " + s;
//		}
//		TextFieldPhase1Report.setText(con);
		//+++++++++++++++++++++++++++++++++++++
	}
	
//	public String[] doo() {
//		String[] parts=  {"Volvo", "BMW", "Ford", "Mazda"};
//		return parts;
//	}
	
	
	
	public static ArrayList<ArrayList<Flight>> GUI(int KAUdays, int KAUbus, int[] KAUstudent) {
		// report lists
		ArrayList<ArrayList<Flight>> flightReport = new ArrayList<ArrayList<Flight>>(KAUdays);
		for(int i = 0; i<KAUdays; i++) {
			flightReport.add(new ArrayList<Flight>());
		}
		
		
        // create time object
		Time realClock = new Time();
		
		
        //************initilize required number of days varaible************
		
		System.out.println("Test days: ");
		int testDays;
		//Exception handling
		try {
			testDays = KAUdays;
		}catch(InputMismatchException e){
			System.out.println("Not a valid input for the number of days. the default value is set {1 day}");
			testDays = 1;
		}
		
		
		//************************BUSSES*************************
        //create a Queue of busses
		System.out.println("Number of busses: ");
		int testBusses;
		//Exception handling
		try {
			testBusses = KAUbus;
			if(testBusses <= 0) throw new BusNumberException(testBusses);
		}catch(BusNumberException e) {
			System.out.println(e.getMessage() + "the default value is set {2 busses}");

			testBusses = 2;
		}catch(InputMismatchException e){
			System.out.println("Not a valid input for the number of busses. the default value is set {2 busses}");

			testBusses = 2;
		}
		
		Queue busses = new Queue(testBusses);
		int scheduledDormDeparture;
		for(int i = 0; i < testBusses; i++) {
			//formula for cal the schduled dorm departure
			scheduledDormDeparture = 30*i + 30;
			busses.enqueue(new Bus(scheduledDormDeparture, i));
		}
		
		//********************LOOPS(MAIN TASK)************************
        //initillize main while loop (number of days)
		int days = 1;
		while(testDays > 0) {
			
			//************************STUDENTS*************************
	        //create an ordered array of random students based on showed up times
			ArrayList<Student> students = generateRandomStudents(days, KAUstudent);
			
			//create a temp array of arrived students
			ArrayList<Student> tempStudents = new ArrayList<>();

			//create pointer student
			int studentPointer = 0;
			
			//while loop on the array of students
			System.out.println("printing the reporting of day#" + days);
			while (tempStudents.size() < students.size()) {
				// if clock >= realClock.endingHour
				if (Time.clock >= realClock.endingHour) break;
				
				//update availability after incrementing the clock
				updateAval(testBusses, busses);

				
				//current bus & current student
				Bus bus = busses.peek();
				Student student = students.get(studentPointer);

				//if not aval update the bus pointer to
				// the next bus and continue back
				if (!bus.isAvailable()) {
					busses.dequeue();
					busses.enqueue(bus);
					Time.incrementClock();
					continue;
				}

                //else if showed up = clock
				if (student.getShowupTime() <= Time.clock) {
                    //load to bus and increment student pointer
					bus.loadStudent(student);
					studentPointer++;
				}
				else {
					//increment the clock                     
					Time.incrementClock();
				}
				//update availability after incrementing the clock
				updateAval(testBusses, busses);

				// if capacity is full:
				if (bus.getCapacity() == 0) {
                    // 1- send the bus (update availability, time of arrival, time of departure, distance covered, fuel consumption, trips)
					System.out.println();
					bus.sendBus(days, tempStudents, flightReport);
                    // 3- increment the bus pointer of the array
					busses.dequeue();
					busses.enqueue(bus);
                    // 4- increment the clock 
					Time.incrementClock();
                    // 5- continue loading students with updated clock
					continue;
				}
        		// else if there is at least one student in the bus(cap < 10):
				else if (bus.getCapacity() < 10) {
					// if clock meets (every 30 mins a bus should move regardless of students) && there is at least one student in the bus:
					if (bus.getScheduledDormDeparture() == Time.clock) {
	                    // 1- send the bus (update availability, time of arrival, time of departure, distance covered, fuel consumption, trips)
						System.out.println();
						bus.sendBus(days, tempStudents, flightReport);
	                // 3- increment the bus pointer of the array
						busses.dequeue();
						busses.enqueue(bus);
                    // 4- increment the clock 
						Time.incrementClock();
	                // 5- continue loading students with updated clock
						continue;
				}
					// else if the student pointer is null && there is at least one student in the bus
					else if (studentPointer >= students.size()) {
						//send the bus
						System.out.println();
						while (true) {
							if (bus.getScheduledDormDeparture() == Time.clock) break;
							Time.incrementClock();	
						}
						bus.sendBus(days, tempStudents, flightReport);
						break;
					}
			
					}
				
				// if there are no students in the bus and clock meets scheduled dormDeparture:
				noStudentsAndTimeToMove(bus, busses);
		}//end students loop
		//loop in the array of arrived students and print the report
		System.out.println("\n\nstudents in the whole day:");
		System.out.println(tempStudents.size());
		for(int i = 0; i < tempStudents.size(); i++) {
			Student s = tempStudents.get(i);
			System.out.println(s);
		}
		//reinitilize the array of students and busses and reset clock
		Time.clock = realClock.getStartingHour();
		tempStudents.clear();
		//reinitilize pointers(bus and student)
		studentPointer = 0;
		//decrement the testDays
		testDays--;
		days++;
		//new busses
		
		//************************BUSSES*************************
        //create a Queue of busses
		busses = new Queue(testBusses);
		scheduledDormDeparture = 0;
		for(int i = 0; i < testBusses; i++) {
			//formula for cal the schduled dorm departure
			scheduledDormDeparture = 30*i + 30;
			busses.enqueue(new Bus(scheduledDormDeparture, i));
		}
		} //end days loop
		
		
		
		
		return flightReport;	
	}
		
		
	public static void updateAval(int testBusses, Queue busses) {
		//update availability after incrementing the clock
		for(int i = 0; i < testBusses; i++) {
			Bus currentBus = busses.peek();
			currentBus.checkAval(Time.clock);
			busses.dequeue();
			busses.enqueue(currentBus);
		}
	}
	
	public static ArrayList<Student> generateRandomStudents(int days, int[] KAUStudent) {
		Scanner input = new Scanner(System.in);
		System.out.println("Number of students in day#" + days);
		int testStudents = KAUStudent[days-1];
		ArrayList<Student> students = new ArrayList<>();
		for(int i = 0; i < testStudents; i++) {
			students.add(new Student());
		}
		Collections.sort(students);
		return students;
	}
	
	public static void noStudentsAndTimeToMove(Bus bus, Queue busses) {
		if (bus.getCapacity() == 10 && bus.getScheduledDormDeparture() == Time.clock) {
            //update the scheduled dormDeparture to the current scheduled bus departure time + 30
			// whish is (period for waiting students)
			for(int i = 1; i <= busses.size(); i++) {
				int NSDD = Time.clock + i*30;
				Bus current = busses.peek();
				current.setScheduledDormDeparture(NSDD);
				busses.dequeue();
				busses.enqueue(current);
			}
	}

}
		
		
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}