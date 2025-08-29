import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileString() {
        DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + super.toFileString()
                + " | " + from.format(saveFormat)
                + " | " + to.format(saveFormat);
    }

    @Override
    public String toString() {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        return "[E]" + super.toString()
                + " (from: " + from.format(displayFormat)
                + " to: " + to.format(displayFormat) + ")";
    }
}
