import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    GamePanel panel = new GamePanel(); // the painting inside

    //Frame around the painting
    GameFrame(){

        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Ping Pong");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);//the middle of the screen
    }
}
