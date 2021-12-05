/**
 * @author Ali + Azeez 
 *
 *
 */
package application;

import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.TextArea;

public class FXMLmainController  implements Initializable{
	public static ArrayList<Student> studentsAll = new ArrayList<>();
	public static ArrayList<Flight> flightsAll = new ArrayList<>();
	public static int flightNumber;
	public static String summaryP1;
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
	private TextField SearchIDTextField;
	@FXML
	private TextField SearchIDTextField2;
	@FXML
	private Button CloseSearchIDButton;
	//Inside SearchID Window
	@FXML
	private Button SearchIDButton;
	@FXML
	private Button SearchIDButton2;
	@FXML
	private Button ButtonSearchIDButton3;
	@FXML
	private TextArea SearchIDReport;
	
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
	
	@FXML
	public void flightNumberSearch(ActionEvent e) throws IOException{
		String flightNum = FlightNumberTextField.getText();
		
		for(int i = 0; i < FXMLmainController.flightsAll.size(); i++) {
			if(i == Integer.parseInt(flightNum) - 1) {
				FlightSummaryReport.setText("Flight Number: " + (i + 1) + "     " +FXMLmainController.flightsAll.get(i).toString());
				FlightSummaryReport.setStyle("-fx-text-fill: red; -fx-font-size: 2em;");
//				FlightSummaryReport.setStyle("-fx-font-size: 2em;");
			}
		}
		
	}
	
	@FXML
	public void flightNumberButton(ActionEvent e) throws IOException{

		Stage stage;
		Parent root;
			
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("FXMLFlightSummary.fxml"));
			stage.setScene(new Scene(root));	
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(SearchIDButton.getScene().getWindow());
			stage.showAndWait();
		
	}
	
	@FXML
	public void close(ActionEvent e) throws IOException{
		Stage stage;
		try {
			stage = (Stage) CloseSearchIDButton.getScene().getWindow();
			stage.close();
		}catch(Exception e1) {
			System.out.print(e1.getMessage());
		}
		
	}
	
	//Search ID PopUp window
		@FXML
		public void SearchIDButton(ActionEvent e) throws IOException{
			String ID = SearchIDTextField2.getText();

			for(Student S : studentsAll) {
				if(S.getID() == Integer.parseInt(ID)) {
					SearchIDReport.setText(S.toString());
					SearchIDReport.setStyle("-fx-font-size: 1.3em; -fx-text-fill: red ;");
				}
			}		
		}
	
	
	//Search ID PopUp window
	@FXML
	public void SearchIDButtonPopUp(ActionEvent e) throws IOException{
		Stage stage;
		Parent root;
		
//		SearchIDReport.setText("jyhh");
		
//		if(e.getSource() == SearchIDButton) {
//		String ID = SearchIDTextField.getText();
//		System.out.print(ID);
		
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("FXMLsearchID.fxml"));
			stage.setScene(new Scene(root));
			
			//Student ID
//			SearchIDTextField.getText();
			
			//Get student Info.
			
			//Set info
			
			
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(SearchIDButton.getScene().getWindow());
			stage.showAndWait();
//			SearchIDReport.setText("jyhh");
			
			
//		}else {
//			stage = (Stage) CloseSearchIDButton.getScene().getWindow();
//			stage.close();
//			}
		
		
		
		
		
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
	public void ExportPhase1(ActionEvent e) throws IOException, FileNotFoundException{
		File file = new File("ReportOfPhase1.txt");
		
		try(PrintWriter write = new PrintWriter(file)){
			write.printf("&&&&&&&&&&&&&&&&&&&&& %s &&&&&&&&&&&&&&&&&&&&&\n\n", "Pahse#1 Full Report");
			write.print(FXMLmainController.summaryP1);
		}
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle("Success");
		alert.setHeaderText(null);
		alert.setContentText(String.format("%s", "Phase1 report exported successfully to 'ReportOfPhase1.txt' in the same directory!"));
		alert.showAndWait();
	}
	@FXML
	public void ExportPhase2(ActionEvent e) throws IOException{
		
		
	}
	
	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	
	@FXML
	public void start(ActionEvent ae) {
		
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle("Error Message");
		alert.setHeaderText(null);
		System.out.println("Start Simulation");
		int KAUdays;
		try {
			if(!isNumeric(NumberOfDays.getText())) {
				throw new InputMismatchException();
			}
			if(isNumeric(NumberOfDays.getText()) && Integer.parseInt(NumberOfDays.getText()) <= 0) {
				throw new BusNumberException(Integer.parseInt(NumberOfDays.getText()));
			}
			 
			KAUdays = Integer.parseInt(NumberOfDays.getText());
		}catch(InputMismatchException e){
			alert.setContentText(String.format("'%s' %s", NumberOfDays.getText(),  "Not a valid input for the number of days. the default value is set {1 day}"));
			alert.showAndWait();
			KAUdays = 1;
		}catch(BusNumberException e){
			alert.setContentText(String.format("'%s' %s", NumberOfDays.getText(),  "Not a valid input for the number of days. the default value is set {1 day}"));
			alert.showAndWait();
			KAUdays = 1;
		}catch(Exception e){
			System.out.println("Not a valid input for the number of days. the default value is set {1 day}");
			KAUdays = 1;
		}
		
		
		int KAUbusses;
		try {
			if(!isNumeric(NumberOfBusses.getText())) {
				throw new InputMismatchException();
			}
			if(isNumeric(NumberOfBusses.getText()) && Integer.parseInt(NumberOfBusses.getText()) <= 0) {
				throw new BusNumberException(Integer.parseInt(NumberOfBusses.getText()));
			}
			 
			KAUbusses = Integer.parseInt(NumberOfBusses.getText());
		}catch(InputMismatchException e){
			alert.setContentText(String.format("'%s' %s", NumberOfBusses.getText(),  "Not a valid input for the number of busses. the default value is set {2 busses}"));
			alert.showAndWait();
			KAUbusses = 2;
		}catch(BusNumberException e){
			alert.setContentText(String.format("'%s' %s", NumberOfBusses.getText(),  "Not a valid number for the number of busses. the default value is set {2 busses}"));
			alert.showAndWait();
			KAUbusses = 2;
		}catch(Exception e){
			System.out.println("Not a valid input for the number of busses. the default value is set {2 busses}");
			KAUbusses = 2;
		}
		
		
		
		
		
		String KAUstudents = NumberOfStudents.getText();

		
		String[] parts = KAUstudents.split(",");
		int[] n1 = new int[parts.length];
		for(int n = 0; n < parts.length; n++) {
		   n1[n] = Integer.parseInt(parts[n]);
		}
		
		try {
			if(n1.length != KAUdays) {
				throw new Exception();
			}
		}catch(Exception e){
			alert.setContentText(String.format("'%s' %s", NumberOfStudents.getText(),  "the length of the array of the number of students doesn't match the number of days!"));
			alert.showAndWait();
//			System.exit(1);
		}
		
		ArrayList<Object> report = GUI(KAUdays, KAUbusses, n1);
		ArrayList<ArrayList<Flight>> flightReport = (ArrayList<ArrayList<Flight>>)report.get(0);
		ArrayList<ArrayList<Bus>> BusReport = (ArrayList<ArrayList<Bus>>)report.get(1);
		double[] summaryP1 =  (double[]) report.get(2);
		
		String comprehensiveReport ="";
		String summaryReportP1 = "";
		
        
        for(int day = 0; day < flightReport.size(); day++) {
        	comprehensiveReport+=String.format("\n%n%s Day %d %s%n\n",star(60), day + 1, star(69));
        	comprehensiveReport+=String.format("%n%s%n",minus(142));
        	comprehensiveReport+=String.format(" 									        %s","Flights Information:");
        	
        	for(int flight = 0; flight < flightReport.get(day).size(); flight++) {
//        		+++++++++++++++++++++++++++Flights Information+++++++++++++++++++++++++++++++++++++
        		Flight currentFlight = flightReport.get(day).get(flight);
        		comprehensiveReport+=String.format("%n%s%n",minus(142));
                comprehensiveReport+=String.format("\s\s\s\s%s", "Flight");
                comprehensiveReport+=String.format("	   %s", "Students");
                comprehensiveReport+=String.format("	   %s", "Moved at");
                comprehensiveReport+=String.format("	      %s", "Arraived at");
                comprehensiveReport+=String.format("	 	   	%s", "Cathces");
                comprehensiveReport+=String.format("	    %s", "Misses");
                comprehensiveReport+=String.format("	 	  %s", "Catch %");
                comprehensiveReport+=String.format(" 	 %s", "Flight Type");
                comprehensiveReport+=String.format("	    %s\n", "BusID");
                
                comprehensiveReport+=String.format("	%d", flight + 1);
                comprehensiveReport+=String.format("		  %d", currentFlight.studentsInTrip.size());
                comprehensiveReport+=String.format("		     %s", Time.MinutesToTime(currentFlight.getTimeOfDeparture()));
                comprehensiveReport+=String.format("		%s", Time.MinutesToTime(currentFlight.getTimeOfArrival()));
                comprehensiveReport+=String.format("\t\t\t\s\s\s\s\s%d", currentFlight.getCatches());
                comprehensiveReport+=String.format("			%d", currentFlight.studentsInTrip.size() - currentFlight.getCatches());
                comprehensiveReport+=String.format("		  %s", currentFlight.getCatchesPer());
                comprehensiveReport+=String.format("	        %s", currentFlight.getTypeOfFilght());
                comprehensiveReport+=String.format("		%s", currentFlight.getBusUsed().getID());
                comprehensiveReport+=String.format("%n%s%n",minus(142));
//        		+++++++++++++++++++++++++++Flights Information+++++++++++++++++++++++++++++++++++++
                
//        		+++++++++++++++++++++++++++Student in Flight Information+++++++++++++++++++++++++++++++++++++
                
                comprehensiveReport+=String.format("   %s", "Student");
                comprehensiveReport+=String.format("         %s", "ID");
                comprehensiveReport+=String.format("	         %s", "ShowUp Time");
                comprehensiveReport+=String.format("	    %s", "Intended Arraival");
                comprehensiveReport+=String.format(" 	%s", "isCatch?");
                comprehensiveReport+=String.format("	   %s\n", "HasExam?");

                for(int S = 0; S < currentFlight.studentsInTrip.size(); S++) {
                	Student stu = currentFlight.studentsInTrip.get(S);
                    comprehensiveReport+=String.format("	%d", S + 1);
                    comprehensiveReport+=String.format("	    %d", stu.getID());
                    comprehensiveReport+=String.format("	     %s", Time.MinutesToTime(stu.getShowupTime()));
                    comprehensiveReport+=String.format("		%s", Time.MinutesToTime(stu.getIntendedArrivalTime()));
                    comprehensiveReport+=String.format("			  %s", stu.isCatch());
                    comprehensiveReport+=String.format("		 %s\n", stu.getHasExam());
                }
//        		+++++++++++++++++++++++++++Student in Flight Information+++++++++++++++++++++++++++++++++++++
        		
        	}
//        		+++++++++++++++++++++++++++Bus in Flight Information+++++++++++++++++++++++++++++++++++++
        		comprehensiveReport+=String.format("%n\t\t\t%s",minus(110));
        		comprehensiveReport+=String.format("%n\t\t\t 				     	   	            %s","Bus Information:");
            	comprehensiveReport+=String.format("%n\t\t\t%s%n",minus(110));
                comprehensiveReport+=String.format("\t\t\t    %s", "BusID");
                comprehensiveReport+=String.format("	 %s", "Moved-Distance KM");
                comprehensiveReport+=String.format("	 %s", "FuelConsumbtion L");
                comprehensiveReport+=String.format(" 	 %s", "Number Of Trips");
                comprehensiveReport+=String.format("	%s\n", "Students Delivered");

                for(int B = 0; B < BusReport.get(day).size(); B++) {
                	Bus Cbus = BusReport.get(day).get(B);
                    comprehensiveReport+=String.format("\t\t\t	%d", Cbus.getID());
                    comprehensiveReport+=String.format("	            %.2f", Cbus.getDistanceKm());
                    comprehensiveReport+=String.format("				   %.2f", Cbus.getFuelConsumption());
                    comprehensiveReport+=String.format("				  %d", Cbus.getNumberTrips());
                    comprehensiveReport+=String.format("			 	 %d\n", Cbus.getStudentsDelivered().size());
                }
//        		+++++++++++++++++++++++++++Bus in Flight Information+++++++++++++++++++++++++++++++++++++
                
                
        	
        	
        	
        }

        double catchPer = 100*((double)(summaryP1[0]) / (summaryP1[0]+summaryP1[1]));

        summaryReportP1+=String.format("%s		 ", "Total Catches");
        summaryReportP1+=String.format("%s		", "Total Misses");
        summaryReportP1+=String.format("%s	 	", "Total Catches%");
        summaryReportP1+=String.format("%s\n", "Total Number of Flgihts");
        summaryReportP1+=String.format("	%d", (int)summaryP1[0]);
        summaryReportP1+=String.format("				%d", (int)summaryP1[1]);
        summaryReportP1+=String.format("				%.2f%%", catchPer);
        summaryReportP1+=String.format("					%d\n\n", (int)summaryP1[2]);
        
        summaryReportP1+=String.format("%s	 	", "Total Students Delivered");
        summaryReportP1+=String.format("%s	 	", "Total Students not Delivered");
        summaryReportP1+=String.format("%s	 		    ", "Total Distance KM");
        summaryReportP1+=String.format("%s\n", "Total Fuel L");
        
        

        summaryReportP1+=String.format("		%d", (int)summaryP1[3]);
        summaryReportP1+=String.format("							%d", (int)summaryP1[4]);
        summaryReportP1+=String.format("					%.2f", summaryP1[5]);
        summaryReportP1+=String.format("					%.2f", summaryP1[6]);
 


        
        FXMLmainController.summaryP1 = comprehensiveReport;
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
	
    public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
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
//			if(!isNumeric(testDays) || Integer.parseInt(testDays) <= 0) {
//				
//			}
			testDays = KAUdays;
		}catch(InputMismatchException e){
			System.out.println("Not a valid input for the number of days. the default value is set {1 day}");
			testDays = 1;
		}catch(Exception e){
			System.out.println("Not a valid input for the number of days. the default value is set {1 day}");
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
					bus.sendBus(days, tempStudents, flightReport, studentsAll, flightsAll);
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
						bus.sendBus(days, tempStudents, flightReport, studentsAll, flightsAll);
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
						bus.sendBus(days, tempStudents, flightReport, studentsAll, flightsAll);
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
