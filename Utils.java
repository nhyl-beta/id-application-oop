public class Utils {
    public static void frame(int stringLength) {
        System.out.println("-".repeat(stringLength));
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
