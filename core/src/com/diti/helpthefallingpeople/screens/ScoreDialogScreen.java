package com.diti.helpthefallingpeople.screens;

import com.badlogic.gdx.graphics.Texture;
import com.diti.helpthefallingpeople.HTFPGame;

/**
 * Created by terebun on 2017-06-08.
 */

public class ScoreDialogScreen extends AbstractScreen {

    private Texture background;

    public ScoreDialogScreen(HTFPGame game) {
        super(game);
    }

    @Override
    protected void init() {
        background = new Texture("scoredialog_placeholder.jpg");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        drawing();
    }

    private void update() {
        stage.act();
    }

    private void drawing(){
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
