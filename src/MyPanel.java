import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.List;

public class MyPanel extends JPanel implements ActionListener
{
    final int PANEL_WIDTH = 540;
    final int PANEL_HEIGHT = 763;
    Image alienImage;
    Image backgroundImage;
    Image rocket;
    Image missileImage;

    Timer timer, timer1;
    int rocketX = 50;
    int rocketY = 700;
    int missileX = rocketX + 16;
    int missileY = rocketY - 32;

    boolean fireMissile = false;
    int score = 0;

    int xVelocity = 1;
    int yVelocity = 1;
    char direction = 'W';
    int x = 40;
    int y = 40;
    int xALien = 0;
    int yAlien = 0;
    Random random;
    File fontFile;
    Font customFont;

    private final ArrayList<Missile> missiles = new ArrayList<>();
    private final ArrayList<Alien> aliens = new ArrayList<>();

    MyPanel()
    {
        try
        {
            fontFile = new File("BabyBoo-Regular.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            customFont = customFont.deriveFont(Font.BOLD,45);
        }
        catch (FontFormatException | IOException e)
        {
            e.printStackTrace();
        }

        random = new Random();
        alienImage = new ImageIcon("alien.png").getImage();
        backgroundImage = new ImageIcon("stars.gif").getImage();
        rocket = new ImageIcon("rocket.png").getImage();
        missileImage = new ImageIcon("missile.png").getImage();

        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame()
    {
        timer = new Timer(10,this);
        timer.start();
    }

    public void paint(Graphics g)
    {
        super.paint(g); // paint background

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImage, 0,0,null);        //adding out background GIF of space: stars.gif
        g2d.drawImage(alienImage,x,y,null);                  //drawing our alien
        g2d.drawImage(rocket,rocketX,rocketY,null);         //drawing our rocket

        Iterator<Alien> alienIterator = aliens.iterator();
        while (alienIterator.hasNext()){
            Alien alien = alienIterator.next();
            xALien = alien.getX();
            yAlien = alien.getY();
            g2d.drawImage(alienImage,alien.getX(),alien.getY(),null);  //drawing our alien but now Object ALien
        }


        if(fireMissile)     //if fireMissile == true, then we want to launch or missile, and starting drawing it
        {
            missileImage = new ImageIcon("missile.png").getImage();
            Iterator<Missile> iterator = missiles.iterator();
            while(iterator.hasNext()){
                Missile missile = iterator.next();
                g2d.drawImage(missileImage,missile.getX(),missile.getY(),null);
                checkMissile(iterator, missile, alienIterator, xALien, yAlien);
            }
            //------------Before using Iterator------------

            /*for(Missile missile : missiles){
                g2d.drawImage(missileImage,missile.getX(),missile.getY(),null);
                checkMissile();
            }*/

        }


        g2d.setColor(new Color(30,140,255));
        g2d.setFont(customFont);
        FontMetrics metrics = getFontMetrics(g.getFont());
        g2d.drawString("SCORE: " + score, (PANEL_WIDTH  - metrics.stringWidth("SCORE: " + score))/2,g2d.getFont().getSize());


    }

    public void missileMove()
    {
        for(Missile missile : missiles){
            missile.move();
        }
        repaint();

        /*timer1 = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                missileY =  missileY - 3;
            }
        });
        timer1.start();*/
    }
    public void checkMissile(Iterator<Missile> iterator, Missile missile, Iterator<Alien> alienIterator, int x, int y)
    {
        if(missile.getY() < 0){
            fireMissile = false;
            missile.timer.stop();
            iterator.remove();
        }
        else if( (this.y <= missile.getY() && missile.getY() <= this.y + alienImage.getHeight(null)) &&
                (this.x <= missile.getX() && missile.getX() <= this.x + alienImage.getWidth(null) )) {
            iterator.remove();
            score++;
            newAlien();
        }
        else if( (y <= missile.getY() && missile.getY() <= y + alienImage.getHeight(null)) &&
            (x <= missile.getX() && missile.getX() <= x + alienImage.getWidth(null) )) {
        iterator.remove();
        alienIterator.remove();
        score++;
        newAlien();
        }

    }
    public void newAlien()
    {
        aliens.add(new Alien(random.nextInt((int)(PANEL_WIDTH/alienImage.getWidth(null))*alienImage.getWidth(null)),
                random.nextInt((int)(PANEL_HEIGHT/alienImage.getHeight(null))*alienImage.getHeight(null) / 2),
                PANEL_WIDTH, PANEL_HEIGHT));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(x >= PANEL_WIDTH - alienImage.getWidth(null) || x < 0)
        {
            xVelocity = xVelocity * (-1);
        }
        x = x + xVelocity;
        repaint();
    }




    public class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyChar()) {
                case 'W':
                    rocketY = rocketY - 10;
                    break;
                case 'S':
                    rocketY = rocketY + 10;
                    break;
                case 'D':
                    rocketX = rocketX + 10;
                    break;
                case 'A':
                    rocketX = rocketX - 10;
                    break;
                case  ' ':
                    fireMissile = true;
                    missileX = rocketX + 16;
                    missileY = rocketY - 32;
                    missiles.add(new Missile(missileX,missileY));
                    missileMove();
                    break;
            }
        }
    }

}
