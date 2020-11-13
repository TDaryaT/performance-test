import java.util.List;

public class StatisticUtil {
    public static double getPercentile(List<Short> sortedSequence) {
        int size = sortedSequence.size();
        if (size == 0) {
            throw new IllegalArgumentException("Empty sequence");
        }

        double percentile;
        if (size > 1) {
            double n = (size - 1) * 0.9 + 1;
            int k = (int) n;
            double d = n - k;
            percentile = sortedSequence.get(k - 1) + d * (sortedSequence.get(k) - sortedSequence.get(k - 1));
        } else {
            percentile = sortedSequence.get(0);
        }
        return percentile;
    }

    public static double getMedian(List<Short> sortedSequence) {
        int size = sortedSequence.size();
        if (size == 0) {
            throw new IllegalArgumentException("Empty sequence");
        }

        double median;
        if (size % 2 == 1) {
            median = sortedSequence.get(size / 2);
        } else {
            median = (sortedSequence.get(size / 2) + sortedSequence.get(size / 2 - 1)) / 2f;
        }
        return median;
    }

    private static double getExtremalValue(List<Short> sequence, boolean isMax) {
        if (sequence.size() == 0) {
            throw new IllegalArgumentException("Empty sequence");
        }
        double extremalValue = sequence.get(0);
        for (short elem : sequence) {
            if ((isMax && elem > extremalValue) || (!isMax && elem < extremalValue)) {
                extremalValue = elem;
            }
        }
        return extremalValue;
    }

    public static double getMax(List<Short> sequence) {
        return getExtremalValue(sequence, true);
    }

    public static double getMin(List<Short> sequence) {
        return getExtremalValue(sequence, false);
    }

    public static double getAverage(List<Short> sequence) {
        int size = sequence.size();
        if (size == 0) {
            throw new IllegalArgumentException("Empty sequence");
        }
        int sum = 0;
        for (short elem : sequence) {
            sum += elem;
        }
        return (double) sum / size;
    }
}
