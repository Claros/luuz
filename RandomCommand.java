
public class RandomCommand extends Command {

	@Override
	public boolean execute(Player player) {
        if(!this.hasSecondWord()) {
            // if there is no second word, we disable the seed...
        	GameEngine.gui.println("Random enabled.");
        	RoomRandomizer.setSeed(null);
        }
        else
        {
        	try{
        		RoomRandomizer.setSeed( Long.parseLong(this.getSecondWord(), 10) );
        	} catch (NumberFormatException E)
        	{
        		GameEngine.gui.println("Wrong seed.");
        		return false;
        	}
        	
    		GameEngine.gui.println("You changed the seed.");        		
        }
		return false;
	}

}
