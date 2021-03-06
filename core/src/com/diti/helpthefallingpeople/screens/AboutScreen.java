package com.diti.helpthefallingpeople.screens;

import com.badlogic.gdx.graphics.Texture;
import com.diti.helpthefallingpeople.HTFPGame;

/**
 * Created by Andrzej on 2017-05-23.
 */

public class AboutScreen extends AbstractScreen {
    private Texture background;

    AboutScreen(HTFPGame game) {
        super(game);
    }

    @Override
    protected void init() {
        background = new Texture("screen_backgrounds/sub_screen.png");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        drawing();
        game.backToMenu();
    }

    private void drawing() {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        spriteBatch.end();
    }
}
