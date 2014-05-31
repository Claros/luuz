package commands;
import characters.Player;
import mechanics.GameEngine;
import items.Item;


public class TakeCommand extends Command {

	@Override
	public boolean execute(Player player) {
        if (!this.hasSecondWord()) {
            GameEngine.gui.println("Take what?");
            return false;
        }
        
        if (player.getCurrentRoom().getItemList().hasItem(this.getSecondWord()))
        {
        	Item item = player.getCurrentRoom().getItemList().getItem(this.getSecondWord());
        	if ((player.getInventory().getTotalWeight() + item.getWeight()) > player.getMaxWeight())
        	{
        		GameEngine.gui.println("You can not carry more items.");
        	} else {
            	player.getInventory().addItem(item);
            	player.getCurrentRoom().getItemList().removeItem(this.getSecondWord());
            	GameEngine.gui.println("You took " + item.getName());
        	}
        } else {
        	GameEngine.gui.println(this.getSecondWord() + " does not exist.");
        }
		return false;
	}

}
