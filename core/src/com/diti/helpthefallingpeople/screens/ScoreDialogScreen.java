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
 * Created by terebun on 2017-06-08.
 */

public class ScoreDialogScreen extends AbstractScreen {

    private Texture background;
    private TextButton restartBtn, menuBtn;

    public ScoreDialogScreen(HTFPGame game) {
        super(game);
    }

    @Override
    protected void init() {
        background = new Texture("scoredialog_placeholder.jpg");
        initButtons();
    }

    private void initButtons() {
        // Table for Buttons
        Table table = new Table();
        table.setSize(HTFPGame.WIDTH, HTFPGame.HEIGHT);

        // Button Skin
        Skin skin = new Skin();
        TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("skin/ui-blue.atlas"));
        skin.addRegions(buttonAtlas);

        // Button font
        BitmapFont font = new BitmapFont();

        //Button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("button_04");

        // Restart Button
        restartBtn = new TextButton("Play again", textButtonStyle);
        restartBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new GameplayScreen(game));
            }
        });
        restartBtn.setDebug(true); //TODO turn off debug before releasing

        // Menu Button
        menuBtn = new TextButton("Menu", textButtonStyle);
        menuBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new Menu(game));
            }
        });
        menuBtn.setDebug(true); //TODO turn off debug before releasing

        table.add(restartBtn).minWidth(120);
        table.add().minWidth(120);
        table.add(menuBtn).minWidth(60);
        stage.addActor(table);
        Gdx.input.setCatchBackKey(true);
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
