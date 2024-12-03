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
            
            String titlePage = "      Login/Register Page      ";

            Utils.frame(titlePage.length());
            System.out.println("\n" + titlePage + "\n");
            Utils.frame(titlePage.length());

            System.out.println();
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("\nEnter the corresponding number to select: ");

            String option = sc.nextLine();
            switch (option) {
                case "1":
                    register();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    System.out.println("Exiting the system....");
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void register() {
        Utils.clearScreen();
        String registerTitle = "      Registration      ";

        Utils.frame(registerTitle.length());
        System.out.println("\n" + registerTitle + "\n");
        Utils.frame(registerTitle.length());
        System.out.println();

        
        String username;
        while (true) {
            System.out.print("Enter a username: ");
            username = sc.nextLine().trim();
            
            if (username.isEmpty()) {
                System.out.println("Username cannot be blank. Please enter a valid username.");
                continue;
            }

            if (userDatabase.containsKey(username)) {
                System.out.println("Username already exists. Try a different one.");
            } else {
                break;
            }
        }


        // Restricting name fields to alphabetic characters only
        String lastName = getValidName("Enter your Last name: ");
        String firstName = getValidName("Enter your First name: ");
        String middleName = getValidName("Enter your Middle name: ");

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
        Utils.clearScreen();
        String loginTitle = "              Login              ";

        Utils.frame(loginTitle.length());
        System.out.println("\n" + loginTitle + "\n");
        Utils.frame(loginTitle.length());
        System.out.println();

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
            new Dashboard().displayDashboard(username);
        } else {
            System.out.println("Incorrect password. Please try again.");
        }
    }
}
