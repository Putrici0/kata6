package software.ulpgc.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;

import static java.time.DayOfWeek.*;

public class WorkingDaysCalendar{

    public Iterator<LocalDate> iteratorFor(LocalDate date) {

        return new Iterator<LocalDate>() {

            LocalDate current = date;
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public LocalDate next() {
                var next = current.plusDays(1);
                while (!isWorkingDay(next)) {
                    next = next.plusDays(1);
                }
                current = next;
                return next;
            }

            private boolean isWorkingDay(LocalDate date) {
                return workingDays.contains(date.getDayOfWeek());
            }
        };
    }

    private final Set<DayOfWeek> workingDays = Set.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY);
}
