import java.util.ArrayList;
import java.util.List;

public class RectangleOverlap {

    private Rectangle first;
    private Rectangle second;

    public RectangleOverlap(Rectangle first, Rectangle second) {

        this.first = first;
        this.second = second;
    }


    /* Returns a collection of at most 4 points. */
    public List<Point> getIntersectionPoints() {

        /* get points which are not an intersection of edges */
        List<Point> alone = new ArrayList<>();
        alone.addAll(first.obscuringPointsOf(second)); // points of second that obscure first
        for (Point pt : second.obscuringPointsOf(first)) {// points of first that obscure second

            if (!alone.contains(pt)) {

                alone.add(pt);
            }
        }
        /* get edge intersection points */
        List<Point> intersecting = new ArrayList<>();

        List<Edge> secondEdges = second.toEdges();
        for (Edge outerEdge : first.toEdges()) {

            for (Edge innerEdge : secondEdges) {

                if (innerEdge.hasPointIntersectionWith(outerEdge)) {

                    intersecting.add(innerEdge.getIntersectionPoint(outerEdge));
                }
            }
        }

        /* combine the result points */
        List<Point> combined = new ArrayList<>(alone);
        combined.addAll(intersecting);
        return combined;
    }

}
