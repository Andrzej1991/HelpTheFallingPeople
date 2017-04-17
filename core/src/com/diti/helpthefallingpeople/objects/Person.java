package com.diti.helpthefallingpeople.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.Random;

/**
 * Created by DiTi on 2017-04-17.
 */

public class Person extends Image {
    float posX;
    int frameCols, frameRows;
    Animation animation;
    Texture frameSheet;
    TextureRegion[] frames;
    TextureRegion currentFrame;
    int width, height;
    private float stateTime;
    float speed;

    public Person() {
        posX = 100;//new Random().nextFloat();
        initGraphic();
        this.setSize(width, height);
        speed = 10;
    }

    private void initGraphic() {
        frameSheet = new Texture(Gdx.files.internal("sticker_anim_2x1.png"));
        frameCols = 2;
        frameRows = 1;
        width = frameSheet.getWidth() / frameCols;
        height = frameSheet.getHeight() / frameRows;
        TextureRegion[][] personTMP = TextureRegion.split(frameSheet, width, height);
        frames = new TextureRegion[frameCols * frameRows];
        int mIndex = 0;
        for (int i = 0; i < frameRows; i++) {
            for (int j = 0; j < frameCols; j++) {
                frames[mIndex++] = personTMP[i][j];
            }
        }
        animation = new Animation(0.5f, frames);
        setCurrentFrame(getAnimation().getKeyFrame(0));
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setCurrentFrame(TextureRegion currentFrame) {
        this.currentFrame = currentFrame;
    }

    public float getStateTime() {
        return stateTime;
    }

    public float getSpeed() {
        return speed;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }
}
