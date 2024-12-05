// File path: Utils.java
import java.io.*;
import java.util.HashMap;
import java.util.function.Predicate;

public class Utils {
    private static final String DATABASE_FILE = "userDatabase.txt";

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void displayFramedMessage(String message) {
        System.out.println("-".repeat(message.length()));
        System.out.println(message);
        System.out.println("-".repeat(message.length()));
    }

    public static String getValidInput(String prompt, Predicate<String> validator, String errorMessage) {
        while (true) {
            System.out.print(prompt);
            String input = Main.sc.nextLine().trim();
            if (validator.test(input)) {
                return input;
            } else {
                System.out.println(errorMessage);
            }
        }
    }

    public static String getValidName(String prompt) {
        return getValidInput(prompt, input -> input.matches("^[a-zA-Z ]+$"), 
            "Invalid input. Please enter alphabetic characters and spaces only.");
    }

    public static int getValidInteger(String prompt, Predicate<Integer> validator, String errorMessage) {
        while (true) {
            System.out.print(prompt);
            try {
                int input = Integer.parseInt(Main.sc.nextLine().trim());
                if (validator.test(input)) {
                    return input;
                } else {
                    System.out.println(errorMessage);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^09[0-9]{9}$");
    }

    public static HashMap<String, User> loadUsers() {
        HashMap<String, User> userDatabase = new HashMap<>();
        File file = new File(DATABASE_FILE);
    
        if (!file.exists()) {
            System.out.println("Database file does not exist. Starting with an empty database.");
            return userDatabase; // Return an empty database if the file does not exist
        }
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Use "\\|" to correctly split on the literal pipe character
                String[] parts = line.split("\\|");
                if (parts.length == 7) { // Ensure all fields are present
                    String username = parts[0].trim();
                    String lastName = parts[1].trim();
                    String firstName = parts[2].trim();
                    String middleName = parts[3].trim();
                    int age = Integer.parseInt(parts[4].trim());
                    String phoneNumber = parts[5].trim();
                    String password = parts[6].trim();
                    User user = new User(username, lastName, firstName, middleName, age, phoneNumber, password);
                    userDatabase.put(username, user);
                } else {
                    System.out.println("Skipping malformed line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading user database: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing age in user database: " + e.getMessage());
        }
    
        return userDatabase;
    }
    
    public static void saveUsers(HashMap<String, User> userDatabase) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE_FILE))) {
            for (User user : userDatabase.values()) {
                writer.write(String.join("|",
                        user.getUsername(),
                        user.getLastName(),
                        user.getFirstName(),
                        user.getMiddleName(),
                        String.valueOf(user.getAge()),
                        user.getPhoneNumber(),
                        user.getPassword()
                ));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving user database: " + e.getMessage());
        }
    }
    
}
