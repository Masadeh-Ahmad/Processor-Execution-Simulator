package program;
import java.util.*;

public class Scheduler {
    PriorityQueue<Task> taskQueue;
    List<Processor> processors;

    public Scheduler(List<Processor> processors) {
        this.taskQueue = new PriorityQueue<>();
        this.processors = processors;
    }
    public void addTask(Task task){
        taskQueue.add(task);
    }
    // Schedule the tasks to the processors
    public void ScheduleTasksToProcessors(Clock clock){
        for (Processor processor : processors) {
            if (processor.isAvailable() && !taskQueue.isEmpty()) {
                Task task = taskQueue.poll();
                processor.setTask(task,clock);
                System.out.println("Task " + task.getId() + " assigned to Processor " + processor.getId());
            }
        }
    }
}