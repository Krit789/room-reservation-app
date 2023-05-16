package net.itkmitl.room.libs.peeranat.measure;

public class MeasurePerformance {

    private long start;

    public MeasurePerformance() {
        resetStart();
    }

    public void resetStart() {
        this.start = System.currentTimeMillis();
    }

    public long getElapsed() {
        return System.currentTimeMillis() - start;
    }


}
