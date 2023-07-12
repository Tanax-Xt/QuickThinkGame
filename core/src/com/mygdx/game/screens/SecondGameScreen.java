package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.actorsGame2.Card;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.UiComponent;
import com.mygdx.game.ui.WhiteRectangle;
import com.mygdx.game.utils.GameSettings;
import com.mygdx.game.utils.MemoryLoader;

import java.util.ArrayList;
import java.util.Collections;

public class SecondGameScreen implements Screen {
    MyGdxGame myGdxGame;
    ArrayList<UiComponent> components;
    ArrayList<Card> cards;
    ArrayList<Integer> cardsIntegers;
    ArrayList<UiComponent> uiComponentsEndOfGame;
    ArrayList<Card> matrix;
    WhiteRectangle whiteRect;

    ImageView rightIcon;

    boolean isShow = false;
    boolean isGameFinished = false;
    int iconWidth = (int) (GameSettings.SCR_WIDTH * 0.35);
    int iconHeight = (int) (GameSettings.SCR_HEIGHT * 0.18);

    int bgSettingsHeight = (int) (GameSettings.SCR_HEIGHT * 0.4);

    int returnMenuWidth = (int) (GameSettings.SCR_WIDTH * 0.6);
    int returnMenuHeight = (int) (GameSettings.SCR_HEIGHT * 0.1);
    int rightIconBgWidth = (int) (GameSettings.SCR_WIDTH * 0.2);
    int rightIconBgHeight = (int) (GameSettings.SCR_HEIGHT * 0.1);
    int activeCard = 1;

    int sequence = 0;

    public SecondGameScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        components = new ArrayList<>();
        cards = new ArrayList<>();
        cardsIntegers = new ArrayList<>();
        matrix = new ArrayList<>();

        whiteRect = new WhiteRectangle(myGdxGame.gameOverBlueFont.bitmapFont, myGdxGame.gameOverBlueSmallFont.bitmapFont);
        whiteRect.initRestartButton(onClickBtnRestart);
        whiteRect.initReturnMenu(onClickBtnReturn);
        uiComponentsEndOfGame = whiteRect.getComponents();

        ImageView background = new ImageView(0, 0, GameSettings.SCR_WIDTH, GameSettings.SCR_HEIGHT, "backgrounds/CollectOrderBG.png");
        ImageView returnMenu = new ImageView(0, GameSettings.SCR_HEIGHT - returnMenuHeight, returnMenuWidth, returnMenuHeight, "buttons/returnButtonGame2.png");
        ImageView rightTopBg = new ImageView(GameSettings.SCR_WIDTH - rightIconBgWidth, GameSettings.SCR_HEIGHT - rightIconBgHeight, rightIconBgWidth, rightIconBgHeight, "images/right_top_bg_game3.png");

        returnMenu.setOnClickListener(onClickBtnReturn);

        components.add(background);
        components.add(returnMenu);
        components.add(rightTopBg);
    }

    @Override
    public void show() {
        rightIcon = new ImageView(GameSettings.SCR_WIDTH - rightIconBgWidth, GameSettings.SCR_HEIGHT - rightIconBgHeight, rightIconBgWidth, rightIconBgHeight, "icons/icon" + MemoryLoader.loadIconState() + ".png");
        components.add(rightIcon);

        initData();
    }

    public void initData () {
        cardsIntegers = generateRandomArray();
        cards.clear();
        for (int i : cardsIntegers) {
            Texture cardTexture = new Texture("icons/game2/card" + i + ".png");
            Card card = new Card(cardTexture, GameSettings.SCR_WIDTH / 2 - 250, GameSettings.SCR_HEIGHT / 2 - 100, i);
            cards.add(card);
        }

        matrix.clear();
        for (int i = 1; i <= 2; i++) initImages(i, i);
        for (int i = 3; i <= 4; i++) initImages(i, i - 2);
        for (int i = 5; i <= 6; i++) initImages(i, i - 4);

        Timer.instance().scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                if (activeCard > 1) cards.get(activeCard - 2).isVisible = false;
                cards.get(activeCard - 1).isVisible = true;
                activeCard++;
            }
        }, 1, 1);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.camera.unproject(myGdxGame.touch);

            for (UiComponent component : components) {
                if (component.isVisible) component.isHit((int) myGdxGame.touch.x, (int) myGdxGame.touch.y);
            }

            for (Card card : cards) {
                if (card.isVisible2) card.cardImgView2.isHit((int) myGdxGame.touch.x, (int) myGdxGame.touch.y);
            }

            for (Card component : matrix) {
                if (component.isVisible2) component.cardImgView2.isHit((int) myGdxGame.touch.x, (int) myGdxGame.touch.y);
            }

            for (UiComponent component : uiComponentsEndOfGame) {
                if (component.isVisible) component.isHit((int) myGdxGame.touch.x, (int) myGdxGame.touch.y);
            }
        }
        if (activeCard > 6) {
            if (!isShow) {
                Timer.instance().clear();
                Timer.instance().scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        cards.get(cards.size() - 1).isVisible = false;
                        for (int i = 0; i < matrix.size(); i++) {
                            matrix.get(i).isVisible2 = true;
                        }
                    }
                }, 1);
                isShow = true;
            }
        }

        ScreenUtils.clear(0.95686274509f, 0.95686274509f, 0.95686274509f, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.begin();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);

        for (UiComponent component: components) {
            if (component.isVisible) component.draw(myGdxGame.batch);
        }
        for (Card card: cards) {
            if (card.isVisible) card.cardImgView1.draw(myGdxGame.batch);
        }
        for (Card card : cards) {
            if (card.isVisible2) card.cardImgView2.draw(myGdxGame.batch);
        }
        for (Card card : matrix) {
            if (card.isVisible2) card.cardImgView2.draw(myGdxGame.batch);
        }
        if (isGameFinished) {
            for (UiComponent component: uiComponentsEndOfGame) {
                component.draw(myGdxGame.batch);
            }
        }

        myGdxGame.batch.end();
    }
    public void initImages(final int sourceI, int i) {
        double isTop = sourceI != i ? 2.1 : 1;
        if (sourceI > 4) isTop = 3.2;

        int x = i % 2 != 0 ? (int) (i * 50 + (i - 1) * (GameSettings.SCR_WIDTH * 0.27) + 75) : (int) ((GameSettings.SCR_WIDTH - iconWidth - 125));
        int y =  (int) (1.24 * GameSettings.SCR_HEIGHT - bgSettingsHeight - isTop * iconHeight);

        Card card = new Card(new Texture("icons/game2/card" + sourceI + ".png"), x, y, sourceI);
        matrix.add(card);
        matrix.get(sourceI - 1).loadImg2(x, y, iconWidth, iconHeight, new Texture("icons/game2/card" + sourceI + ".png"));
        matrix.get(sourceI - 1).cardImgView2.setOnClickListener(new UiComponent.OnClickListener() {
            @Override
            public void onClick() {
                if (sequence + 1 >= cards.size()) {
                    matrix.get(sourceI - 1).isVisible2 = false;
                    whiteRect.setResult(sequence + "/" + matrix.size());
                    isGameFinished = true;
                }
                else if (matrix.get(sourceI - 1).type == cards.get(sequence).type) {
                    matrix.get(sourceI - 1).isVisible2 = false;
                    sequence++;
                } else {
                    Timer.instance().clear();
                    whiteRect.setResult(sequence + "/" + matrix.size());
                    isGameFinished = true;
                }
            }
        });
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

    public ArrayList<Integer> generateRandomArray() {
        ArrayList <Integer> randomCards = new ArrayList<>();
        for(int i = 1; i <= 6; i++) {
            randomCards.add(i);
        }
        Collections.shuffle(randomCards);
        return randomCards;
    }

    public void clearData() {
        cardsIntegers.clear();
        Timer.instance().clear();
        for (Card card : cards) {
            card.isVisible2 = false;
        }
        for (Card card : matrix) {
            card.isVisible2 = false;
        }
        isShow = false;
        sequence = 0;
        activeCard = 1;
        isGameFinished = false;
    }

    UiComponent.OnClickListener onClickBtnReturn = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            clearData();
            myGdxGame.setScreen(myGdxGame.menuScreen);
        }
    };
    UiComponent.OnClickListener onClickBtnRestart = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            clearData();
            initData();
        }
    };
}
