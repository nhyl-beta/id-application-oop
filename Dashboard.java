import java.util.ArrayList;
import java.util.List;

public class Dashboard {

    private final static List<String> finalizedDetails = new ArrayList<>();

    public void run() {
        try {
            while (true) {
                displayMenu();
                String entry = Main.sc.nextLine().trim().toUpperCase();
                if ("E".equals(entry)) {
                    System.out.println("Exiting the system. Thank you!");
                    break;
                }
                handleUserEntry(entry);
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void displayMenu() {
        Utils.displayFramedMessage("Dashboard");
        System.out.println("Press the corresponding number to proceed to the ID application form.");
        System.out.println("1 for Postal ID");
        System.out.println("2 for Barangay ID");
        System.out.println("3 for Company ID");
        System.out.println("Press E to Exit");
        System.out.print("Enter your choice: ");
    }

    private static void handleUserEntry(String entry) {
        switch (entry) {
            case "1":
                showPostalForm();
                break;
            case "2":
                showBarangayForm();
                break;
            case "3":
                showCompanyForm();
                break;
            default:
                System.out.println("Invalid choice. Please select 1, 2, 3, or E.");
        }
    }

    private static void showPostalForm() {
        System.out.println("You selected Postal ID. Please fill out the Postal ID application form.");
        User user = autofillOrManual();
        System.out.print("Enter Address: ");
        String address = Main.sc.nextLine();
        String validDate = Utils.getValidDate("Enter your date of birth (MM-DD-YYYY): ");
        String nationality = Utils.isValidString("Enter your Nationality: ");

        if (editApplicationDetails(user, new String[]{address, validDate, nationality})) {
            displayPostalDetails(user, address, validDate, nationality);
            addFinalizedDetails("Postal ID", user, address, validDate, nationality);
            displayFinalDetails("Postal ID", user, address, validDate, nationality);
        }
    }

    private static void showBarangayForm() {
        System.out.println("You selected Barangay ID. Please fill out the Barangay ID application form.");
        User user = autofillOrManual();
        System.out.print("Enter Address: ");
        String address = Main.sc.nextLine();
        String validDate = Utils.getValidDate("Enter your date of birth (MM-DD-YYYY): ");
        String contactNumber = user.getPhoneNumber();
        String sex = Utils.getValidGender("Enter your gender (M/F): ");
        System.out.print("Enter Civil Status: ");
        String civilStatus = Main.sc.nextLine();
        String[] emergencyDetails = getEmergencyDetails();

        if (editApplicationDetails(user, new String[]{address, validDate, contactNumber, sex, civilStatus}, emergencyDetails)) {
            displayBarangayDetails(user, address, validDate, contactNumber, sex, civilStatus, emergencyDetails);
            addFinalizedDetails("Barangay ID", user, address, validDate, contactNumber, sex, civilStatus, 
                                emergencyDetails[0], emergencyDetails[1], emergencyDetails[2]);
            displayFinalDetails("Barangay ID", user, address, validDate, contactNumber, sex, civilStatus, 
                                emergencyDetails[0], emergencyDetails[1], emergencyDetails[2]);
        }
    }

    private static void showCompanyForm() {
        System.out.println("You selected Company ID. Please fill out the Company ID application form.");
        User user = autofillOrManual();
        System.out.print("Enter Address: ");
        String address = Main.sc.nextLine();
        String tin = Utils.getValidInput("Enter TIN: ", Utils::isValidTIN, "Invalid TIN.");
        String philHealth = Utils.getValidInput("Enter PhilHealth Number: ", Utils::isValidPhilHealth, "Invalid PhilHealth number.");
        String pagIbig = Utils.getValidInput("Enter Pag-IBIG Number: ", Utils::isValidPagIbig, "Invalid Pag-IBIG number.");
        String[] emergencyDetails = getEmergencyDetails();

        if (editApplicationDetails(user, new String[]{address, tin, philHealth, pagIbig}, emergencyDetails)) {
            displayCompanyDetails(user, address, tin, philHealth, pagIbig, emergencyDetails);
            addFinalizedDetails("Company ID", user, address, tin, philHealth, pagIbig, 
                                emergencyDetails[0], emergencyDetails[1], emergencyDetails[2]);
            displayFinalDetails("Company ID", user, address, tin, philHealth, pagIbig, 
                                emergencyDetails[0], emergencyDetails[1], emergencyDetails[2]);
        }
    }
    
    private static void addFinalizedDetails(String formType, User user, String... details) {
        StringBuilder finalizedDetail = new StringBuilder();
        finalizedDetail.append("\n--- Finalized Details ---\n");
        finalizedDetail.append("Form Type: ").append(formType).append("\n");
        finalizedDetail.append("Name: ").append(user.getFirstName()).append(" ")
                .append(user.getMiddleName()).append(" ").append(user.getLastName()).append("\n");
        finalizedDetail.append("Age: ").append(user.getAge()).append("\n");
        finalizedDetail.append("Phone Number: ").append(user.getPhoneNumber()).append("\n");
        for (String detail : details) {
            finalizedDetail.append(detail).append("\n");
        }
        finalizedDetails.add(finalizedDetail.toString());
    }

    private static void displayFinalDetails(String formType, User user, String... details) {
        Utils.displayFramedMessage("Finalized Details");
        System.out.println("Form Type: " + formType);
        System.out.printf("Name: %s %s %s%n", user.getFirstName(), user.getMiddleName(), user.getLastName());
        System.out.printf("Age: %d%n", user.getAge());
        System.out.printf("Phone Number: %s%n", user.getPhoneNumber());
        for (String detail : details) {
            System.out.println(detail);
        }

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Return to Dashboard");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            String choice = Main.sc.nextLine().trim();
            if ("1".equals(choice)) {
                return; // Return to the main menu
            } else if ("2".equals(choice)) {
                System.out.println("Exiting the system. Thank you!");
                System.exit(0); // Exit the application
            } else {
                System.out.println("Invalid choice. Please select 1 or 2.");
            }
        }
    }

    private static User autofillOrManual() {
        System.out.print("Do you want to autofill the form? (y/n): ");
        String choice = Main.sc.nextLine().trim().toLowerCase();
        if (choice.equals("y")) {
            System.out.print("Enter your username: ");
            String username = Main.sc.nextLine().trim().toLowerCase();
            User user = Utils.findUser(username);
            if (user != null) {
                System.out.print("Enter your password: ");
                String password = Main.sc.nextLine();
                if (user.getPassword().equals(password)) {
                    System.out.println("Autofilling details...");
                    System.out.printf("Name: %s %s %s%n", user.getFirstName(), user.getMiddleName(), user.getLastName());
                    System.out.printf("Age: %d%n", user.getAge());
                    System.out.printf("Phone Number: %s%n", user.getPhoneNumber());
                    return user;
                } else {
                    System.out.println("Incorrect password. Switching to manual entry.");
                }
            } else {
                System.out.println("User not found. Switching to manual entry.");
            }
        }
        System.out.println("Please enter your details manually:");
        return createUserManually();
    }

    private static User createUserManually() {
        String firstName = Utils.getValidName("Enter your First Name: ");
        String middleName = Utils.getValidName("Enter your Middle Name: ");
        String lastName = Utils.getValidName("Enter your Last Name: ");
        int age = Utils.getValidInteger("Enter Age: ", a -> a > 0, "Age must be a positive number.");
        String phoneNumber = Utils.getValidInput("Enter your contact number (11 digits, starting with 09): ", Utils::isValidPhoneNumber, "Invalid phone number.");

        return new User("", lastName, firstName, middleName, age, phoneNumber, "");
    }

    private static String[] getEmergencyDetails() {
        System.out.println("In case of emergency:");
        System.out.print("Enter Full Name: ");
        String emergencyName = Main.sc.nextLine();
        String emergencyContact = Utils.getValidInput("Enter Contact Number: ", Utils::isValidPhoneNumber, "Invalid phone number.");
        System.out.print("Enter Relationship: ");
        String relationship = Main.sc.nextLine();
        return new String[]{emergencyName, emergencyContact, relationship};
    }

    private static boolean editApplicationDetails(User user, String[] mainDetails, String... optionalDetails) {
        System.out.println("\nReview your details:");
        System.out.printf("Name: %s %s %s%n", user.getFirstName(), user.getMiddleName(), user.getLastName());
        System.out.printf("Age: %d%n", user.getAge());
        System.out.printf("Phone Number: %s%n", user.getPhoneNumber());

        for (String detail : mainDetails) {
            System.out.println(detail);
        }
        if (optionalDetails.length > 0) {
            System.out.println("Emergency Details:");
            System.out.println("  Name: " + optionalDetails[0]);
            System.out.println("  Contact Number: " + optionalDetails[1]);
            System.out.println("  Relationship: " + optionalDetails[2]);
        }

        System.out.print("Would you like to edit any information? (y/n): ");
        String choice = Main.sc.nextLine().trim().toLowerCase();
        return !choice.equals("y");
    }

    public static void displayPostalDetails(User user, String address, String validDate, String nationality) {
        System.out.println("\n--- Postal Details ---");
        System.out.printf("Name: %s %s %s%n", user.getFirstName(), user.getMiddleName(), user.getLastName());
        System.out.printf("Address: %s%n", address);
        System.out.printf("Date of Birth: %s%n", validDate);
        System.out.printf("Nationality: %s%n", nationality);
    }

    public static void displayBarangayDetails(User user, String address, String validDate, String contactNumber,
                                              String sex, String civilStatus, String[] emergencyDetails) {
        System.out.println("\n--- Barangay Details ---");
        System.out.printf("Name: %s %s %s%n", user.getFirstName(), user.getMiddleName(), user.getLastName());
        System.out.printf("Address: %s%n", address);
        System.out.printf("Date of Birth: %s%n", validDate);
        System.out.printf("Contact Number: %s%n", contactNumber);
        System.out.printf("Gender: %s%n", sex);
        System.out.printf("Civil Status: %s%n", civilStatus);
        System.out.println("Emergency Contact:");
        System.out.printf("  Name: %s%n", emergencyDetails[0]);
        System.out.printf("  Contact Number: %s%n", emergencyDetails[1]);
        System.out.printf("  Relationship: %s%n", emergencyDetails[2]);
    }

    public static void displayCompanyDetails(User user, String address, String tin, String philHealth,
                                             String pagIbig, String[] emergencyDetails) {
        System.out.println("\n--- Company Details ---");
        System.out.printf("Name: %s %s %s%n", user.getFirstName(), user.getMiddleName(), user.getLastName());
        System.out.printf("Address: %s%n", address);
        System.out.printf("TIN: %s%n", tin);
        System.out.printf("PhilHealth Number: %s%n", philHealth);
        System.out.printf("Pag-IBIG Number: %s%n", pagIbig);
        System.out.println("Emergency Contact:");
        System.out.printf("  Name: %s%n", emergencyDetails[0]);
        System.out.printf("  Contact Number: %s%n", emergencyDetails[1]);
        System.out.printf("  Relationship: %s%n", emergencyDetails[2]);
    }
}
