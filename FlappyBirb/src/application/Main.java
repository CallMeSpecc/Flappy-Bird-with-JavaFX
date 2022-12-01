package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
			//Parent root = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
	    	//Parent root = FXMLLoader.load(getClass().getResource("HighScore.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			scene.getRoot().requestFocus();
			primaryStage.setScene(scene);
			primaryStage.setTitle("Flappy Bird!");
			primaryStage.setResizable(false);
	//Icon for the game
			primaryStage.getIcons().add(new Image("Assets/icon.png"));
			primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
}

