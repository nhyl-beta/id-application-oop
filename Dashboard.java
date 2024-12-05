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
                    testForm(user);
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
        System.out.println("\nUsername: " + user.getUsername());
        System.out.println("Name: " + user.getFirstName() + " " + user.getLastName());
        System.out.println("Age: " + user.getAge());
        System.out.println("Phone: " + user.getPhoneNumber());
        System.out.println();
    }

    public void testForm(User user){
        Utils.clearScreen();
        Utils.displayFramedMessage("      Test Form      ");
        System.out.println(user.getLastName());
        System.out.println(user.getFirstName());
        System.out.println(user.getMiddleName());
    }
}
