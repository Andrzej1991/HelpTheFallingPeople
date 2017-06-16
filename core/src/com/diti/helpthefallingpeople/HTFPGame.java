package com.diti.helpthefallingpeople;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.diti.helpthefallingpeople.screens.Menu;
import com.diti.helpthefallingpeople.screens.SplashScreen;
import com.diti.helpthefallingpeople.services.PlayServices;

public class HTFPGame extends Game {
    public final static String GAME_NAME = "Help The Falling People";
    public final static int HEIGHT = 480;
    public final static int WIDTH = 800;
    private boolean paused;
    private int currentGameScore;

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

    public int getCurrentGameScore() {
        return currentGameScore;
    }

    public void setCurrentGameScore(int currentGameScore) {
        this.currentGameScore = currentGameScore;
    }

    @Override
    public void create() {
        this.setScreen(new SplashScreen(this));
    }

    public void backToMenu() {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            this.setScreen(new Menu(this));
        }
    }
}
