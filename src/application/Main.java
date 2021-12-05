package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("FXMLmain.fxml"));
			Scene scene = new Scene(root,1450,960);
//			Scene scene = new Scene(root,1920,960);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			primaryStage.setMinWidth(1490);
			primaryStage.setMinHeight(960);
			primaryStage.setMaxWidth(1490);
			primaryStage.setMaxHeight(910);
			
			primaryStage.setTitle("KAU Transportation System");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
