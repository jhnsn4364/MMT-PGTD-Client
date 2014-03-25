/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mmtclient;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author harlan.howe
 */
public class MMTGamePanel extends JPanel implements KeyListener
{
    private boolean AisDown,SisDown,DisDown,WisDown;
    
    public MMTGamePanel()
    {
        super();
        AisDown = false;
        SisDown = false;
        DisDown = false;
        WisDown = false;
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
        if (e.getKeyChar()=='a') // notice... lower case, and this method only
            AisDown = true;      //    works with alphanumeric keys. If you 
        if (e.getKeyChar()=='s') //    want the arrow keys, you'll need to use 
            SisDown = true;      //    getKeyCode() instead. (See API.)
        if (e.getKeyChar()=='d')
            DisDown = true;
        if (e.getKeyChar()=='w')
            WisDown = true;
       
        
       
    }
    /**
     * detects when a key is let up by the user. One of the required methods
     * for the KeyListener interface.
     * @param e 
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar()=='a')
            AisDown = false;
        if (e.getKeyChar()=='s')
            SisDown = false;
        if (e.getKeyChar()=='d')
            DisDown = false;
        if (e.getKeyChar()=='w')
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
    
}
