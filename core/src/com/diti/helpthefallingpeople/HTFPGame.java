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
    public final static int LEFT_SIDE = -100;
    public final static int RIGHT_SIDE = HTFPGame.WIDTH + 100;
    private boolean paused;
    private int currentGameScore;
    private int currentLevel; //TEST
    private final static double MAX_SCORE_MULTIPLYER = 1.3;
    private int currentIdlePeople; //TEST
    private int currentWorkingPeople; //TEST
    private int currentAliens; //TEST
    public static PlayServices playServices;

    public HTFPGame() {} //FIXME not sure if it should be here, but otherwise DesktopLauncher crashes
    public HTFPGame(PlayServices playServices) {
        this.playServices = playServices;
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

    public int countMaxScoreForLevel (int level) {
        int max = 10;
        for (int i = 1; i < level; i++) {
            max += Math.floor(i * Math.pow(MAX_SCORE_MULTIPLYER, i - 1) * 10);
        }
        return max;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCurrentIdlePeople() {
        return currentIdlePeople;
    }

    public void setCurrentIdlePeople(int currentIdlePeople) {
        this.currentIdlePeople = currentIdlePeople;
    }

    public int getCurrentWorkingPeople() {
        return currentWorkingPeople;
    }

    public void setCurrentWorkingPeople(int currentWorkingPeople) {
        this.currentWorkingPeople = currentWorkingPeople;
    }

    public int getCurrentAliens() {
        return currentAliens;
    }

    public void setCurrentAliens(int currentAliens) {
        this.currentAliens = currentAliens;
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
