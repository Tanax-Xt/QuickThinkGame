package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.actorsGame1.Character;
import com.mygdx.game.actorsGame3.Item;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.ui.UiComponent;
import com.mygdx.game.ui.WhiteRectangle;
import com.mygdx.game.utils.GameSettings;
import com.mygdx.game.utils.MemoryLoader;
import com.mygdx.game.utils.Sounds;

import java.util.ArrayList;

public class FirstGameScreen implements Screen {
    MyGdxGame myGdxGame;
    ArrayList<UiComponent> components;
    ArrayList<Character> actorsComponents;
    ArrayList<UiComponent> uiComponentsEndOfGame;
    ArrayList<Item> itemsComponents;
    TextView score;
    TextView time;
    Character character1;
    Character character2;
    WhiteRectangle whiteRect;
    TextView timerExpires;
    final int bgHeight = GameSettings.SCR_HEIGHT;
    final int bgWidth = GameSettings.SCR_WIDTH;
    private float secondsToEnd = 30f;
    private int gameScore = 0;
    ImageView rightIcon;
    boolean isGameFinished = false;
    private int returnMenuWidth = (int) (GameSettings.SCR_WIDTH * 0.6);
    private int returnMenuHeight = (int) (GameSettings.SCR_HEIGHT * 0.11);
    private int rightIconBgWidth = (int) (GameSettings.SCR_WIDTH * 0.2);
    private int rightIconBgHeight = (int) (GameSettings.SCR_HEIGHT * 0.1);

    public FirstGameScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        components = new ArrayList<>();
        actorsComponents = new ArrayList<>();
        itemsComponents = new ArrayList<>();
        uiComponentsEndOfGame = new ArrayList<>();

        whiteRect = new WhiteRectangle(myGdxGame.gameBoldBlueFont.bitmapFont, myGdxGame.gameOverBlueSmallFont.bitmapFont, new Color(0, 0, 100, 0.33f));
        whiteRect.initRestartButton(onClickBtnRestart);
        whiteRect.initReturnMenu(onClickBtnReturn);
        uiComponentsEndOfGame = whiteRect.getComponents();

        ImageView game1BG = new ImageView(0, 0, bgWidth, bgHeight, "backgrounds/game1BG.png");
        ImageView returnMenu = new ImageView(0, GameSettings.SCR_HEIGHT - returnMenuHeight, returnMenuWidth, returnMenuHeight, "buttons/returnButtonGame1.png");
        timerExpires = new TextView(myGdxGame.gameFontLarge2.bitmapFont, Float.toString(secondsToEnd), -1, (int) (GameSettings.SCR_HEIGHT * 0.8));

        score = new TextView(myGdxGame.gameFontLarge2.bitmapFont, String.valueOf(gameScore), -1, (int) (GameSettings.SCR_HEIGHT * 0.7));
        time = new TextView(myGdxGame.gameFontLarge2.bitmapFont, Float.toString(secondsToEnd), -1, (int) (GameSettings.SCR_HEIGHT * 0.8));
        returnMenu.setOnClickListener(onClickBtnReturn);

        components.add(game1BG);
        components.add(returnMenu);
        components.add(score);
        components.add(time);
        loadActors();
    }

    @Override
    public void show() {
        rightIcon = new ImageView(GameSettings.SCR_WIDTH - rightIconBgWidth, GameSettings.SCR_HEIGHT - rightIconBgHeight, rightIconBgWidth, rightIconBgHeight, "images/icons_default/icon" + MemoryLoader.loadIconState() + ".png");
        ScreenUtils.clear(0.95686274509f, 0.95686274509f, 0.95686274509f, 1);
        Gdx.app.debug("show", "is show");

        score.setText(Integer.toString(gameScore), true);
        time.setText(Integer.toString((int) secondsToEnd), true);
        components.add(rightIcon);

        components.add(score);
        components.add(time);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.camera.unproject(myGdxGame.touch);
            for (UiComponent component : components) {
                    component.isHit((int) myGdxGame.touch.x, (int) myGdxGame.touch.y);
            }
            for (UiComponent component : uiComponentsEndOfGame) {
                component.isHit((int) myGdxGame.touch.x, (int) myGdxGame.touch.y);
            }
        }

        secondsToEnd -= Gdx.graphics.getDeltaTime();
        Timer.instance().scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                time.setText(String.valueOf((int) secondsToEnd), true);
            }
        }, 1, 1);

        if (secondsToEnd <= 0) {
            secondsToEnd = 0;
            time.setText("");
            Timer.instance().clear();
            whiteRect.setResult(String.valueOf(gameScore));
            isGameFinished = true;
        }

        for (Character item : actorsComponents) {
            item.update();
        }

        ScreenUtils.clear(0.95686274509f, 0.95686274509f, 0.95686274509f, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.begin();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        for (UiComponent component : components) {
            component.draw(myGdxGame.batch);
        }

        if (isGameFinished) {
            for (UiComponent component : uiComponentsEndOfGame) {
                component.draw(myGdxGame.batch);
            }
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
        secondsToEnd = 30f;
        gameScore = 0;
    }

    @Override
    public void dispose() {
    }

    void loadActors() {
        character2 = new Character(new Texture("icons/game1/flower.png"), (int) (GameSettings.SCR_WIDTH * 0.6), (int) (GameSettings.SCR_HEIGHT * 0.15));
        character1 = new Character(new Texture("icons/game1/flower.png"), (int) (GameSettings.SCR_WIDTH * 0.05), (int) (GameSettings.SCR_HEIGHT * 0.15));

        character1.actorImgView.setOnClickListener(new UiComponent.OnClickListener() {
            @Override
            public void onClick() {
                if (!isGameFinished) {
                    Sounds.playGame1();
                    gameScore += character1.getEx() == 1 ? character1.getEx() : (int) (character1.getEx() * MathUtils.random(1, 4));
                    score.setText(String.valueOf(gameScore), true);
                    int modex = (int) ((MathUtils.random(0, 1) - 0.5) * 2);
                    if (modex == -1) {
                        character2.setEx(1);
                        character2.update();
                        if (character1.getEx() == -1) modex = 1;
                    }
                    character1.setEx(modex);
                    character1.update();
                }
            }
        });

        character2.actorImgView.setOnClickListener(new UiComponent.OnClickListener() {
            @Override
            public void onClick() {
                if (!isGameFinished) {
                    Sounds.playGame1();
                    gameScore += character2.getEx() == 1 ? character2.getEx() : (int) (character2.getEx() * MathUtils.random(1, 4));
                    score.setText(String.valueOf(gameScore), true);
                    int modex = (int) ((MathUtils.random(0, 1) - 0.5) * 2);
                    if (modex == -1) {
                        character1.setEx(1);
                        character1.update();
                        if (character2.getEx() == -1) {
                            modex = 1;
                        }
                    }
                    character2.setEx(modex);
                    character2.update();
                }
            }
        });

        actorsComponents.add(character1);
        actorsComponents.add(character2);
        components.add(character1.actorImgView);
        components.add(character2.actorImgView);
    }

    public void clearData() {
        secondsToEnd = 30f;
        gameScore = 0;
        time.setText(Float.toString(secondsToEnd), true);
        score.setText(Integer.toString(gameScore), true);
        isGameFinished = false;
    }

    private UiComponent.OnClickListener onClickBtnReturn = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            Sounds.MenuButtons();
            clearData();
            myGdxGame.setScreen(myGdxGame.menuScreen);
        }
    };

    UiComponent.OnClickListener onClickBtnRestart = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            Sounds.playGame1();
            clearData();
        }
    };
}
