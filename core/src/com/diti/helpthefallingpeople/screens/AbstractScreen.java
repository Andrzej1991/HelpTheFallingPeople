package com.diti.helpthefallingpeople.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.diti.helpthefallingpeople.HTFPGame;

/**
 * Created by DiTi on 2017-04-09.
 */

abstract class AbstractScreen implements Screen {
    HTFPGame game;
    Stage stage;
    SpriteBatch spriteBatch;
    private OrthographicCamera camera;

    AbstractScreen(HTFPGame game) {
        this.game = game;
        stage = new Stage(new FitViewport(HTFPGame.WIDTH, HTFPGame.HEIGHT));
        spriteBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);
        init();
    }

    protected abstract void init();

    private void clearScreen() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        game.dispose();
    }

    private void createCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, HTFPGame.WIDTH / 2, HTFPGame.HEIGHT / 2);
        camera.update();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        clearScreen();
    }

    @Override
    public void pause() {
        game.setPaused(true);
    }

    @Override
    public void resume() {
        game.setPaused(false);
    }
}

