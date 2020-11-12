import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task2 {
    private static final int countVertices = 4;
    private static Point2D.Float[] vertices = new Point2D.Float[countVertices];

    private static boolean isVertex(Point2D.Float point) {
        return point.equals(vertices[0]) || point.equals(vertices[1])
                || point.equals(vertices[2]) || point.equals(vertices[3]);
    }

    private static boolean isPointOnBorder(Point2D.Float test, Point2D.Float point1, Point2D.Float point2) {
        return (point2.getX() - point1.getX()) * (test.getY() - point2.getY()) -
                (point2.getY() - point1.getY()) * (test.getX() - point1.getX()) == 0;
    }

    private static boolean isPointLeft(Point2D.Float test, Point2D.Float point1, Point2D.Float point2) {
        return (point2.getX() - point1.getX()) * (test.getY() - point2.getY()) -
                (point2.getY() - point1.getY()) * (test.getX() - point1.getX()) > 0;
    }

    /**
     * @param point - point on plane
     * @return 0 - точка на одной из вершин
     * 1 - точка на одной из сторон
     * 2 - точка внутри
     * 3 - точка снаружи
     */
    private static int isPosition(Point2D.Float point) {
        if (isVertex(point)) {
            //вершина
            return 0;
        } else {
            //выкидываем то что снаружи
            for (int i = 0; i < countVertices - 1; i++) {
                if (isPointLeft(vertices[i], vertices[i + 1], point)) {
                    return 3;
                }
            }
            if (isPointLeft(vertices[countVertices - 1], vertices[0], point)) {
                return 3;
            } else {
                //внутри или на гранях
                for (int i = 0; i < countVertices - 1; i++) {
                    if (isPointOnBorder(vertices[i], vertices[i + 1], point)) {
                        //грань
                        return 1;
                    }
                }
                //внутри
                return 2;
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileInputStream(args[0]));
             Scanner scanner2 = new Scanner(new FileInputStream(args[1]))) {
            int vertexCount = vertices.length;

            for (int i = 0; i < vertexCount; i++) {
                float x = scanner.nextFloat();
                String s = scanner.nextLine();
                s = s.substring(0, s.length() - 2);
                float y = Float.parseFloat(s);
                vertices[i] = new Point2D.Float(x, y);
            }

            while (scanner2.hasNextLine()) {
                float x = scanner2.nextFloat();
                String s = scanner2.nextLine();
                s = s.substring(0, s.length() - 2);
                float y = Float.parseFloat(s);

                Point2D.Float point = new Point2D.Float(x, y);
                System.out.println(isPosition(point));
            }
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
