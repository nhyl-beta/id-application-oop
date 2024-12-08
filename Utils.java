import java.io.*;
import java.util.HashMap;
import java.util.function.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utils {
    private static final String DATABASE_FILE = "userDatabase.txt";
    private static final int TOTALWIDTH = 70;

    // Clear the screen
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Display a framed message
    public static void displayFramedMessage(String message) {
        int messageLength = message.length();
        if (messageLength > TOTALWIDTH) {
            System.out.println("Message is too long to fit in the frame.");
            return;
        }

        int padding = (TOTALWIDTH - messageLength) / 2;
        String paddedMessage = " ".repeat(padding) + message + " ".repeat(padding);

        // Add extra space if TOTALWIDTH is odd
        if ((TOTALWIDTH - messageLength) % 2 != 0) {
            paddedMessage += " ";
        }

        System.out.println("-".repeat(TOTALWIDTH));
        System.out.println();
        System.out.println(paddedMessage);
        System.out.println();
        System.out.println("-".repeat(TOTALWIDTH));
    }

    // Get valid input using a custom validator
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

    // Get valid name (alphabetic characters and spaces)
    public static String getValidName(String prompt) {
        return getValidInput(prompt, input -> input.matches("^[a-zA-Z ]+$"),
                "Invalid input. Please enter alphabetic characters and spaces only.");
    }

    // Get valid general string
    public static String isValidString(String prompt) {
        return getValidInput(prompt, input -> input.matches("^[a-zA-Z ]+$"),
                "Invalid input. Please enter alphabetic characters and spaces only.");
    }

    // Get valid integer
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

    // Get valid date in MM-DD-YYYY format
    public static String getValidDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = Main.sc.nextLine().trim();

            if (input.matches("^(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])-(\\d{4})$")) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
                    LocalDate.parse(input, formatter); // Validate date
                    return input;
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date. Please ensure the day is valid for the given month and year.");
                }
            } else {
                System.out.println("Invalid format. Please use MM-DD-YYYY.");
            }
        }
    }

    // Get valid gender
    public static String getValidGender(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = Main.sc.nextLine().trim().toUpperCase();
            if (input.equals("M") || input.equals("F")) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter 'M' for Male or 'F' for Female.");
            }
        }
    }

    // Validate phone number
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^09[0-9]{9}$");
    }

    // Validate TIN (9 digits)
    public static boolean isValidTIN(String tin) {
        return tin.matches("^\\d{9}$");
    }

    // Validate PhilHealth number (12 digits)
    public static boolean isValidPhilHealth(String philHealth) {
        return philHealth.matches("^\\d{12}$");
    }

    // Validate Pag-IBIG number (10 digits)
    public static boolean isValidPagIbig(String pagIbig) {
        return pagIbig.matches("^\\d{10}$");
    }

    // Load user data from file
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

    // Save user data to file
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

    public static User findUser(String username) {
        HashMap<String, User> userDatabase = loadUsers();
        return userDatabase.get(username);
    }
}
