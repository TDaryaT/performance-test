import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task3 {
    private static final int CASH_REGISTERS_COUNT = 5;
    private static final int TIME_COUNT = 16;

    public static void main(String[] args) {
        try {
            Scanner[] scanners = new Scanner[CASH_REGISTERS_COUNT];
            for (int i = 0; i < CASH_REGISTERS_COUNT; i++) {
                scanners[i] = new Scanner(new FileInputStream(args[0] + "Cash" + (i + 1) + ".txt"));
            }

            float max = 0;
            int maxTimeCount = 1;
            for (int i = 1; i <= TIME_COUNT; i++) {
                float sumPeopleCount = 0;
                for (int j = 0; j < CASH_REGISTERS_COUNT; j++) {
                    String s = scanners[j].nextLine();
                    s = s.substring(0, s.length() - 2);
                    sumPeopleCount += Float.parseFloat(s);
                }
                if (max < sumPeopleCount) {
                    max = sumPeopleCount;
                    maxTimeCount = i;
                }
            }

            for (int i = 0; i < CASH_REGISTERS_COUNT; i++) {
                scanners[i].close();
            }
            System.out.println(maxTimeCount);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}
