public class Dashboard {

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
        } finally {
            Main.sc.close();
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

        displayPostalDetails(user, address, validDate, nationality);
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

        displayBarangayDetails(user, address, validDate, contactNumber, sex, civilStatus, emergencyDetails);
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

        displayCompanyDetails(user, address, tin, philHealth, pagIbig, emergencyDetails);
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
        System.out.print("Enter Age: ");
        int age = Utils.getValidInteger("Enter Age: ", a -> a > 0, "Age must be a positive number.");
        System.out.print("Enter Phone Number: ");
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

    private static void displayPostalDetails(User user, String address, String validDate, String nationality) {
        System.out.println("\n--- Postal ID Details ---");
        System.out.printf("Name: %s %s %s%n", user.getFirstName(), user.getMiddleName(), user.getLastName());
        System.out.println("Age: " + user.getAge());
        System.out.println("Phone Number: " + user.getPhoneNumber());
        System.out.println("Address: " + address);
        System.out.println("Date of Birth: " + validDate);
        System.out.println("Nationality: " + nationality);
    }

    private static void displayBarangayDetails(User user, String address, String validDate, String contactNumber, String sex, String civilStatus, String[] emergencyDetails) {
        System.out.println("\n--- Barangay Gaya-Gaya Resident ID Details ---");
        System.out.printf("Name: %s %s %s%n", user.getFirstName(), user.getMiddleName(), user.getLastName());
        System.out.println("Age: " + user.getAge());
        System.out.println("Phone Number: " + contactNumber);
        System.out.println("Address: " + address);
        System.out.println("Date of Birth: " + validDate);
        System.out.println("Sex: " + sex);
        System.out.println("Civil Status: " + civilStatus);
        System.out.println("In Case of Emergency:");
        System.out.println("  Name: " + emergencyDetails[0]);
        System.out.println("  Contact Number: " + emergencyDetails[1]);
        System.out.println("  Relationship: " + emergencyDetails[2]);
    }

    private static void displayCompanyDetails(User user, String address, String tin, String philHealth, String pagIbig, String[] emergencyDetails) {
        System.out.println("\n--- Company ID Details ---");
        System.out.printf("Name: %s %s %s%n", user.getFirstName(), user.getMiddleName(), user.getLastName());
        System.out.println("Age: " + user.getAge());
        System.out.println("Phone Number: " + user.getPhoneNumber());
        System.out.println("Address: " + address);
        System.out.println("TIN: " + tin);
        System.out.println("PhilHealth Number: " + philHealth);
        System.out.println("Pag-IBIG Number: " + pagIbig);
        System.out.println("In Case of Emergency:");
        System.out.println("  Name: " + emergencyDetails[0]);
        System.out.println("  Contact Number: " + emergencyDetails[1]);
        System.out.println("  Relationship: " + emergencyDetails[2]);
    }
}
