public class TimeAndBinaryAction implements Comparable<TimeAndBinaryAction> {
    /**
     * minute - time in minutes, when a binary action occurs
     * action - true, if start this action; false, if stop this action
     */
    private final int minute;
    private final boolean actionIn;

    public TimeAndBinaryAction(int hour, int minute, boolean actionIn) {
        this.minute = hour * 60 + minute;
        this.actionIn = actionIn;
    }

    @Override
    public int compareTo(TimeAndBinaryAction o) {
        return Integer.compare(minute, o.minute);
    }

    public Integer getMinute() {
        return minute;
    }

    public boolean isActionIn() {
        return actionIn;
    }
}
