package web.itmo.lab3_final.mbeans;

public class HitPercentage implements HitPercentageMBean {

    private final PointsCounterMBean pointsCounter;

    public HitPercentage(PointsCounterMBean pointsCounter) {
        this.pointsCounter = pointsCounter;
    }

    @Override
    public double getHitPercentage() {
        int totalPoints = pointsCounter.getTotalPoints();
        int hitPoints = pointsCounter.getHitPoints();
        if (totalPoints == 0) {
            return 0;
        }
        return (double) hitPoints / totalPoints * 100;
    }
}
