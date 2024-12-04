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
        System.out.println();
        System.out.println(message);
        System.out.println();
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
        return getValidInput(prompt, input -> input.matches("^[a-zA-Z ]+$"), "Invalid input. Please enter alphabetic characters and spaces only.");

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

    // Load user data from file
    public static HashMap<String, User> loadUsers() {
        HashMap<String, User> userDatabase = new HashMap<>();
        File file = new File(DATABASE_FILE);

        if (!file.exists()) {
            return userDatabase; // Return an empty database if the file does not exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) { // Ensure all fields are present
                    String username = parts[0];
                    String lastName = parts[1];
                    String firstName = parts[2];
                    String middleName = parts[3];
                    int age = Integer.parseInt(parts[4]);
                    String phoneNumber = parts[5];
                    String password = parts[6];
                    User user = new User(username, lastName, firstName, middleName, age, phoneNumber, password);
                    userDatabase.put(username, user);
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
            //System.out.println("User data saved");
        } catch (IOException e) {
            System.out.println("Error saving user database: " + e.getMessage());
        }
    }
}
