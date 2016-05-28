
import java.awt.*;

public class Gumba extends Default
{
    private Image sprite;
    private int velX;
    private int velY;
    

    public Gumba(int x, int y)
    {
        super(x,y);
        hitBox = new Rectangle(this.x, this.y, 24, 34);
        sprite = Toolkit.getDefaultToolkit().getImage("Images//Gumba_STANDING.png");
    }
    
    public void updateGumba()
    {
        x+=velX;
        y+=velY;
        velY = 0;
        velX = 0;
        hitBox = new Rectangle(this.x, this.y, 24, 34);
    }
    
    public void move(Mario player)
    {
        if (x < player.x)
        {
            velX += 1;
            sprite = Toolkit.getDefaultToolkit().getImage("Images//Gumba_RIGHT.png");
        }
        else if (x > player.x)
        {
            velX -= 1;
            sprite = Toolkit.getDefaultToolkit().getImage("Images//Gumba_LEFT.png");
        }
        if (y < player.y)
        {
            velY += 1;
            sprite = Toolkit.getDefaultToolkit().getImage("Images//Gumba_DOWN.png");
        }
        else if (y > player.y)
        {
            velY -= 1;
            sprite = Toolkit.getDefaultToolkit().getImage("Images//Gumba_UP.png");
        }
    }
    
    public void draw(Graphics2D g2d)
    {
        g2d.drawImage(sprite,this.x, this.y, null);
        g2d.draw(hitBox);
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