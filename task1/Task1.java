import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        ArrayList<Short> seq = new ArrayList<>(1000);

        double percentile = 0;
        double mean = 0;
        double min = 32767;
        double max = -32767;
        double average = 0;

        try (Scanner scanner = new Scanner(new FileInputStream(args[0]))) {
            while (scanner.hasNextLine()) {
                short next = scanner.nextShort();
                seq.add(next);
                if (next < min) {
                    min = next;
                }
                if (next > max) {
                    max = next;
                }
                average += next;
            }

            int count = seq.size();
            if (count > 0) {
                Collections.sort(seq);

                //percentile-calc
                if (count > 1) {
                    double n = (count - 1) * 0.9 + 1;
                    int k = (int) n;
                    double d = n - k;
                    percentile = seq.get(k - 1) + d * (seq.get(k) - seq.get(k - 1));
                } else {
                    percentile = seq.get(0);
                }

                //mean-calc
                if (count % 2 == 1) {
                    mean = seq.get(count / 2);
                } else {
                    mean = (seq.get(count / 2) + seq.get(count / 2 - 1)) / 2f;
                }
                //average-calc
                average = average / count;
            }
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        System.out.printf("%.2f%n", percentile);
        System.out.printf("%.2f%n", mean);
        System.out.printf("%.2f%n", max);
        System.out.printf("%.2f%n", min);
        System.out.printf("%.2f%n", average);
    }
}