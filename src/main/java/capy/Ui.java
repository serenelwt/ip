package capy;

import java.util.Scanner;

/**
 * Ui handles all interactions with the user.
 * It prints messages, reads user input, and displays task updates.
 */
public class Ui {
    private Scanner sc;

    /**
     * Creates a new Ui object and initializes the input scanner.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /** Prints the welcome message */
    public void showWelcome() {
        showLine();
        System.out.println(" Hello! I'm Capy! Nice to meet you!");
        System.out.println(" What can I do for you?");
        showLine();
    }

    /** Prints a line divider */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads the next user command from standard input.
     *
     * @return The string entered by the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a message indicating that a task has been added.
     *
     * @param task The task that was added.
     * @param size The current total number of tasks in the list.
     */
    public void showAdded(Task task, int size) {
        showLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        showLine();
    }

    /**
     * Prints a message indicating that a task has been removed.
     *
     * @param task The task that was removed.
     * @param size The current total number of tasks in the list.
     */
    public void showRemoved(Task task, int size) {
        showLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        showLine();
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked done.
     */
    public void showMark(Task task) {
        showLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        showLine();
    }

    /**
     * Prints a message indicating that a task has been unmarked as done.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmark(Task task) {
        showLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        showLine();
    }

    /**
     * Prints an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        showLine();
        System.out.println(" " + message);
        showLine();
    }

    /** Prints the goodbye message */
    public void showBye() {
        showLine();
        System.out.println(" Bye! Hope to see you again soon!");
        showLine();
    }
}
