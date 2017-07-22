package com.diti.helpthefallingpeople.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.diti.helpthefallingpeople.HTFPGame;

/**
 * Created by DiTi on 2017-04-17.
 */

public class Person extends FallingObj {

    public Person(float posX, float speed, String fileName, int frameCols , int frameRows) {
        super(posX, speed, fileName, frameCols, frameRows);
    }
}