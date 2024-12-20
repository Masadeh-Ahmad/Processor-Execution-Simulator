package program;
import java.util.*;

public class Scheduler {
    private final PriorityQueue<Task> taskQueue;
    private final List<Processor> processors;

    public Scheduler(List<Processor> processors) {
        taskQueue = new PriorityQueue<>();
        this.processors = processors;
    }
    public Scheduler(List<Processor> processors,Comparator<Task> co) {
        taskQueue = new PriorityQueue<>(co);
        this.processors = processors;
    }

    public void addTask(Task task) {
        taskQueue.offer(task);
    }

    public void ScheduleTasksToProcessors(Clock clock) {
        for (Processor processor : processors) {
            if (processor.isAvailable() && !taskQueue.isEmpty()) {
                Task task = taskQueue.poll();
                processor.setTask(task, clock);
                System.out.println("Task " + task.getId() + " assigned to Processor " + processor.getId());
            }
        }
    }
}