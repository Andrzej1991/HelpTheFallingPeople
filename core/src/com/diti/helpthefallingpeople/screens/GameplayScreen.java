package com.diti.helpthefallingpeople.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.diti.helpthefallingpeople.HTFPGame;
import com.diti.helpthefallingpeople.objects.Person;
import com.diti.helpthefallingpeople.objects.SpawnPoint;

import java.util.ArrayList;
import java.util.Collection;
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

    GameplayScreen(final HTFPGame game) {
        super(game);
    }

    @Override
    protected void init() {
        background = new Texture("gameplayscreen_placeholder.jpg");
        random = new Random();
        people = new ArrayList<Person>();
        generatePeople(15);
        generateSpawn();
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
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        spriteBatch.end();
        stage.draw();
    }

    private void update() {
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).isVisible()) {
                people.get(i).setY(people.get(i).getY() - Gdx.graphics.getDeltaTime() * people.get(i).getSpeed());
            }
        }
        spawn.setX(spawn.getX() + Gdx.graphics.getDeltaTime() * spawn.getSpeed());
        throwPeople();
        stage.act();
    }

    private void generatePeople(int number) {
        for (int i = 0; i < number; i++) {
            person = new Person(random.nextFloat(), random.nextFloat());
            person.setY(HTFPGame.HEIGHT - 50);
            person.setDebug(true); //TODO turn off debug before releasing
            person.setVisible(false);
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

    private void throwPeople() {
        for (int i = 0; i < people.size(); i++) {
            if (spawn.getX() + spawn.getWidth()/2 >= people.get(i).getPosX()) {
                people.get(i).setVisible(true);
            }
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
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