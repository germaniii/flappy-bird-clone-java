package com.germaniii.bird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import org.w3c.dom.Text;

public class Bird extends Game {
	SpriteBatch batch;
	BitmapFont font;
	Texture backgroundTexture;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		backgroundTexture = new Texture("background-day.png");
		this.setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		backgroundTexture.dispose();
	}
}
