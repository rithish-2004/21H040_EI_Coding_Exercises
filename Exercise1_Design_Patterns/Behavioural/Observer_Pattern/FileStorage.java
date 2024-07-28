package Exercise1_Design_Patterns.Behavioural.Observer_Pattern;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    public static void saveStateToFile(String filename, List<String> states) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String state : states) {
                writer.write(state);
                writer.newLine();
            }
        }
    }

    public static List<String> loadStateFromFile(String filename) throws IOException {
        List<String> states = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                states.add(line);
            }
        }
        return states;
    }
}
