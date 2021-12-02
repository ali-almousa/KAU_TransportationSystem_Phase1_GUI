package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

public class FXMLmainController  implements Initializable{
	//Starting
	@FXML
	private Button Start;
	@FXML
	private TextField NumberOfDays;
	@FXML
	private TextField NumberOfBusses;
	@FXML
	private TextField NumberOfStudents;

	// Exporting-------------------------------------
	@FXML
	private Button ExportPhase1Button;
	@FXML
	private Button ExportPhase2Button;
	// Reporting Phase 1 and 2-----------------------
	@FXML
	private TextArea ReportTextAreaPhase1;
	@FXML
	private TextArea TotalSummaryPhase1;
	@FXML
	private TextArea ReportTextAreaPhase2;
	@FXML
	private TextArea TotalSummaryPhase2;
	//-----------------------------------------------
	//SearchID pop Up
	@FXML
	private TextArea SearchIDReport;
	@FXML
	private Button CloseSearchIDButton;
	//Inside SearchID Window
	@FXML
	private Button SearchIDButton;
	@FXML
	private TextField SearchIDTextField;
	//-----------------------------------------------
	//Flight pop Up
	@FXML
	private TextField FlightNumberTextField;
	@FXML
	private Button FlightButton;
	//Inside Flight Window
	@FXML
	private TextArea FlightSummaryReport;
	@FXML
	private Button CloseFlightButton;
	//-----------------------------------------------
	//Day Summary pop Up
	@FXML
	private TextField SummaryDayTextField;
	@FXML
	private Button SummaryDayButton;
	//Inside Day Summary Window
	@FXML
	private TextArea DaySummaryReport;
	@FXML
	private Button CloseDaySummaryButton;
	//-----------------------------------------------
	
	
	//Search ID PopUp window
	@FXML
	public void SearchIDButtonPopUp(ActionEvent e) throws IOException{
		Stage stage;
		Parent root;
		
		if(e.getSource() == SearchIDButton) {
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("FXMLsearchID.fxml"));
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(SearchIDButton.getScene().getWindow());
			stage.showAndWait();
		}else {
			stage = (Stage) CloseSearchIDButton.getScene().getWindow();
			stage.close();
			}
		
		
		
	}
	//Flight Summary PopUp window
	@FXML
	public void FlightButtonPopUp(ActionEvent e) throws IOException{
		
		Stage stage;
		Parent root;
		
		if(e.getSource() == FlightButton) {
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("FXMLFlightSummary.fxml"));
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(FlightButton.getScene().getWindow());
			stage.showAndWait();
		}else {
			stage = (Stage) CloseFlightButton.getScene().getWindow();
			stage.close();
			}
		
	}
	
	//Summary Day PopUp window
	@FXML
	public void SummaryDayButtonPopUp(ActionEvent e) throws IOException{
		
		Stage stage;
		Parent root;
		
		if(e.getSource() == SummaryDayButton) {
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("FXMLDaySummary.fxml"));
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(SummaryDayButton.getScene().getWindow());
			stage.showAndWait();
		}else {
			stage = (Stage) CloseDaySummaryButton.getScene().getWindow();
			stage.close();
			}
		
		
	}
	//Exprting Phase1
	@FXML
	public void ExportPhase1(ActionEvent e) throws IOException{
		
		
	}
	@FXML
	public void ExportPhase2(ActionEvent e) throws IOException{
		
		
	}
	
	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	
	@FXML
	public void start(ActionEvent ae) {
		System.out.println("crjknf");
		int KAUdays = Integer.parseInt(NumberOfDays.getText());
		int KAUbusses = Integer.parseInt(NumberOfBusses.getText());
		String KAUstudents = NumberOfStudents.getText();

		
		String[] parts = KAUstudents.split(",");
		int[] n1 = new int[parts.length];
		for(int n = 0; n < parts.length; n++) {
		   n1[n] = Integer.parseInt(parts[n]);
		}
		
		
		ArrayList<Object> report = GUI(KAUdays, KAUbusses, n1);
		ArrayList<ArrayList<Flight>> flightReport = (ArrayList<ArrayList<Flight>>)report.get(0);
		ArrayList<ArrayList<Bus>> BusReport = (ArrayList<ArrayList<Bus>>)report.get(1);
		double[] summaryP1 =  (double[]) report.get(2);
		
		String comprehensiveReport ="";
		String summaryReportP1 = "";
		
        
        for(int day = 0; day < flightReport.size(); day++) {
        	comprehensiveReport+=String.format("\n%n%s Day %d %s%n",star(77), day + 1, star(77));
        	comprehensiveReport+=String.format("%s\n\n","Flights Information:");

        	
        	for(int flight = 0; flight < flightReport.get(day).size(); flight++) {
//        		+++++++++++++++++++++++++++Flights Information+++++++++++++++++++++++++++++++++++++
        		Flight currentFlight = flightReport.get(day).get(flight);
        		comprehensiveReport+=String.format("%n%s%n",minus(206));
                comprehensiveReport+=String.format("%s\t\s", "Flight Number");
                comprehensiveReport+=String.format("%s\t", "Number of Students");
                comprehensiveReport+=String.format("%s\t\s", "Moved at");
                comprehensiveReport+=String.format("%s\t\s\t", "Arraived at");
                comprehensiveReport+=String.format("%s\t\s", "Number of Cathces");
                comprehensiveReport+=String.format("%s\t\s\t", "Number of misses");
                comprehensiveReport+=String.format("%s\s\t\s", "Catch %");
                comprehensiveReport+=String.format("%s\t\s\s\t", "Flight Type");
                comprehensiveReport+=String.format("%s\n", "BusID");

                comprehensiveReport+=String.format("\t%d\t\t\t", flight + 1);
                comprehensiveReport+=String.format("%d\t\t\t", currentFlight.studentsInTrip.size());
                comprehensiveReport+=String.format("%s\t\t\t", Time.MinutesToTime(currentFlight.getTimeOfDeparture()));
                comprehensiveReport+=String.format("%s\t\t\t", Time.MinutesToTime(currentFlight.getTimeOfArrival()));
                comprehensiveReport+=String.format("%d\t\t\t\t", currentFlight.getCatches());
                comprehensiveReport+=String.format("%d\t\t\t", currentFlight.studentsInTrip.size() - currentFlight.getCatches());
                comprehensiveReport+=String.format("%s\t\t", currentFlight.getCatchesPer());
                comprehensiveReport+=String.format("%s\t\t", currentFlight.getTypeOfFilght());
                comprehensiveReport+=String.format("%s\n", currentFlight.getBusUsed().getID());
                comprehensiveReport+=String.format("%n%s%n",minus(206));
//        		+++++++++++++++++++++++++++Flights Information+++++++++++++++++++++++++++++++++++++
                
//        		+++++++++++++++++++++++++++Student in Flight Information+++++++++++++++++++++++++++++++++++++
                
                comprehensiveReport+=String.format("%s\t\t", "Student Number");
                comprehensiveReport+=String.format("%s\t     ", "ID");
                comprehensiveReport+=String.format("%s\t\t", "ShowUp Time");
                comprehensiveReport+=String.format("  %s\t\t", "Intended Arraival Time");
                comprehensiveReport+=String.format("%s\t\t", "isCatch?");
                comprehensiveReport+=String.format("%s\n", "HasExam?");

                for(int S = 0; S < currentFlight.studentsInTrip.size(); S++) {
                	Student stu = currentFlight.studentsInTrip.get(S);
                    comprehensiveReport+=String.format("\t%d\t", S + 1);
                    comprehensiveReport+=String.format("      %d\t\t", stu.getID());
                    comprehensiveReport+=String.format("%s\t\t\t\t", Time.MinutesToTime(stu.getShowupTime()));
                    comprehensiveReport+=String.format("%s\t\t\t\t", Time.MinutesToTime(stu.getIntendedArrivalTime()));
                    comprehensiveReport+=String.format("  %s\t\t\t", stu.isCatch());
                    comprehensiveReport+=String.format(" %s\n\n\n", stu.getHasExam());
                }
//        		+++++++++++++++++++++++++++Student in Flight Information+++++++++++++++++++++++++++++++++++++
        		
        	}
//        		+++++++++++++++++++++++++++Bus in Flight Information+++++++++++++++++++++++++++++++++++++
            	comprehensiveReport+=String.format("\n%s\n\n","Bus Information:");
            	
                comprehensiveReport+=String.format("%s\t\t", "BusID");
                comprehensiveReport+=String.format("%s\t     ", "Moved-Distance KM");
                comprehensiveReport+=String.format("%s\t\t", "fuelConsumbtion L");
                comprehensiveReport+=String.format("  %s\t\t", "number Of Trips");
                comprehensiveReport+=String.format("%s\n", "Students Delivered");
        	
                for(int B = 0; B < BusReport.get(day).size(); B++) {
                	Bus Cbus = BusReport.get(day).get(B);
                    comprehensiveReport+=String.format("\t%d\t", Cbus.getID());
                    comprehensiveReport+=String.format("      %.2f\t\t", Cbus.getDistanceKm());
                    comprehensiveReport+=String.format("%.2f\t\t\t\t", Cbus.getFuelConsumption());
                    comprehensiveReport+=String.format("%d\t\t\t\t", Cbus.getNumberTrips());
                    comprehensiveReport+=String.format(" %d\n\n\n", Cbus.getStudentsDelivered().size());
                }
//        		+++++++++++++++++++++++++++Bus in Flight Information+++++++++++++++++++++++++++++++++++++
                
                
        	
        	
        	
        }

        double catchPer = 100*((double)(summaryP1[0]) / (summaryP1[0]+summaryP1[1]));

        summaryReportP1+=String.format("%s\t\s", "Total Catches");
        summaryReportP1+=String.format("%s\t", "Total Misses");
        summaryReportP1+=String.format("%s\t\s\t", "Total Catches%");
        summaryReportP1+=String.format("%s\t\s", "Total Number of Flgihts");
        summaryReportP1+=String.format("%s\t\s\t", "Total Students Delivered");
        summaryReportP1+=String.format("%s\t\s", "Total Students not Delivered");
        summaryReportP1+=String.format("%s\t\s\t", "Total Distance");
        summaryReportP1+=String.format("%s\n", "Total Fuel");
        
        
        summaryReportP1+=String.format("\t%d\t\t\t", (int)summaryP1[0]);
        summaryReportP1+=String.format("%d\t\t\t", (int)summaryP1[1]);
        summaryReportP1+=String.format("%.2f%%\t\t\t", catchPer);
        summaryReportP1+=String.format("%d\t\t\t", (int)summaryP1[2]);
        summaryReportP1+=String.format("%d\t\t\t\t", (int)summaryP1[3]);
        summaryReportP1+=String.format("%d\t\t\t", (int)summaryP1[4]);
        summaryReportP1+=String.format("%.2f\t\t", summaryP1[5]);
        summaryReportP1+=String.format("%.2f\n", summaryP1[6]);
 


        
   
        ReportTextAreaPhase1.setText(comprehensiveReport);
        TotalSummaryPhase1.setText(summaryReportP1);
		
	}
	
	
	
	public static String star(int a){
        String star="";
        for(int i=0;i<a;i++){
            star+="*";
        }
        return star;
    }
    
    public static String minus(int a){
        String star="";
        for(int i=0;i<a;i++){
            star+="-";
        }
        return star;
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
//		System.out.println("Number of students in day#" + days);
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
	
	
	
	public static ArrayList<Object> GUI(int KAUdays, int KAUbus, int[] KAUstudent) {
		// report lists
		double[] totalSummary = new double[7];
		ArrayList<ArrayList<Flight>> flightReport = new ArrayList<ArrayList<Flight>>(KAUdays);
		ArrayList<ArrayList<Bus>> busReport = new ArrayList<ArrayList<Bus>>(KAUdays);		
		for(int i = 0; i<KAUdays; i++) {
			flightReport.add(new ArrayList<Flight>());
			busReport.add(new ArrayList<Bus>());
		}

		
		
        // create time object
		Time realClock = new Time();
		
		
        //************initilize required number of days varaible************
		
//		System.out.println("Test days: ");
		int testDays;
		//Exception handling
		try {
			testDays = KAUdays;
		}catch(InputMismatchException e){
//			System.out.println("Not a valid input for the number of days. the default value is set {1 day}");
			testDays = 1;
		}
		
		
		//************************BUSSES*************************
        //create a Queue of busses
//		System.out.println("Number of busses: ");
		int testBusses;
		//Exception handling
		try {
			testBusses = KAUbus;
			if(testBusses <= 0) throw new BusNumberException(testBusses);
		}catch(BusNumberException e) {
//			System.out.println(e.getMessage() + "the default value is set {2 busses}");

			testBusses = 2;
		}catch(InputMismatchException e){
//			System.out.println("Not a valid input for the number of busses. the default value is set {2 busses}");

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
//			System.out.println("printing the reporting of day#" + days);
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
//					System.out.println();
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
//						System.out.println();
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
//						System.out.println();
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
		for(int i = 0; i < tempStudents.size(); i++) {
			Student s = tempStudents.get(i);
//			System.out.println(s);
		}
		//reinitilize the array of students and busses and reset clock
		Time.clock = realClock.getStartingHour();
		tempStudents.clear();
		//reinitilize pointers(bus and student)
		studentPointer = 0;
		int totalDeliveredStudents = 0;
		double totalDistance = 0;
		double totalFuelConsumption = 0;
		for(Bus B : busses.arr) {
			totalDeliveredStudents = totalDeliveredStudents + B.getStudentsDelivered().size();
			totalDistance = totalDistance + B.getDistanceKm();
			totalFuelConsumption = totalFuelConsumption + B.getFuelConsumption();
			try {
				busReport.get(days - 1).add((Bus)B.clone());
			}catch(CloneNotSupportedException e) {
				System.out.println(e.getMessage());
			}
		} 
		//decrement the testDays
		testDays--;
		days++;
		//new busses
		
		//************************BUSSES*************************
		//send busses used for today for report list

        //create a Queue of busses
		busses = new Queue(testBusses);
		scheduledDormDeparture = 0;
		for(int i = 0; i < testBusses; i++) {
			//formula for cal the schduled dorm departure
			scheduledDormDeparture = 30*i + 30;
			busses.enqueue(new Bus(scheduledDormDeparture, i));
		}
		
		// total catches & misses
		int catchesOfTheDay = 0;
		for (Student S : students) {
			if(S.isCatch) catchesOfTheDay++;
		}
		totalSummary[0] = totalSummary[0] + catchesOfTheDay;
		totalSummary[1] = totalSummary[1] + (students.size() - catchesOfTheDay);
		totalSummary[3] = totalSummary[3] + totalDeliveredStudents;
		totalSummary[4] = totalSummary[4] + (students.size() - totalDeliveredStudents);
		totalSummary[5] = totalSummary[5] + totalDistance;
		totalSummary[6] = totalSummary[6] + totalFuelConsumption;
		
		
		} //end days loop
		
		
		totalSummary[2] = Flight.getNumFlights();
		ArrayList<Object> result = new ArrayList<>();
		result.add(flightReport);
		result.add(busReport);
		result.add(totalSummary);
		return result;
		
	}
	
	
	
	
		@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
