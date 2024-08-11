package esoij.game.input;

import static org.lwjgl.glfw.GLFW.*;

public class Input {
    public boolean isKeyReleased(long window, Keys key) {
        return glfwGetKey(window, key.keycode) == GLFW_RELEASE;
    }
    public boolean isKeyPressed(long window, Keys key) {
        return glfwGetKey(window, key.keycode) == GLFW_PRESS;
    }
    public boolean isKeyRepeated(long window, Keys key) {
        return glfwGetKey(window, key.keycode) == GLFW_REPEAT;
    }
}
