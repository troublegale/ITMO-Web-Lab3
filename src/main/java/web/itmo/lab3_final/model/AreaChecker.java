package web.itmo.lab3_final.model;

public class AreaChecker {

    public static boolean checkHit(double x, double y, double r) {
        if (x >= 0) {
            if (y >= 0) return checkRectangleHit(x, y, r);
            return checkTriangleHit(x, y, r);
        }
        if (y <= 0) return checkQuarterCircleHit(x, y, r);
        return false;
    }

    private static boolean checkRectangleHit(double x, double y, double r) {
        return (x <= r / 2) && (y <= r);
    }

    private static boolean checkTriangleHit(double x, double y, double r) {
        return y >= 2 * x - r;
    }

    private static boolean checkQuarterCircleHit(double x, double y, double r) {
        return Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2);
    }

}
