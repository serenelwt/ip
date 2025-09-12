package capy.command;

import capy.TaskList;
import capy.Ui;
import capy.Storage;
import capy.task.Task;
import capy.task.Todo;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Todo(description);
        tasks.add(task);
        storage.save(tasks.getAllTasks());
        return ui.showAdded(task, tasks.size());
    }
}
