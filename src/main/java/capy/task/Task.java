package capy.task;

/**
 * Represents a task.
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Creates a Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null : "Task description must not be null";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Converts the task into a string representation for saving to file.
     * Subclasses (Todo, Deadline, Event) will extend this.
     *
     * @return File string representation of the task.
     */
    public String toFileString() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if done, " " if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Converts the task into a string representation for display.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
