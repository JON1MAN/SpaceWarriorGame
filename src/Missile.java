import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class Missile implements ActionListener{
    private int x;
    private int y;
    Timer timer;
    Missile(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void  move(){
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                y =  y - 3;
            }
        });
        timer.start();
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public Timer getTimer(){
        return this.timer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        y -=3;
    }
}
