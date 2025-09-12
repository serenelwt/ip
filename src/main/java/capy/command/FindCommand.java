package capy.command;

import capy.TaskList;
import capy.Ui;
import capy.Storage;
import capy.task.Task;
import java.util.List;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.findTasks(keyword);
        return ui.showFoundTasks(matchingTasks);
    }
}
