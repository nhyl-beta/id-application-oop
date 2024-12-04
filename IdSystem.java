public class IdSystem {
    private final UserInterface userInterface = new UserInterface();

    //Initial method to run the system
    public void run() {
        Utils.clearScreen();
        String landingMessage = "      Welcome to Philippines!      ";
        Utils.displayFramedMessage(landingMessage);
        displayLandingMenu();
    }

    private void displayLandingMenu() {
        while (true) {
            System.out.println("Press Enter to Continue");
            System.out.println("Press 1 to Exit.");
            String option = Main.sc.nextLine().trim();

            if (option.isEmpty()) {
                Utils.clearScreen();
                userInterface.displayLoginPage();
                break;
            } else if ("1".equals(option)) {
                Utils.clearScreen();
                System.out.println("Exiting program. Goodbye!");
                System.exit(0);
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
}
