package capy.command;

import capy.task.TaskList;
import capy.Ui;
import capy.Storage;

/**
 * Represents a command to exit the application.
 * <p>
 * When executed, this command signals the application to terminate
 * by returning a goodbye message via {@link Ui}.
 */
public class ExitCommand extends Command {

    /**
     * Executes this command to exit the application.
     * <p>
     * This method does not modify the {@link TaskList} or {@link Storage}.
     *
     * @param tasks   The {@link TaskList} (unused in this command).
     * @param ui      The {@link Ui} used to generate the goodbye message.
     * @param storage The {@link Storage} (unused in this command).
     * @return A string goodbye message to indicate the application should exit.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showBye();
    }
}
