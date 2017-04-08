package com.diti.helpthefallingpeople.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.diti.helpthefallingpeople.HTFPGame;

/**
 * Created by DiTi on 2017-04-09.
 */

public abstract class AbstractScreen implements Screen {
    protected HTFPGame game;
    protected Stage stage;
    // camera?
    protected SpriteBatch spriteBatch;

    public AbstractScreen(HTFPGame game){
        this.game = game;
        stage = new Stage(new FitViewport(HTFPGame.WIDTH, HTFPGame.HEIGHT));
        spriteBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);
        init();
    }

    protected abstract void init();

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        clearScreen();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.dispose();
    }
}
