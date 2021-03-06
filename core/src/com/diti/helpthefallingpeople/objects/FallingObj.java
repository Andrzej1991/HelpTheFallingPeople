package com.diti.helpthefallingpeople.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.diti.helpthefallingpeople.HTFPGame;

/**
 * Created by terebun on 2017-07-20.
 */

public abstract class FallingObj extends Image {
    float posX;
    private Animation animation;
    private TextureRegion currentFrame;
    private int width, height;
    private float stateTime;
    private float speed;

    public FallingObj(float posX, float speed, String fileName, int frameCols , int frameRows) {
        initGraphic(fileName, frameCols, frameRows);
        this.posX = posX * (HTFPGame.WIDTH - width);
        setX(this.posX);
        this.speed = speed * 350 + 50;
        this.setSize(width, height);
        setOrigin(0, 0);
    }

    private void initGraphic(String fileName, int frameCols , int frameRows) {
        Texture frameSheet = new Texture(Gdx.files.internal(fileName));
        width = frameSheet.getWidth() / frameCols;
        height = frameSheet.getHeight() / frameRows;
        TextureRegion[][] personTMP = TextureRegion.split(frameSheet, width, height);
        TextureRegion[] frames = new TextureRegion[frameCols * frameRows];
        int mIndex = 0;
        for (int i = 0; i < frameRows; i++) {
            for (int j = 0; j < frameCols; j++) {
                frames[mIndex++] = personTMP[i][j];
            }
        }
        animation = new Animation(0.1f, frames);
        setCurrentFrame(getAnimation().getKeyFrame(0));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch, parentAlpha);
        batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight());
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

    public float getPosX() {
        return posX;
    }
}
