import java.util.Scanner;

public class RunMain {
    public static final Scanner sc = new Scanner(System.in);

    public static void landingPage(String message){
        int stringLength = message.length() + 6;

        frame(stringLength);
        System.out.println();
        System.out.println(message);
        System.out.println();
        frame(stringLength);
        System.out.println("\nPress Enter to Continue");
        System.out.println("Press 1 to exit.");

        while (true) {
            String option = sc.nextLine();
            if (option.isEmpty()) {
                clearScreen();
                LoginPage lg = new LoginPage();
                lg.display();
                break;
            }else if (option.equals("1")) {
                System.out.println("Exiting program. Goodbye!");
                System.exit(0);
            }else{
                System.out.println("Invalid input. Please try again.");
            }
            
        }

    }
    
    public static void frame(int stringLength){
        for (int i = 0; i < stringLength; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void clearScreen() {
        try {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception e) {
            System.out.println("Unable to clear the screen.");
        }
    }

    public static void main(String[] args) {
        clearScreen();  
        String message = "Welkam to Pilipins";

        landingPage(message);
    }
}

class LoginPage{
    public void display(){
        System.out.println("Login/Register Page");
    }
}