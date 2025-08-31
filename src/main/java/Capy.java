import java.time.LocalDateTime;

/**
 * Capy is a simple command-line chatbot that manages a task list.
 * Refactored to use OOP: Ui, Storage, TaskList, Parser.
 */
public class Capy {
    private static final String DATA_FOLDER = "./data";
    private static final String DATA_FILE = DATA_FOLDER + "/capy.txt";

    private Ui ui;
    private Storage storage;
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
                    ui.showLine();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.getAllTasks().get(i));
                    }
                    ui.showLine();

                } else if (input.startsWith("todo")) {
                    handleTodo(input);
                }
            }
        }
    }

    /** Handles todo command */
    private void handleTodo(String input) throws CapyException {
        if (input.trim().equals("todo")) {
            throw new CapyException("OOPS! The description of a todo cannot be empty!");
        }
        String description = input.substring(5).trim();
        Task task = new Task(description);
        tasks.add(task);
        ui.showAdded(task, tasks.size());
    }
}
        while (true) {
            String input = sc.nextLine();

            try {
                if (input.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;

                } else if (input.equals("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println("____________________________________________________________");

                } else if (input.startsWith("todo")) {
                    if (input.trim().equals("todo")) {
                        throw new CapyException("OOPS! The description of the todo cannot be empty!! Remember to fill up the todo description!!");
                    }
                    String description = input.substring(5).trim();
                    tasks.add(new Todo(description));
                    printAdded(tasks.get(tasks.size() - 1), tasks.size());

                } else if (input.startsWith("deadline")) {
                    String[] parts = input.substring(9).split("/by", 2);
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        throw new CapyException("OOPS!!! Deadline command must have a description and /by date/time. Fill them up if you haven't done so!!");
                    }
                    String description = parts[0].trim();
                    LocalDateTime by = parseDateTime(parts[1].trim());
                    tasks.add(new Deadline(description, by));
                    printAdded(tasks.get(tasks.size() - 1), tasks.size());

                } else if (input.startsWith("event")) {
                    String[] parts = input.substring(6).split("/from|/to");
                    if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                        throw new CapyException("OOPS!!! Event command must have a description, /from and /to time. Fill them up if you haven't done so!!");
                    }
                    String description = parts[0].trim();
                    LocalDateTime from = parseDateTime(parts[1].trim());
                    LocalDateTime to = parseDateTime(parts[2].trim());
                    tasks.add(new Event(description, from, to));
                    printAdded(tasks.get(tasks.size() - 1), tasks.size());

                } else if (input.startsWith("mark ")) {
                    int taskNum = Integer.parseInt(input.substring(5));
                    if (taskNum < 1 || taskNum > tasks.size()) {
                        throw new CapyException("OOPS!!! Task number out of range. Please enter again!!");
                    }
                    tasks.get(taskNum - 1).markDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks.get(taskNum - 1));
                    System.out.println("____________________________________________________________");

                } else if (input.startsWith("unmark ")) {
                    int taskNum = Integer.parseInt(input.substring(7));
                    if (taskNum < 1 || taskNum > tasks.size()) {
                        throw new CapyException("OOPS!!! Task number out of range. Please enter again!!");
                    }
                    tasks.get(taskNum - 1).markNotDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks.get(taskNum - 1));
                    System.out.println("____________________________________________________________");

                } else if (input.startsWith("delete ")) {
                    int taskNum = Integer.parseInt(input.substring(7)) - 1;
                    if (taskNum < 0 || taskNum >= tasks.size()) {
                        throw new CapyException("OOPS!!! Task number out of range.");
                    }
                    Task removed = tasks.remove(taskNum);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removed);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");

                } else {
                    throw new CapyException("OOPS!!! Capy is sorry, but Capy don't know what that means :-(");
                }

            } catch (CapyException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (NumberFormatException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! That doesn't look like a valid number.");
                System.out.println("____________________________________________________________");
            }

            saveTasks(tasks);

        }
        sc.close();
    }


    /**
     * Prints a message after a task has been added.
     *
     * @param task  The task that was added.
     * @param count The total number of tasks.
     */
    private static void printAdded(Task task, int count) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + count + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Loads tasks from disk into the task list.
     *
     * @param tasks The list to populate with tasks.
     */
    private static void loadTasks(List<Task> tasks) {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task task = null;

                switch (type) {
                    case "T":
                        task = new Todo(description);
                        break;
                    case "D":
                        LocalDateTime by = parseDateTime(parts[3]);
                        task = new Deadline(description, by);
                        break;
                    case "E":
                        String[] times = parts[3].split("-");
                        LocalDateTime from = parseDateTime(times[0]);
                        LocalDateTime to = parseDateTime(times[1]);
                        task = new Event(description, from, to);
                        break;
                }

                if (task != null && isDone) task.markDone();
                if (task != null) tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Data file is corrupted or invalid.");
        }
    }

    /**
     * Saves tasks to disk.
     *
     * @param tasks The list of tasks to save.
     */
    private static void saveTasks(List<Task> tasks) {
        File folder = new File(DATA_FOLDER);
        if (!folder.exists()) folder.mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Converts a string to LocalDateTime.
     *
     * @param input The string in format "yyyy-MM-dd HHmm".
     * @return Parsed LocalDateTime object.
     * @throws CapyException If parsing fails.
     */
    private static LocalDateTime parseDateTime(String input) throws CapyException {
        try {
            return LocalDateTime.parse(input, FILE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new CapyException("OOPS! Invalid date/time format. Use yyyy-MM-dd HHmm");
        }
    }
}
