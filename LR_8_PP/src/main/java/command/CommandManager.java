package command;

public class CommandManager {
    public void runCommand(Command command) {
        command.execute();
    }
}

