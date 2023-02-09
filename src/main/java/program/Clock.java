package program;

import java.util.ArrayList;
import java.util.List;

public class Clock {
    private final int id;
    private final int time;
    private final List<Task> createdTasks = new ArrayList<>();
    private final List<Task> completedTasks = new ArrayList<>();

    public Clock(int id, int time) {
        this.id = id;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public int getTime() {
        return time;
    }

    public List<Task> getCreatedTasks() {
        return createdTasks;
    }

    public List<Task> getCompletedTasks() {
        return completedTasks;
    }

    public void addNewTask(Task task) {
        createdTasks.add(task);
    }

    public void addCompletedTask(Task task) {
        completedTasks.add(task);
    }
}
