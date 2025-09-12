package capy.command;

import capy.CapyException;
import capy.TaskList;
import capy.Ui;
import capy.Storage;
import capy.task.Task;

public class MarkCommand extends Command {
    private final String argument;

    public MarkCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(argument) - 1;
            Task task = tasks.mark(index); // may throw IndexOutOfBoundsException
            storage.save(tasks.getAllTasks()); // may throw IOException
            return ui.showMark(task);
        } catch (NumberFormatException e) {
            return ui.showError("OOPS!!! That doesn't look like a valid number.");
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("OOPS!!! Task index is out of range.");
        } catch (Exception e) {
            return ui.showError("An unexpected error occurred: " + e.getMessage());
        }
    }
}
