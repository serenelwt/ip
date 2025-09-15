package capy.command;

import capy.task.TaskList;
import capy.Ui;
import capy.Storage;
import capy.task.Task;

public class DeleteCommand extends Command {
    private final String argument;

    public DeleteCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(argument) - 1;
            Task removedTask = tasks.delete(index); // may throw IndexOutOfBoundsException
            storage.save(tasks.getAllTasks());      // may throw IOException
            return ui.showRemoved(removedTask, tasks.size());
        } catch (NumberFormatException e) {
            return ui.showError("OOPS!!! That doesn't look like a valid number.");
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("OOPS!!! Task index is out of range.");
        } catch (Exception e) {
            return ui.showError("An unexpected error occurred: " + e.getMessage());
        }
    }
}
