import java.io.*;
import java.util.Scanner;
import java.util.HashMap;

public class RunMain {
    public static final Scanner sc = new Scanner(System.in);

    private static void landingPage(String message){
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
                UserAccess user = new UserAccess();
                user.display(); 
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

        //Landing Page
        clearScreen();  
        String landingMessage = "Welkam to Pilipins";
        landingPage(landingMessage);


    }
}

class UserAccess{
    private static final String FILE_NAME = "users.txt";
    private HashMap<String, String> userDatabase = new HashMap<>();
    private final Scanner sc = RunMain.sc;
    private final RunMain rm = new RunMain();

    private void loadUserFromFile(){
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    userDatabase.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users from the file: " + e.getMessage());
        }
    }

    private void  saveUserToFile(String username, String password){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(username + ":" + password);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving user to file: " + e.getMessage());
        }
    }
    public void display() {
        while (true) {
            RunMain.clearScreen();
            System.out.println("\nLogin/Register Page");
            System.out.println("Press 1 to Register");
            System.out.println("Press 2 to Login");
            System.out.println("Press 3 to Exit");

            String option = sc.nextLine();
            switch (option) {
                case "1":
                    register();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    System.out.println("Returning to the main menu...");
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void register() {
        System.out.println("\n--- Registration ---");
        System.out.print("Enter a unique username: ");
        String username = sc.nextLine();

        if (userDatabase.containsKey(username)) {
            System.out.println("Username already exists. Try a different one.");
            return;
        }

        System.out.print("Enter a password: ");
        String password = sc.nextLine();

        userDatabase.put(username, password); // Store credentials in memory
        saveUserToFile(username, password); // Save to file
        System.out.println("Registration successful!");
    }

    private void login() {
        System.out.println("\n--- Login ---");
        System.out.print("Enter your username: ");
        String username = sc.nextLine();

        if (!userDatabase.containsKey(username)) {
            System.out.println("Username not found. Please register first.");
            return;
        }

        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        if (userDatabase.get(username).equals(password)) {
            System.out.println("Login successful! Welcome, " + username + "!");
        } else {
            System.out.println("Incorrect password. Please try again.");
        }
    }

} 