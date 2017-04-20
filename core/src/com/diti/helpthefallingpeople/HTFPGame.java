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
    private boolean paused;

    HTFPGame(PlayServices playServices) {
    }

//    to use
//   game.playServices.signIn();
//game.playServices.signOut();
//game.playServices.rateGame();
//game.playServices.unlockAchievement();
//game.playServices.submitScore(score);
//game.playServices.showScore();
//game.playServices.showAchievement();
//game.playServices.isSignedIn();

    public HTFPGame() {

    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    @Override
    public void create() {
        this.setScreen(new SplashScreen(this));
    }
}
