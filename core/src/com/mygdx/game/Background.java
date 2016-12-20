package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Andrey on 17.12.2016.
 */
public class Background {
    class Star {
        private Vector2 position;
        private float speed;

        public Star() {
            position = new Vector2((float) Math.random() * 1920, (float) Math.random() * 1080);
            speed = 1.0f + (float) Math.random() * 7.0f;
        }

        public void update() {
            position.x -= speed;
            if (position.x < -20) {
                position.x = 1920;
                position.y = (float) Math.random() * 1080;
                speed = 1.0f + (float) Math.random() * 7.0f;
            }
        }
    }

    private Texture texture;
    private Texture textureStar;
    private final int STARS_COUNT = 200;
    private Star[] stars;


    public Background() {
        texture = new Texture("background.jpg");
        textureStar = new Texture("star.png");
        stars = new Star[STARS_COUNT];
        for (int i = 0; i < STARS_COUNT; i++) {
            stars[i] = new Star();
        }
    }

    public void render(SpriteBatch bacth) {
        bacth.draw(texture, 0, 0);
        for (int i = 0; i < STARS_COUNT; i++) {
            bacth.draw(textureStar, stars[i].position.x, stars[i].position.y);
        }
    }

    public void update() {
        for (int i = 0; i < STARS_COUNT; i++) {
            stars[i].update();
        }
    }

}
