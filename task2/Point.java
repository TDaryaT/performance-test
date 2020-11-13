public class Point {
    /**
     * point in 2D space
     */
    private final float x;
    private final float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Point p = (Point) o;
        return x == p.x && y == p.y;
    }

    /**
     * @param testPoint - point on 2D space
     * @param point1    - point on line
     * @param point2    - point on line
     * @return true, if testPoint is on the two-point straight line
     */
    public static boolean isPointOnLine(Point testPoint, Point point1, Point point2) {
        return (point2.getX() - point1.getX()) * (testPoint.getY() - point2.getY()) -
                (point2.getY() - point1.getY()) * (testPoint.getX() - point1.getX()) == 0;
    }

    /**
     * @param testPoint - point on 2D space
     * @param point1    - point on line
     * @param point2    - point on line
     * @return true, if testPoint is to the left of the two-point straight line
     */
    public static boolean isPointLeftLine(Point testPoint, Point point1, Point point2) {
        return (point2.getX() - point1.getX()) * (testPoint.getY() - point2.getY()) -
                (point2.getY() - point1.getY()) * (testPoint.getX() - point1.getX()) > 0;
    }
}
