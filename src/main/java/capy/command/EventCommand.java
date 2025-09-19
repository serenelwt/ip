package capy.command;

import capy.exception.CapyException;
import capy.task.TaskList;
import capy.Ui;
import capy.Storage;
import capy.task.Task;
import capy.task.Event;
import capy.DateTimeParser;

import java.time.LocalDateTime;

/**
 * Represents a command to add an {@link Event} task to the {@link TaskList}.
 * <p>
 * The input should include a description, a start time, and an end time in the format:
 * <pre>description /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm</pre>
 * This command validates the input, ensures the start time is before the end time,
 * checks for duplicate tasks, and saves the updated task list to storage.
 */
public class EventCommand extends Command {
    private final String args;

    /**
     * Constructs an {@code EventCommand} with the given arguments.
     *
     * @param args The full arguments string containing the description and time ranges.
     */
    public EventCommand(String args) {
        this.args = args;
    }

    /**
     * Executes this command to add an {@link Event} task to the provided {@link TaskList}.
     * <p>
     * It parses the input into description, start time, and end time, validates them,
     * checks for duplicates, adds the task, and saves the task list.
     *
     * @param tasks   The {@link TaskList} to which the new task will be added.
     * @param ui      The {@link Ui} used to generate user-facing messages.
     * @param storage The {@link Storage} used to persist tasks.
     * @return A string message indicating the result of the command execution,
     *         including error messages for invalid input or storage failure.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            // Split args into description, from and to
            String[] parts = args.split("/from|/to");
            if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                throw new CapyException("Event command must have a description, /from and /to time...");
            }
            String description = parts[0].trim();
            LocalDateTime from = DateTimeParser.parseDateTime(parts[1].trim());
            LocalDateTime to = DateTimeParser.parseDateTime(parts[2].trim());

            if (from.isAfter(to) || from.equals(to)) {
                throw new CapyException("Start time must be before end time...");
            }

            Task task = new Event(description, from, to);

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
        } catch (CapyException e) {
            return ui.showError(e.getMessage());
        }
    }
}
