package capy;

import java.util.List;
import java.util.Scanner;

/**
 * Ui handles all interactions with the user.
 * It prints messages, reads user input, and displays task updates.
 */
public class Ui {
    private Scanner sc;

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

    /** Reads the next user command */
    public String readCommand() {
        return sc.nextLine();
    }

    public void showAdded(Task task, int size) {
        showLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        showLine();
    }

    /** Prints a task that was removed */
    public void showRemoved(Task task, int size) {
        showLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        showLine();
    }

    /** Prints a task that was marked done */
    public void showMark(Task task) {
        showLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        showLine();
    }

    /** Prints a task that was unmarked */
    public void showUnmark(Task task) {
        showLine();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        showLine();
    }

    /** Prints an error message */
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

    public void showFoundTasks(List<Task> matchingTasks) {
        showLine();
        if (matchingTasks.isEmpty()) {
            System.out.println(" No tasks found matching your keyword.");
        } else {
            System.out.println(" Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + matchingTasks.get(i));
            }
        }
        showLine();
    }
}
