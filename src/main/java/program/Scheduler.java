package program;
import java.util.*;

public class Scheduler {
    private final PriorityQueue<Task> taskQueue = new PriorityQueue<>();
    private final List<Processor> processors;

    public Scheduler(List<Processor> processors) {
        this.processors = processors;
    }

    public void addTask(Task task) {
        taskQueue.offer(task);
    }

    public void completeTasks(Clock clock) {
        for (Processor processor : processors) {
            if (processor.isAvailable() && !taskQueue.isEmpty()) {
                Task task = taskQueue.poll();
                processor.setTask(task, clock);
                System.out.println("Task " + task.getId() + " assigned to Processor " + processor.getId());
            }
        }
    }
}