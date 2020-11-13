import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TimePeriod {
    /**
     * start for the time period (in minute)
     * end for the time period (in minute)
     */
    private int start;
    private int end;

    public TimePeriod(TimeAndBinaryAction start, TimeAndBinaryAction end) {
        if (start.getMinute() <= end.getMinute()) {
            this.start = start.getMinute();
            this.end = end.getMinute();
        } else {
            throw new IllegalArgumentException("Invalid time period: [" + start + ";" + end + "]");
        }
    }

    public int getEnd() {
        return end;
    }

    public int getStart() {
        return start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getStartHHMM() {
        int hour = getStart() / 60;
        int minute = getStart() % 60;
        if (minute < 10) {
            return hour + ":0" + minute;
        }
        return hour + ":" + minute;
    }

    public String getEndHHMM() {
        int hour = getEnd() / 60;
        int minute = getEnd() % 60;
        if (minute < 10) {
            return hour + ":0" + minute;
        }
        return hour + ":" + minute;
    }

    /**
     * @param sortedPeopleList - sorted array with points of time with action
     * @return - deque of periods, when the maximum number of actions occurs
     */
    public static Deque<TimePeriod> getPeriodsWithMaxPeople(List<TimeAndBinaryAction> sortedPeopleList) {
        Deque<TimePeriod> periodsWithMaxPeople = new LinkedList<>();
        int peopleInCount = 0;
        int maxPeopleCount = 0;

        for (TimeAndBinaryAction timeAndBinaryActionNow : sortedPeopleList) {
            //если вышел
            if (!timeAndBinaryActionNow.isActionIn()) {
                //если конец максимального интервала
                if (peopleInCount == maxPeopleCount) {
                    TimePeriod timePeriod = periodsWithMaxPeople.removeLast();
                    timePeriod.setEnd(timeAndBinaryActionNow.getMinute());
                    periodsWithMaxPeople.addLast(timePeriod);
                }
                peopleInCount--;
            } else {
                //если вошел
                peopleInCount++;
                if (maxPeopleCount < peopleInCount) {
                    //если максимальное число людей меньше, чем теперь
                    maxPeopleCount = peopleInCount;
                    periodsWithMaxPeople.clear();
                    TimePeriod timePeriod = new TimePeriod(timeAndBinaryActionNow, timeAndBinaryActionNow);
                    periodsWithMaxPeople.addLast(timePeriod);
                } else if (maxPeopleCount == peopleInCount) {
                    //если максимальное число людей такое же, как сейчас
                    TimePeriod lastMaxTimePeriod = periodsWithMaxPeople.getLast();
                    //если конец последнего максимального периода не совпадает с нынешнем временем
                    if (lastMaxTimePeriod.getEnd() != timeAndBinaryActionNow.getMinute()) {
                        TimePeriod timePeriod = new TimePeriod(timeAndBinaryActionNow, timeAndBinaryActionNow);
                        periodsWithMaxPeople.addLast(timePeriod);
                    }
                }
            }
        }
        return periodsWithMaxPeople;
    }
}
