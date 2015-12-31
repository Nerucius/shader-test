package net.neru.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.WindowedMean;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import net.neru.game.util.Shaders;

public class ShaderGame extends ApplicationAdapter {
    ScreenViewport viewport;
    OrthographicCamera camera;

    SpriteBatch batch;
    Texture img;

    float w, h;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);

        batch = new SpriteBatch(1000, Shaders.DEFAULT);
        img = new Texture("map.png");

        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

        Gdx.input.setInputProcessor(new InputAdapter() {
            public boolean keyUp(int keycode) {
                switch (keycode) {
                    case Input.Keys.F1:
                        batch.setShader(Shaders.DEFAULT);
                        Gdx.app.log("SHADER", "Using default shader");
                        break;
                    case Input.Keys.F2:
                        batch.setShader(Shaders.CONTRAST);
                        Gdx.app.log("SHADER", "Using contrast shader");
                        break;
                    case Input.Keys.F3:
                        batch.setShader(Shaders.LIGHTMAP);
                        Gdx.app.log("SHADER", "Using lightmap shader");
                        break;
                }
                return false;
            }

            private Vector3 screen = new Vector3();

            public boolean touchDragged(int screenX, int screenY, int pointer) {
                screen.set(screenX, screenY, 1);
                camera.unproject(screen);
                Shaders.LIGHTMAP.begin();
                Shaders.LIGHTMAP.setUniformf("u_lightPos", screen.x, screen.y);
                Shaders.LIGHTMAP.end();
                return false;
            }
        });


    }

    private WindowedMean renderTime = new WindowedMean(10);

    private long dt;

    @Override
    public void render() {
        dt = System.nanoTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.graphics.setTitle(String.format("FPS: %d, Render time: %.2fms", Gdx.graphics.getFramesPerSecond(), renderTime.getMean()));

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();

        renderTime.addValue((System.nanoTime() - dt) / 1e6f);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);

        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
    }

    @Override
    public void dispose() {
        img.dispose();
        batch.dispose();
    }
}
