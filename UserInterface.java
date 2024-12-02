import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

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

        // Collecting user details with validation
        System.out.print("Enter a unique username: ");
        String username = sc.nextLine();

        if (userDatabase.containsKey(username)) {
            System.out.println("Username already exists. Try a different one.");
            return;
        }

        // Restricting name fields to alphabetic characters only
        String lastName = getValidName("Enter your last name: ");
        String firstName = getValidName("Enter your first name: ");
        String middleName = getValidName("Enter your middle name: ");

        // Restricting age input to integers only
        int age = getValidAge();

        String phoneNumber;
        while (true) {
            System.out.print("Enter your phone number (11 digits, starting with 09): ");
            phoneNumber = sc.nextLine();
            if (isValidPhoneNumber(phoneNumber)) {
                break;
            } else {
                System.out.println("Invalid phone number. Make sure it starts with '09' and has 11 digits.");
            }
        }

        System.out.print("Enter a password: ");
        String password = sc.nextLine();

        // Create user info
        String userInfo = lastName + "," + firstName + "," + middleName + "," + age + "," + phoneNumber + "," + password;
        userDatabase.put(username, userInfo);

        FileManager.saveUsers(userDatabase); // Save updated database
        System.out.println("Registration successful!");
    }

    private String getValidName(String prompt) {
        String name;
        while (true) {
            System.out.print(prompt);
            name = sc.nextLine();
            if (name.matches("[a-zA-Z]+")) {  // Only alphabetic characters allowed
                break;
            } else {
                System.out.println("Invalid input. Please enter alphabetic characters only.");
            }
        }
        return name;
    }

    private int getValidAge() {
        int age;
        while (true) {
            System.out.print("Enter your age: ");
            String ageInput = sc.nextLine();
            try {
                age = Integer.parseInt(ageInput);
                if (age < 0) {
                    System.out.println("Age cannot be negative. Please enter a valid age.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Please enter a valid integer.");
            }
        }
        return age;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return Pattern.matches("^09[0-9]{9}$", phoneNumber);
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

        // Retrieve user info and validate password
        String userInfo = userDatabase.get(username);
        String[] parts = userInfo.split(",");
        String storedPassword = parts[5];

        if (storedPassword.equals(password)) {
            System.out.println("Login successful! Welcome, " + username + "!");
        } else {
            System.out.println("Incorrect password. Please try again.");
        }
    }
}
