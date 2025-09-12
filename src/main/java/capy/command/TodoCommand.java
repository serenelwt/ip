package capy.command;

import capy.CapyException;
import capy.TaskList;
import capy.Ui;
import capy.Storage;
import capy.task.Task;
import capy.task.Todo;

import java.io.IOException;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(description);

        if (tasks.hasDuplicate(task)) {
            return ui.showError("This task already exists! Duplicate not added.");
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
