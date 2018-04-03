public class Edge {

    private Point firstPoint;
    private Point secondPoint;

    public Edge(Point first, Point second) {

        this.firstPoint = first;
        this.secondPoint = second;
    }

    /* WARNING: the method assumes that the point intersection exists. */
    public Point getIntersectionPoint(Edge other) {

        boolean thisIsVertical = firstPoint.getX() == secondPoint.getX();
        Point point = null;
        if (thisIsVertical) {
            /* this - vertical, other - horizontal */
            return new Point(firstPoint.getX(), other.getFirstPoint().getY());

        } else {
            /* this - horizontal, other - vertical */
            return new Point(other.getFirstPoint().getX(), firstPoint.getY());
        }
    }

    /* A method for checking whether a point intersection occurs. */
    public boolean hasPointIntersectionWith(Edge other) {

        /* test for overlapping edges */
        boolean thisIsVertical = firstPoint.getX() == secondPoint.getX();
        boolean thisIsHorizontal = firstPoint.getY() == secondPoint.getY();

        boolean otherIsVertical = other.getFirstPoint().getX() == other.getSecondPoint().getX();
        boolean otherIsHorizontal = other.getFirstPoint().getY() == other.getSecondPoint().getY();

        if ((thisIsVertical && otherIsVertical) || (thisIsHorizontal && otherIsHorizontal)) {

            return false;
        }

        boolean hasPointIntersection = false;
        if (thisIsVertical) {
            /* this - vertical, other - horizontal */
            boolean xIsWithinOther = xCoordIsBetweenPointAbscissas(firstPoint.getX(), other.getFirstPoint(), other.getSecondPoint());
            boolean yIsWithinThis = yCoordIsBetweenPointOrdinates(other.getFirstPoint().getY(), firstPoint, secondPoint);

            hasPointIntersection = xIsWithinOther && yIsWithinThis;
        } else {
            /* this - horizontal, other - vertical */
            boolean yIsWithinOther = yCoordIsBetweenPointOrdinates(firstPoint.getY(), other.getFirstPoint(), other.getSecondPoint());
            boolean xIsWithinThis = xCoordIsBetweenPointAbscissas(other.getFirstPoint().getX(), firstPoint, secondPoint);

            hasPointIntersection = yIsWithinOther && xIsWithinThis;
        }

        return hasPointIntersection;
    }

    private boolean xCoordIsBetweenPointAbscissas(Integer x, Point p1, Point p2) {

        Integer minArg = Math.min(p1.getX(), p2.getX());
        Integer maxArg = Math.max(p1.getX(), p2.getX());

        return minArg < x && x < maxArg;
    }

    private boolean yCoordIsBetweenPointOrdinates(Integer y, Point p1, Point p2) {

        Integer minOrd = Math.min(p1.getY(), p2.getY());
        Integer maxOrd = Math.max(p1.getY(), p2.getY());

        return minOrd < y && y < maxOrd;
    }

    private Point getFirstPoint() { return this.firstPoint; }

    private Point getSecondPoint() { return this.secondPoint; }

}
