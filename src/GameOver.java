import javax.swing.*;
import java.awt.*;

public class GameOver extends JFrame {
    GameOver(){
        ImageIcon gameOverLogo = new ImageIcon("gameOverLogo.png");
        ImageIcon gameOverGif = new ImageIcon("gameOver.mp4");

        JLabel astronaut = new JLabel();
        astronaut.setSize(500, 300);
        astronaut.setIcon(gameOverGif);
        astronaut.setBounds(20, 300, 500,300);


        JPanel backGround = new JPanel();
        backGround.setSize(new Dimension(540,763));
        backGround.setBackground(Color.black);
        backGround.setVisible(true);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(540,763);
        frame.setBackground(Color.BLACK);
        frame.add(backGround);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
