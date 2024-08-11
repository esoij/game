package esoij.game.main;

import com.mojang.logging.LogUtils;
import dev.deftu.componency.engine.InputEngine;
import dev.deftu.componency.engine.RenderEngine;
import esoij.game.componency.ComponencyEngine;
import esoij.game.input.Input;
import esoij.game.input.Keys;
import esoij.game.util.Textures;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.slf4j.Logger;

import static esoij.game.SharedConstants.IDE;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;

public class MainThread implements Runnable {
    public FPSAndTPSTimer timer = new FPSAndTPSTimer(60, FPS);
    public static double FPS = 60;
    public Input input = new Input();
    public Window window = new Window();
    public static Textures textures = new Textures();
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final RenderEngine componencyRenderEngine = new esoij.game.componency.RenderEngine();
    public static final InputEngine componencyInputEngine = new esoij.game.componency.InputEngine();
    public static final ComponencyEngine UIEngine = new ComponencyEngine(componencyRenderEngine, componencyInputEngine);
    public static final UI UI = new UI(UIEngine);
    public void init() {
        if (!glfwInit()) throw new RuntimeException("Unable to initialize GLFW.");
        if (IDE) glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        this.window.init();
        glfwSetCursorPosCallback(this.window.window, (window, x, y) -> {
            UIEngine.updateMousePos((float) x, (float) y);
        });
    }
    public void loop() {
        GL.createCapabilities();
        glClearColor(0, 0, 0, 0);
        while (!glfwWindowShouldClose(this.window.window)) {
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glfwSwapBuffers(this.window.window);
            UI.render();
            this.timer.advanceTime();
            if (input.isKeyPressed(this.window.window, Keys.ESCAPE)) glfwSetWindowShouldClose(this.window.window, true);
            //LOGGER.info("FPS: {}", this.timer.fps);
        }
    }
    @Override
    public void run() {
        try {
            this.init();
        } catch (Exception e) {
            LOGGER.error("Could not initialize the game!", e);
        }
        try {
            this.loop();
        } catch (Exception e) {
            LOGGER.error("Error in loop!", e);
        }
        glfwFreeCallbacks(this.window.window);
        glfwDestroyWindow(this.window.window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
}
