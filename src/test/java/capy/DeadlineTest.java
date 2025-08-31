package capy;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDeadlineCreationAndToString() throws Exception {
        // setup test input
        String description = "Submit assignment";
        LocalDateTime by = LocalDateTime.of(2025, 9, 1, 23, 59);

        Deadline d = new Deadline(description, by);

        // test 1: toString() output
        String expectedDisplay = "[D][ ] Submit assignment (by: Sep 1 2025, 11:59 pm)";
        assertEquals(expectedDisplay, d.toString(), "toString() output mismatch");

        // test 2: toFileString() output
        DateTimeFormatter saveFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String expectedFile = "D | 0 | Submit assignment | " + by.format(saveFormat);
        assertEquals(expectedFile, d.toFileString(), "toFileString() output mismatch");
    }
}
