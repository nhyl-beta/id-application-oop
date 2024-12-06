import java.util.HashMap;

/**
 * Dashboard provides functionalities for users to view and update their profile,
 * access various forms, or log out.
 */
public class Dashboard {
    private final HashMap<String, User> userDatabase;

    public Dashboard(HashMap<String, User> userDatabase) {
        this.userDatabase = userDatabase;
    }

    /**
     * Entry point to run the dashboard.
     */
    public void run(User user) {
        while (true) {
            try {
                displayDashboard(user);
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Displays the main dashboard with options for the user.
     */
    public void displayDashboard(User user) {
        Utils.clearScreen();
        Utils.displayFramedMessage("      Dashboard      ");

        while (true) {
            System.out.println("\n1. View Profile");
            System.out.println("2. ID Forms");
            System.out.println("3. Logout");
            System.out.println("4. Exit");
            System.out.print("\nYour choice: ");
            String choice = Main.sc.nextLine().trim();

            switch (choice) {
                case "1" -> displayProfile(user);
                case "2" -> displayIDMenu();
                case "3" -> {
                    System.out.println("Logging out...");
                    return;
                }
                case "4" -> exitProgram();
                default -> System.out.println("Invalid input. Please try again.");
            }
        }
    }

    /**
     * Displays the user's profile and provides an option to update information.
     */
    public void displayProfile(User user) {
        Utils.clearScreen();
        Utils.displayFramedMessage("      Profile      ");

        System.out.println("\nUsername: " + user.getUsername());
        System.out.println("Name: " + user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName());
        System.out.println("Age: " + user.getAge());
        System.out.println("Phone: " + user.getPhoneNumber());

        System.out.print("\nWould you like to update your profile information? (y/n): ");
        String choice = Main.sc.nextLine().trim().toLowerCase();

        if ("y".equals(choice)) {
            updateProfile(user);
        } else {
            System.out.println("\nReturning to the main menu...");
        }
    }

    /**
     * Allows the user to update their profile information.
     */
    public void updateProfile(User user) {
        while (true) {
            Utils.clearScreen();
            Utils.displayFramedMessage("      Update Profile      ");
    
            System.out.println("\nCurrent Information:");
            System.out.println("1. Last Name: " + user.getLastName());
            System.out.println("2. First Name: " + user.getFirstName());
            System.out.println("3. Middle Name: " + user.getMiddleName());
            System.out.println("4. Age: " + user.getAge());
            System.out.println("5. Phone: " + user.getPhoneNumber());
            System.out.println("6. Done");
    
            System.out.print("\nSelect an option to update (1-6): ");
            String choice = Main.sc.nextLine().trim();
    
            switch (choice) {
                case "1" -> user = user.withLastName(Utils.getValidInput("Enter new Last Name: ", input -> !input.isEmpty(), "Invalid input."));
                case "2" -> user = user.withFirstName(Utils.getValidInput("Enter new First Name: ", input -> !input.isEmpty(), "Invalid input."));
                case "3" -> user = user.withMiddleName(Utils.getValidInput("Enter new Middle Name: ", input -> !input.isEmpty(), "Invalid input."));
                case "4" -> {
                    int newAge = Integer.parseInt(Utils.getValidInput("Enter new Age: ", input -> input.matches("\\d+"), "Invalid input."));
                    user = user.withAge(newAge);
                }
                case "5" -> user = user.withPhoneNumber(Utils.getValidInput("Enter new Phone Number: ", input -> input.matches("\\d+"), "Invalid input."));
                case "6" -> {
                    userDatabase.put(user.getUsername(), user);
                    Utils.saveUsers(userDatabase);
                    System.out.println("\nProfile updated successfully!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Saves user changes to the database.
     */
    private void saveUserChanges(User user) {
        userDatabase.put(user.getUsername(), user);
        Utils.saveUsers(userDatabase);
    }

    /**
     * Displays the ID forms menu.
     */
    private void displayIDMenu() {
        Utils.clearScreen();
        Utils.displayFramedMessage("      ID Forms      ");

        System.out.println("Press the corresponding number to proceed to the ID application form:");
        System.out.println("1. Postal ID");
        System.out.println("2. Barangay ID");
        System.out.println("3. Company ID");
        System.out.println("E. Exit");

        System.out.print("\nEnter your choice: ");
        String choice = Main.sc.nextLine().trim().toUpperCase();

        switch (choice) {
            case "1" -> showForm("Postal ID");
            case "2" -> showForm("Barangay ID");
            case "3" -> showForm("Company ID");
            case "E" -> System.out.println("Returning to the dashboard...");
            default -> System.out.println("Invalid choice. Please select 1, 2, 3, or E.");
        }
    }

    /**
     * Displays a placeholder for the selected form.
     */
    private void showForm(String formType) {
        System.out.println("\nYou selected " + formType + ". Please fill out the " + formType + " application form.");
    }

    /**
     * Exits the program after saving data.
     */
    private void exitProgram() {
        System.out.println("\nExiting program. Goodbye!");
        Utils.saveUsers(userDatabase);
        System.exit(0);
    }
}
