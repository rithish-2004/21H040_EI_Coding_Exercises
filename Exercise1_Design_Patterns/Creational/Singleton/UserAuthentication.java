import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserAuthentication {
    private static UserAuthentication instance;
    private Map<String, String> users;
    private static final String FILE_NAME = "./Exercise1_Design_Patterns/Creational/Singleton/users.txt";

    // Private constructor to prevent instantiation
    private UserAuthentication() {
        users = new HashMap<>();
        loadUsersFromFile();
    }

    // Static method to provide a global point of access
    public static UserAuthentication getInstance() {
        if (instance == null) {
            instance = new UserAuthentication();
        }
        return instance;
    }

    // Method to load users from file
    private void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users from file: " + e.getMessage());
        }
    }

    // Method to save users to file
    private void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users to file: " + e.getMessage());
        }
    }

    // Method to validate user credentials
    public boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    // Method to check if username exists
    public boolean usernameExists(String username) {
        return users.containsKey(username);
    }

    // Method to sign up a new user
    public boolean signup(String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, password);
            saveUsersToFile();
            return true;
        }
        return false;
    }
}
