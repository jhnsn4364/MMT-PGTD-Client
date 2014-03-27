/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mmtclient;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author harlan.howe
 */
public class MMTClient extends JFrame{

    private Socket mySocket;
    private final String ServerIP = "172.16.220.130";
    private Scanner mySocketScanner;
    private PrintWriter mySocketWriter;
    private String name;
    private int id;
    private MMTGamePanel thePanel;
    private ArrayList<Integer> usedIds = new ArrayList<Integer>();
    //private int localId;
    
    public MMTClient()
    {
        super ("MMT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,800);
        getContentPane().setLayout(new GridLayout(1,1));
        thePanel = new MMTGamePanel();
        getContentPane().add(thePanel);
        this.addKeyListener(thePanel);
        setVisible(true);
        
        name = JOptionPane.showInputDialog(this,"What is your name?");
        //testPlayer();
        setupNetworking();
        
    }
    
    public void testPlayer()
    {
        Random generator = new Random();
        id = generator.nextInt(12);
        thePanel.setHeroId(id);
        int x = generator.nextInt(800);
        int y = generator.nextInt(800);
        int isIt = 1;
        thePanel.addPlayer(id, x, y, isIt);
        thePanel.repaint();
    }
    
    public void setupNetworking()
    {
        try
        {
            mySocket = new Socket(ServerIP, 5001);
            mySocketScanner = new Scanner(mySocket.getInputStream());
            mySocketWriter = new PrintWriter(mySocket.getOutputStream());
            
            Thread readerThread = new Thread(new IncomingReader());
            readerThread.start();
            
            
            mySocketWriter.println("Joining"+"\t");
            mySocketWriter.println(name);
            mySocketWriter.flush();
            String incomingParsable = mySocketScanner.nextLine();
            String[] part = incomingParsable.split("\t");
            id = Integer.parseInt(part[0]);
            usedIds.add(id);
            thePanel.setHeroId(id);
            int x = Integer.parseInt(part[1]);
            int y = Integer.parseInt(part[2]);
            int isIt = Integer.parseInt(part[3]);
            thePanel.addPlayer(id, x, y, isIt);
            
            
            
            
            
        }
        catch(IOException ioe)
        {
            System.out.println(ioe.getStackTrace());
        }
    }
    
    public class IncomingReader implements Runnable
    {
        public void run()
        {
            while (true)
            {
                try
                {
                    String incomingParsable = mySocketScanner.nextLine();
                    String[] part = incomingParsable.split("\t");
                    
                    int total = Integer.parseInt(part[0]);
                    
                    for (int i = 1; i < total; i+=4)
                    {
                       
                       int newId = Integer.parseInt(part[i]);
                       int newX = Integer.parseInt(part[i+1]);
                       int newY = Integer.parseInt(part[i+2]);
                       int newIt = Integer.parseInt(part[i+3]);
                       
                       if (thePanel.containsPlayer(newId))
                       {
                           thePanel.updatePlayer(newId,newX,newY,newIt);
                       }
                       else if (!(usedIds.contains(newId)))
                       {
                           thePanel.addPlayer(newId,newX,newY,newIt);
                           usedIds.add(newId);
                       }
                                               
                                
                    }
                    
                    //myTextArea.setText(myTextArea.getText()+mySocketScanner.nextLine()+"\n");
                }
                catch(NoSuchElementException nsee)
                {
                    //myTextArea.setText(myTextArea.getText()+"Lost connection.");
                    System.out.println("Connection Lost");
                }
            }
        }
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        System.out.println(ae.getActionCommand());
        //System.out.println("I just sent:\n\t "+userTextField.getText());
        mySocketWriter.println(this.id + "\t" + thePanel.wStatus() + "\t" + thePanel.aStatus() + "\t" + thePanel.sStatus() + "\t" + thePanel.dStatus());
        //userTextField.getText());
        mySocketWriter.flush();
        //userTextField.setText("");
        //userTextField.requestFocus(); // ask to put the cursor back in the field.
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MMTClient theApp = new MMTClient();
    }
    
}
