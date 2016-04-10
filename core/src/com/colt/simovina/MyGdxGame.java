package com.colt.simovina;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private BitmapFont font;
    private Texture image;
    private Sprite sprite;

	@Override
	public void create () {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.GREEN);
        font.getData().scale(5); //Grow fonts 5x.
        image = new Texture("choco.jpg");
        sprite = new Sprite(image);
	}

    @Override
	public void render () {
        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        sprite.draw(batch);
        font.draw(batch, "Hello, World!", 100, 100); //0, 0 is bottom left, but counting top left font object corner.
        batch.end();
	}

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        image.dispose();
    }

}