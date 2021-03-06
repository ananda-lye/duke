package Duke;

/**
 * Represents a task object that the user can mark as done when completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public String getStatusIcon() {
        return (isDone ? "O" : "X"); //return tick or X symbols
    }

    /**
     * Updates the status of a task to done.
     * @return a response string showing the updated status icon of the task
     */
    public String markAsDone () {
        this.isDone = true;
        return ("[" + this.getStatusIcon() + "] "+ this.description);
    }

    /**
     * @return a string containing the task status and description for printing
     */
    public String toString() {
        return ("["+ this.getStatusIcon() + "] " + this.description);
    }

    /**
     * @return a string of containing the task status and description for saving
     */
    public String toSaveFormat() {
        if (this.isDone == true) {
            return ("1|" + this.description+ "|");
        } else {
            return ("0|" + this.description + "|");
        }
    }

}