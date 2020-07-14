package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class DisplayManager {

    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    private static final int FPS_CAP = 2985984;

    public static void createDisplay() throws LWJGLException {

        ContextAttribs attribs = new ContextAttribs(3,2);

        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create(new PixelFormat(), attribs);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        GL11.glViewport(0,0,WIDTH,HEIGHT);

    }

    public static void updateDisplay() {

        Display.sync(FPS_CAP);
        Display.update();

    }

    public static void closeDisplay() {

        Display.destroy();

    }

}
