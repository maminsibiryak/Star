package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by Andrey on 17.12.2016.
 */
public class Hero {
    private Texture texture;
    private Texture textureBoom;
    private Vector2 position;
    private float speed;
    private int fireRate;
    private int fireCounter;
    private Rectangle rect;

    private boolean active;

    public boolean isActive() {
        return active;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Hero() {


        textureBoom = new Texture("boom.png");
        position = new Vector2(100, 100);
        speed = 6.0f;
        fireRate = 10;
        active = false;
        rect = new Rectangle(position.x, position.y, 48, 100);

            texture = new Texture("steve.png");


    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x , position.y );
    }
    public void renderBoom(SpriteBatch batch){
        batch.draw(textureBoom,position.x,position.y);

    }

    public void update() {


        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            fireCounter++;
            if (fireCounter > fireRate) {
                fireCounter = 0;
                fire(position.x, position.y+40);
                Sound sound = Gdx.audio.newSound(Gdx.files.internal("arrows.mp3"));
                sound.play(0.2f);
            }
        }

        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.UP)) {
            position.y += speed;
            if (position.y > 1080) {
                position.y = -30;
            }
        }
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.DOWN)) {
            position.y -= speed;
            if (position.y < -30) {
                position.y = 1080;
            }
        }
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.LEFT)) {
            position.x -= speed;
            if (position.x < 0) {
                position.x = 0;
            }
        }
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.RIGHT)) {
            position.x += speed;
            if (position.x > 1920) {
                position.x = 1920;
            }
        }
    }

    public void fire(float x, float y) {
        for (int i = 0; i < MyGdxGame.bullets.length; i++) {
            if (!MyGdxGame.bullets[i].isActive()) {
                MyGdxGame.bullets[i].setup(x, y);
                break;
            }
        }
    }



}
