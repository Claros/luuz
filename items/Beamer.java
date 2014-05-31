package items;
import rooms.Room;


public class Beamer extends Item {
	private Room savedRoom;
	private boolean charged;

	public Beamer(String name, String description, int weight) {
		super(name, description, weight);
	}

	public Room getSavedRoom() {
		return savedRoom;
	}

	public void setSavedRoom(Room savedRoom) {
		this.savedRoom = savedRoom;
	}

	public boolean isCharged() {
		return charged;
	}

	public void setCharged(boolean charged) {
		this.charged = charged;
	}

}
