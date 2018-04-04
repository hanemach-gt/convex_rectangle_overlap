import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangleOverlapTest {

    @Test
    void testVectorsShouldProvideExpectedOutput() {

        /* overlap #1 */
        Rectangle r1 = new Rectangle(new Point(-1, -1), new Point(10, 10));
        Rectangle r2 = new Rectangle(new Point(-1, 0), new Point(3, 9));

        RectangleOverlap ro = new RectangleOverlap(r1, r2);
        List<Point> intersection = ro.getIntersectionPoints();

        BigInteger expected = BigInteger.valueOf(36);
        assertEquals(Rectangle.getArea(intersection), expected);

        /* overlap #2 */
        r1 = new Rectangle(new Point(-12, -4), new Point(-1, -2));
        r2 = new Rectangle(new Point(0, 0), new Point(100, 3000));

        ro = new RectangleOverlap(r1, r2);
        intersection = ro.getIntersectionPoints();

        expected = BigInteger.valueOf(0);
        assertEquals(Rectangle.getArea(intersection), expected);

    }

    @Test
    void obscuringRectanglesOverlapShouldProvideExpectedOutput() {

        Rectangle r1 = new Rectangle(new Point(0, 0), new Point(2, 2));
        Rectangle r2 = new Rectangle(new Point(1, 1), new Point(3, 3));

        RectangleOverlap ro = new RectangleOverlap(r1, r2);
        List<Point> intersection = ro.getIntersectionPoints();

        BigInteger expected = BigInteger.valueOf(1);
        assertEquals(Rectangle.getArea(intersection), expected);

    }
}