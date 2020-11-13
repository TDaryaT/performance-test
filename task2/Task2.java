import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Task2 {
    public static List<Point> getPoints(String path) {
        List<Point> points = new LinkedList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            while (scanner.hasNext()) {
                float x = scanner.nextFloat();
                float y = scanner.nextFloat();
                points.add(new Point(x, y));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return points;
    }

    public static void main(String[] args) {
        ConvexQuadrilateral convexQuadrilateral = new ConvexQuadrilateral(getPoints(args[0]));
        List<Point> points = getPoints(args[1]);

        for (Point point : points) {
            System.out.println(convexQuadrilateral.pointPosition(point));
        }
    }
}
