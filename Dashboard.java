import java.util.HashMap;

public class Dashboard {

    private final HashMap<String, User> userDatabase;

    // Constructor accepting a HashMap<String, User>
    public Dashboard(HashMap<String, User> userDatabase) {
        this.userDatabase = userDatabase;
    }

    // Updated displayDashboard method to accept a User parameter
    public void displayDashboard(User user) {
        try {
            displayMenu();
            String entry = Main.sc.nextLine().trim().toUpperCase();
            userEntry(entry, user); // Pass the user to form methods
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void displayMenu() {
        Utils.clearScreen();
        Utils.displayFramedMessage("Dashboard");
        System.out.println("Press the corresponding number to proceed to the ID application form.");
        System.out.println("1 for Postal ID");
        System.out.println("2 for Barangay ID");
        System.out.println("3 for Company ID");
        System.out.println("Press E to Exit");
        System.out.print("Enter your choice: ");
    }

    private void userEntry(String entry, User user) {
        switch (entry) {
            case "1":
                showPostalForm(user);  // Pass user to the form
                break;
            case "2":
                showBarangayForm(user);  // Pass user to the form
                break;
            case "3":
                showCompanyForm(user);  // Pass user to the form
                break;
            case "E":
                System.out.println("Exiting the system. Thank you!");
                break;
            default:
                System.out.println("Invalid choice. Please select 1, 2, 3, or E.");
        }
    }

    private void showPostalForm(User user) {
        System.out.println("You selected Postal ID. Please fill out the Postal ID application form.");
        
        String lastName = getUpdatedValue("Last Name", user.getLastName());
        String firstName = getUpdatedValue("First Name", user.getFirstName());
        String middleName = getUpdatedValue("Middle Name", user.getMiddleName());
        int age = getUpdatedInteger("Age", user.getAge());
        String phoneNumber = getUpdatedValue("Phone Number", user.getPhoneNumber());
    
        // Continue with the rest of the form (e.g., address collection).
    }
    
    private void showBarangayForm(User user) {
        System.out.println("You selected Barangay ID. Please fill out the Barangay ID application form.");
    
        String lastName = getUpdatedValue("Last Name", user.getLastName());
        String firstName = getUpdatedValue("First Name", user.getFirstName());
        String middleName = getUpdatedValue("Middle Name", user.getMiddleName());
        int age = getUpdatedInteger("Age", user.getAge());
        String phoneNumber = getUpdatedValue("Phone Number", user.getPhoneNumber());
    
        // Additional form details
    }
    

    private void showCompanyForm(User user) {
        System.out.println("You selected Company ID. Please fill out the Company ID application form.");
        System.out.println("Automatically filled data:");
        System.out.println("Last Name: " + user.getLastName());
        System.out.println("First Name: " + user.getFirstName());
        System.out.println("Middle Name: " + user.getMiddleName());
        System.out.println("Age: " + user.getAge());
        System.out.println("Phone Number: " + user.getPhoneNumber());
        // Continue the form collection
    }

    private String getUpdatedValue(String fieldName, String currentValue) {
        System.out.println(fieldName + " (" + currentValue + "): Would you like to change it? (Y/N)");
        String choice = Main.sc.nextLine().trim().toUpperCase();
    
        if ("Y".equals(choice)) {
            return Utils.getValidInput("Enter new " + fieldName + ": ", input -> !input.isEmpty(), fieldName + " cannot be empty.");
        } else {
            return currentValue; // Retain the current value
        }
    }
    
    private int getUpdatedInteger(String fieldName, int currentValue) {
        System.out.println(fieldName + " (" + currentValue + "): Would you like to change it? (Y/N)");
        String choice = Main.sc.nextLine().trim().toUpperCase();
    
        if ("Y".equals(choice)) {
            return Utils.getValidInteger("Enter new " + fieldName + ": ", i -> i > 0, fieldName + " must be a positive number.");
        } else {
            return currentValue; // Retain the current value
        }
    }
}
