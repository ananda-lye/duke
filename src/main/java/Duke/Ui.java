package Duke;

import java.util.ArrayList;

public class Ui {

    public static void displayMatchingTasks(TaskList taskArray, ArrayList<Task> lastShownList, int lastShownListSize, String keyword) {
        for (Task i : taskArray.tasks) {
            if (i.description.contains(keyword)) {
                lastShownList.add(i);
                lastShownListSize++;
            }
        }
        if (lastShownListSize > 0) {
            System.out.println("____________________________________________________________\n" +
                    "     Here are the matching tasks in your list:\n");
            Ui.printFindResults(lastShownList, lastShownListSize);
            System.out.println("____________________________________________________________\n");
        } else {
            System.out.println("There are no tasks matching that description.\n");
        }

    }

    public static void respondDeleteSuccess(int taskListSize, Task task) {

        System.out.println("____________________________________________________________\n" +
                "Noted. I've removed this task:\n" + task.toString());
        System.out.print("Now you have ");
        System.out.print(taskListSize-1);
        if (taskListSize == 1) {
            System.out.print(" task in the list.\n" +
                    "____________________________________________________________\n");
        } else {
            System.out.print(" tasks in the list.\n" +
                    "____________________________________________________________\n");
        }
    }

    public static void printTasks(TaskList taskArray) {
        for (int i = 1; i <= taskArray.size; i++) {
            System.out.print(i);
            System.out.println("." + taskArray.get(i - 1).toString());
        }
    }

    public static void printFindResults (ArrayList <Task> results, int size) {
        for (int i = 1; i <= size; i++) {
            System.out.print(i);
            System.out.println("." + results.get(i - 1).toString());
        }
    }

    public static void respondAddedSuccess(int taskListSize, Task task) {
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n" + task.toString());
        System.out.print("Now you have ");
        System.out.print(taskListSize + 1);
        if (taskListSize <= 0) {
            System.out.print(" task in the list.\n" +
                    "____________________________________________________________");
        } else {
            System.out.print(" tasks in the list.\n" +
                    "____________________________________________________________\n");
        }
    }
}