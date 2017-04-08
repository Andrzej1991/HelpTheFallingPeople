package com.diti.helpthefallingpeople.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.diti.helpthefallingpeople.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Game.GAME_NAME;
		config.width = Game.WIDTH;
		config.height = Game.HEIGHT;
		config.resizable = false;
		new LwjglApplication(new Game(), config);
	}
}
