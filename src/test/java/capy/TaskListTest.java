package capy;

import capy.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskListTest {
    @Test
    public void add_taskIncreasesSizeAndStoresTask_correctly() {
        TaskList taskList = new TaskList();
        Task task1 = new Task("Write report");
        Task task2 = new Task("Submit homework");

        taskList.add(task1);
        taskList.add(task2);

        assertEquals(2, taskList.size(), "TaskList size should be 2 after adding two tasks");
    }
}
