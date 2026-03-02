import entity.TaskManager;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        System.out.println("--------WELCOME TO TASK-TRACKER--------");
        System.out.println("=== System starting (type 'quit' to finish) ===");
        TaskManager taskManager = new TaskManager();

        while (running) {

            String input = sc.nextLine().trim();
            System.out.println(input);
            String[] command = input.split(" ");
            String operation = command[0];

            if (input.isEmpty()) {
                continue;
            }
            switch (operation) {
                case "add":
                    command = input.split(" ", 2);
                    if (command.length > 1) {
                        taskManager.addTask(command[1]);
                        System.out.println("Added: " + command[1]);
                    } else {
                        System.out.println("Error: Please provide a task description.");
                    }
                    break;

                case "update":
                    command = input.split(" ", 3);
                    if (command.length > 2) {
                        taskManager.updateTask(command[1], command[2]);
                    } else {
                        System.out.println("Error: Usage is 'update <id> <description>'.");
                    }
                    break;

                case "delete":
                    if (command.length > 1) {
                        taskManager.deleteTask(command[1]);
                    } else {
                        System.out.println("Error: Please provide a task ID.");
                    }
                    break;

                case "mark-in-progress":
                    if (command.length > 1) {
                        taskManager.updateStatus(command[1], "in-progress");
                    } else {
                        System.out.println("Error: Please provide a task ID.");
                    }
                    break;

                case "mark-done":
                    if (command.length > 1) {
                        taskManager.updateStatus(command[1], "done");
                    } else {
                        System.out.println("Error: Please provide a task ID.");
                    }
                    break;

                case "list":
                    taskManager.showAllTasks();
                    break;

                case "list-done":
                    taskManager.showDoneTasks();
                    break;

                case "list-to-do":
                    taskManager.showToDoTasks();
                    break;

                case "list-in-progress":
                    taskManager.showInProgressTasks();
                    break;

                case "help":
                    System.out.println("--- Available Commands ---");
                    System.out.println("  add <description>       - Adds a new task");
                    System.out.println("  update <id> <desc>      - Updates a task's description");
                    System.out.println("  delete <id>             - Removes a task by ID");
                    System.out.println("  mark-in-progress <id>   - Marks a task as 'in progress'");
                    System.out.println("  mark-done <id>          - Marks a task as 'done'");
                    System.out.println("  list                    - Lists all tasks");
                    System.out.println("  list-done               - Lists only done tasks");
                    System.out.println("  list-to-do              - Lists tasks to do");
                    System.out.println("  list-in-progress        - Lists tasks in progress");
                    System.out.println("  help                    - Shows this help menu");
                    System.out.println("  quit                    - Exits the program");
                    System.out.println("--------------------------");
                    break;

                case "quit":
                    System.out.println("Finishing program...");
                    running = false;
                    break;

                default:
                    System.out.println("Unknown command: " + operation + ". Type 'help' to see available commands.");
                    break;
            }

        }
        sc.close();
    }
}
