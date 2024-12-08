public class Dashboard {

    public void run() {

        try {
            displayMenu();
            String entry = Main.sc.nextLine().trim().toUpperCase();
            userEntry(entry);
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

    private static void userEntry(String entry) {
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
            case "E":
                System.out.println("Exiting the system. Thank you!");
                break;
            default:
                System.out.println("Invalid choice. Please select 1, 2, 3, or E.");
        }
    }

    private static void showPostalForm() {
        System.out.println("You selected Postal ID. Please fill out the Postal ID application form.");

        String firstName = Utils.getValidName("Enter your First Name: "); 

        String middleName = Utils.getValidName("Enter your Middle Name: "); 

        String lastName = Utils.getValidName("Enter your Last Name: "); 

        System.out.print("Enter Suffix (e.g., Jr., Sr., etc.): ");
        String suffix = Main.sc.nextLine();

        System.out.print("Enter Address: ");
        String address = sc.nextLine(); 

        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        String dateOfBirth = sc.nextLine(); 

        System.out.print("Enter Nationality: ");
        String nationality = sc.nextLine(); 
        
        System.out.println("\n--- Postal ID Details ---");
        System.out.println("Name: " + firstName + " " + middleName + " " + lastName + " " + suffix);
        System.out.println("Address: " + address);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Nationality: " + nationality);
    }

    private static void showBarangayForm(Scanner sc) {
        System.out.println("You selected Barangay ID. Please fill out the Barangay ID application form.");

        System.out.print("Enter First Name: ");
        String firstName = sc.nextLine(); 

        System.out.print("Enter Middle Name: ");
        String middleName = sc.nextLine(); 

        System.out.print("Enter Surname: ");
        String surname = sc.nextLine(); 

        System.out.print("Enter Suffix (e.g., Jr., Sr., etc.): ");
        String suffix = sc.nextLine();
 
        System.out.print("Enter Address: ");
        String address = sc.nextLine(); 

        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        String dateOfBirth = sc.nextLine(); 

        System.out.print("Enter Contact Number: ");
        String contactNo = sc.nextLine();
        
        System.out.print("Enter Sex (Male/Female): ");
        String sex = sc.nextLine(); 

        System.out.print("Enter Civil Status: ");
        String civilStatus = sc.nextLine(); 

        System.out.println("In case of emergency:");
        
        System.out.print("Enter Full Name: ");
        String emergencyName = sc.nextLine(); 

        System.out.print("Enter Contact Number: ");
        String emergencyContact = sc.nextLine(); 

        System.out.print("Enter Relationship: ");
        String relationship = sc.nextLine(); 

        System.out.println("\n--- Barangay Gaya-Gaya Resident ID Details ---");
        System.out.println("Name: " + firstName + " " + middleName + " " + surname + " " + suffix);
        System.out.println("Address: " + address);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Contact Number: " + contactNo);
        System.out.println("Sex: " + sex);
        System.out.println("Civil Status: " + civilStatus);
        System.out.println("In Case of Emergency:");
        System.out.println("  Name: " + emergencyName);
        System.out.println("  Contact Number: " + emergencyContact);
        System.out.println("  Relationship: " + relationship);

    }

    private static void showCompanyForm(Scanner sc) {
        System.out.println("You selected Company ID. Please fill out the Company ID application form.");

        System.out.println("Enter First Name: ");
        String firstName = sc.nextLine(); 

        System.out.println("Enter Middle Name: ");
        String middleName = sc.nextLine(); 

        System.out.println("Enter Surname: ");
        String surname = sc.nextLine(); 

        System.out.println("Enter Suffix (e.g., Jr., Sr., etc.): ");
        String suffix = sc.nextLine();
 
        System.out.println("Enter Address: ");
        String address = sc.nextLine(); 

        System.out.println("Enter TIN: ");
        String tin = sc.nextLine(); 

        System.out.println("Enter PhilHealth Number: ");
        String philHealth = sc.nextLine(); 

        System.out.println("Enter Pag-IBIG Number: ");
        String pagIbig = sc.nextLine(); 

        System.out.println("In case of emergency:");

        System.out.println("Enter Full Name: ");
        String emergencyName = sc.nextLine(); 

        System.out.println("Enter Contact Number: ");
        String emergencyContact = sc.nextLine(); 

        System.out.println("Enter Relationship: ");
        String relationship = sc.nextLine(); 

        System.out.println("\n--- Company ID Details ---");
        System.out.println("Name: " + firstName + " " + middleName + " " + surname + " " + suffix);
        System.out.println("Address: " + address);
        System.out.println("TIN: " + tin);
        System.out.println("PhilHealth Number: " + philHealth);
        System.out.println("Pag-IBIG Number: " + pagIbig);
        System.out.println("In Case of Emergency:");
        System.out.println("  Name: " + emergencyName);
        System.out.println("  Contact Number: " + emergencyContact);
        System.out.println("  Relationship: " + relationship);
    }
}
