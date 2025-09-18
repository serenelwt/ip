package capy.command;

import capy.Storage;
import capy.Ui;
import capy.exception.CapyException;
import capy.task.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventCommandTest {

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
        EventCommand cmd = new EventCommand("Team Meeting /from 2025-09-18 1000 /to 2025-09-18 1200");
        String output = cmd.execute(tasks, ui, storage);
        assertEquals(1, tasks.size());
    }

    @Test
    void execute_startEqualsEnd_throwsCapyException() {
        EventCommand cmd = new EventCommand("Team Meeting /from 2025-09-18 1000 /to 2025-09-18 1000");
        String result = cmd.execute(tasks, ui, storage);
        assertTrue(result.contains("Start time must be before end time"));
        assertEquals(0, tasks.size());
    }

    @Test
    void execute_startAfterEnd_throwsCapyException() {
        EventCommand cmd = new EventCommand("Team Meeting /from 2025-09-18 1200 /to 2025-09-18 1000");
        String result = cmd.execute(tasks, ui, storage);
        assertTrue(result.contains("Start time must be before end time"));
        assertEquals(0, tasks.size());
    }

    @Test
    void execute_missingFrom_throwsCapyException() {
        EventCommand cmd = new EventCommand("Team Meeting /to 2025-09-18 1200");
        String result = cmd.execute(tasks, ui, storage);
        assertTrue(result.contains("must have a description, /from and /to"));
    }

    @Test
    void execute_missingTo_throwsCapyException() {
        EventCommand cmd = new EventCommand("Team Meeting /from 2025-09-18 1000");
        String result = cmd.execute(tasks, ui, storage);
        assertTrue(result.contains("must have a description, /from and /to"));
    }

    @Test
    void execute_missingDescription_throwsCapyException() {
        EventCommand cmd = new EventCommand("/from 2025-09-18 1000 /to 2025-09-18 1200");
        String result = cmd.execute(tasks, ui, storage);
        assertTrue(result.contains("must have a description, /from and /to"));
    }

    @Test
    void execute_duplicateTask_returnsErrorMessage() {
        EventCommand cmd1 = new EventCommand("Team Meeting /from 2025-09-18 1000 /to 2025-09-18 1200");
        EventCommand cmd2 = new EventCommand("Team Meeting /from 2025-09-18 1000 /to 2025-09-18 1200");
        cmd1.execute(tasks, ui, storage);
        String result = cmd2.execute(tasks, ui, storage);
        assertTrue(result.contains("Duplicate not added"));
        assertEquals(1, tasks.size());
    }
}
