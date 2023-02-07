package program;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Simulator {
    private final int numProcessors;
    private final int numCycles;
    private final String taskFilePath;
    private final List<Task> tasks;
    private final List<Processor> processors;
    private final Clock clock;
    private final Scheduler scheduler;

    public Simulator(int numProcessors, int numCycles, String taskFilePath) {
        this.numProcessors = numProcessors;
        this.numCycles = numCycles;
        this.taskFilePath = taskFilePath;
        this.tasks = new ArrayList<>();
        this.processors = new ArrayList<>();
        this.clock = new Clock();
        initializeProcessors();
        this.scheduler = new Scheduler(processors);
    }
    // Initialize the processors
    private void initializeProcessors() {

        for (int i = 0; i < numProcessors; i++) {
            Processor processor = new Processor(i + 1);
            processors.add(processor);
        }
    }
    public void start() throws IOException {
        // Read the task file and create the tasks
        BufferedReader reader = new BufferedReader(new FileReader(taskFilePath));
        int n = Integer.parseInt(reader.readLine());
        for(int i = 0; i < n; i++) {
            String[] line = reader.readLine().split(" ");
            int creationTime = Integer.parseInt(line[0]);
            int executionTime = Integer.parseInt(line[1]);
            int priority = Integer.parseInt(line[2]);
            Task task = new Task(i+1,creationTime, executionTime, priority);
            tasks.add(task);
        }
        reader.close();

        // Start the simulation
        System.out.println("Starting simulation...");
        while (clock.getTime() <= numCycles){
            System.out.println("In cycle "+clock.getTime()+": ");

            // Check if any task was created in this cycle
            for (Task task : tasks) {
                if (task.getCreationTime() == clock.getTime()) {
                    System.out.println("Task " + task.getId() + " created");
                    scheduler.addTask(task);
                }
            }
            // Schedule the tasks to the processors
            scheduler.ScheduleTasksToProcessors(clock);
            // Advance the clock by one cycle

            clock.tick();
            for (Processor processor : processors) {
                Task task = processor.getTask();
                if (task != null && clock.getTime() - (task.getExecutionTime() - task.getAssignedCycle()+1) == 0) {
                    System.out.println("Task " + task.getId() + " completed by Processor " + processor.getId());
                    processor.clearTask();
                }
            }
        }
        System.out.println("Simulation completed.");
    }
}