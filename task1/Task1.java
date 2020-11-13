import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Task1 {
    public static List<Short> getSequence(String path) {
        List<Short> sequence = new ArrayList<>(1000);
        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            while (scanner.hasNext()) {
                short next = scanner.nextShort();
                sequence.add(next);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return sequence;
    }

    public static void main(String[] args) {
        List<Short> sequence = getSequence(args[0]);
        Collections.sort(sequence);

        double percentile = StatisticUtil.getPercentile(sequence);
        double median = StatisticUtil.getMedian(sequence);
        double min = StatisticUtil.getMin(sequence);
        double max = StatisticUtil.getMax(sequence);
        double average = StatisticUtil.getAverage(sequence);

        System.out.printf("%.2f%n", percentile);
        System.out.printf("%.2f%n", median);
        System.out.printf("%.2f%n", max);
        System.out.printf("%.2f%n", min);
        System.out.printf("%.2f%n", average);
    }
}