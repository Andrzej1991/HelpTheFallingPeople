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
import com.diti.helpthefallingpeople.objects.Person;
import com.diti.helpthefallingpeople.objects.SpawnPoint;

import java.awt.Component;
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
    private Person person;
    private List<Person> people;
    private SpawnPoint spawn;
    private Random random;
    private Label.LabelStyle labelStyle;
    private Label startCounterLabel;
    private String startCounterStr;
    private float mStartCounter;

    private int gameScore;
    private Label scoreLabel;

    private Label clockLabel;
    private String clockStr;
    private float mClock;

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
        random = new Random();
        people = new ArrayList<Person>();
        initWave();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();
        for (int i = 0; i < people.size(); i++) {
                people.get(i).setStateTime(people.get(i).getStateTime() + Gdx.graphics.getDeltaTime());
                people.get(i).setCurrentFrame(people.get(i).getAnimation().getKeyFrame(people.get(i).getStateTime(), true));
        }
        spawn.setStateTime(spawn.getStateTime() + Gdx.graphics.getDeltaTime());
        spawn.setCurrentFrame(spawn.getAnimation().getKeyFrame(spawn.getStateTime(), true));
        drawing();
    }

    private void update() {
        startCounterLabel.setText(startCounterStr);
        startCounterLabel.setFontScale(5);

        clockLabel.setText(clockStr);

        scoreLabel.setText("Score: " + String.valueOf(getGameScore()));
        scoreLabel.setFontScale(1);

        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).isVisible()) {
                people.get(i).setY(people.get(i).getY() - Gdx.graphics.getDeltaTime() * people.get(i).getSpeed());
            }
        }
        if (mStartCounter < 0) {
            sendWave();
        }
        if (people.size() > 0) {
            for (int i = 0; i <= people.size() - 1; i++) {
                if (people.get(i).getY() > 0) {
                    break;
                } else if (i == people.size() - 1) {
                    clearWave();
                    initWave();
                    sendWave();
                }
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
        scoreLabel.setPosition(20, (float) (HTFPGame.HEIGHT*2/9));
        stage.addActor(scoreLabel);
        setGameScore(0);
    }

    private void initWave() {
        generatePeople(15);
        generateSpawn();
    }

    private void generatePeople(int number) {
        for (int i = 0; i < number; i++) {
            person = new Person(random.nextFloat(), random.nextFloat());
            person.setY(HTFPGame.HEIGHT - 50);
            person.setDebug(true); //TODO turn off debug before releasing
            person.setVisible(false);
            person.addListener(new ClickListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Actor a = event.getListenerActor();
                    int i = people.indexOf(a);
                    people.remove(i);
                    stage.getActors().removeValue(a,false);
                    updateScore(1);
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
            people.add(person);
        }
        Collections.sort(people, new PersonComparatorByPosX());
        for (int i = 0; i < people.size(); i++) {
            stage.addActor(people.get(i));
        }
    }

    private void generateSpawn() {
        spawn = new SpawnPoint(200);
        spawn.setDebug(true); //TODO turn off debug before releasing
        stage.addActor(spawn);
    }

    private void sendWave() {
        spawn.setX(spawn.getX() + Gdx.graphics.getDeltaTime() * spawn.getSpeed());
        throwPeople();
    }

    private void throwPeople() {
        for (int i = 0; i < people.size(); i++) {
            if (spawn.getX() + spawn.getWidth()/2 >= people.get(i).getPosX()) {
                people.get(i).setVisible(true);
            }
        }
    }

    private void clearWave() {
        stage.getActors().removeValue(spawn,true);
        for (int i = 0; i < people.size(); i++) {
            stage.getActors().removeValue(people.get(i),true);
            //System.out.println(stage.getActors());
        }
        people.clear();
    }

    private void showScoreDialog(){
        // TODO add code to pause and present dialog with score
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }
}

// returns Person with lower PosX (one which spawns more to the left)
class PersonComparatorByPosX implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        if (p1.getPosX() <= p2.getPosX()) {
            return 1;
        } else {
            return 2;
        }
    }
}