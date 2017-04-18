package com.diti.helpthefallingpeople.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.diti.helpthefallingpeople.HTFPGame;
import com.diti.helpthefallingpeople.objects.Person;

/**
 * Created by DiTi on 2017-04-09.
 */

public class GameplayScreen extends AbstractScreen {
    private Texture background;
    private Person person;

    public GameplayScreen(final HTFPGame game) {
        super(game);
    }

    @Override
    protected void init() {
        background = new Texture("gameplayscreen_placeholder.jpg");
        person = new Person();
        person.setX(100);
        person.setY(500);
        person.setDebug(true); //TODO turn off debug before releasing
        stage.addActor(person);
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
        person.setY(person.getY() - Gdx.graphics.getDeltaTime() * person.getSpeed());
        stage.act();
    }


    @Override
    public void dispose() {
        stage.dispose();
    }
}
