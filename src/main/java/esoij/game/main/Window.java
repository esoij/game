package esoij.game.main;

import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    public static int WIDTH = 1024;
    public static int HEIGHT = 768;
    public long window;
    public int width;
    public int height;
    public void init() {
        this.window = glfwCreateWindow(WIDTH, HEIGHT, "game", NULL, NULL);
        if (this.window == NULL) throw new RuntimeException("Failed to create window.");
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);
            glfwGetWindowSize(this.window, pWidth, pHeight);
            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(this.window, (vidMode.width() - pWidth.get(0)) / 2, (vidMode.height() - pHeight.get(0)));
        }
        glfwSetFramebufferSizeCallback(this.window, new GLFWFramebufferSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                Window.this.width = width;
                Window.this.height = height;
                MainThread.UIEngine.resize(width, height);
            }
        });
        glfwMakeContextCurrent(this.window);
        //vsync: glfwSwapInterval(1);
        glfwShowWindow(this.window);
    }
}
