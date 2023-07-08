package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.actorsGame1.Character;
import com.mygdx.game.actorsGame1.Flower;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.ui.UiComponent;
import com.mygdx.game.utils.GameSettings;

import java.util.ArrayList;

public class FirstGameScreen implements Screen {
    MyGdxGame myGdxGame;
    ArrayList<UiComponent> components;
    ArrayList<Character> actorsComponents;

    TextView score;
    TextView time;

    final int bgHeight = GameSettings.SCR_HEIGHT;
    final int bgWidth = GameSettings.SCR_WIDTH;
    private int secondsToEnd;
    private int gameScore;
    private int returnMenuWidth = (int) (GameSettings.SCR_WIDTH * 0.6);
    private int returnMenuHeight = (int) (GameSettings.SCR_HEIGHT * 0.11);

    public FirstGameScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        components = new ArrayList<>();
        actorsComponents = new ArrayList<>();

        ImageView game1BG = new ImageView(0, 0, bgWidth, bgHeight, "backgrounds/game1BG.png");
        ImageView returnMenu = new ImageView(0, GameSettings.SCR_HEIGHT - returnMenuHeight, returnMenuWidth, returnMenuHeight, "buttons/returnButtonGame1.png");
//        ImageView title = new ImageView(GameSettings.SCR_WIDTH / 2 - titleWidth / 2, (int) (GameSettings.SCR_HEIGHT - titleHeight * 1.5), titleWidth, titleHeight, "images/title.png");
//        ImageView versionText = new ImageView(GameSettings.SCR_WIDTH / 2 - versionTextWidth / 2, versionTextHeight / 2, versionTextWidth, versionTextHeight, "images/version.png");
//        ImageView clickCarefullyBtn = new ImageView((int) (GameSettings.SCR_WIDTH / 2 - 0.5 * buttonWidth), GameSettings.SCR_HEIGHT / 2 - 80, buttonWidth, buttonHeight, "buttons/clickcarefully.png");
//        ImageView collectOrderBtn = new ImageView((int) (GameSettings.SCR_WIDTH / 2 - 0.5 * buttonWidth), GameSettings.SCR_HEIGHT / 2 - 330, buttonWidth, buttonHeight, "buttons/collectorder.png");
//        ImageView chooseRightBtn = new ImageView((int) (GameSettings.SCR_WIDTH / 2 - 0.5 * buttonWidth), GameSettings.SCR_HEIGHT / 2 - 600, buttonWidth, buttonHeight, "buttons/chooseright.png");
//        ImageView settingsBtn = new ImageView((int) (GameSettings.SCR_WIDTH / 2 - 0.5 * buttonWidth), GameSettings.SCR_HEIGHT / 2 - 860, buttonWidth, buttonHeight, "buttons/settings.png");

        components.add(game1BG);
        components.add(returnMenu);
//        components.add(title);
//        components.add(versionText);
//        components.add(clickCarefullyBtn);
//        components.add(collectOrderBtn);
//        components.add(chooseRightBtn);
//        components.add(settingsBtn);

//        clickCarefullyBtn.setOnClickListener(onClickBtnCarefully);
//        collectOrderBtn.setOnClickListener(onClickBtnCollectOrder);
//        chooseRightBtn.setOnClickListener(onClickBtnChooseRight);
        returnMenu.setOnClickListener(onClickBtnReturn);
    }

    @Override
    public void show() {
        secondsToEnd = 60;
        gameScore = 0;
        score = new TextView(myGdxGame.gameFont1.bitmapFont, "0 px", GameSettings.SCR_WIDTH / 2 - 60, (int) (GameSettings.SCR_HEIGHT * 0.7));
        time = new TextView(myGdxGame.gameFontLarge1.bitmapFont, "01:00", GameSettings.SCR_WIDTH / 2 - 220, (int) (GameSettings.SCR_HEIGHT * 0.8));
        components.add(score);
        components.add(time);
        loadActors();
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.camera.unproject(myGdxGame.touch);
            for (UiComponent component : components) {
                if (component.isVisible)
                    component.isHit((int) myGdxGame.touch.x, (int) myGdxGame.touch.y);
            }
        }

        ScreenUtils.clear(0.95686274509f, 0.95686274509f, 0.95686274509f, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.begin();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        for (UiComponent component : components) {
            component.draw(myGdxGame.batch);
        }
//        for ()
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

    void loadActors() {
        Flower flower1 = new Flower(new Texture("icons/game1/flower.png"), (int) (GameSettings.SCR_WIDTH * 0.6), (int) (GameSettings.SCR_HEIGHT * 0.15));
        Flower flower2 = new Flower(new Texture("icons/game1/flower.png"), (int) (GameSettings.SCR_WIDTH * 0.05), (int) (GameSettings.SCR_HEIGHT * 0.15));
        components.add(flower1.actorImgView);
        components.add(flower2.actorImgView);
    }


    private UiComponent.OnClickListener onClickBtnReturn = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            myGdxGame.setScreen(myGdxGame.menuScreen);
        }
    };
}
