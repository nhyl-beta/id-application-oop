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
            userEntry(entry);
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

    private void userEntry(String entry) {
        switch (entry) {
            case "1":
                showPostalForm();
                break;
            case "2":
                showBarangayForm();
                break;
            case "3":
                showCompanyForm();
                break;
            case "E":
                System.out.println("Exiting the system. Thank you!");
                break;
            default:
                System.out.println("Invalid choice. Please select 1, 2, 3, or E.");
        }
    }

    private void showPostalForm() {
        System.out.println("You selected Postal ID. Please fill out the Postal ID application form.");
    }

    private void showBarangayForm() {
        System.out.println("You selected Barangay ID. Please fill out the Barangay ID application form.");
    }

    private void showCompanyForm() {
        System.out.println("You selected Company ID. Please fill out the Company ID application form.");
    }
}
