package capy.command;

import capy.task.TaskList;
import capy.Ui;
import capy.Storage;
import capy.task.Task;

/**
 * Represents a command to mark a task in the {@link TaskList} as completed.
 * <p>
 * The task to mark is specified by its 1-based index provided as a string argument.
 * This command handles invalid indices, non-numeric input, and storage errors,
 * and returns appropriate messages via {@link Ui}.
 */
public class MarkCommand extends Command {
    private final String argument;

    /**
     * Constructs a {@code MarkCommand} with the given argument.
     *
     * @param argument The 1-based index of the task to mark, as a string.
     */
    public MarkCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Executes this command to mark a task as completed in the provided {@link TaskList}.
     * <p>
     * Parses the argument as an integer index, marks the corresponding task,
     * saves the updated task list, and returns a message indicating the result.
     * Handles invalid input, out-of-range indices, and storage errors.
     *
     * @param tasks   The {@link TaskList} containing the task to mark.
     * @param ui      The {@link Ui} used to generate user-facing messages.
     * @param storage The {@link Storage} used to persist the updated task list.
     * @return A string message indicating the result of the mark operation.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(argument) - 1;
            Task task = tasks.mark(index); // may throw IndexOutOfBoundsException
            storage.save(tasks.getAllTasks()); // may throw IOException
            return ui.showMark(task);
        } catch (NumberFormatException e) {
            return ui.showError("That doesn't look like a valid number...");
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("Task index is out of range...");
        } catch (Exception e) {
            return ui.showError("An unexpected error occurred: " + e.getMessage());
        }
    }
}
