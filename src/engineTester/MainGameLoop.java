package engineTester;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import renderEngine.rawModel;

public class MainGameLoop{
    public static void main(String[] args) throws LWJGLException {
        DisplayManager.createDisplay();
        Display.setTitle("3D Game");

        Loader loader = new Loader();
        Renderer renderer = new Renderer();

        float[] vertices = {
                -0.5f, 0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                0.5f, 0.5f, 0.0f
                -0.5f, 0.5f, 0.0f
        };

        rawModel model = loader.loadToVAO(vertices);

        while (!Display.isCloseRequested()) {

            renderer.prepare();

            // TODO: Add game logic.
            // TODO: Add rendering.

            renderer.render(model);
            DisplayManager.updateDisplay();

        }

        loader.cleanUp();
        DisplayManager.closeDisplay();

    }
}
