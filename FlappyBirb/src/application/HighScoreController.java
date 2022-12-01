package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class HighScoreController {

	@FXML
	private Label bestScore;
	@FXML
	private Label currentScore;

	
	int score=0, hscore=0;
	
	void find_score(int s) {
		score = s;
		try {
			FileWriter fw = new FileWriter("scores.txt", true);
			fw.write( score + "\n");
			fw.close();
		}
		catch(IOException e) {
			System.out.print(e.getMessage());
		}
		//currentScore.setText("" + score);
	}
	
	void highscore() {
		ArrayList<Integer> all_scores = new ArrayList<Integer>();
		try {
			Scanner sc = new Scanner(new FileReader("scores.txt")).useDelimiter("\n");
			int s;
			while(sc.hasNext()) {
				s = Integer.parseInt(sc.next());
				all_scores.add(s);
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		hscore = Collections.max(all_scores);
		//bestScore.setText("" + hscore);
	}
	
}
