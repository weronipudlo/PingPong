import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable{

    // wymiary

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT =(int)(GAME_WIDTH*(0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int BALL_DIAMETER = 25;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 120;

    Thread gameThread; // new Thread/ new game
    Image image;
    Graphics graphics;
    //Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;


    GamePanel(){
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT); //-> Score
        this.setFocusable(true);
        this.addKeyListener(new AL()); // AL - inner Class GamePanel (down)
        this.setPreferredSize(SCREEN_SIZE); //Dimension

        gameThread = new Thread(this);
        gameThread.start();

    }

    public void newBall(){
        //random = new Random();

        //ball - center
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),(GAME_HEIGHT/2)-(BALL_DIAMETER/2), BALL_DIAMETER, BALL_DIAMETER);

    }

    public void newPaddles(){
        paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH, PADDLE_HEIGHT,1);// na lewo, na srodku
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH, PADDLE_HEIGHT,2); // na prawo, na środku
    }

    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        //draw a ball
        ball.draw(g);
        //draw a score
        score.draw(g); 
    }

    public void move(){ //smooth move
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollision(){

        //odbicie piłki od górnej/dolnej krawędzi
        if(ball.y <= 0){
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >= GAME_HEIGHT-BALL_DIAMETER)
            ball.setYDirection(-ball.yVelocity);

        //odbicie piłki od Paddles
        if(ball.intersects(paddle1)){ // dla paddle1
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //po kazdym uderzeniu zwieksza sie prędkość pilki
            if(ball.yVelocity>0)
                ball.yVelocity++;//optional for more difficulty
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        if(ball.intersects(paddle2)){ // dla paddle2
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if(ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //zatrzymuje paddles na krawędzi okna
        if(paddle1.y<=0)
            paddle1.y=0;
        if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;

        if(paddle2.y<=0)
            paddle2.y=0;
        if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;

        //PLAYER 2 zdobył punkt + tworzona nowa piłka i paddles
        if(ball.x <= 0){ //zdobedzie punkt
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Player 2: "+score.player2);
        }

        //PLAYER 1 zdobył punkt + tworzona nowa piłka i paddles
        if(ball.x >= GAME_WIDTH-BALL_DIAMETER){ //zdobedzie punkt
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("Player 1: "+score.player1);
        }
    }

    public void run(){
        //game loop - pętla gry
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;

        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if (delta >= 1){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    public class AL extends KeyAdapter{  //AL - action listener

        public void keyPressed(KeyEvent e){ //przycisk  // -> Klasa Paddle
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e){ //bez przycisku  // -> Klasa Paddle
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
