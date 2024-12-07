// File path: Utils.java
import java.io.*;
import java.util.HashMap;
import java.util.function.Predicate;
import java.io.Console;

public class Utils {
    private static final String DATABASE_FILE = "userDatabase.txt";

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void displayFramedMessage(String message) {
        int totalWidth = 70; // Set the total width of the frame
        int messageLength = message.length();
        
        if (messageLength > totalWidth) {
            System.out.println("Message is too long to fit in the frame.");
            return;
        }
    
        int padding = (totalWidth - messageLength) / 2;
        String paddedMessage = " ".repeat(padding) + message + " ".repeat(padding);
    
        // Add extra space if totalWidth is odd and messageLength is even (or vice versa)
        if ((totalWidth - messageLength) % 2 != 0) {
            paddedMessage += " ";
        }
    
        System.out.println("-".repeat(totalWidth));
        System.out.println(paddedMessage);
        System.out.println("-".repeat(totalWidth));
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

    /**
     * Reads a password from the user while displaying asterisks for each character.
     *
     * @param prompt The prompt to display to the user.
     * @return The password entered by the user.
     */
    public static String getPasswordInput(String prompt) {
        Console console = System.console();

        if (console != null) {
            // Use the console's built-in password masking
            System.out.print(prompt);
            char[] passwordChars = console.readPassword();
            return new String(passwordChars);
        } else {
            // Fallback for environments without a console
            System.out.print(prompt);
            StringBuilder password = new StringBuilder();
            try {
                while (true) {
                    char input = (char) System.in.read();
                    if (input == '\n' || input == '\r') {
                        // Enter key ends input
                        break;
                    } else if (input == '\b' && password.length() > 0) {
                        // Handle backspace
                        password.deleteCharAt(password.length() - 1);
                        System.out.print("\b \b"); // Erase a character on the console
                    } else {
                        password.append(input);
                        System.out.print("*"); // Display asterisk for each character
                    }
                }
            } catch (Exception e) {
                System.out.println("\nError reading password: " + e.getMessage());
            }
            System.out.println(); // Move to the next line
            return password.toString();
        }
    }

}
