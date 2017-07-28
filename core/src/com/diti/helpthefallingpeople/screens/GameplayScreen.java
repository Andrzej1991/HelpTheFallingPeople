package com.diti.helpthefallingpeople.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.diti.helpthefallingpeople.HTFPGame;
import com.diti.helpthefallingpeople.objects.Alien;
import com.diti.helpthefallingpeople.objects.Bomb;
import com.diti.helpthefallingpeople.objects.FallingObj;
import com.diti.helpthefallingpeople.objects.Person;
import com.diti.helpthefallingpeople.objects.SpawnPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by DiTi on 2017-04-09.
 */

class GameplayScreen extends AbstractScreen {
    private Texture background;

    // falling objects
    private Person person;
    private Alien alien;
    private Bomb bomb;
    private List<FallingObj> fallingObjList;

    private SpawnPoint spawn;
    private Random random;

    private Label.LabelStyle labelStyle;
    // Start counter
    private Label startCounterLabel;
    private String startCounterStr;
    private float mStartCounter;

    // Score
    private int gameScore;
    private Label scoreLabel;

    // Clock
    private Label clockLabel;
    private String clockStr;
    private float mClock;

    // Idle people
    private int idlePeopleCount;
    private Label idlePeopleLabel;

    // Working people
    private int workingPeopleCount;
    private Label workingPeopleLabel;

    // Aliens count
    private int aliensCount;
    private Label aliensLabel;

    // Level count
    private int levelCount;
    private Label levelLabel;
    private int levelMaxScore;

    GameplayScreen(final HTFPGame game) {
        super(game);
    }

    @Override
    protected void init() {
        background = new Texture("sub_screen.png");
        labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = Color.BLACK;
        initStartCounter();
        initClock();
        initScore();
        initFallingObjectsCounters();
        initLevelCounter();
        random = new Random();
        fallingObjList = new ArrayList<FallingObj>();
        initWave(HTFPGame.LEFT_SIDE); // set starting side of first wave
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        // render fallingObj
        for (int i = 0; i < fallingObjList.size(); i++) {
            fallingObjList.get(i).setStateTime(fallingObjList.get(i).getStateTime() + Gdx.graphics.getDeltaTime());
            fallingObjList.get(i).setCurrentFrame(fallingObjList.get(i).getAnimation().getKeyFrame(fallingObjList.get(i).getStateTime(), true));
        }

        // render spawn
        spawn.setStateTime(spawn.getStateTime() + Gdx.graphics.getDeltaTime());
        spawn.setCurrentFrame(spawn.getAnimation().getKeyFrame(spawn.getStateTime(), true));
        drawing();
    }

    private void update() {
        startCounterLabel.setText(startCounterStr);
        startCounterLabel.setFontScale(5);

        clockLabel.setText(clockStr);

        scoreLabel.setText("Score: " + String.valueOf(getGameScore()) + "/" + String.valueOf(getLevelMaxScore()));
        scoreLabel.setFontScale(1);

        idlePeopleLabel.setText("Idle people: " + String.valueOf(getIdlePeopleCount()));
        idlePeopleLabel.setFontScale(1);

        workingPeopleLabel.setText("Working people: " + String.valueOf(getWorkingPeopleCount()));
        workingPeopleLabel.setFontScale(1);

        aliensLabel.setText("Aliens: " + String.valueOf(getAliensCount()));
        aliensLabel.setFontScale(1);

        levelLabel.setText("Lvl: " + String.valueOf(getLevelCount()));
        levelLabel.setFontScale(1);

        for (int i = 0; i < fallingObjList.size(); i++) {
            if (fallingObjList.get(i).isVisible()) {
                fallingObjList.get(i).setY(fallingObjList.get(i).getY() - Gdx.graphics.getDeltaTime() * fallingObjList.get(i).getSpeed());
            }
        }

        if (mStartCounter < 0) {
            sendWave(spawn.getSide());
        }

        // Get rid of all FallingObj when they reach bottom of the stage
        List<FallingObj> foToRemove = new ArrayList<FallingObj>();
        for (FallingObj fo:fallingObjList) {
            if (fo.getY() < 0){
                stage.getActors().removeValue(fo,false);
                foToRemove.add(fo);
            }
        }
        fallingObjList.removeAll(foToRemove);
        foToRemove.clear();

        // If there is no FallingObj on the stage then send new wave
        if (fallingObjList.size() == 0) {
            if (spawn.getX() > HTFPGame.WIDTH || spawn.getX() < -spawn.getWidth()) {
                clearWave();
                //set starting side of next wave randomly
                Random r = new Random();
                if (r.nextBoolean()) {
                    initWave(HTFPGame.LEFT_SIDE);
                } else {
                    initWave(HTFPGame.RIGHT_SIDE);
                }
                sendWave(spawn.getSide());
            }
        }

        stage.act();
    }

    private void drawing(){
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        spriteBatch.end();
        if (mStartCounter > 0){
            updateStartCounter();
        }
        if (mStartCounter < 0){
            updateClock(true);
            stage.getActors().removeValue(startCounterLabel,true);
        }
        if (mClock <= 0){
            updateClock(false);
            showScoreDialog();
        }
        stage.draw();
    }

    private void initStartCounter(){
        startCounterLabel = new Label("", labelStyle);
        startCounterLabel.setPosition((float) ((HTFPGame.WIDTH*0.9)/2), HTFPGame.HEIGHT/2);
        stage.addActor(startCounterLabel);
        mStartCounter = 3;
    }

    private void initClock(){
        clockLabel = new Label("", labelStyle);
        clockLabel.setPosition((float)(HTFPGame.WIDTH*8/9), (float)(HTFPGame.HEIGHT*8/9));
        stage.addActor(clockLabel);
        mClock = 30;
    }

    private void initScore() {
        scoreLabel = new Label("", labelStyle);
        scoreLabel.setPosition(700, HTFPGame.HEIGHT - 10);
        stage.addActor(scoreLabel);
        setGameScore(0);
    }

    private void initFallingObjectsCounters() {
        // Idle people
        idlePeopleLabel = new Label("", labelStyle);
        idlePeopleLabel.setPosition(10, HTFPGame.HEIGHT - 10);
        stage.addActor(idlePeopleLabel);
        setIdlePeopleCount(0);

        // Working people
        workingPeopleLabel = new Label("", labelStyle);
        workingPeopleLabel.setPosition(130, HTFPGame.HEIGHT - 10);
        stage.addActor(workingPeopleLabel);
        setWorkingPeopleCount(0);

        // Aliens count
        aliensLabel = new Label("", labelStyle);
        aliensLabel.setPosition(300, HTFPGame.HEIGHT - 10);
        stage.addActor(aliensLabel);
        setAliensCount(0);
    }

    private void initLevelCounter() {
        levelLabel = new Label("", labelStyle);
        levelLabel.setPosition(650, HTFPGame.HEIGHT - 10);
        stage.addActor(levelLabel);
        setLevelCount(1);
        setLevelMaxScore(updateMaxScoreForLevel(game.getCurrentLevel()));
    }

    private void initWave(int side) {
        generateSpawn(side);
        generateFallingObj(spawn.getSide(), 10, 10, 5);
    }

    private void generateFallingObj(int side, int pplNumber, int alienNumber, int bombNumber) {
        // generate people
        for (int i = 0; i < pplNumber; i++) {
            person = new Person(random.nextFloat(), random.nextFloat(), "sticker_anim_2x1.png", 2, 1);
            person.setY(spawn.getY());
            person.setDebug(true); //TODO turn off debug before releasing
            person.setVisible(false);
            person.addListener(new ClickListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Actor a = event.getListenerActor();
                    fallingObjList.remove(a);
                    stage.getActors().removeValue(a,false);
                    updateScore(1);
                    updateIdlePeopleCount(1);
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
            fallingObjList.add(person);
        }

        // generate aliens
        for (int i = 0; i < alienNumber; i++) {
            alien = new Alien(random.nextFloat(), random.nextFloat(), "alien_anim_2x1.png", 2, 1);
            alien.setY(spawn.getY());
            alien.setDebug(true); //TODO turn off debug before releasing
            alien.setVisible(false);
            alien.addListener(new ClickListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Actor a = event.getListenerActor();
                    fallingObjList.remove(a);
                    stage.getActors().removeValue(a,false);
                    updateScore(-1);
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
            fallingObjList.add(alien);
        }

        // generate bombs
        for (int i = 0; i < bombNumber; i++) {
            bomb = new Bomb(random.nextFloat(), random.nextFloat(), "bomb_anim_2x1.png", 2, 1);
            bomb.setY(spawn.getY());
            bomb.setDebug(true); //TODO turn off debug before releasing
            bomb.setVisible(false);
            bomb.addListener(new ClickListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Actor a = event.getListenerActor();
                    // Kill people visible on the stage
                    List<FallingObj> foToRemove = new ArrayList<FallingObj>();
                    for (FallingObj fo : fallingObjList) {
                        if (fo instanceof Person && fo.isVisible()){
                            int aIndex = stage.getActors().indexOf(fo, false);
                            foToRemove.add(fo);
                            if (aIndex >= 0) {
                                stage.getActors().removeIndex(aIndex);
                            }
                        }
                    }
                    fallingObjList.remove(a);
                    fallingObjList.removeAll(foToRemove);
                    foToRemove.clear();
                    stage.getActors().removeValue(a,false);
                    updateScore(-10);
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
            fallingObjList.add(bomb);
        }

        // sort list
        if (side == HTFPGame.LEFT_SIDE) {
            Collections.sort(fallingObjList,new FallingObjectComparatorByPosX());
        } else if (side == HTFPGame.RIGHT_SIDE) {
            Collections.sort(fallingObjList, new FallingObjectComparatorByPosX().reversed());
        }

        // add falling objects as actors
        for (int i = 0; i < fallingObjList.size(); i++) {
            stage.addActor(fallingObjList.get(i));
        }
    }

    private void generateSpawn(int side) {
        spawn = new SpawnPoint(200, side);
        spawn.setDebug(true); //TODO turn off debug before releasing
        stage.addActor(spawn);
    }

    private void sendWave(int side) {
        spawn.setX(spawn.getX() + Gdx.graphics.getDeltaTime() * spawn.getSpeed());
        throwFallingObj(side);
    }

    private void throwFallingObj(int side) {
        if (side == HTFPGame.LEFT_SIDE) {
            for (int i = 0; i < fallingObjList.size(); i++) {
                if (spawn.getX() + spawn.getWidth() / 2 >= fallingObjList.get(i).getPosX()) {
                    fallingObjList.get(i).setVisible(true);
                }
            }
        } else if (side == HTFPGame.RIGHT_SIDE) {
            for (int i = 0; i < fallingObjList.size(); i++) {
                if (spawn.getX() + spawn.getWidth() / 2 <= fallingObjList.get(i).getPosX()) {
                    fallingObjList.get(i).setVisible(true);
                }
            }
        }
    }

    private void clearWave() {
        stage.getActors().removeValue(spawn,true);
        for (int i = 0; i < fallingObjList.size(); i++) {
            stage.getActors().removeValue(fallingObjList.get(i),true);
        }
        fallingObjList.clear();
    }

    private void showScoreDialog(){
        // TODO add code to pause and present dialog with score
        game.setScreen(new ScoreDialogScreen(game));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    // ***
    // UPDATERS
    // ***

    private void updateStartCounter(){
        mStartCounter -= Gdx.graphics.getDeltaTime();
        startCounterStr = String.format("%2.0f", mStartCounter);
    }

    private void updateClock(boolean update){
        if (update) {
            mClock -= Gdx.graphics.getDeltaTime();
        } else {
            mClock = 0;
        }
        clockStr = String.format("%2.0f", mClock);
    }

    private void updateScore(int update){
        setGameScore(getGameScore() + update);
    }

    private void updateIdlePeopleCount(int update){
        setIdlePeopleCount(getIdlePeopleCount() + update);
    }

    private void updateWorkingPeopleCount(int update) {
        setWorkingPeopleCount(getWorkingPeopleCount() + update);
    }

    private void updateAliensCount(int update){
        setAliensCount(getAliensCount() + update);
    }

    private void updateLevelCounter(){
        setLevelCount(getLevelCount() + 1);
    }

    private int updateMaxScoreForLevel(int level) {
        int max = 10;
        for (int i = 1; i < level; i++) {
            max += Math.floor((i+1) * Math.pow(HTFPGame.MAX_SCORE_MULTIPLYER, i) * 10);
        }
        setLevelMaxScore(max);
        return max;
    }

    // ***
    // GETTERS AND SETTERS
    // ***

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
        game.setCurrentGameScore(gameScore);
    }

    public int getIdlePeopleCount() {
        return idlePeopleCount;
    }

    public void setIdlePeopleCount(int idlePeopleCount) {
        this.idlePeopleCount = idlePeopleCount;
    }

    public int getWorkingPeopleCount() {
        return workingPeopleCount;
    }

    public void setWorkingPeopleCount(int workingPeopleCount) {
        this.workingPeopleCount = workingPeopleCount;
    }

    public int getAliensCount() {
        return aliensCount;
    }

    public void setAliensCount(int aliensCount) {
        this.aliensCount = aliensCount;
    }

    public int getLevelCount() {
        return levelCount;
    }

    public void setLevelCount(int levelCount) {
        this.levelCount = levelCount;
    }

    public int getLevelMaxScore() {
        return levelMaxScore;
    }

    public void setLevelMaxScore(int levelMaxScore) {
        this.levelMaxScore = levelMaxScore;
    }
}

// returns FallingObj with lower PosX (one which spawns more to the left)
class FallingObjectComparatorByPosX implements Comparator<FallingObj> {
    @Override
    public int compare(FallingObj fo1, FallingObj fo2) {
        if (fo1.getPosX() <= fo2.getPosX()) {
            return 1;
        } else {
            return 2;
        }
    }
}