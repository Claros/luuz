import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class TestCommand extends Command {

	@Override
	public boolean execute(Player player) {
        if (!this.hasSecondWord()) {
        	GameEngine.gui.println("Test what?");
            return false;
        }

        Scanner vScanner;
        
        try 
        { 
        	vScanner = new Scanner(new File("./" + this.getSecondWord()));
            while (vScanner.hasNextLine())
            {
                String ligne = vScanner.nextLine();
                GameEngine.interpretCommand(ligne);
            }
            vScanner.close();
        } 
        catch (FileNotFoundException pObjetException) 
        {  
        	GameEngine.gui.println("File does not exist.");
        } 
		return false;
	}

}
