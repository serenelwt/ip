package capy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser is responsible for parsing user input commands and extracting details.
 */
public class DateTimeParser {
    private static final DateTimeFormatter FILE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses a string representation of a date and time into a {@link LocalDateTime} object.
     *
     * @param input The date and time string in the format "yyyy-MM-dd HHmm".
     * @return A {@link LocalDateTime} object representing the input string.
     * @throws CapyException If the input string is not in the expected format.
     */
    public static LocalDateTime parseDateTime(String input) throws CapyException {
        try {
            return LocalDateTime.parse(input, FILE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new CapyException("OOPS! Invalid date/time format. Use yyyy-MM-dd HHmm");
        }
    }
}
