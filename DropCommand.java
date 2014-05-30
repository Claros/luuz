
public class DropCommand extends Command {

	@Override
	public boolean execute(Player player) {
        if (!this.hasSecondWord()) {
        	GameEngine.gui.println("Drop what?");
            return false;
        }
        
        String name = this.getSecondWord();
        
    	if (player.getInventory().hasItem(name))
    	{
    		player.getCurrentRoom().getItemList().addItem(name, player.getInventory().getItem(name));
    		GameEngine.gui.println("You droped " + name);
    		player.getInventory().removeItem(name);
    	}
    	else
    	{
    		GameEngine.gui.println("You does not have this item.");
    	}
 
		return false;
	}

}
