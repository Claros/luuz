package commands;
import characters.Player;
import mechanics.GameEngine;
import rooms.Room;


public class GoCommand extends Command {

	public GoCommand() {
	}

	@Override
	public boolean execute(Player player) {
        if(!this.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            GameEngine.gui.println("Go where?");
            return false;
        }

        String direction = this.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null)
        	GameEngine.gui.println("There is no door!");
        else {
        	player.changeRoom(nextRoom);
        	GameEngine.gui.println(player.getCurrentRoom().getLongDescription());
            if(player.getCurrentRoom().getImageName() != null)
            	GameEngine.gui.showImage(player.getCurrentRoom().getImageName());
        }
		return false;
	}

}
