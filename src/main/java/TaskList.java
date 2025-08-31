import java.util.ArrayList;
import java.util.List;

/**
 * TaskList stores tasks and provides operations such as add, delete, mark, unmark.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) {
        return tasks.remove(index);
    }

    public Task mark(int index) {
        Task t =  tasks.get(index);
        t.markDone();
        return t;
    }

    public Task unmark(int index) {
        Task t =  tasks.get(index);
        t.markNotDone();
        return t;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }
}
