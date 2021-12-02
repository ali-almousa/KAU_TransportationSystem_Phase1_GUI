module KAU_TransportationSystem_Phase1_GUI {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
}
