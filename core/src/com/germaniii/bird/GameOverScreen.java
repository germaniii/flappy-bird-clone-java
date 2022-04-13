package com.germaniii.bird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;

import org.w3c.dom.Text;

public class GameOverScreen implements Screen {
    private final Bird game;
    private final OrthographicCamera camera;
    Sprite backgroundSprite;

    public GameOverScreen(Bird game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 288,512);

        backgroundSprite = new Sprite(game.backgroundTexture);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        backgroundSprite.draw(game.batch);
        game.font.draw(game.batch, "Game Over!", 50, 400);
        game.font.draw(game.batch, "Press the screen to play again!", 50, 300);
        game.batch.end();


        if(Gdx.input.justTouched()){
            game.setScreen(new GameScreen(game));
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
