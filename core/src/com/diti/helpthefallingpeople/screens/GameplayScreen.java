package com.diti.helpthefallingpeople.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.diti.helpthefallingpeople.HTFPGame;
import com.diti.helpthefallingpeople.objects.Person;

import java.util.Random;

/**
 * Created by DiTi on 2017-04-09.
 */

class GameplayScreen extends AbstractScreen {
    private Texture background;
    private Person person;
    private Array<Person> people;
    private Random random;

    GameplayScreen(final HTFPGame game) {
        super(game);
    }

    @Override
    protected void init() {
        background = new Texture("gameplayscreen_placeholder.jpg");
        random = new Random();
        people = new Array<Person>();
        generatePeople(15);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();
        person.setStateTime(person.getStateTime() + Gdx.graphics.getDeltaTime());
        person.setCurrentFrame(person.getAnimation().getKeyFrame(person.getStateTime(), true));
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        spriteBatch.end();
        stage.draw();
    }

    private void update() {
        //person.setY(person.getY() - Gdx.graphics.getDeltaTime() * person.getSpeed());
        for (int i=0; i<people.size; i++){
            people.get(i).setY(people.get(i).getY() - Gdx.graphics.getDeltaTime() * people.get(i).getSpeed());
        }
        stage.act();
    }

    private void generatePeople(int number){
        for (int i=0; i<number; i++) {
            person = new Person(random.nextFloat(), random.nextFloat());
            person.setY(500);
            person.setDebug(true); //TODO turn off debug before releasing
            people.add(person);
        }
        for (int i=0; i<people.size; i++){
            stage.addActor(people.get(i));
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
