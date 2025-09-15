package capy.command;

import capy.task.TaskList;
import capy.Ui;
import capy.Storage;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showBye();
    }
}
