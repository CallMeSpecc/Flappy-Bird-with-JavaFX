package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Controller implements Initializable
{
	Random random = new Random();
	
	AnimationTimer gameloop;
	@FXML
	private AnchorPane plane;
	
	@FXML
	private ImageView me, base;
	@FXML
	private Text score;
	
	private int gameTime=0; 
	private int scoreCounter=0;
	
	int jumpheight=100;
	double speed=0.1, basespeed=1.5;
	double acc=0;
	
	private ObstaclesHandler obs;
	
	ArrayList<Rectangle> obstacles=new ArrayList<>();
	
	@Override
	public void initialize(URL url, ResourceBundle resourcebundle)
	{ 
		double planeHeight=500, planeWidth=339;
		obs=new ObstaclesHandler(plane, planeHeight, planeWidth);
		gameloop = new AnimationTimer()
		{
			@Override
			public void handle(long l)
				{
				update();
				}
		};
		
		load();
		
		gameloop.start();
	}
	
	@FXML
	void pressed(KeyEvent e)
	{
		if (e.getCode()==KeyCode.SPACE) {
			System.out.println("Hello");
			fly();
		}
	}
	
	public void fly()
	{
		if (me.getLayoutY()+me.getY()<=jumpheight)
			{
			moveBirdY(-(me.getLayoutY()+me.getY()));
			acc=0;
			return;
			}
		moveBirdY(-jumpheight);
		acc=0;
	}
//Update gets called every frame
	public void update()
	{
		gameTime++;
		acc++;
		moveBirdY(speed*acc);
		moveBaseX(basespeed);
		obs.moveObstacles(obstacles);
		
		pointCheck(obstacles);
		if(gameTime%100==0) {
			obstacles.addAll(obs.createObstacles());}
		
		if (baseend())
			resetbase();
		
		if (birbded(obstacles, plane))
			resetbirb();
	}
//Load only gets called once, in the beginning	
	private void load()
	{
		System.out.println("Let us begin Flapping...");
		obstacles.addAll(obs.createObstacles());
	}
//Bird movement	
	private void moveBirdY(double positionChange) 
	{
		// TODO Auto-generated method stub
		me.setY(me.getY()+positionChange);
	}
	
	private boolean birbded(ArrayList<Rectangle> obstacles, AnchorPane plane)
	{
		double birb=me.getLayoutY()+me.getY();
		
		if(collisionDetection(obstacles, me)){
            return  true;
        }
		return birb>=plane.getHeight();
	}
	private void resetbirb()
	{
		HighScoreController highScoreController = new HighScoreController();
		highScoreController.find_score(scoreCounter);
		highScoreController.highscore();
		System.out.println("Your Score is: " + scoreCounter);
		System.out.println("High Score is: " + highScoreController.hscore);
		me.setY(0);
		acc=0;
		plane.getChildren().removeAll(obstacles);
        obstacles.clear();
        gameTime = 0;
        scoreCounter = 0;
        //score.setText(String.valueOf(scoreCounter));
	}
//Base movement	
	private void moveBaseX(double positionChange) 
	{
		// TODO Auto-generated method stub
		base.setX(base.getX()-positionChange);
	}
	
	private boolean baseend()
	{
		double ground=base.getLayoutX()-base.getX();
		return ground>=30;
	}
	private void resetbase()
	{
		base.setX(0);
	}
	
	public boolean collisionDetection(ArrayList<Rectangle> obstacles, ImageView me2){
        for (Rectangle rectangle: obstacles) {
            if(rectangle.getBoundsInParent().intersects(me2.getBoundsInParent())){
                return true;
            }
        }
        return  false;
    }
	public void pointCheck(ArrayList<Rectangle> obstacles){
        for (Rectangle rectangle: obstacles)
            if(rectangle.getLayoutX()+rectangle.getX() == 141) 
            {
                scoreCounter+=1;
                System.out.println(scoreCounter);
            }
    }
/*//Pipe movement
	private void movePipeX(double positionChange) 
	{
		// TODO Auto-generated method stub
		pipeu1.setX(pipeu1.getX()-positionChange);
		pipeu2.setX(pipeu2.getX()-positionChange);
		pipeu3.setX(pipeu3.getX()-positionChange);
		pipeu4.setX(pipeu4.getX()-positionChange);
		piped1.setX(piped1.getX()-positionChange);
		piped2.setX(piped2.getX()-positionChange);
		piped3.setX(piped3.getX()-positionChange);
		piped4.setX(piped4.getX()-positionChange);
	}*/
	/*private void pipeout()
	{
		if (pipeu1.getX()<=-70)
		{
			pipeu1.setX(pipeu1.getLayoutX());piped1.setX(piped1.getLayoutX());
		}
		if (pipeu2.getX()<=-70)
		{
			pipeu2.setX(pipeu2.getLayoutX());piped2.setX(piped2.getLayoutX());
		}
		if (pipeu3.getX()<=-70)
		{
			pipeu3.setX(pipeu1.getLayoutX());piped3.setX(0);
		}
		if (pipeu4.getX()<=-70)
		{
			pipeu4.setX(0);piped4.setX(0);
		}
	}*/
	/*private void createPipes() throws FileNotFoundException
	{
		double xPos = plane.getWidth()-100, space=150;
		double yPos = random.nextInt((int)(400)+50);
		
		InputStream stream = new FileInputStream("C:\\Users\\gopal\\Desktop\\My Files\\College - UFV\\Flappy Bird\\pipe-green.png");
	    Image image = new Image(stream);
		ImageView pipeup = new ImageView();
		pipeup.setImage(image);
		pipeup.setY(-200);
		pipeup.setX(0);
		ImageView pipedo = new ImageView();
		pipedo.setImage(image);
		pipedo.setRotate(180);
		pipedo.setY(yPos+space);
		pipedo.setX(xPos);
		
		plane.getChildren().addAll(pipeup, pipedo);
	}*/
}
