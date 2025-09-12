package capy;

import capy.task.Deadline;
import capy.task.Event;
import capy.task.Task;
import capy.task.Todo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Capy is a simple command-line chatbot that manages a task list.
 * Refactored to use OOP: Ui, Storage, TaskList, Parser.
 */
public class Capy {
    private static final String DATA_FOLDER = "./data";
    private static final String DATA_FILE = DATA_FOLDER + "/capy.txt";

    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    public Capy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError("Error loading tasks from file. Starting with empty task list.");
            tasks = new TaskList();
        }
    }

    public Capy() {
        this(DATA_FILE);
    }

    /**
     * Main loop to run the chatbot
     */
    public void run() {
        ui.showWelcome();

        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();

                if (input.equals("bye")) {
                    isExit = true;
                    ui.showBye();

                } else if (input.equals("list")) {
                    ui.showTaskList(tasks);

                } else if (input.startsWith("todo")) {
                    handleTodo(input);

                } else if (input.startsWith("deadline")) {
                    handleDeadline(input);

                } else if (input.startsWith("event")) {
                    handleEvent(input);

                } else if (input.startsWith("mark")) {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    tasks.mark(index);
                    ui.showMark(tasks.getAllTasks().get(index));

                } else if (input.startsWith("unmark")) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    tasks.unmark(index);
                    ui.showUnmark(tasks.getAllTasks().get(index));

                } else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    Task removed = tasks.delete(index);
                    ui.showRemoved(removed, tasks.size());

                } else if (input.startsWith("find")) {
                    String keyword = input.substring(5).trim();
                    List<Task> results = tasks.findTasks(keyword);
                    ui.showFoundTasks(results);

                } else {
                    throw new CapyException("OOPS!!! Capy doesn't understand that command.");
                }

                // Save tasks after every change
                storage.save(tasks.getAllTasks());
            } catch (CapyException e) {
                ui.showError(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showError("OOPS!!! That doesn't look like a valid number.");
            } catch (Exception e) {
                ui.showError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Handles todo command
     */
    private String handleTodo(String input) throws CapyException {
        if (input.trim().equals("todo")) {
            throw new CapyException("OOPS! The description of a todo cannot be empty!");
        }
        String description = input.substring(5).trim();
        Task task = new Todo(description);
        tasks.add(task);
        return ui.showAdded(task, tasks.size());
    }

    /**
     * Handles deadline command
     */
    private String handleDeadline(String input) throws CapyException {
        String[] parts = input.substring(9).split("/by", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new CapyException("OOPS!!! Deadline command must have a description and /by date/time. "
                    + "Fill them up if you haven't done so!!");
        }
        String description = parts[0].trim();
        LocalDateTime by = DateTimeParser.parseDateTime(parts[1].trim());
        Task task = new Deadline(description, by);
        tasks.add(task);
        return ui.showAdded(task, tasks.size());
    }

    /**
     * Handles event command
     */
    private String handleEvent(String input) throws CapyException {
        String[] parts = input.substring(6).split("/from|/to");
        if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new CapyException("OOPS!!! Event command must have a description, /from and /to time. "
                    + "Fill them up if you haven't done so!!");
        }
        String description = parts[0].trim();
        LocalDateTime from = DateTimeParser.parseDateTime(parts[1].trim());
        LocalDateTime to = DateTimeParser.parseDateTime(parts[2].trim());
        Task task = new Event(description, from, to);
        tasks.add(task);
        return ui.showAdded(task, tasks.size());
    }

    /**
     * Main entry point
     */
    public static void main(String[] args) {
        new Capy(DATA_FILE).run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            if (input.equals("bye")) {
                storage.save(tasks.getAllTasks());
                return ui.showBye();

            } else if (input.equals("list")) {
                storage.save(tasks.getAllTasks());
                return ui.showTaskList(tasks);

            } else if (input.startsWith("todo")) {
                storage.save(tasks.getAllTasks());
                return handleTodo(input);

            } else if (input.startsWith("deadline")) {
                storage.save(tasks.getAllTasks());
                return handleDeadline(input);

            } else if (input.startsWith("event")) {
                storage.save(tasks.getAllTasks());
                return handleEvent(input);

            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                tasks.mark(index);
                storage.save(tasks.getAllTasks());
                return ui.showMark(tasks.getAllTasks().get(index));

            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                tasks.unmark(index);
                storage.save(tasks.getAllTasks());
                return ui.showUnmark(tasks.getAllTasks().get(index));

            } else if (input.startsWith("delete")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                Task removed = tasks.delete(index);
                storage.save(tasks.getAllTasks());
                return ui.showRemoved(removed, tasks.size());

            } else if (input.startsWith("find")) {
                String keyword = input.substring(5).trim();
                List<Task> results = tasks.findTasks(keyword);
                storage.save(tasks.getAllTasks());
                return ui.showFoundTasks(results);

            } else {
                throw new CapyException("OOPS!!! Capy doesn't understand that command.");
            }

        } catch (CapyException e) {
            return ui.showError(e.getMessage());
        } catch (NumberFormatException e) {
            return ui.showError("OOPS!!! That doesn't look like a valid number.");
        } catch (Exception e) {
            return ui.showError("An unexpected error occurred: " + e.getMessage());
        }
    }
}
