import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Point> parsed = Main.parsePoints(args);
        System.out.println("\n\n Interpreted points: ");
        for (Point pt : parsed) {

            System.out.println(pt);
        }

        Rectangle first = new Rectangle(parsed.get(0), parsed.get(1));
        Rectangle second = new Rectangle(parsed.get(2), parsed.get(3));

        RectangleOverlap ro = new RectangleOverlap(first, second);

        List<Point> intersection = ro.getIntersectionPoints();

        System.out.println("\n\n Intersection: ");
        for (Point pt : intersection) {

            System.out.println(pt);
        }

        System.out.println("\n\n Area: " + Rectangle.getArea(intersection));

    }

    public static List<Point> parsePoints(String[] args) {

        List<Point> points = new ArrayList<>();
        for (int i = 0; i < args.length; i += 2) {

            Integer x = Integer.valueOf( args[i].endsWith(",") ? args[i].substring(0, args[i].length() - 1) : args[i]);
            Integer y = Integer.valueOf( args[i+1].endsWith(",") ? args[i+1].substring(0, args[i+1].length() - 1) : args[i+1]);
            points.add(new Point(x, y));
        }
        return points;
    }
}
