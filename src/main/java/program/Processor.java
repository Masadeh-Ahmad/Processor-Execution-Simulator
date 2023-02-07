package program;

public class Processor {
    private int Id;
    private Task task;

    public Processor(int Id) {
        this.Id = Id;
    }

    public int getId() {
        return Id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task ,Clock clock) {
        this.task = task;
        task.assignTask(clock);
    }
    public boolean isAvailable(){
        return task == null;
    }
    public void clearTask(){
        task = null;
    }
}
