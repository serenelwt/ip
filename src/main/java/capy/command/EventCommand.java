package capy.command;

import capy.exception.CapyException;
import capy.task.TaskList;
import capy.Ui;
import capy.Storage;
import capy.task.Task;
import capy.task.Event;
import capy.DateTimeParser;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    private final String args;

    public EventCommand(String args) {
        this.args = args;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            // Split args into description, from and to
            String[] parts = args.split("/from|/to");
            if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                throw new CapyException("OOPS!!! Event command must have a description, /from and /to time.");
            }
            String description = parts[0].trim();
            LocalDateTime from = DateTimeParser.parseDateTime(parts[1].trim());
            LocalDateTime to = DateTimeParser.parseDateTime(parts[2].trim());

            Task task = new Event(description, from, to);

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
        } catch (CapyException e) {
            return ui.showError(e.getMessage());
        }
    }
}
