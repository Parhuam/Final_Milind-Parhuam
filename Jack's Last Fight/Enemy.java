
import java.awt.*;

public class Enemy extends Entity
{
    private Image sprite;
    private Bullet bullet;
    private int velX;
    private int velY;
    private boolean dead;
    public Enemy(int x, int y)
    {
        super(x,y);
        rekt = new Rectangle(this.x, this.y, 35, 35);

    }
    
    public void update()
    {
        x+=velX;
        y+=velY;
        velY = 0;
        velX = 0;
        rekt = new Rectangle(this.x, this.y, 31, 31);
    }
    /*       
    public boolean isDead()
    {
        if(this.rekt.intersects(bullet.getRekt()))
        {
            dead = true;
        }

        return dead;
    }

    public void collide(Entity enemy)
    {

        if(enemy instanceof Enemy)
        {
            if(this.rekt.intersects(enemy.getRekt()))
                velX-=5;
        }
        if(enemy instanceof Bullet)
        {
            if(enemy.getDirection() == 'N')
                velY += 4;
            if(enemy.getDirection() == 'S')
                velY += -4;
            if(enemy.getDirection() == 'E')
                velX += -4;
            if(enemy.getDirection() == 'W')
                velX += 4; 
        }
    }
    */
    
    public void move(Player player)
    {
        int xRandOffset = player.x + (int)(Math.random() * 35);
        int yRandOffset = player.y + (int)(Math.random() * 35); 
        if (x < xRandOffset)
        {
            velX += 1;
            sprite = Toolkit.getDefaultToolkit().getImage("Images//H.png");
        }
        else if (x > xRandOffset)
        {
            velX -= 1;
            sprite = Toolkit.getDefaultToolkit().getImage("Images//H.png");
        }
        if (y < yRandOffset)
        {
            velY += 1;
            sprite = Toolkit.getDefaultToolkit().getImage("Images//H.png");
        }
        else if (y > yRandOffset)
        {
            velY -= 1;
            sprite = Toolkit.getDefaultToolkit().getImage("Images//H.png");
        }
    }
    
    public void draw(Graphics2D g2d)
    {
        g2d.drawImage(sprite,this.x, this.y, null);
        g2d.draw(rekt);
    }
}