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
        initMenuButtons();
    }

    private void initMenuButtons() {
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
        //textButtonStyle.down = skin.getDrawable("button_02");

        // Start Button
        startBtn = new TextButton("New game", textButtonStyle);
        startBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new GameplayScreen(game));
            }
        });
        startBtn.setDebug(true); //TODO turn off debug before releasing

        // Options Button
        optionsBtn = new TextButton("Options", textButtonStyle);
        optionsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new OptionsScreen(game));
            }
        });
        optionsBtn.setDebug(true);

        // Global Ranking Button
        gloRankingBtn = new TextButton("Global Ranking", textButtonStyle);
        gloRankingBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new RankingScreen(game));
            }
        });

        // About Button
        aboutBtn = new TextButton("About", textButtonStyle);
        aboutBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new AboutScreen(game));
            }
        });

        // Help Button
        helpBtn = new TextButton("Help", textButtonStyle);
        helpBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
//                game.setScreen(new HelpScreen(game));
                game.playServices.showScore();
            }
        });


        // Exit Button
        exitBtn = new TextButton("Exit", textButtonStyle);
        exitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.exit();
            }
        });

        table.add(startBtn).minWidth(120);
        table.row();
        table.add(optionsBtn).minWidth(120);
        table.row();
        table.add(gloRankingBtn).minWidth(120);
        table.row();
        table.add(aboutBtn).minWidth(120);
        table.row();
        table.add(helpBtn).minWidth(120);
        table.row();
        table.add(exitBtn).minWidth(120);
        table.row();
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

    private void drawing() {
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