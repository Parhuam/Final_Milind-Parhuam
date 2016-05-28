import java.awt.Graphics2D;
import java.awt.*;
public class Entity 
{
	public int x, y;
	public Rectangle rekt;
	public char direction;
	public Entity(int x, int y) 
	{
		this.x = x;
		this.y = y;
		direction = 'N';
		rekt = new Rectangle(this.x, this.y, 35, 35);
	}
	
	public void update() 
	{
	}
	
	public Rectangle getRekt()
    {
        return rekt;
    }
    
    public char getDirection()
    {
        return direction;
    }
    
	public void draw(Graphics2D g2d) 
	{
	}
}
