package com.germaniii.bird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.germaniii.bird.objects.Pipe;

import java.util.Iterator;

public class GameScreen implements Screen {
    private final Bird game;
    OrthographicCamera camera;
    Texture birdImage, pipeImage;
    Sprite birdSprite, pipeSpriteUp, pipeSpriteDown, backgroundSprite;

    private long lastPipe;

    private Rectangle bird, pointDetector;
    private Array<Pipe> pipesUp, pipesDown;

    private int score = 0;

    public GameScreen(Bird game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 288,512);
        backgroundSprite = new Sprite(game.backgroundTexture);

        birdImage = new Texture("bluebird-midflap.png");
        birdSprite = new Sprite(birdImage);
        birdSprite.setOrigin(34/2,24/2);
        birdSprite.setRotation(0);

        pipeImage = new Texture("pipe-green.png");
        pipeSpriteUp = new Sprite(pipeImage);
        pipeSpriteDown = new Sprite(pipeImage);
        pipeSpriteDown.setOrigin(52/2,320/2);
        pipeSpriteDown.setRotation(180);

        pointDetector = new Rectangle(288/2-34,0,52,320);
        bird = new Rectangle((288/2-34/2), (512/2-24/2),34,24);
        pipesUp = new Array<Pipe>();
        pipesDown = new Array<Pipe>();
        spawnPipes();


    }

    private void spawnPipes(){
        Pipe pipeDown = new Pipe(288,MathUtils.random(-200,-50),52, 320);
        Pipe pipeUp = new Pipe(288, pipeDown.y + 320 + 100, 52,320);
        pipesUp.add(pipeUp);
        pipesDown.add(pipeDown);

        lastPipe = TimeUtils.millis();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        birdSprite.setX(bird.x);
        birdSprite.setY(bird.y);
        ScreenUtils.clear(0,0,0.2f,1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        backgroundSprite.draw(game.batch);
        birdSprite.draw(game.batch);
        for(Pipe pipe : pipesUp)
            game.batch.draw(pipeSpriteUp, pipe.x, pipe.y);
        for(Pipe pipe : pipesDown)
            game.batch.draw(pipeSpriteDown, pipe.x, pipe.y);
        game.font.draw(game.batch,Integer.toString(score), 288/2, 512 * 7/8);
        game.batch.end();

        if(Gdx.input.justTouched()){
            bird.y += 3000 * delta;
            birdSprite.setRotation(30);
        }

        if(bird.y < 0)
            gameOver();
        if(birdSprite.getRotation() < 90)
            birdSprite.rotate(-100 * delta);
        if(birdSprite.getRotation() == 90)
            birdSprite.setRotation(90 * delta);

        bird.y -= 250 * delta;

        if(TimeUtils.millis() - lastPipe > 5000)
            spawnPipes();

        for(Iterator<Pipe> iterPipesUp = pipesUp.iterator(); iterPipesUp.hasNext(); ){
            Pipe pipe = iterPipesUp.next();
            pipe.x -= 50 * delta;

            if(pipe.x < -52)
                iterPipesUp.remove();
            if(pipe.overlaps(bird)){
                gameOver();
            }

            if(pipe.x < 288/2 && !pipe.getCounted()) {
                pipe.setCounted(true);
                score++;
            }
        }

        for(Iterator<Pipe> iterPipesDown = pipesDown.iterator(); iterPipesDown.hasNext(); ){
            Pipe pipe = iterPipesDown.next();
            pipe.x -= 50 * delta;

            if(pipe.x < -52)
                iterPipesDown.remove();
            if(pipe.overlaps(bird)){
                gameOver();
            }
        }



    }

    public void gameOver(){
        game.setScreen(new GameOverScreen(game));
        dispose();
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
        birdImage.dispose();
        pipeImage.dispose();
    }
}
