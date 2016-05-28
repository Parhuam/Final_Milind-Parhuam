import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.*;

public class Player extends Entity
{
    private int velX = 0;
    private int velY = 0;
    private Image sprite;
    private ArrayList<KeyEvent> pressed;
    private ArrayList<Bullet> bulletz;
    private ArrayList<Enemy> enemies;
    private boolean dead;
    public Player(int x, int y) 
    {
        super(x,y);
        direction = 'N';
        bulletz = new ArrayList<Bullet>();
        rekt = new Rectangle(this.x, this.y, 33, 43);
        sprite = Toolkit.getDefaultToolkit().getImage("Images//MM.UP.png");
    }
    
    public void update() 
    {
        y += velY;
        x += velX;
        
        rekt = new Rectangle(this.x, this.y, 33, 43);
        //for(Bullet a: bulletz)   // if commented out, makes mines
        //     a.update();


    }
    
    public ArrayList<Bullet> getBullets()
    {
        return bulletz;
    }
       
    public void setDead(boolean dead)
    {
       this.dead = dead;
    }
    
    public boolean isDead()
    {
        return dead;
    }
    
    public void detectCollide(Enemy enemy)
    {
        if(this.rekt.intersects(enemy.getRekt()))
        {
            if(direction == 'N')
                sprite = Toolkit.getDefaultToolkit().getImage("Images//MM.UP.png");
            if(direction == 'S')
                sprite = Toolkit.getDefaultToolkit().getImage("Images//MM.DOWN.png");
            if(direction == 'E')
                sprite = Toolkit.getDefaultToolkit().getImage("Images//MM.LEFT.png");
            if(direction == 'W')
                sprite = Toolkit.getDefaultToolkit().getImage("Images//MM.RIGHT.png");
            velY = 0;
            velX = 0;
        }
    }
    
    public void draw(Graphics2D g2d) 
    {
        g2d.drawImage(sprite, this.x, this.y, null);
        g2d.draw(rekt);
        for(Bullet a: bulletz)
             a.draw(g2d);
    }

    public void keyPressed(KeyEvent e) 
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W)
        {
            sprite = Toolkit.getDefaultToolkit().getImage("Images//MM.UP.png");
            velY = -5;
            direction = 'N';
        }
        if (key == KeyEvent.VK_A)
        {
            sprite = Toolkit.getDefaultToolkit().getImage("Images//MM.LEFT.png");
            velX = -5;
            direction = 'W';
        }
        if (key == KeyEvent.VK_S)
        {
            sprite = Toolkit.getDefaultToolkit().getImage("Images//MM.DOWN.png");
            velY = 5;
            direction = 'S';
        }
        if (key == KeyEvent.VK_D)
        {   
            sprite = Toolkit.getDefaultToolkit().getImage("Images//MM.RIGHT.png");
            velX = 5;
            direction = 'E';
        }
        if(key == KeyEvent.VK_SPACE)
            bulletz.add(new Bullet(this.x,this.y,this.getDirection()));
    }
    
    public void keyReleased(KeyEvent e) 
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W)
            velY = 0;
        else if (key == KeyEvent.VK_A)
            velX = 0;
        else if (key == KeyEvent.VK_S)
            velY = 0;
        else if (key == KeyEvent.VK_D)
            velX = 0;
    }
}
