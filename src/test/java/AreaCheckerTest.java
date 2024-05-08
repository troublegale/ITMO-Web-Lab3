import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import web.itmo.lab3_final.model.AreaChecker;
import web.itmo.lab3_final.model.Point;

public class AreaCheckerTest {
    private static Point point;

    @BeforeAll
    public static void setUp() {
        point = new Point();
    }

    private void alterPoint(double x, double y, double r) {
        point.setX(x);
        point.setY(y);
        point.setR(r);
    }

    @Test
    public void testRectangle() {
        alterPoint(1, 3, 4);
        point.setResult(AreaChecker.checkHit(point.getX(), point.getY(), point.getR()));
        Assertions.assertTrue(point.isResult());
        alterPoint(0.5, 1, 1);
        point.setResult(AreaChecker.checkHit(point.getX(), point.getY(), point.getR()));
        Assertions.assertTrue(point.isResult());
        alterPoint(2, 2, 2);
        point.setResult(AreaChecker.checkHit(point.getX(), point.getY(), point.getR()));
        Assertions.assertFalse(point.isResult());
        alterPoint(1.5, 6, 5);
        point.setResult(AreaChecker.checkHit(point.getX(), point.getY(), point.getR()));
        Assertions.assertFalse(point.isResult());
    }

    @Test
    public void testTriangle() {
        alterPoint(0, -4, 4);
        point.setResult(AreaChecker.checkHit(point.getX(), point.getY(), point.getR()));
        Assertions.assertTrue(point.isResult());
        alterPoint(0.5, -1, 2);
        point.setResult(AreaChecker.checkHit(point.getX(), point.getY(), point.getR()));
        Assertions.assertTrue(point.isResult());
        alterPoint(1, -1, 2);
        point.setResult(AreaChecker.checkHit(point.getX(), point.getY(), point.getR()));
        Assertions.assertFalse(point.isResult());
        alterPoint(3, 4.5, 5);
        point.setResult(AreaChecker.checkHit(point.getX(), point.getY(), point.getR()));
        Assertions.assertFalse(point.isResult());
    }

    @Test
    public void testQuarterCircle() {
        alterPoint(-3, 0, 3);
        point.setResult(AreaChecker.checkHit(point.getX(), point.getY(), point.getR()));
        Assertions.assertTrue(point.isResult());
        alterPoint(-0.5, -0.5, 1);
        point.setResult(AreaChecker.checkHit(point.getX(), point.getY(), point.getR()));
        Assertions.assertTrue(point.isResult());
        alterPoint(-2, -2, 2);
        point.setResult(AreaChecker.checkHit(point.getX(), point.getY(), point.getR()));
        Assertions.assertFalse(point.isResult());
        alterPoint(-4, -5, 5);
        point.setResult(AreaChecker.checkHit(point.getX(), point.getY(), point.getR()));
        Assertions.assertFalse(point.isResult());
    }

    @Test
    public void testEmptyArea() {
        alterPoint(-2, 2, 2);
        point.setResult(AreaChecker.checkHit(point.getX(), point.getY(), point.getR()));
        Assertions.assertFalse(point.isResult());
        alterPoint(-3, 1, 5);
        point.setResult(AreaChecker.checkHit(point.getX(), point.getY(), point.getR()));
        Assertions.assertFalse(point.isResult());
    }

}
