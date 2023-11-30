import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Alien extends MyPanel implements ActionListener {
    int x;
    int y;
    int xVelocity = 1;
    final int PANEL_WIDTH;
    final int PANEL_HEIGHT;

    Timer timer = new Timer(10, this);
    Alien(int x, int y, int PANEL_WIDTH, int PANEL_HEIGHT){
        this.x = x;
        this.y = y;
        this.PANEL_WIDTH = PANEL_WIDTH;
        this.PANEL_HEIGHT = PANEL_HEIGHT;
    }
    public int getX(){
        return this.x;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getY(){
        return this.y;
    }
    public void setY(int y){
        this.y = y;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(x >= PANEL_WIDTH - 64 || x < 0){
            xVelocity *= -1;
        }
        x += xVelocity;
        super.repaint();
    }
    public boolean alienKilled(int x, int y){
        if((this.y <= y && y <= this.y + alienImage.getHeight(null)) &&
             (this.x <= x && x <= this.x + alienImage.getWidth(null)))
        {
            return true;
        }
        return false;
    }
    class aliensMissile{
        int xMissile;
        int yMissile;
        aliensMissile(){
            this.xMissile = x;
            this.yMissile = y;
        }
    }
}
