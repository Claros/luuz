package commands;
import characters.Player;
import mechanics.GameEngine;
import rooms.Room;
import items.Beamer;


public class TeleportCommand extends Command {

	@Override
	public boolean execute(Player player) {
		if (!player.getInventory().hasItem("beamer"))
		{
			GameEngine.gui.println("You can not teleport without the beamer.");
			return false;
		}
		
		Beamer beamer = (Beamer) player.getInventory().getItem("beamer");
		if (beamer.isCharged())
		{
			Room vRoom = beamer.getSavedRoom();
			if (!vRoom.equals(player.getCurrentRoom()))
			{
				GameEngine.gui.println("FIRE !!!!");
				player.changeRoom(vRoom);
				
				GameEngine.gui.println(player.getCurrentRoom().getLongDescription());
		        if(player.getCurrentRoom().getImageName() != null)
		        	GameEngine.gui.showImage(player.getCurrentRoom().getImageName());
		        
				beamer.setCharged(false);
			}
			else
			{
				GameEngine.gui.println("You are already in the saved room.");
			}
		}
		else
		{
			GameEngine.gui.println("You saved the current room with your beamer.");
			beamer.setCharged(true);
			beamer.setSavedRoom(player.getCurrentRoom());
		}
		return false;
	}

}
