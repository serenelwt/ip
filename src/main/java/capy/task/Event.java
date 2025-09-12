package capy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 * An Event has a description, a completion status,
 * and a start and end date/time.
 */
public class Event extends Task {
    protected final LocalDateTime from;
    protected final LocalDateTime to;

    /**
     * Creates an Event task.
     *
     * @param description The description of the event task.
     * @param from The starting date and time of the event.
     * @param to The ending date and time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        assert from != null : "Event start time (from) must not be null";
        assert to != null : "Event end time (to) must not be null";
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the event into a string representation suitable for saving to file.
     * Format: E | done-status | description | yyyy-MM-dd HHmm | yyyy-MM-dd HHmm
     *
     * @return A formatted string representation of the event for file storage.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + super.toFileString()
                + " | " + from.format(saveFormat)
                + " | " + to.format(saveFormat);
    }

    /**
     * Returns a string representation of the event for display to the user.
     *
     * @return A formatted string containing type, status, description,
     *         start and end times.
     */
    @Override
    public String toString() {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        return "[E]" + super.toString()
                + " (from: " + from.format(displayFormat)
                + " to: " + to.format(displayFormat) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Event)) return false;
        Event other = (Event) o;
        return description.equals(other.description)
                && from.equals(other.from)
                && to.equals(other.to);
    }
}
