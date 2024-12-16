package software.ulpgc;

import software.ulpgc.control.CommandFactory;
import software.ulpgc.view.WorkingDaysService;

public class Main {
    public static void main(String[] args) {
        CommandFactory factory = new CommandFactory();
        new WorkingDaysService(7070,factory).start();
    }
}
