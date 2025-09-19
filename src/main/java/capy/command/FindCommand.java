package capy.command;

import capy.task.TaskList;
import capy.Ui;
import capy.Storage;
import capy.task.Task;
import java.util.List;

/**
 * Represents a command to find tasks in the {@link TaskList} that match a given keyword.
 * <p>
 * When executed, this command searches for tasks whose descriptions contain the keyword
 * and returns a formatted list of matching tasks via {@link Ui}.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes this command to find tasks matching the keyword in the provided {@link TaskList}.
     * <p>
     * The matching tasks are collected and formatted using {@link Ui#showFoundTasks(List)}.
     *
     * @param tasks   The {@link TaskList} in which to search for tasks.
     * @param ui      The {@link Ui} used to generate user-facing messages.
     * @param storage The {@link Storage} (unused in this command).
     * @return A string message listing the matching tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.findTasks(keyword);
        return ui.showFoundTasks(matchingTasks);
    }
}
