import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {

    int id;  //id 1 - player 1, id 2 - player 2
    int yVelocity; //paddle up and down when we press the button
    int speed = 10;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){ // -> GamePanel newPaddle
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT); // wyznacza miejsce
        this.id = id;

    }

    public void keyPressed(KeyEvent e){  // <- klasa GamePanel
        switch(id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){ // w na klawiaturze
                    setYDirection(-speed);//up
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S){ // s na klawiaturze
                    setYDirection(speed);//down
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){ // strzałka up na klawiaturze
                    setYDirection(-speed);//up
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){ // strzałka down na klawiaturze
                    setYDirection(speed);//down
                    move();
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e){
        switch(id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){ // w na klawiaturze
                    setYDirection(0);//up
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S){ // s na klawiaturze
                    setYDirection(0);//down
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){ // gora na klawiaturze
                    setYDirection(0);//up
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){ // dol na klawiaturze
                    setYDirection(0);//down
                    move();
                }
                break;
        }
    }
//ruch tylko góra i dół
    public void setYDirection(int yDirection){
        yVelocity = yDirection;
    }

    public void move(){
        y= y + yVelocity;
    }

    public void draw(Graphics g){ // -> GamePanel draw
        if(id==1)
            g.setColor(Color.CYAN);
        else
            g.setColor(Color.BLUE);
        g.fillRect(x,y,width,height); //wypełnij paddle
    }
}
