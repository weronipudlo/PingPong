import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle{

    Random random;
    int xVelocity; // prędkość na osi x
    int yVelocity; // prędkość na osi y
    int initialSpeed = 2; //prędkość początkowa

    Ball(int x, int y, int width, int height){

        super(x,y,width,height); //assign arguments
        random = new Random();

        int randomXDirection = random.nextInt(2); //0 - left, 1 - right
        if(randomXDirection==0)
            randomXDirection--;
        setXDirection(randomXDirection*initialSpeed);

        int randomYDirection = random.nextInt(2); //0 - down, 1 - up
        if(randomYDirection==0)
            randomYDirection--;
        setYDirection(randomYDirection*initialSpeed);
    }



    public void setXDirection(int randomXDirection){
        xVelocity = randomXDirection; //prędkość - Velocity
    }
    public void setYDirection(int randomYDirection){
        yVelocity = randomYDirection;
    }
    public void move(){
        x += xVelocity;
        y += yVelocity;
    }
    public void draw(Graphics g){ // -> to draw a ball // Klasa GamePanel
        g.setColor(Color.white);
        g.fillOval(x,y,height,width);
    }

}
