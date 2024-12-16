package software.ulpgc.control.commands;

import software.ulpgc.control.Command;
import software.ulpgc.model.WorkingDaysCalendar;

import java.time.LocalDate;
import java.util.Iterator;

public class WorkingDaysCommand implements Command {

    private final Input input;
    private final Output output;

    public WorkingDaysCommand(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void execute() {
        Iterator<LocalDate> iterator = new WorkingDaysCalendar().iteratorFor(input.start());
        LocalDate end = input.start();
        int workingDays = 0;
        for (int i = 0; i < input.workingDays(); i++) {
            workingDays++;
            end = iterator.next();
        }
        output.workingDays(workingDays);
    }

    public interface Input {
        LocalDate start();

        int workingDays();
    }

    public interface Output {
        void workingDays(int days);
    }
}
