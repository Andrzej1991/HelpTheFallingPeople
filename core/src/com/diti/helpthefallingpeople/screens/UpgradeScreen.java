package com.diti.helpthefallingpeople.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.diti.helpthefallingpeople.HTFPGame;

/**
 * Created by DiTi on 2017-08-02.
 */

public class UpgradeScreen extends AbstractScreen {
    private Texture background;
    private Label.LabelStyle labelStyle;

    UpgradeScreen(HTFPGame game) {
        super(game);
    }

    @Override
    protected void init() {
        background = new Texture("screen_backgrounds/upgrade_placeholder.jpg");
        labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = Color.BLACK;
        initUpgrades();
    }

    private void initUpgrades() {
        
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
