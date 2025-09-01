package capy;

/**
 * Represents a custom exception used in the Capy program.
 * This is thrown when the user provides invalid input or
 * when an error occurs during program execution.
 */
public class CapyException extends Exception {

    /**
     * Creates a new {@code CapyException} with the specified detail message.
     *
     * @param message The detail message describing the cause of the exception.
     */
    public CapyException(String message) {
        super(message);
    }
}
