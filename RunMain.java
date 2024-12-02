import java.util.Scanner;

public class RunMain{
    public static final Scanner sc = new Scanner(System.in);

    public static void landingPage(String message){
        int stringLength = message.length();

        for (int i = 0; i < stringLength; i++) {
            System.out.print("-");
        }

        System.out.println(" ");
        System.out.println(" ");

        System.out.println(message);

        System.out.println(" ");
        
        for (int i = 0; i < stringLength; i++) {
            System.out.print("-");
        }
        
        System.out.println(" ");
        System.out.println("Press enter to continue.");
        String option = sc.nextLine();

        while (option!=null) {
            if (option.isEmpty()) {
                clearScreen();
                System.out.println("HALOO EBERYWAN");
            }

            if (sc.hasNextLine()) {
                option = sc.nextLine();
            }else{
                option = null;
            }
        }
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void main(String[] args){
        clearScreen();
        String message = "     Welkam to the Philippines     ";
        landingPage(message);

    }
    
}