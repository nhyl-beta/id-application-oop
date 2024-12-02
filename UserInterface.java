import java.util.HashMap;
import java.util.Scanner;

public class UserInterface {
    private HashMap<String, String> userDatabase;
    private final Scanner sc = RunMain.sc;

    public UserInterface() {
        this.userDatabase = FileManager.loadUsers();
    }

    public void displayLoginPage() {
        while (true) {
            Utils.clearScreen();
            System.out.println("\nLogin/Register Page");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            String option = sc.nextLine();
            switch (option) {
                case "1":
                    register();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    System.out.println("Returning to the main menu...");
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void register() {
        System.out.println("\n--- Registration ---");
        System.out.print("Enter a unique username: ");
        String username = sc.nextLine();

        if (userDatabase.containsKey(username)) {
            System.out.println("Username already exists. Try a different one.");
            return;
        }

        System.out.print("Enter a password: ");
        String password = sc.nextLine();

        userDatabase.put(username, password);
        FileManager.saveUsers(userDatabase); // Save updated database
        System.out.println("Registration successful!");
    }

    private void login() {
        System.out.println("\n--- Login ---");
        System.out.print("Enter your username: ");
        String username = sc.nextLine();

        if (!userDatabase.containsKey(username)) {
            System.out.println("Username not found. Please register first.");
            return;
        }

        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        if (userDatabase.get(username).equals(password)) {
            System.out.println("Login successful! Welcome, " + username + "!");
        } else {
            System.out.println("Incorrect password. Please try again.");
        }
    }
}
