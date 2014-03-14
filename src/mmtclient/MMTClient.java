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
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author harlan.howe
 */
public class MMTClient extends JFrame{

    private Socket mySocket;
    private final String ServerIP = "172.16.218.183";
    private Scanner mySocketScanner;
    private PrintWriter mySocketWriter;
    private String name;
    private int id;
    
    public MMTClient()
    {
        super ("MMT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,800);
        getContentPane().setLayout(new GridLayout(1,1));
        MMTGamePanel thePanel = new MMTGamePanel();
        getContentPane().add(thePanel);
        this.addKeyListener(thePanel);
        setVisible(true);
        
        name = JOptionPane.showInputDialog(this,"What is your name?");
        
        setupNetworking();
    }
    
    public void setupNetworking()
    {
        try
        {
            mySocket = new Socket(ServerIP, 5000);
            mySocketScanner = new Scanner(mySocket.getInputStream());
            mySocketWriter = new PrintWriter(mySocket.getOutputStream());
            
            Thread readerThread = new Thread(new IncomingReader());
            readerThread.start();
            
            mySocketWriter.println(name);
            mySocketWriter.flush();
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
                    
                    //myTextArea.setText(myTextArea.getText()+mySocketScanner.nextLine()+"\n");
                }
                catch(NoSuchElementException nsee)
                {
                    //myTextArea.setText(myTextArea.getText()+"Lost connection.");
                }
            }
        }
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        System.out.println(ae.getActionCommand());
        //System.out.println("I just sent:\n\t "+userTextField.getText());
        mySocketWriter.println();//userTextField.getText());
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
