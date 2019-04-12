package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
			
		Parent root  = FXMLLoader.load(getClass().getResource("MainGui.fxml"));
		Scene scene = new Scene(root,1490,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("TABULAR METHOD APP ^_^");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
