import java.util.Scanner;

public class Dashboard {
    public void displayDashboard(String username) {
        Utils.clearScreen();

        String dashboardTitle = "      Dashboard      ";

        
        Utils.frame(dashboardTitle.length());
        System.out.println("\n" + dashboardTitle + "\n");
        Utils.frame(dashboardTitle.length());

        System.out.println("\nWelcome, " + username + "!");
        System.out.println("1. View Profile");
        System.out.println("2. Logout");
        System.out.println("3. Exit");

        Scanner sc = RunMain.sc;

        while (true) {
            System.out.print("\nChoose an option: ");
            String option = sc.nextLine();
            switch (option) {
                case "1":
                    System.out.println("\n--- Profile ---");
                    System.out.println("Feature not implemented yet.");
                    break;
                case "2":
                    System.out.println("Logging out...");
                    return; // Return to login/register page
                case "3":
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }
}
