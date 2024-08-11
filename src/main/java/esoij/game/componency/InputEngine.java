package esoij.game.componency;

public class InputEngine implements dev.deftu.componency.engine.InputEngine {
    public float mouseX = 0;
    public float mouseY = 0;
    @Override
    public float getMouseX() {
        return 0;
    }

    @Override
    public float getMouseY() {
        return 0;
    }
    public void updateMousePos(float x, float y) {
        this.mouseX = x;
        this.mouseY = y;
    }
}
