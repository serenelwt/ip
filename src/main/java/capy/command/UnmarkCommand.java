package capy.command;

import capy.task.TaskList;
import capy.Ui;
import capy.Storage;
import capy.task.Task;

/**
 * Represents a command to unmark a task in the {@link TaskList} as not completed.
 * <p>
 * The task to unmark is specified by its 1-based index provided as a string argument.
 * This command handles invalid indices, non-numeric input, and storage errors,
 * and returns appropriate messages via {@link Ui}.
 */
public class UnmarkCommand extends Command {
    private final String argument;

    /**
     * Constructs an {@code UnmarkCommand} with the given argument.
     *
     * @param argument The 1-based index of the task to unmark, as a string.
     */
    public UnmarkCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Executes this command to mark a task as not completed in the provided {@link TaskList}.
     * <p>
     * Parses the argument as an integer index, unmarks the corresponding task,
     * saves the updated task list, and returns a message indicating the result.
     * Handles invalid input, out-of-range indices, and storage errors.
     *
     * @param tasks   The {@link TaskList} containing the task to unmark.
     * @param ui      The {@link Ui} used to generate user-facing messages.
     * @param storage The {@link Storage} used to persist the updated task list.
     * @return A string message indicating the result of the unmark operation.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(argument) - 1;
            Task task = tasks.unmark(index); // may throw IndexOutOfBoundsException
            storage.save(tasks.getAllTasks()); // may throw IOException
            return ui.showUnmark(task);
        } catch (NumberFormatException e) {
            return ui.showError("That doesn't look like a valid number...");
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("Task index is out of range...");
        } catch (Exception e) {
            return ui.showError("An unexpected error occurred: " + e.getMessage());
        }
    }
}
