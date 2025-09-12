package capy.task;

/**
 * Represents a task of type <i>Todo</i>, which is a task without any specific
 * deadline or event time. A {@code Todo} stores only a description.
 *
 * <p>Example: {@code new Todo("read book")}</p>
 */
public class Todo extends Task {

    /**
     * Creates a new {@code Todo} task with the given description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of this task formatted for file storage.
     * The string is prefixed with {@code "T | "} to indicate that it is a Todo.
     *
     * @return A string suitable for saving this task in a file.
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }

    /**
     * Returns a string representation of this task for display to the user.
     * The string is prefixed with {@code "[T]"} to indicate that it is a Todo.
     *
     * @return A human-readable string representation of this task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
