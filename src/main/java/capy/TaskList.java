package capy;

import capy.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of Task objects.
 * Provides operations to add, delete, mark, and unmark tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with the given tasks.
     *
     * @param tasks The list of tasks to initialize the task list with.
     */
    public TaskList(List<Task> tasks) {
        assert tasks != null : "Task list must not be null";
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        assert task != null : "Task to add must not be null";
        tasks.add(task);
    }

    /**
     * Deletes a task at the specified index from the task list.
     *
     * @param index The index of the task to be deleted (0-based).
     * @return The task that was removed.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to be marked (0-based).
     * @return The task that was marked as done.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task mark(int index) {
        Task t =  tasks.get(index);
        t.markDone();
        return t;
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The index of the task to be unmarked (0-based).
     * @return The task that was marked as not done.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task unmark(int index) {
        Task t =  tasks.get(index);
        t.markNotDone();
        return t;
    }

    /**
     * Returns all tasks in the list.
     *
     * @return A list of all tasks.
     */
    public List<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
