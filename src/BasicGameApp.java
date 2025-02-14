//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

	//Variable Definition Section
	//Declare the variables used in the program
	//You can set their initial values too

	//Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

	//Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
	public JPanel panel;

	public BufferStrategy bufferStrategy;
	public Image soccerballpic;
	public Image backgroundpic;
	public Image cleatpic;
	public Image cleatpic2;
	public Image goalpic2;
	public Image goalpic;
	public int x;
	public int y;
	public int r;
	public int t;

	//Declare the objects used in the program
	//These are things that are made up of more than one variable type
	private soccer ball;
	private soccer cleat;
	private soccer cleat2;
	private soccer goal;
	private soccer goal2;


	// Main method definition
	// This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
	}


	// Constructor Method
	// This has the same name as the class
	// This section is the setup portion of the program
	// Initialize your variables and construct your program objects here.
	public BasicGameApp() {

		setUpGraphics();

		//variable and objects
		//create (construct) the objects needed for the game and load up
		soccerballpic = Toolkit.getDefaultToolkit().getImage("download.jpeg");
		goalpic = Toolkit.getDefaultToolkit().getImage("goal.png");
		backgroundpic = Toolkit.getDefaultToolkit().getImage("download (2).png");
		cleatpic = Toolkit.getDefaultToolkit().getImage("cleat.png"); // putting images in actual code
		cleatpic2 = Toolkit.getDefaultToolkit().getImage("cleat.png");
		goalpic = Toolkit.getDefaultToolkit().getImage("goal.png");
		goalpic2 = Toolkit.getDefaultToolkit().getImage("goal.png");
		ball = new soccer(500, 350, 40, 40);
		cleat = new soccer(250, 350,60,60);
		cleat2 = new soccer(750,350,60,60);
		goal2 = new soccer(20,250,200,100); // sets size and starting for all of the objects
		goal = new soccer(880, 250,200,100);
		goal.dx = 0;
		goal.dy = 0; // makes the goals stay still
		goal2.dx = 0;
		goal2.dy = 0;
		cleat2.dy = -5;// to make cleat2 different from cleat not move
	}
	// BasicGameApp()


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

	// main thread
	// this is the code that plays the game after you set things up
	public void run() {

		//for the moment we will loop things forever.
		while (true) {

			moveThings();  //move all the game objects
			render();  // paint the graphics
			pause(20); // sleep for 10 ms
		}
	}


	public void moveThings()
	{
		//calls the move( ) code in the objects
		collision();
		ball.bounce();
		cleat.bounce();
		cleat2.wrap(); // sets movement one player moves with bounces and the other with wrap to add variability
		goal.bounce();
		goal2.bounce();

	}

	public void collision(){
		if(ball.rec.intersects(cleat.rec) && ball.crash == false){ //collision for ball and cleat
			ball.dx=(int)(Math.random()*11);//sets ball to be kicked by cleat at random speed
			ball.dy = (int)(Math.random()*11);
			ball.crash = true;
		}
		if(!ball.rec.intersects(cleat.rec)){
			ball.crash = false;
		}
		if(ball.rec.intersects(cleat2.rec) && ball.crash == false){ // collision for ball and cleat2
			ball.dx=(int)(Math.random()*11);
			ball.dy = (int)(Math.random()*11); //sets ball to be kicked by cleat2 at a random speed
			cleat2.dx=cleat2.dx;
			cleat2.dy = cleat2.dy;
			ball.crash = true;
		}
		if(!ball.rec.intersects(cleat2.rec)){
			ball.crash = false;
		}
		if(ball.rec.intersects(goal.rec) && ball.crash == false){ // collision for a goal scored with a ball
			ball.dx=(int)(Math.random()*11);
			ball.dy = (int)(Math.random()*11);
			cleat.dy = (int)(Math.random()*11);//sets move speed to be random after a goal and makes ball go towards other teams goal
			cleat.dx = (int)(Math.random()*11);
			cleat2.dx=(int)(Math.random()*11);
			cleat2.dy = (int)(Math.random()*11);
			ball.crash = true;
			ball.xpos = 500;
			ball.ypos = 350; // puts ball at center and cleats at starting positions
			cleat.xpos = 250;
			cleat.ypos = 350;
			cleat2.xpos =750;
			cleat2.ypos = 350;
			x=x+1; // changes score

			System.out.println("score: " + x + " | " + y);// sets updated score
		}
		if(!ball.rec.intersects(goal.rec)){
			ball.crash = false;
		}
		if(ball.rec.intersects(goal2.rec) && ball.crash == false){ // collision for left goal with a ball
			ball.dx=-(int)(Math.random()*11);
			ball.dy = -(int)(Math.random()*11);
			cleat.dy = (int)(Math.random()*11);//sets move speed to be random after a goal and makes ball go towards other teams goal
			cleat.dx = (int)(Math.random()*11);
			cleat2.dx=(int)(Math.random()*11);
			cleat2.dy = (int)(Math.random()*11);
			ball.crash = true;
			ball.xpos = 500;
			ball.ypos = 350; // puts ball at center and cleats at starting positions
			cleat.xpos = 250;
			cleat.ypos = 350;
			cleat2.xpos =750;
			cleat2.ypos = 350;

			y=y+1; // changes score

			System.out.println("score: " + x + " | " + y);// sets new score
		}
		if(!ball.rec.intersects(goal.rec)){
			ball.crash = false;
		}
		if(cleat.rec.intersects(cleat2.rec) && cleat.crash == false){ // collision for ball and cleat2
			cleat.dx=-(int)(Math.random()*11);
			cleat.dy = (int)(Math.random()*11);
			cleat2.dx=(int)(Math.random()*11);
			cleat2.dy = (int)(Math.random()*11);//collisions change speed to random interval
			cleat.crash = true;
		}
		if(!cleat.rec.intersects(cleat2.rec)){
			cleat.crash = false;
		}
		if(y >5 && r<2){
			System.out.println("right wins!");
			r=r+2;
			cleat.height = 0;
			cleat2.dx = 0;
			ball.height= 0;
			goal.height = 0;
			cleat2.dy = 0;
			cleat2.xpos = 470;
			cleat2.ypos = 350;
			cleat.width = 0;
			ball.width = 0;
			goal.width = 0;
			goal2.width = 0;
			goal2.height = 0;
		}
		if(x >5 && t<2){
			System.out.println("left wins!");
			t=t+2;
			cleat.height = 0;
			cleat2.dx = 0;
			ball.height= 0;
			goal.height = 0;
			cleat2.dy = 0;
			cleat2.xpos = 470;
			cleat2.ypos = 350;
			cleat.width = 0;
			ball.width = 0;
			goal.width = 0;
			goal2.width = 0;
			goal2.height = 0;
		}
	}


	//Pauses or sleeps the computer for the amount specified in milliseconds
	public void pause(int time ){
		//sleep
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
	}

	//Graphics setup method
	private void setUpGraphics() {
		frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

		panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
		panel.setLayout(null);   //set the layout

		// creates a canvas which is a blank rectangular area of the screen onto which the application can draw
		// and trap input events (Mouse and Keyboard events)
		canvas = new Canvas();
		canvas.setBounds(0, 0, WIDTH, HEIGHT);
		canvas.setIgnoreRepaint(true);

		panel.add(canvas);  // adds the canvas to the panel.

		// frame operations
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
		frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
		frame.setResizable(false);   //makes it so the frame cannot be resized
		frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

		// sets up things so the screen displays images nicely.
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		canvas.requestFocus();
		System.out.println("DONE graphic setup");

	}


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(backgroundpic,0,0, WIDTH, HEIGHT, null);
		g.drawImage(soccerballpic, ball.xpos, ball.ypos, ball.width, ball.height, null);
		g.drawImage(cleatpic, cleat.xpos, cleat.ypos, cleat.width, cleat.height, null);
		g.drawImage(cleatpic2, cleat2.xpos, cleat2.ypos, cleat2.width, cleat2.height, null);
		g.drawImage(goalpic2, goal.xpos, goal.ypos, goal.width, goal.height, null);
		g.drawImage(goalpic, goal2.xpos, goal2.ypos, goal2.width, goal2.height, null);// creates the images
		g.setColor(Color.blue);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 24)); // draws scoreboard
		g.drawString("score: "+x + " | " + y, 455, 20);
		g.dispose();
		bufferStrategy.show();
	}
}