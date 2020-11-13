import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CashRegister {
    /**
     * Cash register
     * averageQueueLength - average queue length measurements
     * INTERVALS_COUNT - count of average queue length measurements
     */
    private final double[] averageQueueLength = new double[INTERVALS_COUNT];
    public static final int INTERVALS_COUNT = 16;

    public CashRegister(String path, int indexOfCashRegister) {
        int count = 0;
        try (Scanner scanner = new Scanner(new FileInputStream(path + "Cash" + indexOfCashRegister + ".txt"))) {
            while (scanner.hasNext()) {
                if (count > INTERVALS_COUNT) {
                    throw new IllegalArgumentException("Intervals count mast be 16");
                }
                averageQueueLength[count] = scanner.nextDouble();
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        if (count != 16) {
            throw new IllegalArgumentException("Intervals count mast be 16");
        }
    }

    public double[] getAverageQueueLength() {
        return averageQueueLength;
    }
}
