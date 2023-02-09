package program;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class Main {
    public static File fileChooser(String fileDescription, String fileExtension) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(fileDescription + " files (*." + fileExtension + ")", fileExtension);
        fileChooser.setFileFilter(filter);
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        int result = fileChooser.showOpenDialog(frame);
        if (result != JFileChooser.APPROVE_OPTION) return null;
        return fileChooser.getSelectedFile();
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int numProcessors;
            int numCycles;
            File selectedFile;

            System.out.print("Enter the number of processors (1-16): ");
            numProcessors = Integer.parseInt(reader.readLine());
            while (numProcessors < 1 || numProcessors > 16) {
                System.out.print("Invalid input. Enter the number of processors (1-16): ");
                numProcessors = Integer.parseInt(reader.readLine());
            }

            System.out.print("Enter the number of cycles (1-100): ");
            numCycles = Integer.parseInt(reader.readLine());
            while (numCycles < 1 || numCycles > 100) {
                System.out.print("Invalid input. Enter the number of cycles (1-100): ");
                numCycles = Integer.parseInt(reader.readLine());
            }

            selectedFile = fileChooser("Text", "txt");
            if (selectedFile == null) {
                System.out.println("No file selected. Exiting...");
                System.exit(0);
            }

            System.out.println("Number of processors: " + numProcessors);
            System.out.println("Number of cycles: " + numCycles);
            System.out.println("File name: " + selectedFile.getName());

            Simulator simulator = new Simulator(numProcessors, numCycles, selectedFile.getAbsolutePath());
            simulator.start();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
    }
}