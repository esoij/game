package esoij.game.main;

public class FPSAndTPSTimer {
    public double ticksPerSecond;
    public long lastTime;
    public int ticks;
    public double delta;
    public double timeScale = 1.0;
    public double fps = 0.0;
    public double passedTime = 0.0;
    public double desiredFPS;
    public FPSAndTPSTimer(double ticksPerSecond, double desiredFPS) {
        this.ticksPerSecond = ticksPerSecond;
        this.lastTime = System.nanoTime();
        this.desiredFPS = desiredFPS;
    }
    public void advanceTime() {
        long now = System.nanoTime();
        long passedNs = now - this.lastTime;
        this.lastTime = now;
        if (passedNs < 0) passedNs = 0;
        if (passedNs > 16666666 * desiredFPS) passedNs = (long) (16666666 * desiredFPS);
        this.fps = 16666666 * desiredFPS / passedNs;
        this.passedTime += passedNs * this.timeScale * this.ticksPerSecond / (16666666 * desiredFPS);
        this.ticks = (int) this.passedTime;
        if (this.ticks > 100) this.ticks = 100;
        this.passedTime -= this.ticks;
        this.delta = this.passedTime;
    }
}
