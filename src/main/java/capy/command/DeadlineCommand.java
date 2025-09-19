package capy.command;

import capy.exception.CapyException;
import capy.task.TaskList;
import capy.Ui;
import capy.Storage;
import capy.task.Task;
import capy.task.Deadline;
import capy.DateTimeParser;

import java.time.LocalDateTime;

/**
 * Represents a command to add a {@link Deadline} task to the {@link TaskList}.
 * <p>
 * This command parses a full description in the format:
 * <pre>description /by yyyy-MM-dd HHmm</pre>
 * and creates a {@link Deadline} task with the specified description and deadline.
 * It also checks for duplicate tasks and saves the updated task list to storage.
 */
public class DeadlineCommand extends Command {
    private final String fullDescription;

    /**
     * Constructs a {@code DeadlineCommand} with the given full description.
     *
     * @param fullDescription The full description of the deadline task, including /by date/time.
     */
    public DeadlineCommand(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    /**
     * Executes this command to add a {@link Deadline} task to the provided {@link TaskList}.
     * <p>
     * It validates the input, checks for duplicates, saves the updated task list,
     * and returns a message indicating success or failure.
     *
     * @param tasks   The {@link TaskList} to which the new task will be added.
     * @param ui      The {@link Ui} instance used to generate user-facing messages.
     * @param storage The {@link Storage} used to persist tasks.
     * @return A string message indicating the result of the command execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (fullDescription.isBlank()) {
            return ui.showError("The description of a deadline cannot be empty...");
        }

        String[] parts = fullDescription.split("/by", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            return ui.showError("Deadline must have a description and /by date/time...");
        }

        String description = parts[0].trim();
        LocalDateTime by;
        try {
            by = DateTimeParser.parseDateTime(parts[1].trim());
        } catch (CapyException e) {
            return ui.showError("Invalid date/time format...Use yyyy-MM-dd HHmm");
        }

        Task task = new Deadline(description, by);

        if (tasks.hasDuplicate(task)) {
            return ui.showError("This task already exists...Duplicate not added...");
        }

        tasks.add(task);

        try {
            storage.save(tasks.getAllTasks());
        } catch (Exception e) {
            return ui.showError("Failed to save task: " + e.getMessage());
        }

        return ui.showAdded(task, tasks.size());
    }
}
