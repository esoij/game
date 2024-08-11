package esoij.game.main;

import com.mojang.logging.LogUtils;
import dev.deftu.componency.components.Component;
import dev.deftu.componency.components.ComponentProperties;
import dev.deftu.componency.components.impl.FrameComponent;
import dev.deftu.componency.components.impl.RectangleComponent;
import dev.deftu.componency.engine.Engine;
import dev.deftu.componency.properties.impl.ParentRelativeProperty;
import dev.deftu.componency.properties.impl.PixelProperty;
import org.slf4j.Logger;

public class UI {
    public static final Logger LOGGER = LogUtils.getLogger();
    public final FrameComponent frame = FrameComponent.configure(new FrameComponent(), (frame) -> {
        frame.setName("frame");
        frame.setHidden(false);
        frame.setClipping(true);
        ComponentProperties properties = frame.getProperties();
        properties.setX(new PixelProperty(0));
        properties.setY(new PixelProperty(0));
        properties.setWidth(new ParentRelativeProperty(100));
        properties.setHeight(new ParentRelativeProperty(100));
    });
    public final RectangleComponent box = Component.configure(new RectangleComponent(), (box) -> {
        box.setName("box");
        Component.properties(box.getProperties(), (properties) -> {
            properties.setX(new PixelProperty(25));
            properties.setY(new PixelProperty(25));
            properties.setWidth(new PixelProperty(25));
        });
    }).onMouseClick(event -> LOGGER.info("Box clicked at {}, {}", event.getX(), event.getY())).attachedTo(frame);
    public Engine engine;
    public UI(Engine engine) {
        this.engine = engine;
        frame.makeRoot(this.engine);
    }
    public void render() {
        frame.handleRender();
    }
}
