package esoij.game.input;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;

public enum Keys {
    ESCAPE(GLFW_KEY_ESCAPE, "escape");
    public final String name;
    public final int keycode;
    Keys(int keycode, String name) {
        this.keycode = keycode;
        this.name = name;
    }
}
