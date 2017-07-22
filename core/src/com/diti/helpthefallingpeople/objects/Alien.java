package com.diti.helpthefallingpeople.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.diti.helpthefallingpeople.HTFPGame;

/**
 * Created by terebun on 2017-07-05.
 */

public class Alien extends FallingObj {

    public Alien(float posX, float speed, String fileName, int frameCols , int frameRows) {
        super(posX, speed, fileName, frameCols, frameRows);
    }
}