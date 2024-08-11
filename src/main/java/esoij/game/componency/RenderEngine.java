package esoij.game.componency;

import dev.deftu.componency.font.Font;
import dev.deftu.textile.TextHolder;
import esoij.game.main.MainThread;

import java.awt.*;

public class RenderEngine implements dev.deftu.componency.engine.RenderEngine {
    public int width = 0;
    public int height = 0;
    @Override
    public int getViewportWidth() {
        return width;
    }

    @Override
    public int getViewportHeight() {
        return height;
    }

    @Override
    public int getAnimationFps() {
        return (int) MainThread.FPS;
    }

    @Override
    public void startFrame() {
    }

    @Override
    public void endFrame() {
    }

    @Override
    public void fill(float v, float v1, float v2, float v3, Color color, float v4) {
    }

    @Override
    public void text(float v, float v1, TextHolder textHolder, Color color, Font font) {
    }
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
