import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
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
    private Parser parser;
    private UserInterface gui;
    private Player player;
    private int limit;
    private Timer timer;
        
    /**
     * Create the game and initialise its internal map.
     */
    public GameEngine() 
    {
        parser = new Parser();
        player = new Player(10);
        limit = 10;
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
        theatre = new Room("in a lecture theatre", "castle.gif");
        pub = new Room("in the campus pub", "courtyard.gif");
        lab = new Room("in a computing lab", "stairs.gif");
        office = new Room("the computing admin office", "dungeon.gif");
        
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
    public void interpretCommand(String commandLine) 
    {
        gui.println(commandLine);
        Command command = parser.getCommand(commandLine);

        CommandWord commandWord = command.getCommandWord();
        switch (commandWord)
        {
        	case HELP:
        		printHelp();
        		break;
        	case GO:
        		goRoom(command);
        		break;
        	case QUIT:
	            if(command.hasSecondWord())
	                gui.println("Quit what?");
	            else
	                endGame();
	            break;
        	case LOOK:
	        	look();
	        	break;
	    	case EAT:
	        	eat(command);
	        	break;
	    	case BACK:
	        	back(command);
	        	break;
	    	case TEST:
	        	test(command);
	        	break;
	    	case TAKE:
	        	take(command);
	        	break;
	    	case DROP:
	        	drop(command);
	        	break;
	    	case ITEMS:
	        	items();
	        	break;
	        default:
	        	gui.println("I don't know what you mean...");
	        	break;
        }
        
        gui.print("\n");
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        gui.println("You are lost. You are alone. You wander");
        gui.println("around at Monash Uni, Peninsula Campus." + "\n");
        gui.print("Your command words are: " + parser.showCommands());
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gui.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null)
            gui.println("There is no door!");
        else {
        	player.changeRoom(nextRoom);
            gui.println(player.getCurrentRoom().getLongDescription());
            if(player.getCurrentRoom().getImageName() != null)
                gui.showImage(player.getCurrentRoom().getImageName());
        }
    }

    private void endGame()
    {
        gui.println("Thank you for playing.  Good bye.");
        gui.enable(false);
    }
    
    private void look()
    {
    	gui.println(player.getCurrentRoom().getLongDescription());
    }
    
    private void eat(Command command)
    {
        if(!command.hasSecondWord()) {
            gui.println("Eat what?");
            return;
        }
        
        String name = command.getSecondWord();
        
        if (!player.getInventory().hasItem(name))
        {
            gui.println("You have not any " + name);
            return;
        }
        
        Item item = player.getInventory().getItem(name);
        if (!item.isEdible())
        {
            gui.println("You can not eat " + name);
            return;
        }
        
        player.getInventory().removeItem(name);
        gui.println("You ate " + name);

        if (name.equals("magic-cookie"))
        {
        	player.setMaxWeight(player.getMaxWeight() + 10);
            gui.println("You can now wear more items");
        }
    }

    private void back(Command command) 
    {
        if(command.hasSecondWord()) {
            gui.println("Back what?");
            return;
        }

        if (player.hasPreviousRoom())
            gui.println("You can't go back!");
        else {
        	player.changeRoom(player.getPreviousRoom());
            gui.println(player.getCurrentRoom().getLongDescription());
            if(player.getCurrentRoom().getImageName() != null)
                gui.showImage(player.getCurrentRoom().getImageName());
        }
    }

    private void test(Command command) 
    {
        if (!command.hasSecondWord()) {
            gui.println("Test what?");
            return;
        }

        Scanner vScanner;
        
        try 
        { 
        	vScanner = new Scanner(new File("./" + command.getSecondWord()));
            while (vScanner.hasNextLine())
            {
                String ligne = vScanner.nextLine();
                interpretCommand(ligne);
            }
            vScanner.close();
        } 
        catch (FileNotFoundException pObjetException) 
        {  
        	gui.println("File does not exist.");
        } 
    }
    
    private void take(Command command)
    {
        if (!command.hasSecondWord()) {
            gui.println("Take what?");
            return;
        }
        
        if (player.getCurrentRoom().getItemList().hasItem(command.getSecondWord()))
        {
        	Item item = player.getCurrentRoom().getItemList().getItem(command.getSecondWord());
        	if ((player.getInventory().getTotalWeight() + item.getWeight()) > player.getMaxWeight())
        	{
        		gui.println("You can not carry more items.");
        	} else {
            	player.getInventory().addItem(item);
            	player.getCurrentRoom().getItemList().removeItem(command.getSecondWord());
            	gui.println("You took " + item.getName());
        	}
        } else {
        	gui.println(command.getSecondWord() + " does not exist.");
        }
    }
    
    private void drop(Command command)
    {
        if (!command.hasSecondWord()) {
            gui.println("Drop what?");
            return;
        }
        
        String name = command.getSecondWord();
        
    	if (player.getInventory().hasItem(name))
    	{
    		player.getCurrentRoom().getItemList().addItem(name, player.getInventory().getItem(name));
        	gui.println("You droped " + name);
    		player.getInventory().removeItem(name);
    	}
    	else
    	{
    		gui.println("You does not have this item.");
    	}
    }
    
    private void items()
    {
    	gui.println(player.getLongInventory());
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
}
