import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GamePanelTest {
    
    GamePanel gamePanel = new GamePanel();

    @Test
    void newBall() {
        
        //when
        gamePanel.newBall();
        //then
        Assertions.assertNotNull(gamePanel.ball);
    }
}