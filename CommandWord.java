
public enum CommandWord {
	GO("go", new GoCommand()), 
	QUIT("quit", new QuitCommand()), 
	HELP("help", new HelpCommand()), 
	UNKNOWN("?", null), 
	LOOK("look", new LookCommand()), 
	EAT("eat", new EatCommand()), 
	BACK("back", new BackCommand()), 
	TEST("test", new TestCommand()), 
	TAKE("take", new TakeCommand()), 
	DROP("drop", new DropCommand()), 
	ITEMS("items", new ItemsCommand()), 
	TELEPORT("teleport", new TeleportCommand()), 
	RANDOM("random", new RandomCommand());
    
    // The command string.
    private String commandString;
    private Command command;
    
    /**
     * Initialise with the corresponding command word.
     * @param commandWord The command string.
     */
    CommandWord(String commandString, Command pCommand)
    {
        this.commandString = commandString;
        this.command = pCommand;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }

    /**
     * Gets the command.
     *
     * @return the command
     */
    public Command getCommand()
    {
        return this.command;
    }
}
