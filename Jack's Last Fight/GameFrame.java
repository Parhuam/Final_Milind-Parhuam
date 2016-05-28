import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import java.awt.Component;
public class GameFrame extends JPanel implements ActionListener 
{
    private Player player;
    private ArrayList<Enemy> enemies;
    //private ArrayList<Bullet> bullets;
    private Timer mainTimer; 
    private ImagePanel panel;
    private boolean spawnMore = false;
    static void main(String[] args) 
    {
        JFrame f = new JFrame("Survive!");
        
        f.add(new GameFrame());
        f.setSize(1600, 900);
        f.setResizable(false);
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        //new Sound();
    }
        
      

    public GameFrame() 
    {
        this.setFocusable(true);
        enemies = new ArrayList<Enemy>();
        //bullets = new ArrayList<Bullet>();
        player = new Player(800,450);
        makeEnemies(3);
        mainTimer = new Timer(8, this);
        mainTimer.start();
        addKeyListener(new Movement(getPlayer()));
    }
    
    public void makeEnemies(int amount)
    {
        for(int i = 0; i <= amount; i++)
        {
            int location = (int) (Math.random()*4+1);
            if(location == 1) //top left
            {
                int xRand = (int)(Math.random()*-200+1);
                int yRand = (int)(Math.random()*-200+1);
                Enemy enemy = new Enemy(xRand, yRand);
                enemies.add(enemy);
            }
              if(location == 2) //bottom left
            {
                int xRand = (int)(Math.random()*-200+1);
                int yRand = (int)(Math.random()*9000+800);
                Enemy enemy = new Enemy(xRand, yRand);
                enemies.add(enemy);
            }
              if(location == 3) //top right
            {
                int xRand = (int)(Math.random()*1400+1200);
                int yRand = (int)(Math.random()*-200+1);
                Enemy enemy = new Enemy(xRand, yRand);
                enemies.add(enemy);
            }
            if(location == 4) //bottom right
            {
                int xRand = (int)(Math.random()*1400+1200);
                int yRand = (int)(Math.random()*800+600);
                Enemy enemy = new Enemy(xRand, yRand);
                enemies.add(enemy);
            }
        }
    }
    
    public void spawn(boolean spawnMore)
    {
        if(spawnMore == true)
        {
            int location = (int) (Math.random()*4+1);
            if(location == 1) //top left
            {    
                for(int i = 0; i < 2; i++)
                {
                    int xRand = (int)(Math.random()*-200+1);
                    int yRand = (int)(Math.random()*-200+1);    
                    Enemy enemy = new Enemy(xRand, yRand);
                    enemies.add(enemy);
                    spawnMore = false;
                }
            }
            if(location == 2) //bottom left
            {
               for(int i = 0; i < 2; i++)
               {
                    int xRand = (int)(Math.random()*-200+1);
                    int yRand = (int)(Math.random()*9000+800);
                    Enemy enemy = new Enemy(xRand, yRand);               
                    enemies.add(enemy);
                    spawnMore = false;
               }
            }
            if(location == 3) //top right
            {
                 for(int i = 0; i < 2; i++)
                {
                    int xRand = (int)(Math.random()*1400+1200);
                    int yRand = (int)(Math.random()*-200+1);
                    Enemy enemy = new Enemy(xRand, yRand);
                    enemies.add(enemy);
                    spawnMore = false;
                }
            }
            if(location == 4) //bottom right
            {
               for(int i = 0; i < 2; i++)
                {
                    int xRand = (int)(Math.random()*1400+1200);
                    int yRand = (int)(Math.random()*800+600);
                    Enemy enemy = new Enemy(xRand, yRand);
                    enemies.add(enemy);
                    spawnMore = false;
               }
            }
        }
    }    
   
    public Player getPlayer() 
    {
        return this.player;
    }
    
    public void paint(Graphics g) 
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;        
        if (!player.isDead())
            player.draw(g2d);
        for(int i = 0; i < enemies.size()-1; i++)
        {
            enemies.get(i).draw(g2d);
        }

    }
    
    //@Override
    public void actionPerformed(ActionEvent e) 
    {
        player.update();
        for(int i = 0; i < enemies.size()-1; i++)
            enemies.get(i).move(player);
        for(int i = enemies.size()-1; i >= 0; i--)
        {
            Enemy enemy = enemies.get(i);
            enemy.update();
            if(enemy.getRekt().intersects(player.getRekt()))
            {
                player.setDead(true);
            }
            player.detectCollide(enemy); 
        }
        ArrayList<Bullet> bullets = player.getBullets();
        for (int i = bullets.size() - 1; i >= 0; i --)
        {
            Bullet bullet = bullets.get(i);
            bullets.get(i).update();
            
            // Checks for intersection with any Enemy
            for (int j = enemies.size() - 1; j >= 0; j--)
            {
                Enemy enemy = enemies.get(j);
                if (bullet.getRekt().intersects(enemy.getRekt()))
                {
                    enemies.remove(j); 
                    spawnMore = true;
                    if(spawnMore)
                        spawn(spawnMore);
                    bullets.remove(i);
                    break;
                }
            }
        }
        repaint();
    }   
}
    