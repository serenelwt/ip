package capy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * A Deadline has a description, a completion status,
 * and a due date/time by which it should be done.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a Deadline task.
     *
     * @param description Description of the task.
     * @param by Deadline in yyyy-mm-dd HHmm format (e.g., 2019-12-02 1800).
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.by = by;
    }

    /**
     * Converts the deadline into a string representation suitable for saving to file.
     * Format: D | done-status | description | yyyy-MM-dd HHmm
     *
     * @return A formatted string representation of the deadline for file storage.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + super.toFileString() + " | " + by.format(saveFormat);
    }

    /**
     * Returns a string representation of the deadline for display to the user.
     *
     * @return A formatted string containing type, status, description and due date.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(outputFormat) + ")";
    }
}
