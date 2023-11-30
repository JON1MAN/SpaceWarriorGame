import javax.swing.*;
import java.awt.*;
import java.util.jar.JarFile;

public class MyFrame extends JFrame
{
    MyPanel panel;
    MyFrame()
    {
        panel = new MyPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}
