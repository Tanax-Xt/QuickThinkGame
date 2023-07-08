package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.ui.UiComponent;
import com.mygdx.game.utils.GameSettings;

import java.util.ArrayList;

public class MenuScreen implements Screen {
    MyGdxGame myGdxGame;
    ArrayList<UiComponent> components;
    final int titleWidth = 450;
    final int titleHeight = 200;
    final int bgHeight = (int) (GameSettings.SCR_HEIGHT);
    final int bgWidth = GameSettings.SCR_WIDTH;
    final int versionTextHeight = 50;
    final int versionTextWidth = 300;
    final int buttonWidth = 550;
    final int buttonHeight = 150;
    public MenuScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        components = new ArrayList<>();

        ImageView yellowBG = new ImageView(0, versionTextHeight * 2, bgWidth, bgHeight, "images/yellowbg.png");
        ImageView title = new ImageView(GameSettings.SCR_WIDTH / 2 - titleWidth / 2, (int) (GameSettings.SCR_HEIGHT - titleHeight * 1.5), titleWidth, titleHeight, "images/title.png");
        ImageView versionText = new ImageView(GameSettings.SCR_WIDTH / 2 - versionTextWidth / 2, versionTextHeight / 2, versionTextWidth, versionTextHeight, "images/version.png");
        ImageView clickCarefullyBtn = new ImageView((int) (GameSettings.SCR_WIDTH / 2 - 0.5 * buttonWidth), GameSettings.SCR_HEIGHT / 2 - 20, buttonWidth, buttonHeight, "buttons/clickcarefully.png");
        ImageView collectOrderBtn = new ImageView((int) (GameSettings.SCR_WIDTH / 2 - 0.5 * buttonWidth), GameSettings.SCR_HEIGHT / 2 - 220, buttonWidth, buttonHeight, "buttons/collectorder.png");
        ImageView chooseRightBtn = new ImageView((int) (GameSettings.SCR_WIDTH / 2 - 0.5 * buttonWidth), GameSettings.SCR_HEIGHT / 2 - 420, buttonWidth, buttonHeight, "buttons/chooseright.png");
        ImageView settingsBtn = new ImageView((int) (GameSettings.SCR_WIDTH / 2 - 0.5 * buttonWidth), GameSettings.SCR_HEIGHT / 2 - 620, buttonWidth, buttonHeight, "buttons/settings.png");


        components.add(yellowBG);
        components.add(title);
        components.add(versionText);
        components.add(clickCarefullyBtn);
        components.add(collectOrderBtn);
        components.add(chooseRightBtn);
        components.add(settingsBtn);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
//        ScreenUtils.clear((float) 0.99, (float) 0.91, (float) 0.06, 1);
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
