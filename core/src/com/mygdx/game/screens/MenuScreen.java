package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
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
    final int titleWidth = (int) (GameSettings.SCR_WIDTH * 0.7);
    final int titleHeight = (int) (GameSettings.SCR_HEIGHT * 0.15);
    final int bgHeight = GameSettings.SCR_HEIGHT;
    final int bgWidth = GameSettings.SCR_WIDTH;
    final int versionTextHeight = 50;
    final int versionTextWidth = 300;
    final int buttonWidth = (int) (GameSettings.SCR_WIDTH * 0.75);
    final int buttonHeight = (int) (GameSettings.SCR_WIDTH * 0.21);
    public MenuScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        components = new ArrayList<>();

        ImageView yellowBG = new ImageView(0, versionTextHeight * 2, bgWidth, bgHeight, "images/yellowbg.png");
        ImageView title = new ImageView(GameSettings.SCR_WIDTH / 2 - titleWidth / 2, (int) (GameSettings.SCR_HEIGHT - titleHeight * 1.5), titleWidth, titleHeight, "images/title.png");
        ImageView versionText = new ImageView(GameSettings.SCR_WIDTH / 2 - versionTextWidth / 2, versionTextHeight / 2, versionTextWidth, versionTextHeight, "images/version.png");
        ImageView clickCarefullyBtn = new ImageView((int) (GameSettings.SCR_WIDTH / 2 - 0.5 * buttonWidth), GameSettings.SCR_HEIGHT / 2 - 80, buttonWidth, buttonHeight, "buttons/clickcarefully.png");
        ImageView collectOrderBtn = new ImageView((int) (GameSettings.SCR_WIDTH / 2 - 0.5 * buttonWidth), GameSettings.SCR_HEIGHT / 2 - 330, buttonWidth, buttonHeight, "buttons/collectorder.png");
        ImageView chooseRightBtn = new ImageView((int) (GameSettings.SCR_WIDTH / 2 - 0.5 * buttonWidth), GameSettings.SCR_HEIGHT / 2 - 600, buttonWidth, buttonHeight, "buttons/chooseright.png");
        ImageView settingsBtn = new ImageView((int) (GameSettings.SCR_WIDTH / 2 - 0.5 * buttonWidth), GameSettings.SCR_HEIGHT / 2 - 860, buttonWidth, buttonHeight, "buttons/settings.png");

        components.add(yellowBG);
        components.add(title);
        components.add(versionText);
        components.add(clickCarefullyBtn);
        components.add(collectOrderBtn);
        components.add(chooseRightBtn);
        components.add(settingsBtn);

        clickCarefullyBtn.setOnClickListener(onClickBtnCarefully);
        collectOrderBtn.setOnClickListener(onClickBtnCollectOrder);
        chooseRightBtn.setOnClickListener(onClickBtnChooseRight);
        settingsBtn.setOnClickListener(onClickBtnSettings);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
//        ScreenUtils.clear((float) 0.99, (float) 0.91, (float) 0.06, 1);
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

    UiComponent.OnClickListener onClickBtnCarefully = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            myGdxGame.setScreen(myGdxGame.firstGameScreen);
        }
    };
    UiComponent.OnClickListener onClickBtnCollectOrder = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            myGdxGame.setScreen(myGdxGame.secondGameScreen);
        }
    };

    UiComponent.OnClickListener onClickBtnChooseRight = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            myGdxGame.setScreen(myGdxGame.thirdGameScreen);
        }
    };

    UiComponent.OnClickListener onClickBtnSettings = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            myGdxGame.setScreen(myGdxGame.settingsScreen);
        }
    };
}
