import java.util.HashMap;
import java.util.Set;

/*
 * Class Room - a room in an adventure game.
 *
 * This class is the main class of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Room 
{
    public String description;
    private HashMap<String, Room> exits;
	private String imageName;
	private ItemList items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     */
    public Room(String description, String image) 
    {
        this.description = description;
        this.exits = new HashMap<String, Room>();
		this.imageName = "images/" + image;
		this.items = new ItemList();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }
    
    public Room getExit(String direction)
    {
    	return exits.get(direction);
    }
    
    /**
     * Return a description of the room's exits.
     * for example : 'Exits: north west'
     * @return a Description of the available exits.
     */
    public String getExitString()
    {
    	String returnString = new String("Exits: ");
        Set<String> keys = exits.keySet();
        
        for (String exit : keys)
        {
        	returnString += ' ' + exit;
        }
        
        return returnString;
    }
    
    /**
     * Return a long description of this room, of the form:
     * 		You are in the kitchen.
     * 		Exits: north west
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
    	String vS = "You are " + description + ".\n" + getExitString();
    	if (!items.isEmpty())
    		vS += "Items: " + this.items.getItemString();
    	return vS;
    }

	/**
     * Return a string describing the room's image name
     */
	public String getImageName()
	{
		return imageName;
	}
	
	public ItemList getItemList()
	{
		return items;
	}
}
