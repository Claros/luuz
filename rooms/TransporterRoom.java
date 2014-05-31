package rooms;
import mechanics.GameEngine;


public class TransporterRoom extends Room {
	public TransporterRoom(String description, String image) {
		super(description, image);
	}
    
    @Override
    public Room getExit(String direction)
    {
        RoomRandomizer random = new RoomRandomizer(GameEngine.getRooms());
        return random.nextRoom();
    }
}
