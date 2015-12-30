package net.neru.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import net.neru.game.ShaderGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        // LibGDX Configuration
        config.width = 800;
        config.height = 600;
        config.foregroundFPS = 30;
        config.backgroundFPS = -1;
        config.useGL30 = false;

        new LwjglApplication(new ShaderGame(), config);
    }
}
