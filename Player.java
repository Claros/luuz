import java.util.Stack;


public class Player {
    private Room currentRoom;
    private Stack<Room> previousRoom;
    
    public Player()
    {
        previousRoom = new Stack<Room>();
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
}
