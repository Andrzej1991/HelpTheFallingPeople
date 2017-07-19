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
    private Person person;
    private List<Person> people;
    private Alien alien;
    private List<Alien> aliens;
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
        aliens = new ArrayList<Alien>();
        initWave(HTFPGame.LEFT_SIDE); //set starting side of first wave
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        //render people
        for (int i = 0; i < people.size(); i++) {
                people.get(i).setStateTime(people.get(i).getStateTime() + Gdx.graphics.getDeltaTime());
                people.get(i).setCurrentFrame(people.get(i).getAnimation().getKeyFrame(people.get(i).getStateTime(), true));
        }

        //render aliens
        for (int i = 0; i < aliens.size(); i++) {
            aliens.get(i).setStateTime(aliens.get(i).getStateTime() + Gdx.graphics.getDeltaTime());
            aliens.get(i).setCurrentFrame(aliens.get(i).getAnimation().getKeyFrame(aliens.get(i).getStateTime(), true));
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

        scoreLabel.setText("Score: " + String.valueOf(getGameScore()));
        scoreLabel.setFontScale(1);

        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).isVisible()) {
                people.get(i).setY(people.get(i).getY() - Gdx.graphics.getDeltaTime() * people.get(i).getSpeed());
            }
        }
        for (int i = 0; i < aliens.size(); i++) {
            if (aliens.get(i).isVisible()) {
                aliens.get(i).setY(aliens.get(i).getY() - Gdx.graphics.getDeltaTime() * aliens.get(i).getSpeed());
            }
        }
        if (mStartCounter < 0) {
            sendWave(spawn.getSide());
        }
        // TEST - if nothing to fall then create a wave
        boolean p = false;
        boolean a = false;
        if (people.size() > 0) {
            for (int i = 0; i <= people.size() - 1; i++) {
                if (people.get(i).getY() > 0) {
                    break;
                } else if (i == people.size() - 1) {
                    p = true;
                }
            }
        } else {
            p = true;
        }
        if (p) {
            if (aliens.size() > 0) {
                for (int i = 0; i <= aliens.size() - 1; i++) {
                    if (aliens.get(i).getY() > 0) {
                        break;
                    } else if (i == aliens.size() - 1) {
                        a = true;
                    }
                }
            } else {
                a = true;
            }
        }
        if (p && a) {
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

    private void initWave(int side) {
        generateSpawn(side);
        generatePeople(spawn.getSide(), 1, 1, 0);
    }

    private void generatePeople(int side, int pplNumber, int alienNumber, int bombNumber) {
        for (int i = 0; i < pplNumber; i++) {
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

        // generate aliens TODO
        for (int i = 0; i < alienNumber; i++) {
            alien = new Alien(random.nextFloat(), random.nextFloat());
            alien.setY(HTFPGame.HEIGHT - 50);
            alien.setDebug(true); //TODO turn off debug before releasing
            alien.setVisible(false);
            alien.addListener(new ClickListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Actor a = event.getListenerActor();
                    int i = aliens.indexOf(a);
                    aliens.remove(i);
                    stage.getActors().removeValue(a,false);
                    updateScore(-1);
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
            aliens.add(alien);
        }

        // sort lists
        if (side == HTFPGame.LEFT_SIDE) {
            Collections.sort(people, new PersonComparatorByPosX());
            Collections.sort(aliens, new AlienComparatorByPosX()); //TEMP aliens
        } else if (side == HTFPGame.RIGHT_SIDE) {
            Collections.sort(people, new PersonComparatorByPosX().reversed());
            Collections.sort(aliens, new AlienComparatorByPosX().reversed()); //TEMP aliens
        }

        // add falling objects as actors
        for (int i = 0; i < people.size(); i++) {
            stage.addActor(people.get(i));
        }
        //TEST aliens
        for (int i = 0; i < aliens.size(); i++) {
            stage.addActor(aliens.get(i));
        }
    }

    private void generateSpawn(int side) {
        spawn = new SpawnPoint(200, side);
        spawn.setDebug(true); //TODO turn off debug before releasing
        stage.addActor(spawn);
    }

    private void sendWave(int side) {
        spawn.setX(spawn.getX() + Gdx.graphics.getDeltaTime() * spawn.getSpeed());
        throwPeople(side);
    }

    private void throwPeople(int side) {
        if (side == HTFPGame.LEFT_SIDE) {
            for (int i = 0; i < people.size(); i++) {
                if (spawn.getX() + spawn.getWidth() / 2 >= people.get(i).getPosX()) {
                    people.get(i).setVisible(true);
                }

            }
            //TEST aliens
            for (int i = 0; i < aliens.size(); i++) {
                if (spawn.getX() + spawn.getWidth() / 2 >= aliens.get(i).getPosX()) {
                    aliens.get(i).setVisible(true);
                }
            }
        } else if (side == HTFPGame.RIGHT_SIDE) {
            for (int i = 0; i < people.size(); i++) {
                if (spawn.getX() + spawn.getWidth() / 2 <= people.get(i).getPosX()) {
                    people.get(i).setVisible(true);
                }
            }
            //TEST aliens
            for (int i = 0; i < aliens.size(); i++) {
                if (spawn.getX() + spawn.getWidth() / 2 <= aliens.get(i).getPosX()) {
                    aliens.get(i).setVisible(true);
                }
            }
        }
    }

    private void clearWave() {
        stage.getActors().removeValue(spawn,true);
        for (int i = 0; i < people.size(); i++) {
            stage.getActors().removeValue(people.get(i),true);
            //System.out.println(stage.getActors());
        }
        //TEST aliens
        for (int i = 0; i < aliens.size(); i++) {
            stage.getActors().removeValue(aliens.get(i),true);
            //System.out.println(stage.getActors());
        }
        people.clear();
        aliens.clear(); //TEST aliens
    }

    private void showScoreDialog(){
        // TODO add code to pause and present dialog with score
        game.setScreen(new ScoreDialogScreen(game));
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
        game.setCurrentGameScore(gameScore);
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

// TEMP temporary class to test the game - later need to make abstract class for each falling object
class AlienComparatorByPosX implements Comparator<Alien> {
    @Override
    public int compare(Alien p1, Alien p2) {
        if (p1.getPosX() <= p2.getPosX()) {
            return 1;
        } else {
            return 2;
        }
    }
}