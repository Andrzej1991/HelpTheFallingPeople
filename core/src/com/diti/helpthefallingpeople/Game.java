package com.diti.helpthefallingpeople;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
	public final static String GAME_NAME = "Help The Falling People";
	public final static int HEIGHT = 480;
	public final static int WIDTH = 800;

	SpriteBatch batch; //TODO delete
	Texture img; //TODO delete
	
	@Override
	public void create () {
		batch = new SpriteBatch(); //TODO delete
		img = new Texture("badlogic.jpg"); //TODO delete
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
