package capy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser is responsible for parsing user input commands and extracting details.
 */
public class Parser {
    private static final DateTimeFormatter FILE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /** Parses string to LocalDateTime */
    public static LocalDateTime parseDateTime(String input) throws CapyException {
        try {
            return LocalDateTime.parse(input, FILE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new CapyException("OOPS! Invalid date/time format. Use yyyy-MM-dd HHmm");
        }
    }
}
