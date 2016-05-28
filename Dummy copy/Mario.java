import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.*;
import java.awt.Color;
public class Mario extends Default
{
    private int velX = 0;
    private int velY = 0;
    private Image img;
    private ArrayList<Fireball> projectile;

    public Mario(int x, int y) 
    {
        super(x,y);
        projectile = new ArrayList<Fireball>();
        hitBox = new Rectangle(this.x, this.y, 28, 38);
        img = Toolkit.getDefaultToolkit().getImage("Images//Mario_STANDING.png");
    }
    
    public void updateMario() 
    {
        y += velY;
        x += velX;
        hitBox = new Rectangle(this.x, this.y, 28, 38);
        for(Fireball a: projectile)
             a.shoot(); 
        for(Fireball a: projectile)
             a.update();
    }
    
    public void die(Gumba enemy)
    {
        if(this.hitBox.intersects(enemy.gethitBox()))
        {
            velY = 0;
            velX = 0;
        }
        for(int i = projectile.size()-1; i >=0; i--)
        {
            if(projectile.get(i).gethitBox().intersects(enemy.gethitBox()))
            {
                projectile.remove(i);   
            }  
        }
    }
    
    public boolean boom(Gumba enemy)
    {
        if(this.hitBox.intersects(enemy.gethitBox()))
            return true;
        else
            return false;
    }
    
    public void draw(Graphics2D g2d) 
    {
        g2d.drawImage(img, this.x, this.y, null);
        g2d.draw(hitBox);
        for(Fireball a: projectile)
             a.draw(g2d);
    }

    public void keyPressed(KeyEvent e) 
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W)
        {
            img = Toolkit.getDefaultToolkit().getImage("Images//Mario_UP.png");
            velY = -5;
            direction = 'N';
            System.out.println("UP KEY PRESSED!");
        }
        if (key == KeyEvent.VK_A)
        {
            img = Toolkit.getDefaultToolkit().getImage("Images//Mario_LEFT.png");
            velX = -5;
            direction = 'W';
            System.out.println("LEFT KEY PRESSED!");
        }
        if (key == KeyEvent.VK_S)
        {
            img = Toolkit.getDefaultToolkit().getImage("Images//Mario_DOWN.png");
            velY = 5;
            direction = 'S';
            System.out.println("DOWN KEY PRESSED!");
        }
        if (key == KeyEvent.VK_D)
        {   
            img = Toolkit.getDefaultToolkit().getImage("Images//Mario_RIGHT.png");
            velX = 5;
            direction = 'E';
            System.out.println("RIGHT KEY PRESSED!");
        }
        if(key == KeyEvent.VK_SPACE)
        {
            projectile.add(new Fireball(this.x,this.y,this.getDirection()));
            System.out.println("SPACE KEY PRESSED!");
        }
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
    
    public Rectangle gethitBox()
    {
        return hitBox;
    }
    
    public char getDirection()
    {
        return direction;
    }

}
