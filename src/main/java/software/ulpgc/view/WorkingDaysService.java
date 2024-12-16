package software.ulpgc.view;

import io.javalin.Javalin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import software.ulpgc.control.CommandFactory;
import software.ulpgc.control.commands.WorkingDateCommand;
import software.ulpgc.control.commands.WorkingDaysCommand;
import software.ulpgc.view.adapters.WorkingDaysAdapter;
import software.ulpgc.view.adapters.WorkingDateAdapter;

public class WorkingDaysService {

    private final int port;
    private final CommandFactory factory;
    private Javalin app;


    public WorkingDaysService(int port, CommandFactory factory) {
        this.port = port;
        this.factory = factory;
        factory.register("working-days", workingDaysBuilder());
        factory.register("working-date", workingDateBuilder());
    }

    public void start() {

        app = Javalin.create()
                .get("/working-days", ctx -> execute("working-days", ctx.req(), ctx.res()))
                .get("/working-date", ctx -> execute("working-date", ctx.req(), ctx.res()))
                .start(port);
    }

    private static CommandFactory.Builder workingDaysBuilder() {
        return (req, res) -> new WorkingDaysCommand(WorkingDaysAdapter.inputOf(req), WorkingDaysAdapter.outputOf(res));
    }

    private static CommandFactory.Builder workingDateBuilder() {
        return (req, res) -> new WorkingDateCommand(WorkingDateAdapter.inputOf(req), WorkingDateAdapter.outputOf(res));
    }

    public void stop() {
        app.stop();
    }

    private void execute(String command, HttpServletRequest req, HttpServletResponse res) {
        factory.with(req, res).build(command).execute();
    }

}
