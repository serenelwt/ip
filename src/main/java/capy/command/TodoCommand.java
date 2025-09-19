package capy.command;

import capy.task.TaskList;
import capy.Ui;
import capy.Storage;
import capy.task.Task;
import capy.task.Todo;

import java.io.IOException;

/**
 * Represents a command to add a {@link Todo} task to the {@link TaskList}.
 * <p>
 * This command adds a simple task with a description, checks for duplicates,
 * and saves the updated task list to storage.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a {@code TodoCommand} with the given description.
     *
     * @param description The description of the Todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes this command to add a {@link Todo} task to the provided {@link TaskList}.
     * <p>
     * It checks for duplicate tasks, adds the task to the list, saves the updated
     * task list, and returns a message indicating success or failure.
     *
     * @param tasks   The {@link TaskList} to which the new task will be added.
     * @param ui      The {@link Ui} used to generate user-facing messages.
     * @param storage The {@link Storage} used to persist tasks.
     * @return A string message indicating the result of adding the task,
     *         including error messages for duplicates or storage failure.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(description);

        if (tasks.hasDuplicate(task)) {
            return ui.showError("This task already exists...Duplicate not added...");
        }

        tasks.add(task);

        try {
            storage.save(tasks.getAllTasks());
        } catch (IOException e) {
            return ui.showError("Failed to save task: " + e.getMessage());
        }

        return ui.showAdded(task, tasks.size());
    }
}
