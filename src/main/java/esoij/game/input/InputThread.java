package esoij.game.input;

import esoij.game.main.MainThread;

import static org.lwjgl.glfw.GLFW.*;

public class InputThread implements Runnable {
    public void init() {
        glfwSetKeyCallback(MainThread.window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) glfwSetWindowShouldClose(window, true);
        });
    }
    @Override
    public void run() {
        init();
    }
}
