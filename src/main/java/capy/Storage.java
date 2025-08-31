package capy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Storage handles loading and saving tasks to the file system.
 */
public class Storage {
    private String filePath;
    private static final DateTimeFormatter FILE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /** Loads tasks from file. Returns empty list if file doesn't exist */
    public List<Task> load() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }

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
                        LocalDateTime by = LocalDateTime.parse(parts[3], FILE_FORMAT);
                        task = new Deadline(description, by);
                        break;
                    case "E":
                        String[] times = parts[3].split("-");
                        LocalDateTime from = LocalDateTime.parse(times[0], FILE_FORMAT);
                        LocalDateTime to = LocalDateTime.parse(times[1], FILE_FORMAT);
                        task = new Event(description, from, to);
                        break;
                }

                if (task != null && isDone) task.markDone();
                if (task != null) tasks.add(task);
            }
        }
        return tasks;
    }

    /** Saves tasks to the file */
    public void save(List<Task> tasks) throws IOException {
        File folder = new File(filePath).getParentFile();
        if (!folder.exists()) {
            folder.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        }
    }
}
