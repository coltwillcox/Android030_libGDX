package com.colt.simovina;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MyGdxGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture image;
    private Sprite sprite;
    private BitmapFont font;
    private TextureAtlas textureAtlas;
    private Animation animation;
    private float timePassed = 0;

	@Override
	public void create () {
        batch = new SpriteBatch();

        //Static image.
        image = new Texture("background.png");
        sprite = new Sprite(image);

        //Text.
        font = new BitmapFont();
        font.setColor(Color.GREEN);
        font.getData().scale(5); //Grow fonts 5x.

        //Atlas and animation.
        textureAtlas = new TextureAtlas(Gdx.files.internal("draco.atlas"));
        animation = new Animation(1/10f, textureAtlas.getRegions()); //Frame duration, atlas.
	}

    @Override
	public void render () {
        Gdx.gl.glClearColor(1, 0, 1, 1); //Background color.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Draw everything.
        batch.begin();
        sprite.draw(batch);
        font.draw(batch, "Hello, World!", 100, 100); //0, 0 is bottom left, but counting top left font object corner.

        timePassed += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(timePassed, true), 200, 200); //true - loop animation.
        batch.end();
	}

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        font.dispose();
        textureAtlas.dispose();
    }

}