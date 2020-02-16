package Duke;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static final String GREETING = "Hello! I'm Duke.Duke\n" + "What can I do for you?";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        System.out.println(GREETING);

        ArrayList<Task> taskArrayList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int exit = 0;
        int taskListSize = 0;

        while (exit == 0) {
            String userInput = scanner.nextLine();
            String[] tokenizedInputs = userInput.split(" ", 2);
            String instruction = tokenizedInputs[0];

            switch(instruction) {
            case "bye":
                System.out.println(GOODBYE);
                exit = 1;
                break;
            case "list":
                printTasks(taskArrayList, taskListSize);
                break;
            case "done":
                if (checkEmptyDescription(tokenizedInputs, instruction)) break;
                int taskDone = Integer.valueOf(tokenizedInputs[1]) - 1;
                //to do more error handling here
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(taskArrayList.get(taskDone).markAsDone());
                break;
            case "delete":
                if (checkEmptyDescription(tokenizedInputs, instruction)) break;
                int taskToDelete = Integer.valueOf(tokenizedInputs[1]) - 1;
                //to do more error handling here
                taskListSize--;
                respondDeleteSuccess(taskListSize, "Noted. I've removed this task:\n", taskArrayList.get(taskToDelete));
                taskArrayList.remove(taskToDelete);
                break;
            case "todo":
                if (checkEmptyDescription(tokenizedInputs, instruction)) break;
                String toDo = tokenizedInputs[1];
                taskArrayList.add(new ToDo(tokenizedInputs[1]));
                respondAddedSuccess(taskListSize, taskArrayList.get(taskListSize));
                taskListSize++;
                break;
            case "deadline":
                if (checkEmptyDescription(tokenizedInputs, instruction)) break;
                String[] deadlineInfo = tokenizedInputs[1].split(" /by ");
                if (checkDateEntered(deadlineInfo)) break;
                taskArrayList.add(new Deadline(deadlineInfo[0], deadlineInfo[1]));
                respondAddedSuccess(taskListSize, taskArrayList.get(taskListSize));
                taskListSize++;
                break;
            case "event":
                if (checkEmptyDescription(tokenizedInputs, instruction)) break;
                String[] eventInfo = tokenizedInputs[1].split(" /at ");
                if (checkDateEntered(eventInfo)) break;
                taskArrayList.add(new Event(eventInfo[0], eventInfo[1]));
                respondAddedSuccess(taskListSize, taskArrayList.get(taskListSize));
                taskListSize++;
                break;
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
            }
        }
    }

    private static void respondDeleteSuccess(int taskListSize, String s, Task task) {
        System.out.println("____________________________________________________________\n" +
                s + task.toString());
        System.out.print("Now you have ");
        System.out.print(taskListSize);
        if (taskListSize == 1) {
            System.out.print(" task in the list.\n" +
                    "____________________________________________________________\n");
        } else {
            System.out.print(" tasks in the list.\n" +
                    "____________________________________________________________\n");
        }
    }

    private static void printTasks(ArrayList<Task> taskArrayList, int taskListSize) {
        for (int i = 1; i <= taskListSize; i++) {
            System.out.print(i);
            System.out.println("." + taskArrayList.get(i-1).toString());
        }
    }

    private static void respondAddedSuccess(int taskListSize, Task task) {
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n" + task.toString());
        System.out.print("Now you have ");
        System.out.print(taskListSize + 1);
        if (taskListSize <= 0) {
            System.out.print(" task in the list.\n" +
                    "____________________________________________________________\n");
        } else {
            System.out.print(" tasks in the list.\n" +
                    "____________________________________________________________\n");
        }
    }

    private static boolean checkDateEntered(String[] information) {
        if (information.length == 1) {
            System.out.println("☹ OOPS!!! You did not enter a date");
            return true;
        }
        return false;
    }

    private static boolean checkEmptyDescription(String[] tokens, String instruction) {
        if (tokens.length == 1) {
            System.out.println("☹ OOPS!!! The description of a "+ instruction +" cannot be empty.");
            return true;
        }
        return false;
    }
}
