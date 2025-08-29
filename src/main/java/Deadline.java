import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a deadline task with a description and a due date.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a Deadline task.
     *
     * @param description Description of the task.
     * @param by Deadline in yyyy-mm-dd HHmm format (e.g., 2019-12-02 1800).
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.by = LocalDateTime.parse(by, formatter);
    }

    @Override
    public String toFileString() {
        DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + super.toFileString() + " | " + by.format(saveFormat);
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return "[D]" + super.toString() + " (by: " + by.format(outputFormat) + ")";
    }
}
