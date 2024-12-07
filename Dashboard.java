import java.util.HashMap;

public class Dashboard {

    private final HashMap<String, User> userDatabase;

    // Constructor accepting a HashMap<String, User>
    public Dashboard(HashMap<String, User> userDatabase) {
        this.userDatabase = userDatabase;
    }

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
                showPostalForm(user);
                break;
            case "2":
                showBarangayForm(user);
                break;
            case "3":
                showCompanyForm(user);
                break;
            case "E":
                System.out.println("Exiting the system. Thank you!");
                break;
            default:
                System.out.println("Invalid choice. Please select 1, 2, 3, or E.");
        }
    }

    private void showPostalForm(User user) {
        Utils.clearScreen();
        Utils.displayFramedMessage("Postal ID Application Form");
        System.out.println("You selected Postal ID. Please fill out the Postal ID application form.");
        
        String lastName = getUpdatedValue("Last Name", user.getLastName(), user, "lastName");
        String firstName = getUpdatedValue("First Name", user.getFirstName(), user, "firstName");
        String middleName = getUpdatedValue("Middle Name", user.getMiddleName(), user, "middleName");
        int age = getUpdatedInteger("Age", user.getAge(), user, "age");
        String phoneNumber = getUpdatedValue("Phone Number", user.getPhoneNumber(), user, "phoneNumber");
    }

    private void showBarangayForm(User user) {
        Utils.clearScreen();
        Utils.displayFramedMessage("Barangay ID Application Form");
        System.out.println("You selected Barangay ID. Please fill out the Barangay ID application form.");

        String lastName = getUpdatedValue("Last Name", user.getLastName(), user, "lastName");
        String firstName = getUpdatedValue("First Name", user.getFirstName(), user, "firstName");
        String middleName = getUpdatedValue("Middle Name", user.getMiddleName(), user, "middleName");
        int age = getUpdatedInteger("Age", user.getAge(), user, "age");
        String phoneNumber = getUpdatedValue("Phone Number", user.getPhoneNumber(), user, "phoneNumber");
    }

    private void showCompanyForm(User user) {
        Utils.clearScreen();
        Utils.displayFramedMessage("Company ID Application Form");
        System.out.println("You selected Company ID. Please fill out the Company ID application form.");
        System.out.println("Automatically filled data:");
        System.out.println("Last Name: " + user.getLastName());
        System.out.println("First Name: " + user.getFirstName());
        System.out.println("Middle Name: " + user.getMiddleName());
        System.out.println("Age: " + user.getAge());
        System.out.println("Phone Number: " + user.getPhoneNumber());
        // Continue the form collection
    }

    private String getUpdatedValue(String fieldName, String currentValue, User user, String userField) {
        System.out.println(fieldName + " (" + currentValue + "): Would you like to change it? (Y/N)");
        String choice = Main.sc.nextLine().trim().toUpperCase();

        if ("Y".equals(choice)) {
            String newValue = Utils.getValidInput("Enter new " + fieldName + ": ", input -> !input.isEmpty(), fieldName + " cannot be empty.");
            updateUserField(user, userField, newValue); // Update the user field dynamically
            Utils.updateUserInDatabase(user, userDatabase); // Persist changes to the database
            return newValue;
        } else {
            return currentValue; // Retain the current value
        }
    }

    private int getUpdatedInteger(String fieldName, int currentValue, User user, String userField) {
        System.out.println(fieldName + " (" + currentValue + "): Would you like to change it? (Y/N)");
        String choice = Main.sc.nextLine().trim().toUpperCase();

        if ("Y".equals(choice)) {
            int newValue = Utils.getValidInteger("Enter new " + fieldName + ": ", i -> i > 0, fieldName + " must be a positive number.");
            updateUserField(user, userField, newValue); // Update the user field dynamically
            Utils.updateUserInDatabase(user, userDatabase); // Persist changes to the database
            return newValue;
        } else {
            return currentValue; // Retain the current value
        }
    }

    private void updateUserField(User user, String fieldName, Object newValue) {
        switch (fieldName) {
            case "lastName":
                user.setLastName((String) newValue);
                break;
            case "firstName":
                user.setFirstName((String) newValue);
                break;
            case "middleName":
                user.setMiddleName((String) newValue);
                break;
            case "age":
                user.setAge((int) newValue);
                break;
            case "phoneNumber":
                user.setPhoneNumber((String) newValue);
                break;
            default:
                throw new IllegalArgumentException("Unknown field: " + fieldName);
        }
    }
}
