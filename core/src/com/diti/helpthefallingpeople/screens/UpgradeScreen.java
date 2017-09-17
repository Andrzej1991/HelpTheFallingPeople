package com.diti.helpthefallingpeople.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.diti.helpthefallingpeople.HTFPGame;

/**
 * Created by DiTi on 2017-08-02.
 */

public class UpgradeScreen extends AbstractScreen {
    private Texture background;
    private Label.LabelStyle labelStyle;
    private Texture researchTexture, alienTexture, catchTexture, gunTexture;
    private Image researchImg, alienImg, catchImg, gunImg;
    private Label h1Label, h2Label, h3Label, h4Label;
    private Label researchLabel, alienLabel, catchLabel, gunLabel;
    private TextButton researchBuyBtn, alienBuyBtn, catchBuyBtn, gunBuyBtn;

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
        // Table for Buttons
        Table table = new Table();
        table.setSize(HTFPGame.WIDTH, HTFPGame.HEIGHT);

        // Button Skin
        Skin skin = new Skin();
        TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("skin/ui-blue.atlas"));
        skin.addRegions(buttonAtlas);

        // Button font
        BitmapFont font = new BitmapFont();

        // Button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = skin.getDrawable("button_04");

        // headers
        h1Label = new Label("", labelStyle);
        h1Label.setText("");
        h2Label = new Label("", labelStyle);
        h2Label.setText("Name");
        h3Label = new Label("", labelStyle);
        h3Label.setText("Cost");
        h4Label = new Label("", labelStyle);
        h4Label.setText("");

        // research upgrade
        researchTexture = new Texture("upgrade_buttons/research_button.png");
        researchImg = new Image(researchTexture);
        researchLabel = new Label("", labelStyle);
        researchLabel.setText("Research");
        researchBuyBtn = new TextButton("Buy", textButtonStyle);
        researchBuyBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

            }
        });
        researchBuyBtn.setDebug(true); //TODO turn off debug before releasing

        // alien upgrade
        alienTexture = new Texture("upgrade_buttons/alien_button.png");
        alienImg = new Image(alienTexture);
        alienLabel = new Label("", labelStyle);
        alienLabel.setText("Alien");
        alienBuyBtn = new TextButton("Buy", textButtonStyle);
        alienBuyBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

            }
        });
        alienBuyBtn.setDebug(true); //TODO turn off debug before releasing

        // catch upgrade
        catchTexture = new Texture("upgrade_buttons/catch_button.png");
        catchImg = new Image(catchTexture);
        catchLabel = new Label("", labelStyle);
        catchLabel.setText("Catch");
        catchBuyBtn = new TextButton("Buy", textButtonStyle);
        catchBuyBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

            }
        });
        catchBuyBtn.setDebug(true); //TODO turn off debug before releasing

        // gun upgrade
        gunTexture = new Texture("upgrade_buttons/gun_button.png");
        gunImg = new Image(gunTexture);
        gunLabel = new Label("", labelStyle);
        gunLabel.setText("Gun");
        gunBuyBtn = new TextButton("Buy", textButtonStyle);
        gunBuyBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

            }
        });
        gunBuyBtn.setDebug(true); //TODO turn off debug before releasing

        // Table
        table.add(researchImg).minWidth(120);
        table.add(researchLabel).minWidth(120);
        table.add(researchBuyBtn).minWidth(120);
        table.row();
        table.add(alienImg).minWidth(120);
        table.add(alienLabel).minWidth(120);
        table.add(alienBuyBtn).minWidth(120);
        table.row();
        table.add(catchImg).minWidth(120);
        table.add(catchLabel).minWidth(120);
        table.add(catchBuyBtn).minWidth(120);
        table.row();
        table.add(gunImg).minWidth(120);
        table.add(gunLabel).minWidth(120);
        table.add(gunBuyBtn).minWidth(120);
        table.row();
        table.add(h1Label).maxWidth(50).maxHeight(50).padRight(10);
        table.add(h2Label).minWidth(100);
        table.add(h3Label).minWidth(250).maxWidth(300);
        table.add(h4Label).minWidth(80);
        table.row().padTop(5);
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
