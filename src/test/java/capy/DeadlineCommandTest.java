package capy.command;

import capy.Storage;
import capy.Ui;
import capy.task.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new Ui(); // or a mock if UI prints to console
        storage = new Storage("test-tasks.txt"); // optional test file
    }

    @Test
    void execute_validInput_addsTask() {
        DeadlineCommand cmd = new DeadlineCommand("Submit assignment /by 2025-09-01 2359");
        String result = cmd.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
    }

    @Test
    void execute_missingBy_returnsError() {
        DeadlineCommand cmd = new DeadlineCommand("Submit assignment");
        String result = cmd.execute(tasks, ui, storage);

        assertEquals(0, tasks.size());
    }

    @Test
    void execute_duplicateTask_returnsError() {
        DeadlineCommand cmd1 = new DeadlineCommand("Submit assignment /by 2025-09-01 2359");
        DeadlineCommand cmd2 = new DeadlineCommand("Submit assignment /by 2025-09-01 2359");

        cmd1.execute(tasks, ui, storage);
        String result = cmd2.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertTrue(result.contains("Duplicate"));
    }
}
