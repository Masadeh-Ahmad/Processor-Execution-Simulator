package program;

public class Processor {
    private final int id;
    private Task task;

    public Processor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task newTask, Clock clock) {
        task = newTask;
        task.assignTask(clock);
    }

    public boolean isAvailable() {
        return task == null;
    }

    public void clearTask() {
        task = null;
    }
}