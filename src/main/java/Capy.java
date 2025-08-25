import java.util.Scanner;

public class Capy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Capy");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        // For Level-0, no real input handling yet. Just exit.
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("mark ")) {
                int taskNum = Integer.parseInt(input.substring(5));
                tasks[taskNum - 1].markDone();
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks[taskNum - 1]);
                System.out.println("____________________________________________________________");
            }
            else {
                tasks[taskCount] = new Task(input);
                taskCount++;

                System.out.println("____________________________________________________________");
                System.out.println(" added: " + input);
                System.out.println("____________________________________________________________");
            }

        }

        sc.close();
    }

}
