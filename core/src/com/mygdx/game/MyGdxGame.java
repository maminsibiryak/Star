package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    Background background;
    Hero hero;
    private final int ASTEROIDS_COUNT = 30;
    Asteroid[] asteroids;
    private final int BULLETS_COUNT = 100;
    public static Bullet[] bullets;
    private Texture textureBullet;
    private Sound sound;
    private Sound soundMain;


    @Override
    public void create() {

        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        background = new Background();
        hero = new Hero();
        asteroids = new Asteroid[ASTEROIDS_COUNT];
        for (int i = 0; i < ASTEROIDS_COUNT; i++) {
            asteroids[i] = new Asteroid();

        }
        bullets = new Bullet[BULLETS_COUNT];
        for (int i = 0; i < BULLETS_COUNT; i++) {
            bullets[i] = new Bullet();
        }

        textureBullet = new Texture("arrows.png");
    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        background.render(batch);
        hero.render(batch);

        //
        for (int i = 0; i < ASTEROIDS_COUNT; i++) {
            if (asteroids[i].getRect().contains(hero.getPosition())) {
                asteroids[i].recreate();
                hero.renderBoom(batch);
                break;
            }

        }

        //
        //

        for (int i = 0; i < BULLETS_COUNT; i++) {
            if (bullets[i].isActive()) {
                bullets[i].update();
                for (int j = 0; j < ASTEROIDS_COUNT; j++) {
                    if (asteroids[j].getRect().contains(bullets[i].getPosition())) {
                        asteroids[j].recreate();
                        bullets[i].renderBoom(batch);
                        bullets[i].destroy();
                        break;
                    }

                }
            }

        }


        //

        for (int i = 0; i < ASTEROIDS_COUNT; i++) {
            asteroids[i].render(batch);

        }
        for (int i = 0; i < BULLETS_COUNT; i++) {
            if (bullets[i].isActive())
                batch.draw(textureBullet, bullets[i].getPosition().x, bullets[i].getPosition().y);

        }
        batch.end();
    }

    public void update() {
        background.update();
        hero.update();

        //

        if (Gdx.input.isKeyPressed(Input.Keys.TAB)){
            soundMain = Gdx.audio.newSound(Gdx.files.internal("starboy.mp3"));
            soundMain.play(0.5f);


        }

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){

            soundMain.stop();
        }

        //


        for (int i = 0; i < ASTEROIDS_COUNT; i++) {
            /*if (asteroids[i].getRect().contains(hero.getPosition())) {
                asteroids[i].recreate();
                hero.renderBoom(batch);
            }*/
            asteroids[i].update();


        }
        for (int i = 0; i < BULLETS_COUNT; i++) {
            if (bullets[i].isActive()) {
                bullets[i].update();
                for (int j = 0; j < ASTEROIDS_COUNT; j++) {
                    if (asteroids[j].getRect().contains(bullets[i].getPosition())) {
                        asteroids[j].recreate();
                        bullets[i].destroy();
                        break;
                    }

                }
            }

        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
