import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Task4 {
    /**
     * @param path - path with time with binary action in format:
     *             hh:mm hh:mm\n where first is start binary action,
     *             second is end binary action
     * @return list of a time with binary action
     */
    public static List<TimeAndBinaryAction> getTimePoints(String path) {
        List<TimeAndBinaryAction> list = new LinkedList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            while (scanner.hasNext()) {
                String[] timeIn = scanner.next().split(":");
                int hour = Integer.parseInt(timeIn[0]);
                int minute = Integer.parseInt(timeIn[1]);
                list.add(new TimeAndBinaryAction(hour, minute, true));

                String[] timeOut = scanner.next().split(":");
                hour = Integer.parseInt(timeOut[0]);
                minute = Integer.parseInt(timeOut[1]);
                list.add(new TimeAndBinaryAction(hour, minute, false));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static void main(String[] args) {
        //time with binary action for the person is time + (in/out) of the bank
        List<TimeAndBinaryAction> timeWithPeople = getTimePoints(args[0]);
        Collections.sort(timeWithPeople);
        Queue<TimePeriod> periodsWithMaxPeople = TimePeriod.getPeriodsWithMaxPeople(timeWithPeople);

        for (TimePeriod timePeriod : periodsWithMaxPeople) {
            System.out.println(timePeriod.getStartHHMM() + " " + timePeriod.getEndHHMM());
        }
    }
}
