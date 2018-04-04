import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {

    private class ValidTestRectangleEdges {

        public final Edge horz1;
        public final Edge horz2;
        public final Edge vert1;
        public final Edge vert2;

        private ValidTestRectangleEdges() {

            horz1 = new Edge(new Point(2,2), new Point(2,6));
            horz2 = new Edge(new Point(2,6), new Point(6,6));

            vert1 = new Edge(new Point(2,2), new Point(2,6));
            vert2 = new Edge(new Point(6,2), new Point(6,6));
        }
    }

    @Test
    void edgeVerticesShouldReportNoPointIntersection() {

        ValidTestRectangleEdges edges = new ValidTestRectangleEdges();

        /* horizontal relative to vertical */
        assertFalse(edges.horz1.hasPointIntersectionWith(edges.vert1));
        assertFalse(edges.horz1.hasPointIntersectionWith(edges.vert2));

        assertFalse(edges.horz2.hasPointIntersectionWith(edges.vert1));
        assertFalse(edges.horz2.hasPointIntersectionWith(edges.vert2));

        /* vertical relative to horizontal */
        assertFalse(edges.vert1.hasPointIntersectionWith(edges.horz1));
        assertFalse(edges.vert1.hasPointIntersectionWith(edges.horz2));

        assertFalse(edges.vert2.hasPointIntersectionWith(edges.horz1));
        assertFalse(edges.vert2.hasPointIntersectionWith(edges.horz2));

    }

    @Test
    void disjointOrOverlappingEdgesShouldReportNoPointIntersection() {

        ValidTestRectangleEdges edges = new ValidTestRectangleEdges();

        /* non-overlapping horizontal with horizontal */
        assertFalse(edges.horz1.hasPointIntersectionWith(edges.horz2));
        assertFalse(edges.horz2.hasPointIntersectionWith(edges.horz1));

        /* non-overlapping vertical with vertical */
        assertFalse(edges.vert1.hasPointIntersectionWith(edges.vert2));
        assertFalse(edges.vert2.hasPointIntersectionWith(edges.vert1));

    }

    @Test
    void orthogonalTouchingEdgesShouldReportNoPointIntersection() {

        Edge horz = new Edge(new Point(8,3), new Point(12,3));
        Edge vert = new Edge(new Point(10,3), new Point(10,7));

        assertFalse(horz.hasPointIntersectionWith(vert));
        assertFalse(vert.hasPointIntersectionWith(horz));

    }

    @Test
    void crossingEdgesReportTheSamePointIntersection() {

        Edge horz = new Edge(new Point(8,3), new Point(12,3));
        Edge vert = new Edge(new Point(10,2), new Point(10,7));

        /* assure intersection assurance */
        assertTrue(horz.hasPointIntersectionWith(vert));
        assertTrue(vert.hasPointIntersectionWith(horz));

        Point intersectionPoint1 = horz.getIntersectionPoint(vert);
        Point intersectionPoint2 = vert.getIntersectionPoint(horz);

        assertEquals(intersectionPoint1, intersectionPoint2);

    }

}