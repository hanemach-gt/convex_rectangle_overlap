public class Point {

    private Integer x;
    private Integer y;

    public Point(Integer x, Integer y) {

        this.x = x;
        this.y = y;
    }

    public Integer getX() { return this.x; }

    public Integer getY() { return this.y; }

    public String toString() { return " [" + x + ", " + y + "]"; }

    public boolean equals(Object obj) {

        /* null and type checking intentionally unimplemented */
        Point pt = (Point)obj;
        return x.equals(pt.getX()) && y.equals(pt.getY());
    }
}
