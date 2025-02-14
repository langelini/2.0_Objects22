import java.awt.*;

/**
 * Created by chales on 11/6/2017.
 */
public class soccer {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;//the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;         //a boolean to denote if the hero is alive or dead.
    public Rectangle rec;
    public boolean crash;

    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public soccer(int pXpos, int pYpos, int pheight, int pwidth) { // how i set size and start position and default speed
        xpos = pXpos;
        ypos = pYpos;
        dx = 4;
        dy = 4;
        width = pwidth;
        height = pheight;
        isAlive = true;
        rec = new Rectangle(xpos,ypos, width, height);

    } // constructor

    public void bounce(){ // what allows the ball and cleats to move around and bounce of walls
        if(xpos>1000){
            dx=-dx;
        }
        if(xpos<0){
            dx=-dx;
        }
        if(ypos<0){
            dy=-dy;

        }
        if(ypos>700){
            dy=-dy;
        }
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos,ypos, width, height);
        // System.out.println(dx);
    }
    public void wrap(){
        if(xpos>1000){
            xpos = 0;
        }
        if(xpos<0){
            xpos = 1000;
        }
        if(ypos<0){
            ypos = 700;

        }
        if(ypos>700){
            ypos = 0;
        }
        xpos=xpos+dx;
        ypos=ypos+dy;
        rec = new Rectangle(xpos,ypos, width, height);
    }
}