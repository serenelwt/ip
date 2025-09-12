package capy;

import capy.command.Command;
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
        assert filePath != null && !filePath.isBlank() : "File path must not be null or empty";
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

                Command command = Parser.parse(input); // Parser now returns a Command
                String output = command.execute(tasks, ui, storage); // Execute the command
                System.out.println(output);

                isExit = command.isExit(); // Command determines if program should exit

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Main entry point
     */
    public static void main(String[] args) {
        new Capy(DATA_FILE).run();
    }


    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);       // Parser returns a Command
            String response = command.execute(tasks, ui, storage); // Execute command
            return response;                               // Return response string
        } catch (CapyException e) {
            return ui.showError(e.getMessage());
        } catch (NumberFormatException e) {
            return ui.showError("OOPS!!! That doesn't look like a valid number.");
        } catch (Exception e) {
            return ui.showError("An unexpected error occurred: " + e.getMessage());
        }
    }

}
