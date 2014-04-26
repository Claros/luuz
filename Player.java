import java.util.Stack;


public class Player {
    private Room currentRoom;
    private Stack<Room> previousRoom;
    private ItemList items;
    private int maxWeight;
    
    public Player(int maxWeight)
    {
        previousRoom = new Stack<Room>();
        items = new ItemList();
        this.maxWeight = maxWeight;
    }

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
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

	public int getMaxWeight() {
		return maxWeight;
	}
	
	public String getLongInventory()
	{
    	if (!this.getInventory().isEmpty())
    		return "Inventory: " + this.getInventory().getTotalWeight() + "/" + this.getMaxWeight() + this.getInventory().getItemString();
    	else
    		return "";
	}
}
