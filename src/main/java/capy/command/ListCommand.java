package capy.command;

import capy.task.TaskList;
import capy.Ui;
import capy.Storage;

/**
 * Represents a command to display all tasks in the {@link TaskList}.
 * <p>
 * When executed, this command retrieves the current list of tasks
 * and returns a formatted string representation via {@link Ui}.
 */
public class ListCommand extends Command {

    /**
     * Executes this command to show all tasks in the provided {@link TaskList}.
     * <p>
     * This method does not modify the task list or storage.
     *
     * @param tasks   The {@link TaskList} containing tasks to display.
     * @param ui      The {@link Ui} used to generate the formatted task list.
     * @param storage The {@link Storage} (unused in this command).
     * @return A string containing the formatted list of tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}
