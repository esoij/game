package esoij.game.main;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.opengl.GL;

import static esoij.game.SharedConstants.IDE;
import static org.lwjgl.glfw.GLFW.*;

public class MainThread implements Runnable {
    public static int WIDTH = 1024;
    public static int HEIGHT = 768;
    public static long window;
    public static int width;
    public static int height;
    public FPSAndTPSTimer timer = new FPSAndTPSTimer(60, 60);
    public void init() {
        glfwInit();
        if (IDE) glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        window = glfwCreateWindow(WIDTH, HEIGHT, "game", 0, 0);
        if (window == 0) throw new RuntimeException("Failed to create window.");
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glfwShowWindow(window);
    }
    @Override
    public void run() {
        init();
        glfwSetFramebufferSizeCallback(window, new GLFWFramebufferSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                MainThread.width = width;
                MainThread.height = height;
            }
        });
        while (!glfwWindowShouldClose(window)) {
            this.timer.advanceTime();
            System.out.println(this.timer.fps);
            glfwPollEvents();
            glfwSwapBuffers(window);
        }
    }
}
