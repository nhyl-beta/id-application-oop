import java.util.HashMap;

public class Dashboard {
    private final HashMap<String, User> userDatabase;

    public Dashboard(HashMap<String, User> userDatabase) {
        this.userDatabase = userDatabase;
    }

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
                    return; // Exit to login screen
                case "3":
                    System.out.println("Test Form");
                    testForm(user, user.getUsername()); // Pass the username
                    break;
                case "4":
                    System.out.println("Exiting program. Goodbye!");
                    Utils.saveUsers(userDatabase); // Save changes
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public void displayProfile(User user) {
        Utils.clearScreen();
        Utils.displayFramedMessage("      Profile      ");
    
        // Dynamically display the latest information
        System.out.println("\nUsername: " + user.getUsername());
        System.out.println("Name: " + user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName());
        System.out.println("Age: " + user.getAge());
        System.out.println("Phone: " + user.getPhoneNumber());
    
        // Offer the user a chance to update the profile in real-time
        System.out.println("\nWould you like to update your profile information? (y/n)");
        String choice = Main.sc.nextLine().trim().toLowerCase();
    
        if (choice.equals("y")) {
            // Redirect to the update functionality
            updateProfile(user);
        } else {
            System.out.println("\nReturning to the main menu...");
        }
    }

    public void updateProfile(User user) {
        while (true) {
            Utils.clearScreen();
            Utils.displayFramedMessage("      Update Profile      ");
    
            // Display current user details for context
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
                    String newLastName = Utils.getValidInput(
                            "Enter new Last Name (Leave blank to keep current): ",
                            input -> true, "Invalid input.");
                    if (!newLastName.isEmpty()) {
                        user = new User(
                                user.getUsername(),
                                newLastName,
                                user.getFirstName(),
                                user.getMiddleName(),
                                user.getAge(),
                                user.getPhoneNumber(),
                                user.getPassword()
                        );
                    }
                    break;
                case "2":
                    String newFirstName = Utils.getValidInput(
                            "Enter new First Name (Leave blank to keep current): ",
                            input -> true, "Invalid input.");
                    if (!newFirstName.isEmpty()) {
                        user = new User(
                                user.getUsername(),
                                user.getLastName(),
                                newFirstName,
                                user.getMiddleName(),
                                user.getAge(),
                                user.getPhoneNumber(),
                                user.getPassword()
                        );
                    }
                    break;
                case "3":
                    String newMiddleName = Utils.getValidInput(
                            "Enter new Middle Name (Leave blank to keep current): ",
                            input -> true, "Invalid input.");
                    if (!newMiddleName.isEmpty()) {
                        user = new User(
                                user.getUsername(),
                                user.getLastName(),
                                user.getFirstName(),
                                newMiddleName,
                                user.getAge(),
                                user.getPhoneNumber(),
                                user.getPassword()
                        );
                    }
                    break;
                case "4":
                    int newAge = Utils.getValidInteger(
                            "Enter new Age (Enter -1 to keep current): ",
                            i -> i > 0 || i == -1, "Invalid input.");
                    if (newAge != -1) {
                        user = new User(
                                user.getUsername(),
                                user.getLastName(),
                                user.getFirstName(),
                                user.getMiddleName(),
                                newAge,
                                user.getPhoneNumber(),
                                user.getPassword()
                        );
                    }
                    break;
                case "5":
                    String newPhoneNumber = Utils.getValidInput(
                            "Enter new Phone Number (Leave blank to keep current): ",
                            Utils::isValidPhoneNumber, "Invalid phone number.");
                    if (!newPhoneNumber.isEmpty()) {
                        user = new User(
                                user.getUsername(),
                                user.getLastName(),
                                user.getFirstName(),
                                user.getMiddleName(),
                                user.getAge(),
                                newPhoneNumber,
                                user.getPassword()
                        );
                    }
                    break;
                case "6":
                    // Save the updated user to the database
                    userDatabase.put(user.getUsername(), user);
                    Utils.saveUsers(userDatabase); // Persist changes
                    System.out.println("\nAll changes saved successfully!");
                    return; // Exit the update process
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    

    public void testForm(User user, String username) {
        while (true) {
            Utils.clearScreen();
            Utils.displayFramedMessage("      Test Form      ");
    
            // Retrieve the latest user object from the database
            User latestUser = userDatabase.get(username); // Use the username to fetch the latest user
    
            if (latestUser == null) {
                System.out.println("Error: User not found in the database.");
                return;
            }
    
            // Display the current user details
            System.out.println("Last Name: " + latestUser.getLastName());
            System.out.println("First Name: " + latestUser.getFirstName());
            System.out.println("Middle Name: " + latestUser.getMiddleName());
    
            // Ask if they want to update their information
            System.out.println("\nWould you like to update your information? (y/n)");
            String response = Main.sc.nextLine().trim().toLowerCase();
    
            if (response.equals("y")) {
                // Update the profile and save changes
                updateProfile(latestUser);
            } else {
                System.out.println("\nReturning to the main menu...");
                return; // Exit the test form
            }
        }
    }
    
    
    
    
}
