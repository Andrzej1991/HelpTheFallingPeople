package com.diti.helpthefallingpeople.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.diti.helpthefallingpeople.HTFPGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = HTFPGame.GAME_NAME;
		config.width = HTFPGame.WIDTH;
		config.height = HTFPGame.HEIGHT;
		config.resizable = false;
		new LwjglApplication(new HTFPGame(), config);
	}
}
