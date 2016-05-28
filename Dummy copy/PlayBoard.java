import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JLabel;
public class PlayBoard extends JPanel implements ActionListener 
{
    private Mario player;
    private ArrayList<Gumba> enemies;
    private Timer mainTimer; 
    private Image img;
    static void main(String[] args) 
    { 
        JFrame f = new JFrame("Survive!");
        f.add(new PlayBoard());
        f.setSize(1600, 900);
        f.setVisible(true);
    }
    
    public PlayBoard() 
    {
        this.setFocusable(true);
        enemies = new ArrayList<Gumba>();
        player = new Mario(800, 450);
        int amount = 3;

        setBackground(Color.GREEN);
        for(int i = 0; i < amount; i++)
        {
            int xRand = (int)Math.random()*799+1;
            int yRand = (int)Math.random()*449+1;
            Gumba enemy = new Gumba(xRand, yRand);
            enemies.add(enemy);
        }
        mainTimer = new Timer(8, this);
        mainTimer.start();
        addKeyListener(new Default(this.player));
    }
    
    public void paint(Graphics g) 
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        player.draw(g2d);
        for(int i = 0; i < enemies.size()-1; i++)
        {
            enemies.get(i).draw(g2d); 
        }
        //g.drawImage(img, 0, 0, null);
    }
    
    //@Override
    public void actionPerformed(ActionEvent e) 
    {
        player.updateMario();
        for(int i = 0; i < enemies.size()-1; i++)
            enemies.get(i).move(player);
        for(int i = enemies.size()-1; i >= 0; i--)
        {
            enemies.get(i).updateGumba();
            player.die(enemies.get(i));
            if(player.boom(enemies.get(i)))
            {
                endFrame.displayFrame();
                setVisible(false);
            }
        }
        repaint();
    }
}
    