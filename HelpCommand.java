
public class HelpCommand extends Command {

	@Override
	public boolean execute(Player player) {
        GameEngine.gui.println("You are lost. You are alone. You wander");
        GameEngine.gui.println("around at Monash Uni, Peninsula Campus." + "\n");
        GameEngine.gui.print("Your command words are: " + GameEngine.parser.showCommands());
		return false;
	}

}
