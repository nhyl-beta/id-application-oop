public class Utils {
    public static void frame(int stringLength) {
        System.out.println("-".repeat(stringLength));
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    
    //TODO: Grace gawa ka ng method na gagawa ng vertical line like this (|) na depende sa height ng text like this example

    // |---------------------------|
    // |                           | 1
    // |          (Title)          |
    // |                           | 1
    // |---------------------------|

    // public void verticalFrame(){
            //TODO: dito mo lalagay tatanggalin mo yung comments thankyouuu
    // }
}
