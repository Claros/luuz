import java.util.HashMap;

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
    public Room northExit;
    public Room southExit;
    public Room eastExit;
    public Room westExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     */
    public Room(String description) 
    {
        this.description = description;
        this.exits = new HashMap<String, Room>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     */
    public void setExits(Room north, Room east, Room south, Room west) 
    {
        if(north != null)
        	exits.put("north", north);
        if(east != null)
            exits.put("east", east);
        if(south != null)
            exits.put("south", south);
        if(west != null)
            exits.put("west", west);
    }

    /**
     * Return the description of the room (the one that was defined
     * in the constructor).
     */
    public String getDescription()
    {
        return description;
    }
    
    public Room getExit(String direction)
    {
    	return exits.get(direction);
    }
    
    public String getExitString()
    {
    	String vS = new String("Exits: ");
    	
        if(this.northExit != null)
        	vS += "north ";
        if(this.eastExit != null)
        	vS += "east ";
        if(this.southExit != null)
        	vS += "south ";
        if(this.westExit != null)
        	vS += "west ";
    	
    	return vS;
    }
}
