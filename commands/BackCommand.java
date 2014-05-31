package commands;
import characters.Player;
import mechanics.GameEngine;
import rooms.Room;


public class BackCommand extends Command {

	@Override
	public boolean execute(Player player) {
        if(this.hasSecondWord()) {
            GameEngine.gui.println("Back what?");
            return false;
        }

        if (player.hasPreviousRoom())
        	GameEngine.gui.println("You can't go back!");
        else {
        	Room vRoom = player.getPreviousRoom();
        	String direction = player.getCurrentRoom().getDirection(vRoom);

        	if (direction == null)
        	{// If the rooms are not linked from current room, we can not go back
        		GameEngine.gui.println("The door is locked...");
        		player.addPreviousRoom(vRoom);
        		return false;
        	}

        	player.changeRoom(vRoom);
        	GameEngine.gui.println(player.getCurrentRoom().getLongDescription());
            if(player.getCurrentRoom().getImageName() != null)
            	GameEngine.gui.showImage(player.getCurrentRoom().getImageName());
        }
		return false;
	}

}
