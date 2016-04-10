package com.colt.simovina;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class MyGdxGame extends ApplicationAdapter implements GestureDetector.GestureListener {

    private int screenWidth;
    private int screenHeight;
    private float timePassed = 0;
    private String message = "Touch me!";
    private SpriteBatch batch;
    //private Texture image;
    private Texture worldMapTexture;
    //private Sprite sprite;
    private Sprite worldMapSprite;
    private BitmapFont font;
    private TextureAtlas textureAtlas;
    private Animation animation;
    private GlyphLayout layout;
    private OrthographicCamera camera; //2D camera without perspective.
    private Sound horn;
    private Music music;

	@Override
	public void create () {
        batch = new SpriteBatch();
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        layout = new GlyphLayout();
        Gdx.input.setInputProcessor(new GestureDetector(this)); //Point to this class for user input methods.

        //Camera.
        camera = new OrthographicCamera(screenWidth, screenHeight);

        //World map.
        worldMapTexture = new Texture("worldmap.png");
        worldMapSprite = new Sprite(worldMapTexture);
        worldMapSprite.setPosition(-worldMapSprite.getWidth() / 2, -worldMapSprite.getHeight() / 2);

        //Static image.
        //image = new Texture("background.png");
        //sprite = new Sprite(image);

        //Text.
        font = new BitmapFont();
        font.setColor(Color.GREEN);
        font.getData().scale(5); //Grow fonts 5x.

        //Atlas and animation.
        textureAtlas = new TextureAtlas(Gdx.files.internal("draco.atlas"));
        animation = new Animation(1/10f, textureAtlas.getRegions()); //Frame duration, atlas.

        //Sounds.
        horn = Gdx.audio.newSound(Gdx.files.internal("sounds/horn.wav"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/assault.ogg"));
        music.play();
	}

    @Override
	public void render () {
        Gdx.gl.glClearColor(1, 0, 1, 1); //Background color.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        layout.setText(font, message);
        float textWidth = layout.width; //Contains the width of the current set text.
        float textHeight = layout.height; //Contains the height of the current set text.
        float x = screenWidth / 2 - textWidth / 2;
        float y = screenHeight / 2 + textHeight / 2;

        //Draw everything.
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        //sprite.draw(batch);
        worldMapSprite.draw(batch);
        font.draw(batch, message, x, y); //0, 0 is bottom left, but counting top left font object corner.
        timePassed += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(timePassed, true), 200, 50); //true - loop animation.
        batch.end();
	}

    /*
    //InputProcessor methods.
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        message = "Touching down: " + screenX + ", " + screenY; //screenX and screenY variables start from the top-left instead of the bottom-left.
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        message = "Touching up: " + screenX + ", " + screenY;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        message = "Dragging: " + screenX + ", " + screenY;
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    */

    //GestureListener methods.
    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        camera.translate(-deltaX, deltaY);
        camera.update();
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        message = "Touched at: " + x + ", " + y;
        long hornID = horn.play();
        horn.setVolume(hornID, 1);
        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    //End of program.
    @Override
    public void dispose() {
        batch.dispose();
        //image.dispose();
        worldMapTexture.dispose();
        font.dispose();
        textureAtlas.dispose();
        horn.dispose();
        music.dispose();
    }

}