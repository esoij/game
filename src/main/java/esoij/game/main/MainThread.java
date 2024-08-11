package esoij.game.main;

import esoij.game.input.InputThread;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static esoij.game.SharedConstants.IDE;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class MainThread implements Runnable {
    public static int WIDTH = 1024;
    public static int HEIGHT = 768;
    public static long window;
    public static int width;
    public static int height;
    public FPSAndTPSTimer timer = new FPSAndTPSTimer(60, 60);
    public static Thread inputThread;
    public void init() {
        if (!glfwInit()) throw new RuntimeException("Unable to initialize GLFW.");
        if (IDE) glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        window = glfwCreateWindow(WIDTH, HEIGHT, "game", NULL, NULL);
        if (window == NULL) throw new RuntimeException("Failed to create window.");
        //unsure if having a separate thread for inputs is safe, but who cares. my thinking is that it'll let you send inputs even when the main thread is lagging,
        //which is very good i think
        inputThread = new Thread(new InputThread());
        inputThread.setName("Input Thread");
        inputThread.start();
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);
            glfwGetWindowSize(window, pWidth, pHeight);
            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(window, (vidMode.width() - pWidth.get(0)) / 2, (vidMode.height() - pHeight.get(0)));
        }
        glfwSetFramebufferSizeCallback(window, new GLFWFramebufferSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                MainThread.width = width;
                MainThread.height = height;
            }
        });
        glfwMakeContextCurrent(window);
        //vsync: glfwSwapInterval(1);
        glfwShowWindow(window);
    }
    public void loop() {
        GL.createCapabilities();
        glClearColor(1, 0, 0, 0);
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glfwSwapBuffers(window);
            glfwPollEvents();
            this.timer.advanceTime();
            System.out.println(this.timer.fps);
        }
    }
    @Override
    public void run() {
        init();
        loop();
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
}
