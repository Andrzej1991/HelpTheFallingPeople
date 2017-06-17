package com.diti.helpthefallingpeople.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
    private TextButton submitBtn, restartBtn, menuBtn;
    private Label.LabelStyle labelStyle;
    private Label scoreLabel;

    public ScoreDialogScreen(HTFPGame game) {
        super(game);
    }

    @Override
    protected void init() {
        background = new Texture("scoredialog_placeholder.jpg");
        labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = Color.BLACK;
        initButtons();
        initScore();
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

        // Submit Button
        submitBtn = new TextButton("Submit", textButtonStyle);
        submitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // TODO submit score to play sercives
            }
        });
        submitBtn.setDebug(true); //TODO turn off debug before releasing

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

        table.add(submitBtn).minWidth(120);
        table.add(restartBtn).minWidth(120);
        //table.add().minWidth(120);
        table.add(menuBtn).minWidth(60);
        stage.addActor(table);
        Gdx.input.setCatchBackKey(true);
    }

    private void initScore() {
        scoreLabel = new Label("", labelStyle);
        scoreLabel.setPosition(HTFPGame.WIDTH/2, (float) (HTFPGame.HEIGHT*0.7));
        int score = game.getCurrentGameScore();
        scoreLabel.setText("You have saved " + String.valueOf(score) + " people.");
        stage.addActor(scoreLabel);
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
