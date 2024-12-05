import java.util.HashMap;

public class UserInterface {
    private final HashMap<String, User> userDatabase = Utils.loadUsers();

    public void displayLoginPage() {
        while (true) {
            Utils.clearScreen();
            Utils.displayFramedMessage("      Login/Register Page      ");

            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");
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
                input -> true,
                "Username cannot be empty."
        );
    
        if (!userDatabase.containsKey(username)) {
            System.out.println("Username not found.");
            System.out.println("Press 1 to Register.");
            System.out.println("Press 2 to Re-Login.");
            System.out.println("Press 3 to Exit.");
    
            while (true) {
                String choice = Utils.getValidInput(
                        "Your choice: ",
                        input -> input.equals("1") || input.equals("2"),
                        "Invalid input. Please press 1 or 2."
                );
    
                if (choice.equals("1")) {
                    register();
                    return;
                } else if (choice.equals("2")) {
                    login();
                    return;
                }else if (choice.equals("3")) {
                    System.out.println("Exiting to main menu...");
                    return;
                }
            }
        }
    
        User user = userDatabase.get(username);
    
        String password = Utils.getValidInput(
                "Enter your password: ",
                input -> input.equals(user.getPassword()),
                "Incorrect password. Please try again."
        );
    
        System.out.println("Login successful! Welcome, " + username + "!");
    
        
        Dashboard dashboard = new Dashboard(userDatabase);
        dashboard.displayDashboard(user);
    }
}
