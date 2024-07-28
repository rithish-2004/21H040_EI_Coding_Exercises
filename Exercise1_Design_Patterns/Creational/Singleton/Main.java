import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static UserAuthentication auth = UserAuthentication.getInstance();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter username:");
            String username = scanner.nextLine();

            System.out.println("Enter password:");
            String password = scanner.nextLine();

            if (auth.login(username, password)) {
                System.out.println("Login successful! Welcome " + username);
                break;
            } else if (auth.usernameExists(username)) {
                System.out.println("Password is incorrect. Please try again.");
            } else {
                System.out.println("Username not found. Would you like to sign up? (yes/no)");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("yes")) {
                    if (auth.signup(username, password)) {
                        System.out.println("Signup successful! You can now log in with your new credentials.");
                    } else {
                        System.out.println("Signup failed! Username already exists.");
                    }
                } else {
                    System.out.println("Goodbye!");
                    break;
                }
            }
        }

        scanner.close();
    }
}
