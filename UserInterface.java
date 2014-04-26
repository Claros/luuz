import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling
 * @version 1.0 (Jan 2003)
 */
public class UserInterface implements ActionListener
{
    private GameEngine engine;
    private JFrame myFrame;
    private JTextField entryField;
    private JTextArea log;
    private JLabel image;
    private JButton buttonLook, buttonEast, buttonWest, buttonNorth, buttonSouth; 

    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param gameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface(GameEngine gameEngine)
    {
        engine = gameEngine;
        createGUI();
    }

    /**
     * Print out some text into the text area.
     */
    public void print(String text)
    {
        log.append(text);
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println(String text)
    {
        log.append(text + "\n");
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Show an image file in the interface.
     */
    public void showImage(String imageName)
    {
        URL imageURL = this.getClass().getClassLoader().getResource(imageName);
        if(imageURL == null)
        	println("image not found");
        else {
            ImageIcon icon = new ImageIcon(imageURL);
            image.setIcon(icon);
            myFrame.pack();
        }
    }

    /**
     * Enable or disable input in the input field.
     */
    public void enable(boolean on)
    {
        entryField.setEditable(on);
        buttonLook.setEnabled(on);
        buttonNorth.setEnabled(on);
        buttonSouth.setEnabled(on);
        buttonEast.setEnabled(on);
        buttonWest.setEnabled(on);
        if(!on)
            entryField.getCaret().setBlinkRate(0);
    }

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        myFrame = new JFrame("Zork");
        entryField = new JTextField(34);

        log = new JTextArea();
        log.setEditable(false);
        JScrollPane listScroller = new JScrollPane(log);
        listScroller.setPreferredSize(new Dimension(200, 200));
        listScroller.setMinimumSize(new Dimension(100,100));

        JPanel panel = new JPanel(),
        		panel2 = new JPanel();
        image = new JLabel();

        buttonLook = new JButton("look");
        buttonNorth = new JButton("go north");
        buttonSouth = new JButton("go south");
        buttonEast = new JButton("go east");
        buttonWest = new JButton("go west");

        panel.setLayout(new BorderLayout());
        panel.add(image, BorderLayout.NORTH);
        panel.add(listScroller, BorderLayout.CENTER);
        panel.add(entryField, BorderLayout.SOUTH);
        panel.add(buttonLook, BorderLayout.EAST);
        panel.add(panel2, BorderLayout.WEST);

        panel2.setLayout(new BorderLayout());
        panel2.add(buttonNorth, BorderLayout.NORTH);
        panel2.add(buttonSouth, BorderLayout.SOUTH);
        panel2.add(buttonEast, BorderLayout.EAST);
        panel2.add(buttonWest, BorderLayout.WEST);

        myFrame.getContentPane().add(panel, BorderLayout.CENTER);

        // add some event listeners to some components
        myFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

        entryField.addActionListener(this);
        buttonLook.addActionListener(this);
        buttonNorth.addActionListener(this);
        buttonSouth.addActionListener(this);
        buttonEast.addActionListener(this);
        buttonWest.addActionListener(this);

        myFrame.pack();
        myFrame.setVisible(true);
        entryField.requestFocus();
    }

    /**
     * Actionlistener interface for entry textfield.
     */
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == entryField)
        {
            processCommand();
        } 
        else if (e.getSource() == buttonLook)
        {
        	engine.interpretCommand("look");
        }
        else if (e.getSource() == buttonNorth)
        {
        	engine.interpretCommand("go north");
        }
        else if (e.getSource() == buttonSouth)
        {
        	engine.interpretCommand("go south");
        }
        else if (e.getSource() == buttonEast)
        {
        	engine.interpretCommand("go east");
        }
        else if (e.getSource() == buttonWest)
        {
        	engine.interpretCommand("go west");
        }
    }

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        String input = entryField.getText();
        entryField.setText("");

        engine.interpretCommand(input);
    }
}