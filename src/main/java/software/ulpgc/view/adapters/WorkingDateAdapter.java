package software.ulpgc.view.adapters;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import software.ulpgc.control.commands.WorkingDateCommand;

import java.io.IOException;
import java.time.LocalDate;

public class WorkingDateAdapter {

    public static WorkingDateCommand.Input inputOf(HttpServletRequest request) {
        return new WorkingDateCommand.Input() {
            @Override
            public LocalDate start() {
                return LocalDate.parse(request.getParameter("start"));
            }

            @Override
            public int workingDays() {
                return Integer.parseInt(request.getParameter("days"));
            }
        };
    }

    public static WorkingDateCommand.Output outputOf(HttpServletResponse response) {

        return date -> {
            try {
                response.getWriter().write(date.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
