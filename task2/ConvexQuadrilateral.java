import java.util.LinkedList;
import java.util.List;

public class ConvexQuadrilateral {
    public static final int SIZE = 4;
    private final List<Point> vertices = new LinkedList<>();

    public ConvexQuadrilateral(List<Point> vertices) {
        int size = vertices.size();
        if (size != SIZE) {
            throw new IllegalArgumentException("The quadrilateral must be with four vertices");
        }
        this.vertices.addAll(vertices);
    }

    /**
     * @param point - point on 2D space
     * @return true, if point is a vertex of Quadrilateral
     */
    public boolean isVertex(Point point) {
        return point.equals(vertices.get(0)) || point.equals(vertices.get(1))
                || point.equals(vertices.get(2)) || point.equals(vertices.get(3));
    }

    /**
     * @param point - point in 2D space
     * @return 0 - point is vertex
     * 1 - point on side
     * 2 - point in figure
     * 3 - point inside figure
     */
    public int pointPosition(Point point) {
        if (isVertex(point)) {
            //вершина
            return 0;
        } else {
            //выкидываем то что снаружи
            for (int i = 0; i < SIZE - 1; i++) {
                if (Point.isPointLeftLine(vertices.get(i), vertices.get(i + 1), point)) {
                    return 3;
                }
            }
            if (Point.isPointLeftLine(vertices.get(SIZE - 1), vertices.get(0), point)) {
                return 3;
            } else {
                //внутри или на гранях
                for (int i = 0; i < SIZE - 1; i++) {
                    if (Point.isPointOnLine(vertices.get(i), vertices.get(i + 1), point)) {
                        //грань
                        return 1;
                    }
                }
                //внутри
                return 2;
            }
        }
    }
}
