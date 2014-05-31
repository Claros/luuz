package commands;
import characters.Player;
import mechanics.GameEngine;
import items.Item;


public class EatCommand extends Command {

	@Override
	public boolean execute(Player player) {
        if(!this.hasSecondWord()) {
            GameEngine.gui.println("Eat what?");
            return false;
        }
        
        String name = this.getSecondWord();
        
        if (!player.getInventory().hasItem(name))
        {
        	GameEngine.gui.println("You have not any " + name);
        	return false;
        }
        
        Item item = player.getInventory().getItem(name);
        if (!item.isEdible())
        {
        	GameEngine.gui.println("You can not eat " + name);
        	return false;
        }
        
        player.getInventory().removeItem(name);
        GameEngine.gui.println("You ate " + name);

        if (name.equals("magic-cookie"))
        {
        	player.setMaxWeight(player.getMaxWeight() + 10);
        	GameEngine.gui.println("You can now wear more items");
        }
		return false;
	}

}
