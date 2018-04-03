import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;

public class Rectangle {

    private Point firstCorner;
    private Point secondCorner;

    public Rectangle(Point firstCorner, Point secondCorner) {

        this.firstCorner = firstCorner;
        this.secondCorner = secondCorner;
    }

    /* Returns all the corner points of the other rectangle such that obscure this rectangle. */
    public List<Point> obscuringPointsOf(Rectangle other) {

        List<Point> obscuring = new ArrayList<>();
        for (Point candidatePt : Rectangle.toVertices(other)) {

            if (this.pointIsWithin(candidatePt)) {

                obscuring.add(candidatePt);
            }
        }
        return obscuring;
    }

    public List<Edge> toEdges() {

        List<Point> vertices = Rectangle.toVertices(this);
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < vertices.size(); ++i) {

            Point edgeStart = vertices.get(i);
            Point edgeEnd = vertices.get( (i+1) % vertices.size() ); // with the last edge, we want a wrap-around to the beginning point
            edges.add(new Edge(edgeStart, edgeEnd));
        }
        return edges;
    }

    public static BigInteger getArea(List<Point> vertices) {

        if (vertices.size() < 4) { // degenerate case, or no rectangle

            return BigInteger.valueOf(0);
        }

        /* 2 points with the same abscissa */
        Point p1 = vertices.get(0);
        Point p2 = null;
        for (int i = 1; i < vertices.size(); ++i) {

            if (vertices.get(i).getX().equals(p1.getX())) {

                p2 = vertices.get(i);
                break;
            }
        }

        /* a point with the other abscissa */
        Point p3 = null;
        for (Point pt : vertices) {

            if (!pt.getX().equals(p1.getX())) {

                p3 = pt;
                break;
            }
        }

        BigInteger base = BigInteger.valueOf(Math.abs(p1.getX() - p3.getX()));
        BigInteger height = BigInteger.valueOf(Math.abs(p1.getY() - p2.getY()));

        return base.multiply(height);

    }

    private static List<Point> toVertices(Rectangle rect) {

        ArrayList<Point> pts = new ArrayList<>();

        Point firstCorner = rect.getFirstCorner();
        Point secondCorner = rect.getSecondCorner();

        pts.addAll(Arrays.asList(
                firstCorner,
                new Point(firstCorner.getX(), secondCorner.getY()),
                secondCorner,
                new Point(secondCorner.getX(),firstCorner.getY())
                )
        );

        return pts;
    }

    /* Returns true if point fits within this rectangle. */
    private boolean pointIsWithin(Point point) {

        Integer minX = Math.min(this.getFirstCorner().getX(), this.getSecondCorner().getX());
        Integer maxX = Math.max(this.getFirstCorner().getX(), this.getSecondCorner().getX());

        Integer minY = Math.min(this.getFirstCorner().getY(), this.getSecondCorner().getY());
        Integer maxY = Math.max(this.getFirstCorner().getY(), this.getSecondCorner().getY());

        Integer x = point.getX();
        Integer y = point.getY();

        return minX <= x && x <= maxX
            && minY <= y && y <= maxY
            && minX != maxX // exclude degenerate cases
            && minY != maxY;
    }

    private Point getFirstCorner() { return this.firstCorner; }

    private Point getSecondCorner() { return this.secondCorner; }

}
