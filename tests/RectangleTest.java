import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void degenerateOrNoRectangleAreaShouldEqualZero() {

        BigInteger zero = BigInteger.valueOf(0);

        Rectangle degenerate = new Rectangle(new Point(0,0), new Point(0,0));

        List<Point> vertices = Rectangle.toVertices(degenerate);

        assertEquals(Rectangle.getArea(vertices), zero);

        vertices = new ArrayList<>();
        assertEquals(Rectangle.getArea(vertices), zero);
    }

    @Test
    void areaGetterShouldReturnTheSameAreaRegardlessOfVerticeOrder() {

        BigInteger expected = BigInteger.valueOf(25);
        Point p1 = new Point(5,4);
        Point p2 = new Point(5,9);
        Point p3 = new Point(10,9);
        Point p4 = new Point(10,4);

        assertEquals(Rectangle.getArea(Arrays.asList(p1, p2, p3, p4)), expected);

    }

}