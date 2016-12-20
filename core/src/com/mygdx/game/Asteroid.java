package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Andrey on 17.12.2016.
 */
public class Asteroid {
    private Texture texture;
    private Vector2 position;
    private float speed;
    private Rectangle rect;

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Asteroid() {
        position = new Vector2(1920 + (float) Math.random() * 1920, (float) Math.random() * 1080);
        speed = 4.0f + (float) Math.random() * 8.0f;
        rect = new Rectangle(position.x, position.y, 150, 150);
        if (texture == null) {
            texture = new Texture("zombie.png");
        }
    }

    public void recreate() {
        position.x = 1920 + (float) Math.random() * 1920;
        position.y = (float) Math.random() * 1080;
        speed = 4.0f + (float) Math.random() * 8.0f;
    }

    public void render(SpriteBatch batch) {

        batch.draw(texture, position.x, position.y);
    }

    public void update() {
        position.x -= speed;
        if (position.x < -60) {
            recreate();
        }
        rect.x = position.x;
        rect.y = position.y;
    }
}

