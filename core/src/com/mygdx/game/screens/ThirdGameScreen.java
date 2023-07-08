package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.ui.UiComponent;
import com.mygdx.game.utils.GameSettings;

import java.util.ArrayList;

public class ThirdGameScreen implements Screen {
    MyGdxGame myGdxGame;
    ArrayList<UiComponent> components;
    int returnMenuWidth = (int) (GameSettings.SCR_WIDTH * 0.6);
    int returnMenuHeight = (int) (GameSettings.SCR_HEIGHT * 0.1);
    int settingsIconWidth = (int) (GameSettings.SCR_WIDTH * 0.2);
    int settingsIconHeight = (int) (GameSettings.SCR_HEIGHT * 0.1);
    // ТАЙМЕР ОБРАТНОГО ОТСЧЕТА ПРИ ПЕРЕХОДЕ В GAME OVER
    private float timer = 3f;

    public ThirdGameScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        components = new ArrayList<>();

        ImageView returnMenu = new ImageView(0, GameSettings.SCR_HEIGHT - returnMenuHeight, returnMenuWidth, returnMenuHeight, "buttons/settingsReturn.png");
        ImageView settingsIcon = new ImageView(GameSettings.SCR_WIDTH - settingsIconWidth, GameSettings.SCR_HEIGHT - settingsIconHeight, settingsIconWidth, settingsIconHeight, "icons/settings.png");
        TextView clickText = new TextView(myGdxGame.largeGameFont.bitmapFont, "Click!", GameSettings.SCR_WIDTH / 2 - 150, 200);

        components.add(settingsIcon);
        components.add(returnMenu);
        components.add(clickText);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        timer -= Gdx.graphics.getDeltaTime();

        if (timer < 0) {
            timer = 0;
            myGdxGame.setScreen(myGdxGame.gameOverScreen);
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
