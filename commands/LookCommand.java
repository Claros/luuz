package commands;
import characters.Player;
import mechanics.GameEngine;


public class LookCommand extends Command {

	@Override
	public boolean execute(Player player) {
		GameEngine.gui.println(player.getCurrentRoom().getLongDescription());
		return false;
	}

}
