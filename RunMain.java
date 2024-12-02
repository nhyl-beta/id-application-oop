import java.util.Scanner;

public class RunMain {
    public static final Scanner sc = new Scanner(System.in);

    public static void landingPage(String message) {
        int stringLength = message.length();

        for (int i = 0; i < stringLength; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println(message);
        for (int i = 0; i < stringLength; i++) {
            System.out.print("-");
        }
        System.out.println("\nPress Enter to continue.");
        System.out.println("Press 1 to exit.");

        while (true) {
            String option = sc.nextLine();
            if (option.isEmpty()) {
                clearScreen();
                LoginPage lg = new LoginPage();
                lg.display();
                break;
            } else if (option.equals("1")) {
                System.out.println("Exiting program. Goodbye!");
                System.exit(0);
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        clearScreen();
        String message = "     Welcome to the Philippines     ";
        landingPage(message);
    }
}

class LoginPage {
    public void display() {
        System.out.println("Login/Register Window");
    }
}
