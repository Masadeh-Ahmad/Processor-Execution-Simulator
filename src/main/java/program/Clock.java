package program;

import java.util.ArrayList;
import java.util.List;

public class Clock {
    private final int id;
    private final int time;
    private final List<Task> createdTasks;
    private final List<Task> completedTasks;

    public Clock(int id,int time) {
        this.time = time;
        this.id = id;
        this.createdTasks = new ArrayList<>();
        this.completedTasks = new ArrayList<>();
    }
    public int getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public List<Task> getCreatedTasks() {
        return createdTasks;
    }
    public List<Task> getCompletedTasks() {
        return completedTasks;
    }
    public void addNewTask(Task task){
        createdTasks.add(task);
    }
    public void addCompletedTask(Task task){
        completedTasks.add(task);
    }
}
