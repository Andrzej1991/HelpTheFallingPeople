package com.diti.helpthefallingpeople.screens;

import com.badlogic.gdx.graphics.Texture;
import com.diti.helpthefallingpeople.HTFPGame;

/**
 * Created by DiTi on 2017-04-11.
 */

public class Menu extends AbstractScreen {
    private Texture background;

    public Menu(final HTFPGame game) {
        super(game);
    }

    @Override
    protected void init() {
        background = new Texture("menubgnd_placeholder.jpg");
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