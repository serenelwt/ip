package capy;

import capy.command.Command;
import capy.command.TodoCommand;
import capy.command.DeadlineCommand;

public class Parser {
    public static Command parse(String fullCommand) throws CapyException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
            case "todo":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new CapyException("The description of a todo cannot be empty.");
                }
                return new TodoCommand(parts[1].trim());
            case "deadline":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new CapyException("The description of a deadline cannot be empty.");
                }
                return new DeadlineCommand(parts[1].trim());
            default:
                throw new CapyException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
