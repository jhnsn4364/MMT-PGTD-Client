/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mmtclient;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author harlan.howe
 */
public class MMTGamePanel extends JPanel implements KeyListener
{
    private boolean AisDown,SisDown,DisDown,WisDown;
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private int heroId;
    
    
    public MMTGamePanel()
    {
        super();
        AisDown = false;
        SisDown = false;
        DisDown = false;
        WisDown = false;
    }
    
    public void setHeroId(int inId)
    {
        heroId = inId;
    }
    
    
    /** 
     * detects when a key is pressed AND released. One of the required methods
     * in the KeyListener interface.
     * @param e 
     */
    @Override
    public void keyTyped(KeyEvent e) {
        ;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    /**
     * detects when a key is first pressed. One of the required methods in the
     * KeyListener interface.
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar()=='a'||e.getKeyChar()=='A')   // notice... lower case, and this method only
            AisDown = true;                             //    works with alphanumeric keys. If you 
        if (e.getKeyChar()=='s'||e.getKeyChar()=='S')   //    want the arrow keys, you'll need to use 
            SisDown = true;                             //    getKeyCode() instead. (See API.)
        if (e.getKeyChar()=='d'||e.getKeyChar()=='D')
            DisDown = true;
        if (e.getKeyChar()=='w'||e.getKeyChar()=='W')
            WisDown = true;
       
        
       
    }
    /**
     * detects when a key is let up by the user. One of the required methods
     * for the KeyListener interface.
     * @param e 
     */
    @Override
    public void keyReleased(KeyEvent e) 
    {
        if (e.getKeyChar()=='a'||e.getKeyChar()=='A')
            AisDown = false;
        if (e.getKeyChar()=='s'||e.getKeyChar()=='S')
            SisDown = false;
        if (e.getKeyChar()=='d'||e.getKeyChar()=='D')
            DisDown = false;
        if (e.getKeyChar()=='w'||e.getKeyChar()=='W')
            WisDown = false;
        
        
    }
    
    public int aStatus()
    {
        if (AisDown)
            return 1;
        return 0;
    }
    public int sStatus()
    {
        if (SisDown)
            return 1;
        return 0;
    }
    public int dStatus()
    {
        if (DisDown)
            return 1;
        return 0;
    }
    public int wStatus()
    {
        if (WisDown)
            return 1;
        return 0;
    }
    
    public void addPlayer(int id, int x, int y, int isIt /*, String name*/)
    {
        Player newPlayer = new Player(id, x, y, isIt/*, name*/);
        playerList.add(newPlayer);
        repaint();
    }
    
    public void updatePlayer(int id, int x, int y, int isIt/*, String name*/)
    {
        //System.out.println("I'm updating, go away");
        for (Player p:playerList)
        {
            if (p.getId() == id)
            {
                p.setX(x);
                p.setY(y);
                p.setIt(isIt);
                repaint();
            }
        }
//        for (int i = 0; i < playerList.size(); i++)
//        {
//            if (id == playerList.get(i).getId())
//            {
//                playerList.get(i).setX(x);
//                playerList.get(i).setY(y);
//                playerList.get(i).setIt(isIt);
//                repaint();
//            }
//        }
    }
    
    public void removePlayer(int id)
    { 
        
        ArrayList<Player> playerListCopy = (ArrayList<Player>) playerList.clone();
        for (int i = 0; i < playerListCopy.size(); i++)
        {
            if (id == playerListCopy.get(i).getId())
            {
                playerList.remove(playerListCopy.get(i));
                repaint();
            }
        }
    }
    
    public boolean containsPlayer(int id)
    {
        for (Player p:playerList)
        {
            if (p.getId()==id)
                return true;
        }
        return false;
    }
    
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        if (playerList.size()>0)
        {
            for (Player p:playerList)
            {
                if (p.getIsIt()==1 && p.getId()==heroId)
                
                    //if the player is it, he will appear as a magenta circle
                    g.setColor(Color.magenta);
                
                
                else if (p.getIsIt()==1)
                
                    //if another player is it, he will appear as a red circle
                    g.setColor(Color.red);
                
                
                else if (p.getId()==heroId)
                    //the player will appear as a blue circle
                    g.setColor(Color.blue);
                
                else
                    //all other players will appear as black circles
                    g.setColor(Color.black);
                
                g.fillOval(p.getX(), p.getY(), 20, 20);

            }
        }

    }
    
}
