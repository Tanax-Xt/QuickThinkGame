package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.Blueout;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.ui.UiComponent;
import com.mygdx.game.utils.GameSettings;

import java.util.ArrayList;

public class GameOverScreen implements Screen {
    ArrayList<UiComponent> components;

    MyGdxGame myGdxGame;

    final int whiteRectHigh = (int) (GameSettings.SCR_HEIGHT * 0.75);
    final int whiteRectWidth = (int) (GameSettings.SCR_WIDTH * 0.75);
    final int whiteRectPositionX = (int) (GameSettings.SCR_WIDTH / 2 - 0.5);
    final int whiteRectPositionY = (int) (GameSettings.SCR_HEIGHT / 2 - 0.5);

    TextView pointsView;

    public GameOverScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        components = new ArrayList<>();

        ImageView whiteRectangle = new ImageView(100, whiteRectPositionY, 900, whiteRectWidth, "backgrounds/gameoverWhiteRect.png");
        pointsView = new TextView(myGdxGame.gameOverBlueFont.bitmapFont, "Your points!", 300, 700);

        components.add(whiteRectangle);
        components.add(new Blueout());
        components.add(pointsView);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.camera.unproject(myGdxGame.touch);
            for (UiComponent component : components) {
                if (component.isVisible) component.isHit((int) myGdxGame.touch.x, (int) myGdxGame.touch.y);
            }
        }

        ScreenUtils.clear(1, 1, 1, 1);

        myGdxGame.camera.update();
        myGdxGame.batch.begin();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        for (UiComponent component: components) {
            component.draw(myGdxGame.batch);
        }

        myGdxGame.batch.end();
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
