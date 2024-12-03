import java.util.Scanner;

public class Dashboard {
    public void displayDashboard(String username) {
        Scanner sc = RunMain.sc;

        Utils.clearScreen();

        String dashboardTitle = "      Dashboard      ";

        
        Utils.frame(dashboardTitle.length());
        System.out.println("\n" + dashboardTitle + "\n");
        Utils.frame(dashboardTitle.length());

        System.out.println("\nWelcome, " + username + "!");

        
        
        System.out.println("1. View Profile");
        System.out.println("2. Logout");
        System.out.println("3. Exit");


        while (true) {
            System.out.print("\nChoose an option: ");
            String option = sc.nextLine();
            switch (option) {
                case "1":
                    Utils.clearScreen();
                    new Profile().displayProfilePage();
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
