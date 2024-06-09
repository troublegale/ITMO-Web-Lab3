package web.itmo.lab3_final.mbeans;

import web.itmo.lab3_final.database.DatabaseManager;
import web.itmo.lab3_final.model.Point;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PointsCounter extends NotificationBroadcasterSupport implements PointsCounterMBean {

    private final AtomicInteger totalPoints = new AtomicInteger(0);
    private final AtomicInteger hitPoints = new AtomicInteger(0);
    private long sequenceNumber = 1;
    private final DatabaseManager databaseManager;

    public PointsCounter(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        initializeCounters();
    }

    @Override
    public void initializeCounters() {
        totalPoints.set(0);
        hitPoints.set(0);
        List<Point> points = databaseManager.loadPoints();
        for (Point point : points) {
            incrementTotalPoints();
            if (point.isResult()) {
                incrementHitPoints();
            }
        }
    }

    @Override
    public int getTotalPoints() {
        return totalPoints.get();
    }

    @Override
    public int getHitPoints() {
        return hitPoints.get();
    }

    @Override
    public void incrementTotalPoints() {
        totalPoints.incrementAndGet();
        if (totalPoints.get() % 15 == 0) {
            Notification n = new Notification(
                    "TotalPointsMultipleOf15", this, sequenceNumber++,
                    System.currentTimeMillis(), "Total number of points is a multiple of 15");
            sendNotification(n);
        }
    }

    @Override
    public void incrementHitPoints() {
        hitPoints.incrementAndGet();
    }

}
