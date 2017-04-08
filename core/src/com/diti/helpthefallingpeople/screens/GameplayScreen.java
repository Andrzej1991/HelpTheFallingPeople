package com.diti.helpthefallingpeople.screens;

import com.badlogic.gdx.graphics.Texture;
import com.diti.helpthefallingpeople.HTFPGame;

/**
 * Created by DiTi on 2017-04-09.
 */

public class GameplayScreen extends AbstractScreen {
    private Texture background;

    public GameplayScreen(final HTFPGame game) {
        super(game);
    }

    @Override
    protected void init() {
        background = new Texture("gameplayscreen_placeholder.jpg");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act();
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        spriteBatch.end();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
