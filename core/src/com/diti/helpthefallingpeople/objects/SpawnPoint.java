package com.diti.helpthefallingpeople.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.diti.helpthefallingpeople.HTFPGame;

/**
 * Created by DiTi on 2017-04-23.
 */

public class SpawnPoint extends Image {
    private Animation animation;
    private TextureRegion currentFrame;
    private int width, height;
    private float stateTime;
    private float speed;
    private int side;

    public SpawnPoint(float speed, int side) {
        this.side = side;
        setX(side);
        setY(HTFPGame.HEIGHT - 50);
        if (side == HTFPGame.LEFT_SIDE) {
            this.speed = speed;
        } else if (side == HTFPGame.RIGHT_SIDE) {
            this.speed = -speed;
        }
        initGraphic();
        this.setSize(width, height);
        setOrigin(0, 0);
    }

    private void initGraphic() {
        Texture frameSheet = new Texture(Gdx.files.internal("ufo_anim_4x1.png"));
        int frameCols = 4;
        int frameRows = 1;
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

    public void stopSpawn() {
        this.speed = 0;
    }

    public int getSide() {
        return side;
    }
}
