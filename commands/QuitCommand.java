package commands;
import characters.Player;
import mechanics.GameEngine;


public class QuitCommand extends Command {

	@Override
	public boolean execute(Player player) {
		GameEngine.endGame();
		return true;
	}

}
