package program;
public class Task implements Comparable<Task> {
    private final int id;
    private final int creationTime;
    private final int executionTime;
    private final int priority;
    private Integer assignedCycle;

    public Task(int id, int creationTime, int executionTime, int priority) {
        this.id = id;
        this.creationTime = creationTime;
        this.executionTime = executionTime;
        this.priority = priority;
        this.assignedCycle = null;
    }

    public int getId() {
        return id;
    }

    public int getCreationTime() {
        return creationTime;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public int getPriority() {
        return priority;
    }
    public boolean isAssigned(){ return assignedCycle != null; }
    public int getAssignedCycle(){ return assignedCycle; }
    public void assignTask(Clock clock){
        assignedCycle = clock.getTime();
    }

    @Override
    public int compareTo(Task task) {
        if(this.priority < task.priority)
            return 1;
        else if (this.priority > task.priority)
            return -1;
        else if(this.executionTime < task.executionTime)
            return 1;
        else if(this.executionTime > task.executionTime)
            return -1;
        else
            return 0;
    }
}
