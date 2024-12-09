import java.util.HashMap;

public class UserInterface {
    private final HashMap<String, User> userDatabase = Utils.loadUsers();

    public void displayLoginPage() {
        while (true) {
            Utils.clearScreen();
            Utils.displayFramedMessage("      Login/Register Page      ");

            System.out.println("1. Create Account");
            System.out.println("2. Sign In");
            System.out.println("3. Exit");
            System.out.print("Please select an option: ");
            String choice = Main.sc.nextLine().trim();

            switch (choice) {
                case "1":
                    register();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    System.out.println("Exiting the system...");
                    Utils.saveUsers(userDatabase);
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void register() {
        Utils.clearScreen();
        Utils.displayFramedMessage("      Registration      ");

        String username = Utils.getValidInput("Enter a username: ", input -> !input.isEmpty() && !userDatabase.containsKey(input), "Invalid username or already exists.");
        String lastName = Utils.getValidName("Enter your Last name: ");
        String firstName = Utils.getValidName("Enter your First name: ");
        String middleName = Utils.getValidName("Enter your Middle name: ");
        int age = Utils.getValidInteger("Enter your age: ", i -> i > 0, "Invalid age. Please enter a positive number.");
        String phoneNumber = Utils.getValidInput("Enter your phone number (11 digits, starting with 09): ", Utils::isValidPhoneNumber, "Invalid phone number.");
        String password = Utils.getValidInput("Enter a password: ", input -> !input.isEmpty(), "Password cannot be empty.");

        User user = new User(username, lastName, firstName, middleName, age, phoneNumber, password);
        userDatabase.put(username, user);
        System.out.println("Registration successful!");
    }

    private void login() {
        Utils.clearScreen();
        Utils.displayFramedMessage("              Login              ");
    
        String username = Utils.getValidInput(
                "Enter your username: ",
                input -> !input.isEmpty(),
                "Username cannot be empty."
        ).toLowerCase(); // Convert input to lowercase for case-insensitivity
    
        // If username is not found, ask to register or exit
        if (!userDatabase.containsKey(username)) {
            System.out.println("Username not found.");
            System.out.println("Press 1 to Register.");
            System.out.println("Press 2 to Exit.");
    
            while (true) {
                String choice = Utils.getValidInput(
                        "Your choice: ",
                        input -> input.equals("1") || input.equals("2"),
                        "Invalid input. Please press 1 or 2."
                );
    
                if (choice.equals("1")) {
                    register();
                    return; // Exit login method after registering
                } else if (choice.equals("2")) {
                    System.out.println("Exiting to main menu...");
                    return; // Exit login method
                }
            }
        }
    
        // Username found; proceed to password validation
        User user = userDatabase.get(username);
    
        String password = Utils.getValidInput(
                "Enter your password: ",
                input -> input.equals(user.getPassword()),
                "Incorrect password. Please try again."
        );
    
        System.out.println("Login successful! Welcome, " + username + "!");
        displayDashboard(user);
    }
    

    private void displayDashboard(User user) {
        Utils.clearScreen();
        Utils.displayFramedMessage("      Dashboard      ");

        while (true) {
            System.out.println("1. View Profile");
            System.out.println("2. Forms");
            System.out.println("3. Logout");
            System.out.println("4. Exit");
            System.out.print("Please select an option: ");
            String choice = Main.sc.nextLine().trim();

            switch (choice) {
                case "1":
                    displayProfile(user);
                    break;
                case "2":
                    Dashboard db = new Dashboard();
                    db.run();
                    break;
                case "3":
                    System.out.println("Logging out...");
                    return;
                case "4":
                    System.out.println("Exiting program. Goodbye!");
                    Utils.saveUsers(userDatabase);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void displayProfile(User user) {
        Utils.clearScreen();
        Utils.displayFramedMessage("      Profile      ");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Name: " + user.getFirstName() + "");
        System.out.println("Age: " + user.getAge());
        System.out.println("Phone: " + user.getPhoneNumber());
    }
}
