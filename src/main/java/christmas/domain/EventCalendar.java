package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
/*
 * 이벤트 달력
 * 주말과 평일, 증정이벤트 날짜가  저장된 달력이 저장되어있다.
 * 사용자가 입력한 날짜를 전달받으먼 그 날짜에 대한 이벤트 정보를 반환한다.
 * */


enum DayType {
    WEEKDAY, WEEKEND, EVENTDAY
}

public class EventCalendar {
    private Map<LocalDate, DayType> eventCalendar = new HashMap<>();
    private final int YEAR = 2023;
    private final int MONTH = 12;

    public EventCalendar() {
        initializeCalendar();
    }

    private void initializeCalendar() {
        LocalDate start = LocalDate.of(YEAR, MONTH, 1);
        LocalDate end = LocalDate.of(YEAR, MONTH, 31);

        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            DayType dayType = checkWeekend(date);
            eventCalendar.put(date, dayType);
        }
    }

    private DayType checkWeekend(LocalDate date) {
        if (date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY)
            return DayType.WEEKEND;
        return checkEventday(date);
    }

    private DayType checkEventday(LocalDate date) {
        if (date.getDayOfWeek() == DayOfWeek.SUNDAY || date.getDayOfMonth() == 25)
            return DayType.EVENTDAY;
        return DayType.WEEKDAY;
    }

}
