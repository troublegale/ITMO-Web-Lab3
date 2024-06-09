package web.itmo.lab3_final.mbeans;

public interface PointsCounterMBean {
    int getTotalPoints();
    int getHitPoints();
    void incrementTotalPoints();
    void incrementHitPoints();
    void initializeCounters();
}
