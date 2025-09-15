package capy.command;

import capy.task.TaskList;
import capy.Ui;
import capy.Storage;

public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The task list.
     * @param ui      The UI to interact with the user.
     * @param storage The storage to save/load tasks.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns true if this command ends the program.
     */
    public boolean isExit() {
        return false;
    }
}
