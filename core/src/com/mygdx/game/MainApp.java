package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;


public class MainApp extends ApplicationAdapter {
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	AnalogClock glock;
	BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();
		font.getData().setScale(2F);
		glock = new AnalogClock();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		glock.render(shapeRenderer, batch, font);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
