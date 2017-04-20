package com.diti.helpthefallingpeople;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.diti.helpthefallingpeople.screens.SplashScreen;
import com.diti.helpthefallingpeople.services.PlayServices;

public class HTFPGame extends Game {
    public final static String GAME_NAME = "Help The Falling People";
    public final static int HEIGHT = 480;
    public final static int WIDTH = 800;

    SpriteBatch batch; //TODO delete
    Texture img; //TODO delete

    public static PlayServices playServices;

    public HTFPGame(PlayServices playServices) {
        this.playServices = playServices;
    }

    public HTFPGame(){

    }

    @Override
    public void create() {
        this.setScreen(new SplashScreen(this));
    }
}
