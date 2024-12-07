import java.util.Scanner;

public class Main {
    //Eto kaya para matawag ko siya sa ibang clss na naka sc na den
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        IdSystem idRun = new IdSystem();
        Dashboard db = new Dashboard(null);



        //Main function to run the entire system
        idRun.run();
        

        //test run
        // Utils.clearScreen();
        //db.displayDashboard(null);
    }
}