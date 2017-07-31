package com.diti.helpthefallingpeople.services;

/**
 * Created by Katarzyna on 2017-04-20.
 */

public interface PlayServices {
    public void signIn();
    public void signOut();
    public void rateGame();
    public void unlockAchievement();
    public void submitScore(int highScore);
    public void showAchievement();
    public void showScore();
    public boolean isSignedIn();
    //I could forget something, so if you want to hide just
    // game.playservices.showBannerAdd(false); and to unhide jsut change to true
    public void showBannerAdd(boolean show);
}
