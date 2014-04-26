import java.util.HashMap;
import java.util.Set;
import java.util.Stack;


public class Player {
    private Room currentRoom;
    private Stack<Room> previousRoom;
    private HashMap<String, Item> items;
    
    public Player()
    {
        previousRoom = new Stack<Room>();
        items = new HashMap<String, Item>();
    }

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(final Room currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	public boolean hasPreviousRoom()
	{
		return this.previousRoom.empty();
	}
	
	public Room getPreviousRoom()
	{
		return this.previousRoom.pop();
	}
	
	public void addPreviousRoom(final Room pRoom)
	{
		this.previousRoom.push(pRoom);
	}
	
	public void changeRoom(final Room pRoom)
	{
		this.previousRoom.add(currentRoom);
		this.currentRoom = pRoom;
	}

	public Item getItem(String key) {
		return items.get(key);
	}

	public void addItem(Item item) {
		items.put(item.getName(), item);
	}
	
	public boolean hasItem(String key) {
		return items.containsKey(key);
	}
	
	public void removeItem(String key) {
		items.remove(key);
	}
	
	public String getItemString()
	{
    	String returnString = new String("Inventory: ");
        Set<String> keys = items.keySet();
        
        for (String key : keys)
        {
        	returnString += "\n- " + this.items.get(key).getLongDescription();
        }
        
        return returnString;
	}
}
