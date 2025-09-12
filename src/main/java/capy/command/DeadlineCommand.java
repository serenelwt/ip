package capy.command;

import capy.CapyException;
import capy.TaskList;
import capy.Ui;
import capy.Storage;
import capy.task.Task;
import capy.task.Deadline;
import capy.DateTimeParser;

import java.time.LocalDateTime;

/**
 * Adds a Deadline task to the TaskList.
 */
public class DeadlineCommand extends Command {
    private final String fullDescription;

    public DeadlineCommand(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (fullDescription.isBlank()) {
            return ui.showError("OOPS! The description of a deadline cannot be empty!");
        }

        String[] parts = fullDescription.split("/by", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            return ui.showError("OOPS! Deadline must have a description and /by date/time.");
        }

        String description = parts[0].trim();
        LocalDateTime by;
        try {
            by = DateTimeParser.parseDateTime(parts[1].trim());
        } catch (CapyException e) {
            return ui.showError("OOPS! Invalid date/time format. Use yyyy-MM-dd HHmm");
        }

        Task task = new Deadline(description, by);

        if (tasks.hasDuplicate(task)) {
            return ui.showError("This task already exists! Duplicate not added.");
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
