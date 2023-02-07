package program;

public class Clock {
    private int time;

    public Clock() {
        this.time = 1;
    }

    public int getTime() {
        return time;
    }

    public void tick() {
        time++;
    }
}
