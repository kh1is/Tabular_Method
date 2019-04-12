package application;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.sun.glass.ui.Window;

import Stage1.Answer;
import Stage1.ReadFile;
import Stage2.groupingModule;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MyController {
	
	public Answer a ;
	public ReadFile r;
	
	
	public String minterms;
	public String cares;
	public StringBuilder steps = new StringBuilder();

	public void startButton(ActionEvent e) throws IOException{
		Stage primaryStage = new Stage(); 
		Parent root  = FXMLLoader.load(getClass().getResource("startway.fxml"));
		Scene scene = new Scene(root,1200,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("TABULAR METHOD APP ^_^");
		primaryStage.show();
	}
	
	@FXML
	Button close ;
	public void endButton(ActionEvent e) throws IOException{
		Stage primaryStage = new Stage(); 
		Parent root  = FXMLLoader.load(getClass().getResource("Close.fxml"));
		Scene scene = new Scene(root,400,200);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("TABULAR METHOD APP ^_^");
		primaryStage.show();
	}
	
	public void close(ActionEvent e){
		System.exit(0);
	}
	
	public void restart(ActionEvent e) throws IOException{
		Stage primaryStage = new Stage(); 
		Parent root  = FXMLLoader.load(getClass().getResource("MainGui.fxml"));
		Scene scene = new Scene(root,1490,700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("TABULAR METHOD APP ^_^");
		primaryStage.show();
	}
	
	public void programme(ActionEvent e) throws IOException{
		Stage primaryStage = new Stage(); 
		Parent root  = FXMLLoader.load(getClass().getResource("aboutProgramme.fxml"));
		Scene scene = new Scene(root,600,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("TABULAR METHOD APP ^_^");
		primaryStage.show();
	}
	
	public void us(ActionEvent e) throws IOException{
		Stage primaryStage = new Stage(); 
		Parent root  = FXMLLoader.load(getClass().getResource("aboutUS.fxml"));
		Scene scene = new Scene(root,600,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("TABULAR METHOD APP ^_^");
		primaryStage.show();
	}
	
	
	public void manulal(ActionEvent e) throws IOException{
		Stage primaryStage = new Stage(); 
		Parent root  = FXMLLoader.load(getClass().getResource("manual.fxml"));
		Scene scene = new Scene(root,1200,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("TABULAR METHOD APP ^_^");
		primaryStage.show();
	}
	
	
	@FXML
	public TextArea tf2;
	@FXML
	public TextArea tf4;
	@FXML
	public Label lb2;
	@FXML
	private TextField tf5;
	@FXML
	private TextArea step1;
	public void answer(ActionEvent e) throws IOException{
		lb2.setText(" ");
	    minterms = tf2.getText();
		cares = tf4.getText();
	    if(tf2.getText().isEmpty()){
			lb2.setText("YOU HAVE TO ENTER  MINTERMS");
		}else{
			if(tf4.getText().isEmpty()){
				cares = null;
			}
			a = new Answer(this.minterms,this.cares);
			steps.append(a.Step1());
			steps.append("\n\n");
			steps.append(a.Step2());
			steps.append("\n\n");
			steps.append(a.Step3());
			steps.append("\n\n");
			steps.append(a.Step4());
			steps.append("\n\n");
			steps.append(a.Step5());
			steps.append("\n\n");
			steps.append(a.Step6());
			steps.append("\n\n");
			steps.append(a.Step7());
			tf5.setText(a.Solution());
			step1.setText(String.valueOf(steps));
		}
	}
	
	
	public void choose(ActionEvent e) throws IOException{
		Stage primaryStage = new Stage(); 
		Parent root  = FXMLLoader.load(getClass().getResource("choose.fxml"));
		Scene scene = new Scene(root,1200,600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("TABULAR METHOD APP ^_^");
		primaryStage.show();
	}
	
	@FXML
	private ListView lv1;
	public void fileChoose(ActionEvent e){
		FileChooser f1 = new FileChooser();
		File f2 = f1.showOpenDialog(null);
		if(f2!=null){
			lv1.getItems().add(f2.getAbsolutePath());
		}
	}
	
	
	
	@FXML
	private TextField answer12;
	@FXML
	private TextArea step12;
	@FXML
	private Label lbpath;
	public void passpath(ActionEvent e) throws IOException{
		lbpath.setText(" ");
		if((boolean)lv1.getItems().isEmpty()){
			lbpath.setText("YOU SHOULD ENTER A PATH");
		}else{
			String path = (String) lv1.getItems().get(0);
			r = new ReadFile(path);
			answer12.setText(r.Solution());
			steps.append(r.Step1());
			steps.append("\n\n");
			steps.append(r.Step2());
			steps.append("\n\n");
			steps.append(r.Step3());
			steps.append("\n\n");
			steps.append(r.Step4());
			steps.append("\n\n");
			steps.append(r.Step5());
			steps.append("\n\n");
			steps.append(r.Step6());
			steps.append("\n\n");
			steps.append(r.Step7());
			step12.setText(String.valueOf(steps));	
		}
	}
}
