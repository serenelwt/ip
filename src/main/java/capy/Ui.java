package capy;

import capy.task.Task;
import capy.task.TaskList;

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
    public String showWelcome() {
        return "Hey buddy, Capy's here...\nWhat do you want from me today...";
    }

    /** Prints a line divider */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /** Reads the next user command */
    public String readCommand() {
        return sc.nextLine();
    }

    public String showTaskList(TaskList tasks) {
        String message = "Here are the tasks in your list...\n";
        for (int i = 0; i < tasks.size(); i++) {
            message += " " + (i + 1) + ". " + tasks.getAllTasks().get(i) +"\n";
        }
        return message;
    }

    public String showAdded(Task task, int size) {
        String message = "Got it...Capy has added this task...\n";
        message += "   " + task + "\n";
        message += "Now you have " + size + " tasks in the list...";
        return message;
    }

    /** Prints a task that was removed */
    public String showRemoved(Task task, int size) {
        String message = "Noted...Capy has removed this task...\n";
        message += "   " + task + "\n";
        message += "Now you have " + size + " tasks in the list...";
        return message;
    }

    /** Prints a task that was marked done */
    public String showMark(Task task) {
        String message = "OK...Capy has marked this task as done...\n";
        message += "   " + task;
        return message;
    }

    /** Prints a task that was unmarked */
    public String showUnmark(Task task) {
        String message = "OK...Capy has marked this task as not done yet...\n";
        message += "   " + task;
        return message;
    }

    /** Prints an error message */
    public String showError(String str) {
        return " " + str;
    }

    /** Prints the goodbye message */
    public String showBye() {
        return "Bai...Hope to never see you again...";
    }

    /**
     * Prints tasks that match the search keyword.
     *
     * @param matchingTasks List of tasks that matched the keyword.
     */
    public String showFoundTasks(List<Task> matchingTasks) {
        String message = "";
        if (matchingTasks.isEmpty()) {
            message = "Capy did not find any tasks that match your keyword...\n";
        } else {
            message = "Capy found these matching tasks in your list...\n";
            for (int i = 0; i < matchingTasks.size(); i++) {
                message += " " + (i + 1) + ". " + matchingTasks.get(i);
            }
        }
        return message;
    }
}
