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
                    System.out.println("Exiting the system. Have a great day!");
                    break;
                }
                handleUserEntry(entry);
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void displayMenu() {
        Utils.clearScreen();
        Utils.displayFramedMessage("Dashboard");
        System.out.println("Press the corresponding number to proceed to the ID application form.");
        System.out.println("1 for Postal ID");
        System.out.println("2 for Barangay ID");
        System.out.println("3 for Company ID");
        System.out.println("Press E to Exit");
        System.out.print("Please select an option: ");
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
                System.out.println("Invalid entry. Please select 1, 2, 3, or E.");
        }
    }

    private static void showPostalForm() {
        Utils.clearScreen();
        Utils.displayFramedMessage("POSTAL ID");
        System.out.println("You selected Postal ID. Please fill out the Postal ID application form.");
        User user = autofillOrManual();
        System.out.print("Enter Address: ");
        String address = Main.sc.nextLine();
        String validDate = Utils.getValidDate("Enter your date of birth (MM-DD-YYYY): ");
        String nationality = Utils.getValidInput("Enter your Nationality: ", Utils::isValidString, "Nationality cannot be empty.");

        if (editApplicationDetails(user, new String[]{address, validDate, nationality})) {
            displayPostalDetails(user, address, validDate, nationality);
            addFinalizedDetails("Postal ID", user, address, validDate, nationality);
            displayFinalDetails("Postal ID", user, address, validDate, nationality);
        }
    }

    private static void showBarangayForm() {
        Utils.clearScreen();
        Utils.displayFramedMessage("BARANGAY ID");
        System.out.println("You selected Barangay ID. Please fill out the Barangay ID application form.");
        User user = autofillOrManual();
        System.out.print("Enter Address: ");
        String address = Main.sc.nextLine();
        String validDate = Utils.getValidDate("Enter your date of birth (MM-DD-YYYY): ");
        String contactNumber = user.getPhoneNumber();
        String sex = Utils.getValidGender("Enter your gender (Male/Female): ");
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
        Utils.clearScreen();
        Utils.displayFramedMessage("COMPANY ID");
        System.out.println("You selected Company ID. Please fill out the Company ID application form.");
        User user = autofillOrManual();
        System.out.print("Enter Address: ");
        String address = Main.sc.nextLine();
        String tin = Utils.getValidInput("Enter TIN: ", Utils::isValidTIN, "Invalid TIN.");
        String philHealth = Utils.getValidInput("Enter PhilHealth Number: ", Utils::isValidPhilHealth, "Invalid PhilHealth number.");
        String gender = Utils.getValidGender("Enter your Gender (Male/Female): "); // Correct field label for gender
        String civilStatus = Utils.getValidInput("Enter Civil Status: ", Utils::isValidString, "Invalid Civil Status."); // Correct field for civil status
        String[] emergencyDetails = getEmergencyDetails();
    
        if (editApplicationDetails(user, new String[]{address, tin, philHealth, gender, civilStatus}, emergencyDetails)) {
            displayCompanyDetails(user, address, tin, philHealth, gender, civilStatus, emergencyDetails);
            addFinalizedDetails("Company ID", user, 
                                "Address: " + address, "TIN: " + tin, "PhilHealth Number: " + philHealth, 
                                "Gender: " + gender, "Civil Status: " + civilStatus,
                                "Emergency Contact Name: " + emergencyDetails[0], 
                                "Emergency Contact Number: " + emergencyDetails[1], 
                                "Emergency Contact Relationship: " + emergencyDetails[2]);
            displayFinalDetails("Company ID", user, 
                                "Address: " + address, "TIN: " + tin, "PhilHealth Number: " + philHealth, 
                                "Gender: " + gender, "Civil Status: " + civilStatus,
                                "Emergency Contact Name: " + emergencyDetails[0], 
                                "Emergency Contact Number: " + emergencyDetails[1], 
                                "Emergency Contact Relationship: " + emergencyDetails[2]);
        }
    }
    
    private static void addFinalizedDetails(String formType, User user, String... details) {
        StringBuilder finalizedDetail = new StringBuilder();
        finalizedDetail.append("Finalized Details\n");
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
        System.out.println("User Information:");
        System.out.printf("  Name: %s %s %s%n", user.getFirstName(), user.getMiddleName(), user.getLastName());
        System.out.printf("  Age: %d%n", user.getAge());
        System.out.printf("  Phone Number: %s%n", user.getPhoneNumber());

        System.out.println("Additional Details:");
        for (String detail : details) {
            System.out.printf("  %s%n", detail);
        }

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Return to Dashboard");
            System.out.println("2. Exit");
            System.out.print("Please select an option: ");
            String entry = Main.sc.nextLine().trim();
            if ("1".equals(entry)) {
                return;
            } else if ("2".equals(entry)) {
                System.out.println("Exiting the system. Have a great day!");
                System.exit(0);
            } else {
                System.out.println("Invalid entry. Please select 1 or 2.");
            }
        }
    }
    
    

    private static User autofillOrManual() {
        System.out.print("Do you want to autofill the form? (yes/no): ");
        String entry = Main.sc.nextLine().trim().toLowerCase();
        if (entry.equals("y")) {
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
        System.out.println("Name: " + user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName());
        System.out.println("Age: " + user.getAge());
        System.out.println("Phone Number: " + user.getPhoneNumber());
    
        // Define labels for the main details
        String[] mainDetailLabels = {};
    
        // Display main details with labels
        System.out.println("\nAdditional Details:");
        for (int i = 0; i < mainDetails.length; i++) {
            String label = i < mainDetailLabels.length ? mainDetailLabels[i] : " ";
            System.out.println("  " + label + "  " + mainDetails[i]);
        }
    
        // Display optional/emergency details if present
        if (optionalDetails.length > 0) {
            System.out.println("\nIn case of emergency:");
            System.out.println("  Name: " + optionalDetails[0]);
            System.out.println("  Contact Number: " + optionalDetails[1]);
            System.out.println("  Contact Relationship: " + optionalDetails[2]);
        }
    
        System.out.print("\nWould you like to edit any information? (yes/no): ");
        String entry = Main.sc.nextLine().trim().toLowerCase();
        return !entry.equals("yes");
    }
    public static void displayPostalDetails(User user, String address, String validDate, String nationality) {
        Utils.displayFramedMessage("POSTAL ID");
        System.out.printf("Name: %s %s %s%n", user.getFirstName(), user.getMiddleName(), user.getLastName());
        System.out.printf("Address: %s%n", address);
        System.out.printf("Date of Birth: %s%n", validDate);
        System.out.printf("Nationality: %s%n", nationality);
    }

    public static void displayBarangayDetails(User user, String address, String validDate, String contactNumber,
                                              String sex, String civilStatus, String[] emergencyDetails) {
        Utils.displayFramedMessage("Barangay ID");
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
                                         String gender, String civilStatus, String[] emergencyDetails) {
    Utils.displayFramedMessage("Company ID");
    System.out.printf("Name: %s %s %s%n", user.getFirstName(), user.getMiddleName(), user.getLastName());
    System.out.printf("Address: %s%n", address);
    System.out.printf("TIN: %s%n", tin);
    System.out.printf("PhilHealth Number: %s%n", philHealth);
    System.out.printf("Gender: %s%n", gender);
    System.out.printf("Civil Status: %s%n", civilStatus);
    System.out.println("Emergency Contact:");
    System.out.printf("  Name: %s%n", emergencyDetails[0]);
    System.out.printf("  Contact Number: %s%n", emergencyDetails[1]);
    System.out.printf("  Relationship: %s%n", emergencyDetails[2]);
}
}
