package capy;

import capy.command.Command;
import capy.command.TodoCommand;
import capy.command.DeadlineCommand;
import capy.command.EventCommand;
import capy.command.ExitCommand;
import capy.command.ListCommand;
import capy.command.MarkCommand;
import capy.command.UnmarkCommand;
import capy.command.DeleteCommand;
import capy.command.FindCommand;
import capy.exception.CapyException;

public class Parser {
    public static Command parse(String fullCommand) throws CapyException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "todo":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new CapyException("The description of a todo cannot be empty...");
                }
                return new TodoCommand(parts[1].trim());

            case "deadline":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new CapyException("The description of a deadline cannot be empty...");
                }
                return new DeadlineCommand(parts[1].trim());

            case "event":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new CapyException("The description of an event cannot be empty...");
                }
                return new EventCommand(parts[1].trim());

            case "mark":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new CapyException("Please specify the task number to mark...");
                }
                return new MarkCommand(parts[1].trim());

            case "unmark":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new CapyException("Please specify the task number to unmark...");
                }
                return new UnmarkCommand(parts[1].trim());

            case "delete":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new CapyException("Please specify the task number to delete...");
                }
                return new DeleteCommand(parts[1].trim());

            case "find":
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new CapyException("Please specify a keyword to search...");
                }
                return new FindCommand(parts[1].trim());

            default:
                throw new CapyException("Capy do not know what you mean...");
        }
    }
}
