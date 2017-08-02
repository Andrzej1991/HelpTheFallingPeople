package com.diti.helpthefallingpeople.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.diti.helpthefallingpeople.HTFPGame;
import com.diti.helpthefallingpeople.services.PlayServices;

/**
 * Created by DiTi on 2017-04-09.
 */

public class SplashScreen extends AbstractScreen {
    private Texture splashImg;

    public SplashScreen(final HTFPGame game) {
        super(game);
        init();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(new Menu(game));
            }
        }, 2f);
    }

    protected void init() {
        splashImg = new Texture("screen_backgrounds/splashscreen_placeholder.jpg");
    }

    public void render(float delta) {
        super.render(delta);
        stage.act();
        spriteBatch.begin();
        spriteBatch.draw(splashImg, 0, 0);
        spriteBatch.end();
    }

    public void dispose() {
        stage.dispose();
    }
}
