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
    private final List<Clock> cycles;
    private final Scheduler scheduler;

    public Simulator(int numProcessors, int numCycles, String taskFilePath) {
        this.numProcessors = numProcessors;
        this.numCycles = numCycles;
        this.taskFilePath = taskFilePath;
        this.tasks = new ArrayList<>();
        this.processors = initializeProcessors();
        this.cycles = new ArrayList<>();
        this.scheduler = new Scheduler(processors);
    }

    // Initialize the processors
    private List<Processor> initializeProcessors() {
        List<Processor> processors = new ArrayList<>();
        for (int i = 0; i < numProcessors; i++) {
            processors.add(new Processor(i + 1));
        }
        return processors;
    }
    private void readTasksFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(taskFilePath))) {
            int n = Integer.parseInt(reader.readLine());
            for (int i = 0; i < n; i++) {
                String[] line = reader.readLine().split(" ");
                int creationTime = Integer.parseInt(line[0]);
                int executionTime = Integer.parseInt(line[1]);
                int priority = Integer.parseInt(line[2]);
                Task task = new Task(i + 1, creationTime, executionTime, priority);
                tasks.add(task);
            }
        }
    }
    public void start() throws IOException {
        readTasksFile();
        System.out.println("Starting simulation with " + numProcessors + " Processors, " + numCycles + " Cycles...");
        for (int i = 0; i < numCycles; i++) {
            Clock clock = new Clock(i + 1, i + 1);
            cycles.add(clock);
            System.out.println("Starting cycle " + clock.getTime() + ": ");
            checkForNewTasks(clock);
            scheduler.completeTasks(clock);
            System.out.println("Ending cycle " + clock.getTime() + ": ");
            completeTasks(clock);
            System.out.println("\n");
        }
        System.out.println("Simulation completed.");
    }


    private void checkForNewTasks(Clock clock) {
        tasks.stream()
                .filter(task -> task.getCreationTime() == clock.getTime())
                .forEach(task -> {
                    System.out.println("Task " + task.getId() + " created");
                    clock.addNewTask(task);
                    scheduler.addTask(task);
                });
    }
    private void completeTasks(Clock clock){
        for (Processor processor : processors) {
            Task task = processor.getTask();
            if (task != null && task.getExecutionTime() - (clock.getTime()+1 - task.getAssignedCycle()) == 0) {
                System.out.println("Task " + task.getId() + " completed by Processor " + processor.getId());
                clock.addCompletedTask(task);
                processor.clearTask();
            }
        }
    }
}