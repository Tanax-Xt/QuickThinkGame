package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.actorsGame2.Card;
import com.mygdx.game.actorsGame3.Item;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.UiComponent;
import com.mygdx.game.utils.GameSettings;
import com.mygdx.game.utils.MemoryLoader;

import java.util.ArrayList;
import java.util.Collections;

public class SecondGameScreen implements Screen {
    MyGdxGame myGdxGame;

    ArrayList<UiComponent> components;
    ArrayList<Card> cards;
    ArrayList<Integer> cardsIntegers;

    ImageView rightIcon;
    ImageView cardImgView;

    int returnMenuWidth = (int) (GameSettings.SCR_WIDTH * 0.6);
    int returnMenuHeight = (int) (GameSettings.SCR_HEIGHT * 0.1);
    int rightIconBgWidth = (int) (GameSettings.SCR_WIDTH * 0.2);
    int rightIconBgHeight = (int) (GameSettings.SCR_HEIGHT * 0.1);
    int activeCard = 1;

    public SecondGameScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        components = new ArrayList<>();
        cards = new ArrayList<>();
        cardsIntegers = new ArrayList<>();

        ImageView background = new ImageView(0, 0, GameSettings.SCR_WIDTH, GameSettings.SCR_HEIGHT, "backgrounds/CollectOrderBG.png");
        ImageView returnMenu = new ImageView(0, GameSettings.SCR_HEIGHT - returnMenuHeight, returnMenuWidth, returnMenuHeight, "buttons/returnButtonGame2.png");
        ImageView rightTopBg = new ImageView(GameSettings.SCR_WIDTH - rightIconBgWidth, GameSettings.SCR_HEIGHT - rightIconBgHeight, rightIconBgWidth, rightIconBgHeight, "images/right_top_bg_game3.png");

        components.add(background);
        components.add(returnMenu);
        components.add(rightTopBg);

        cardsIntegers = generateRandomArray();
        for (int i : cardsIntegers) {
             Texture cardTexture = new Texture("icons/game2/card" + i + ".png");
             Card card = new Card(cardTexture, GameSettings.SCR_WIDTH / 2 - 250, GameSettings.SCR_HEIGHT / 2 - 100, i);
             cards.add(card);
        }

        returnMenu.setOnClickListener(onClickBtnReturn);
    }

    @Override
    public void show() {
        rightIcon = new ImageView(GameSettings.SCR_WIDTH - rightIconBgWidth, GameSettings.SCR_HEIGHT - rightIconBgHeight, rightIconBgWidth, rightIconBgHeight, "icons/icon" + MemoryLoader.loadIconState() + ".png");
        components.add(rightIcon);
        Timer.instance().scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                    if (activeCard > 1) cards.get(activeCard - 2).isVisible = false;
                    cards.get(activeCard - 1).isVisible = true;
                    activeCard++;
                }
        }, 1, 1);
        if (activeCard > 6) Timer.instance().clear();
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.camera.unproject(myGdxGame.touch);
            //if (!isGameFinished) {
                for (UiComponent component : components) {
                    if (component.isVisible)
                        component.isHit((int) myGdxGame.touch.x, (int) myGdxGame.touch.y);
                }
                //for (UiComponent component : itemsUIcomponents) {
                    //if (component.isVisible)
                        //component.isHit((int) myGdxGame.touch.x, (int) myGdxGame.touch.y);
                //}
            //}
            //for (UiComponent component : uiComponentsEndOfGame) {
                //if (component.isVisible) component.isHit((int) myGdxGame.touch.x, (int) myGdxGame.touch.y);
            //}
        }
/*
        if (!isGameFinished) {
            initMainTimer();
            initGenerateItemsTimer();
        }*/

        /*for (Item item: itemsComponents) {
            if (!isGameFinished) {
                item.update();
                if (item.getY() < borderPosition) {
                    if (item.isActive) {
                        if (item.getTypeItem() == 1) XP--;
                        hpText.setText(String.valueOf(XP));
                    }
                    item.isActive = false;
                }
            }
        }*/


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


/*        for (UiComponent component: itemsUIcomponents) {
            if (component.isVisible) component.draw(myGdxGame.batch);
        }*/

/*        if (isGameFinished) {
            for (UiComponent component: uiComponentsEndOfGame) {
                component.draw(myGdxGame.batch);
            }
        }*/

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

    public ArrayList<Integer> generateRandomArray() {
        ArrayList <Integer> randomCards = new ArrayList<>();
        for(int i = 1; i <= 6; i++) {
            randomCards.add(i);
        }
        Collections.shuffle(randomCards);
        return randomCards;
    }

    UiComponent.OnClickListener onClickBtnReturn = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            //clearData();
            myGdxGame.setScreen(myGdxGame.menuScreen);
        }
    };

}
