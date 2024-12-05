import java.util.HashMap;

/**
 * Dashboard provides functionalities for users to view and update their profile,
 * log out, or access the test form.
 */
public class Dashboard {
    private final HashMap<String, User> userDatabase;

    public Dashboard(HashMap<String, User> userDatabase) {
        this.userDatabase = userDatabase;
    }

    /**
     * Displays the main dashboard with options for the user.
     */
    public void displayDashboard(User user) {
        Utils.clearScreen();
        Utils.displayFramedMessage("      Dashboard      ");

        while (true) {
            System.out.println("1. View Profile");
            System.out.println("2. Logout");
            System.out.println("3. Test Form");
            System.out.println("4. Exit");
            System.out.print("Your choice: ");
            String choice = Main.sc.nextLine().trim();

            switch (choice) {
                case "1":
                    displayProfile(user);
                    break;
                case "2":
                    System.out.println("Logging out...");
                    return;
                case "3":
                    System.out.println("Test Form");
                    testForm(user.getUsername()); // Pass the username for real-time updates
                    break;
                case "4":
                    System.out.println("Exiting program. Goodbye!");
                    Utils.saveUsers(userDatabase);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
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

        System.out.println("\nWould you like to update your profile information? (y/n)");
        String choice = Main.sc.nextLine().trim().toLowerCase();

        if (choice.equals("y")) {
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
                case "1":
                    String newLastName = Utils.getValidInput("Enter new Last Name: ", input -> true, "Invalid input.");
                    userDatabase.put(user.getUsername(), new User(user.getUsername(), newLastName, user.getFirstName(), user.getMiddleName(), user.getAge(), user.getPhoneNumber(), user.getPassword()));
                    break;
                case "2":
                    String newFirstName = Utils.getValidInput("Enter new First Name: ", input -> true, "Invalid input.");
                    userDatabase.put(user.getUsername(), new User(user.getUsername(), user.getLastName(), newFirstName, user.getMiddleName(), user.getAge(), user.getPhoneNumber(), user.getPassword()));
                    break;
                // Add similar cases for Middle Name, Age, and Phone
                case "6":
                    Utils.saveUsers(userDatabase);
                    System.out.println("Profile updated successfully!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Displays and allows updates to user information via the test form.
     */
    public void testForm(String username) {
        Utils.clearScreen();
        Utils.displayFramedMessage("      Test Form      ");
        User user = userDatabase.get(username);

        if (user == null) {
            System.out.println("Error: User not found.");
            return;
        }

        System.out.println("Last Name: " + user.getLastName());
        System.out.println("First Name: " + user.getFirstName());
        System.out.println("Middle Name: " + user.getMiddleName());
        System.out.println("\nWould you like to update your information? (y/n)");
        String choice = Main.sc.nextLine().trim().toLowerCase();

        if (choice.equals("y")) {
            updateProfile(user);
        } else {
            System.out.println("Returning to the menu...");
        }
    }
}
