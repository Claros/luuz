import java.util.HashMap;
import java.util.Set;
import java.util.Stack;


public class Player {
    private Room currentRoom;
    private Stack<Room> previousRoom;
    private ItemList items;
    
    public Player()
    {
        previousRoom = new Stack<Room>();
        items = new ItemList();
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
	
	public ItemList getInventory()
	{
		return this.items;
	}
}
