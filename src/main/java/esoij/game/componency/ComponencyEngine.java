package esoij.game.componency;

import dev.deftu.componency.engine.Engine;
import dev.deftu.componency.engine.InputEngine;
import dev.deftu.componency.engine.RenderEngine;

public class ComponencyEngine extends Engine {
    public final esoij.game.componency.RenderEngine renderEngine;
    public final esoij.game.componency.InputEngine inputEngine;
    public ComponencyEngine(RenderEngine renderEngine, InputEngine inputEngine) {
        this.renderEngine = (esoij.game.componency.RenderEngine) renderEngine;
        this.inputEngine = (esoij.game.componency.InputEngine) inputEngine;
    }
    @Override
    public RenderEngine getRenderEngine() {
        return this.renderEngine;
    }

    @Override
    public InputEngine getInputEngine() {
        return this.inputEngine;
    }
    public void resize(int width, int height) {
        this.renderEngine.resize(width, height);
    }
    public void updateMousePos(float x, float y) {
        this.inputEngine.updateMousePos(x, y);
    }
}
