import java.util.Scanner;

public class RunMain {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Utils.clearScreen();
        String landingMessage = "      Welcome to Philippines!      ";
        displayLandingPage(landingMessage);
    }

    private static void displayLandingPage(String message) {
        Utils.clearScreen();
        
        Utils.frame(message.length());
        System.out.println("\n" + message + "\n");
        Utils.frame(message.length());
        System.out.println("\nPress Enter to Continue");
        System.out.println("Press 1 to Exit.");

        while (true) {
            String option = sc.nextLine();
            if (option.isEmpty()) {
                Utils.clearScreen();
                new UserInterface().displayLoginPage();
                break;
            } else if ("1".equals(option)) {
                Utils.clearScreen();
                System.out.println("Exiting program. Goodbye!");
                System.exit(0);
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
}
