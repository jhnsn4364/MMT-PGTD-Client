/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mmtclient;

/**
 *
 * @author phillip.johnson
 */
public class Player 
{
    private int id;
    private int x;
    private int y;
    private int isIt;
    
    public Player()
    {
        
    }
    
    public Player(int inId, int inX, int inY, int inIt)
    {
        id = inId;
        x = inX;
        y = inY;
        isIt = inIt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setIt(int isIt) {
        this.isIt = isIt;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getIsIt() {
        return isIt;
    }
    
}
