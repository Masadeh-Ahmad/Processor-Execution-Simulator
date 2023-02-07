package program;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Simulator simulator = new Simulator(2, 10, "Tasks1.txt");
        simulator.start();
    }
}