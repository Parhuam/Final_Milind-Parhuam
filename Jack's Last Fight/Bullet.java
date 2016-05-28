
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.*;
public class Bullet extends Entity
{
    private int velX = 0;
    private int velY = 0;
    private Image image;
    public Bullet(int x, int y, char direction)
    {
        super(x,y);
        this.direction = direction;
        rekt = new Rectangle(this.x, this.y, 16, 16);
        image = Toolkit.getDefaultToolkit().getImage("Images//Bullet.png");
    }
    
    public void update()
    {
        x+=velX;
        y+=velY;
        rekt = new Rectangle(this.x , this.y, 16, 16);
        move();
        
        // check collision with enemy
        
    }
    
    public void draw(Graphics2D g2d) 
    {
        g2d.drawImage(image, this.x, this.y, null);
        g2d.draw(rekt);
    }
    
    public void move()
    {
        if(direction == 'N')
        {
            velY = -25;
            velX = 0;
            image = Toolkit.getDefaultToolkit().getImage("Images//Bullet.png");
        }
        if(direction == 'W')
        {
            velY = 0;
            velX = -25;
            image = Toolkit.getDefaultToolkit().getImage("Images//Bullet.png");
        }
        if(direction == 'E')
        {
            velY = 0;
            velX = 25;
            image = Toolkit.getDefaultToolkit().getImage("Images//Bullet.png");
        }
        if(direction == 'S')
        {
            velY = 25;
            velX = 0;
            image = Toolkit.getDefaultToolkit().getImage("Images//Bullet.png");
        }
    }
}