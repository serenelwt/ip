import java.util.ArrayList;
import java.util.Scanner;

public class Capy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Capy");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

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
                    tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
                    printAdded(tasks.get(tasks.size() - 1), tasks.size());

                } else if (input.startsWith("event")) {
                    String[] parts = input.substring(6).split("/from|/to");
                    if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                        throw new CapyException("OOPS!!! Event command must have a description, /from and /to time. Fill them up if you haven't done so!!");
                    }
                    tasks.add(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                    printAdded(tasks.get(tasks.size() - 1), tasks.size());

                } else if (input.startsWith("mark ")) {
                    int taskNum = Integer.parseInt(input.substring(5));
                    if (taskNum < 1 || taskNum > tasks.size()) {
                        throw new CapyException("OOPS!!! Task number out of range. Please enter again!!");
                    }
                    tasks.get(taskNum).markDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks.get(taskNum));
                    System.out.println("____________________________________________________________");

                } else if (input.startsWith("unmark ")) {
                    int taskNum = Integer.parseInt(input.substring(7));
                    if (taskNum < 1 || taskNum > tasks.size()) {
                        throw new CapyException("OOPS!!! Task number out of range. Please enter again!!");
                    }
                    tasks.get(taskNum).markNotDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks.get(taskNum));
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
        }
        sc.close();
    }

    private static void printAdded(Task task, int count) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + count + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

}
