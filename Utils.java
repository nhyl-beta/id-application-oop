import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Predicate;

public class Utils {
    public static final Scanner sc = new Scanner(System.in);
    private static final String DATABASE_FILE = "userDatabase.txt";

    public static String getValidInput(String prompt, Predicate<String> validator, String errorMessage) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (validator.test(input)) {
                return input;
            } else {
                System.out.println(errorMessage);
            }
        }
    }

    public static boolean isValidString(String input) {
        return input != null && !input.isBlank();
    }

    public static boolean isValidTIN(String tin) {
        return tin.matches("\\d{9}"); // Example: TIN must be 9 digits
    }

    public static boolean isValidPhoneNumber(String phone) {
        return phone.matches("^09\\d{9}$"); // Example: 11 digits, starts with 09
    }

    public static boolean isValidPhilHealth(String philHealth) {
        return philHealth.matches("\\d{12}"); // Example: PhilHealth must be 12 digits
    }

    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
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
    
        System.out.println("=".repeat(totalWidth));
        System.out.println();
        System.out.println(paddedMessage);
        System.out.println();
        System.out.println("=".repeat(totalWidth));
    }

    public static String getValidDate(String prompt) {
        return getValidInput(prompt, input -> input.matches("^\\d{2}-\\d{2}-\\d{4}$"),
                "Invalid date format. Please use MM-DD-YYYY.");
    }

    public static String getValidGender(String prompt) {
        return getValidInput(prompt, input -> input.equalsIgnoreCase("Male") || input.equalsIgnoreCase("Female"),
                "Invalid gender. Please enter Male or Female.");
    }

    public static User findUser(String username) {
        HashMap<String, User> userDatabase = loadUsers();
        return userDatabase.get(username);
    }

    public static HashMap<String, User> loadUsers() {
        HashMap<String, User> userDatabase = new HashMap<>();
        File file = new File(DATABASE_FILE);

        if (!file.exists()) {
            System.out.println("Database file not found. Starting with an empty user database.");
            return userDatabase;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
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
                    userDatabase.put(username.toLowerCase(), user); // Case-insensitive username
                } else {
                    System.out.println("Skipping corrupted line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading user database: " + e.getMessage());
        }

        return userDatabase;
    }

     public static void saveUsers(HashMap<String, User> userDatabase) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE_FILE))) {
            for (User user : userDatabase.values()) {
                writer.write(String.join("|",
                        user.getUsername().toLowerCase(),
                        user.getLastName(),
                        user.getFirstName(),
                        user.getMiddleName(),
                        String.valueOf(user.getAge()),
                        user.getPhoneNumber(),
                        user.getPassword()
                ));
                writer.newLine();
            }
            System.out.println("User data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving user database: " + e.getMessage());
        }
    }

    public static String getValidName(String prompt) {
        return getValidInput(prompt, Utils::isValidString, "Name cannot be empty.");
    }

    public static int getValidInteger(String prompt, Predicate<Integer> validator, String errorMessage) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = Integer.parseInt(sc.nextLine().trim());
                if (validator.test(input)) {
                    return input;
                }
            } catch (NumberFormatException e) {
                // Fall through to error message
            }
            System.out.println(errorMessage);
        }
    }
}