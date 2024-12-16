package software.ulpgc.control.commands;

import org.eclipse.jetty.http.HttpTester;
import software.ulpgc.control.Command;
import software.ulpgc.model.WorkingDaysCalendar;

import java.time.LocalDate;
import java.util.Iterator;

public class WorkingDateCommand implements Command {

    private final Input input;
    private final Output output;

    public WorkingDateCommand(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void execute() {
        Iterator<LocalDate> iterator = new WorkingDaysCalendar().iteratorFor(input.start());
        LocalDate end = input.start();
        for (int i = 0; i < input.workingDays(); i++) {
            end = iterator.next();
        }
        output.end(end);
    }

    public interface Input {
        LocalDate start();

        int workingDays();
    }

    public interface Output {
        void end(LocalDate date);
    }





}
