package capy.command;

import capy.task.TaskList;
import capy.Ui;
import capy.Storage;
import capy.task.Task;

/**
 * Represents a command to delete a task from the {@link TaskList}.
 * <p>
 * The task to delete is specified by its 1-based index as a string argument.
 * This command handles invalid indices, non-numeric input, and storage errors,
 * and returns appropriate messages via {@link Ui}.
 */
public class DeleteCommand extends Command {
    private final String argument;

    /** The argument representing the 1-based index of the task to delete. */
    private final String argument;

    /**
     * Constructs a {@code DeleteCommand} with the given argument.
     *
     * @param argument The 1-based index of the task to delete, as a string.
     */
    public DeleteCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Executes this command to delete a task from the provided {@link TaskList}.
     * <p>
     * Parses the argument as an integer index, deletes the corresponding task,
     * saves the updated task list, and returns a message indicating the result.
     * Handles invalid input, out-of-range indices, and storage errors.
     *
     * @param tasks   The {@link TaskList} from which the task will be deleted.
     * @param ui      The {@link Ui} used to generate user-facing messages.
     * @param storage The {@link Storage} used to persist the updated task list.
     * @return A string message indicating the result of the delete operation.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(argument) - 1;
            Task removedTask = tasks.delete(index); // may throw IndexOutOfBoundsException
            storage.save(tasks.getAllTasks());      // may throw IOException
            return ui.showRemoved(removedTask, tasks.size());
        } catch (NumberFormatException e) {
            return ui.showError("That doesn't look like a valid number...");
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("Task index is out of range...");
        } catch (Exception e) {
            return ui.showError("An unexpected error occurred: " + e.getMessage());
        }
    }
}
