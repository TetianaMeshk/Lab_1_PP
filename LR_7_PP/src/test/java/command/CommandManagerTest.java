package command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandManagerTest {

    @Test
    void testRunCommand() {
        CommandManager manager = new CommandManager();

        final boolean[] isExecuted = {false};

        Command command = () -> isExecuted[0] = true;

        manager.runCommand(command);

        assertTrue(isExecuted[0], "Метод execute повинен бути викликаний.");
    }
}
