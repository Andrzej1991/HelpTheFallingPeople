package com.diti.helpthefallingpeople.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.diti.helpthefallingpeople.HTFPGame;

/**
 * Created by DiTi on 2017-04-11.
 */

public class Menu extends AbstractScreen {
    private Texture background;
    private TextButton startBtn;
    private TextButton optionsBtn;
    private TextButton gloRankingBtn;
    private TextButton aboutBtn;
    private TextButton helpBtn;
    private TextButton exitBtn;

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