import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.Timer;

/**
 *  This class is part of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.
 * 
 *  This class creates all rooms, creates the parser and starts
 *  the game.  It also evaluates and executes the commands that 
 *  the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (Jan 2003)
 */
public class GameEngine implements ActionListener
{
    public static Parser parser;
    public static UserInterface gui;
    private static Player player;
    private int limit;
    private Timer timer;
    private static HashMap<String,Room> rooms;
        
    /**
     * Create the game and initialise its internal map.
     */
    public GameEngine() 
    {
        parser = new Parser();
        player = new Player(10);
        limit = 180;
        rooms = new HashMap<String,Room>();
        createRooms();
        timer = new Timer(1000, this);//1000 -> 1000ms -> 1s
        timer.start();
    }

    public void setGUI(UserInterface userInterface)
    {
        gui = userInterface;
        printWelcome();
    	gui.setTimer("" + limit);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university", "outside.gif");
        theatre = new TransporterRoom("in a lecture theatre", "castle.gif");
        pub = new Room("in the campus pub", "courtyard.gif");
        lab = new Room("in a computing lab", "stairs.gif");
        office = new Room("the computing admin office", "dungeon.gif");
        
        rooms.put("outside", outside);
        rooms.put("theatre", theatre);
        rooms.put("pub", pub);
        rooms.put("lab", lab);
        rooms.put("office", office);
        
        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        
        outside.getItemList().addItem("caca", "A big shit", 10);
        outside.getItemList().addItem("pipi", "It's quite sliding there...", 1);
        Item magicCookie = new Item("magic-cookie", "It's shining...", 1);
        magicCookie.setEdible(true);
        outside.getItemList().addItem(magicCookie.getName(), magicCookie);
        Beamer beamer = new Beamer("beamer", "An item to teleport", 1);
        outside.getItemList().addItem(beamer.getName(), beamer);

        player.setCurrentRoom(outside);  // start game outside
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
    	gui.print("\n");
    	gui.println("Welcome to Adventure!");
    	gui.println("Adventure is a new, incredibly boring adventure game.");
    	gui.println("Type 'help' if you need help.");
    	gui.print("\n");
        gui.println(player.getCurrentRoom().getLongDescription());
    	gui.print("\n");
        gui.showImage(player.getCurrentRoom().getImageName());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    public static void interpretCommand(String commandLine) 
    {
        gui.println(commandLine);
        Command command = parser.getCommand(commandLine);
        
        if (command == null)
        {
        	gui.println("I don't know what you mean...");
        }
        else
        {
        	command.execute(player);
        }
        
        gui.print("\n");
    }

    public static void endGame()
    {
        gui.println("Thank you for playing.  Good bye.");
        gui.enable(false);
    }

	@Override
	// Called by Timer every second
	public void actionPerformed(ActionEvent e) {
    	limit--;
    	gui.setTimer("" + limit);
    	if (limit <= 0)
    	{
    		gui.println("You lose.");
    		endGame();
    		timer.stop();
    	}
	}
	
	// ETDDC
	public void changeRoom(Room pRoom)
	{
    	player.changeRoom(pRoom);
        gui.println(player.getCurrentRoom().getLongDescription());
        if(player.getCurrentRoom().getImageName() != null)
            gui.showImage(player.getCurrentRoom().getImageName());
	}
	
	public static HashMap<String,Room> getRooms()
	{
		return GameEngine.rooms;
	}
}
