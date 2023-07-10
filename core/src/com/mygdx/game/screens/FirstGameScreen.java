package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.actorsGame1.Character;
//import com.mygdx.game.actorsGame1.Flower;
import com.mygdx.game.actorsGame3.Item;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.ui.UiComponent;
import com.mygdx.game.ui.WhiteRectangle;
import com.mygdx.game.utils.GameSettings;
import com.mygdx.game.utils.MemoryLoader;

import java.util.ArrayList;
import java.util.Random;

public class FirstGameScreen implements Screen {
    MyGdxGame myGdxGame;
    ArrayList<UiComponent> components;
    ArrayList<Character> actorsComponents;
    ArrayList<UiComponent> uiComponentsEndOfGame;
    ArrayList<Item> itemsComponents;

    private Timer.Task createObjectTask;

    TextView score;
    TextView time;
    Character character1;
    Character character2;
    WhiteRectangle whiteRect;
    TextView timerExpires;

    final int bgHeight = GameSettings.SCR_HEIGHT;
    final int bgWidth = GameSettings.SCR_WIDTH;
    private float secondsToEnd = 60f;
    private int gameScore = 0;
    private int characterEx1;
    private int characterEx2;
    ImageView rightIcon;
    boolean isGameFinished = false;
    private float intervalTimer = 0f;
    private int returnMenuWidth = (int) (GameSettings.SCR_WIDTH * 0.6);
    private int returnMenuHeight = (int) (GameSettings.SCR_HEIGHT * 0.11);
    private int rightIconBgWidth = (int) (GameSettings.SCR_WIDTH * 0.2);
    private int rightIconBgHeight = (int) (GameSettings.SCR_HEIGHT * 0.1);

    public FirstGameScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        components = new ArrayList<>();
        actorsComponents = new ArrayList<>();
        itemsComponents = new ArrayList<>();

        ImageView game1BG = new ImageView(0, 0, bgWidth, bgHeight, "backgrounds/game1BG.png");
        ImageView returnMenu = new ImageView(0, GameSettings.SCR_HEIGHT - returnMenuHeight, returnMenuWidth, returnMenuHeight, "buttons/returnButtonGame1.png");
        timerExpires = new TextView(myGdxGame.gameFontLarge2.bitmapFont, Float.toString(secondsToEnd), -1, (int) (GameSettings.SCR_HEIGHT * 0.8));

        components.add(game1BG);
        components.add(returnMenu);
        returnMenu.setOnClickListener(onClickBtnReturn);

//        whiteRect = new WhiteRectangle(myGdxGame);
//        whiteRect.initRestartButton(onClickBtnRestart);
//        whiteRect.initReturnMenu(onClickBtnReturn);
//        uiComponentsEndOfGame = whiteRect.getComponents();


    }

    @Override
    public void show() {

        secondsToEnd = 60;
        gameScore = 0;
        score = new TextView(myGdxGame.gameFontLarge2.bitmapFont, String.valueOf(gameScore), -1, (int) (GameSettings.SCR_HEIGHT * 0.7));
        time = new TextView(myGdxGame.gameFontLarge2.bitmapFont, Float.toString(secondsToEnd), -1, (int) (GameSettings.SCR_HEIGHT * 0.8));

//        score = new TextView(myGdxGame.gameFont1.bitmapFont, "0 px", GameSettings.SCR_WIDTH / 2 - 60, (int) (GameSettings.SCR_HEIGHT * 0.7));
//        time = new TextView(myGdxGame.gameFontLarge1.bitmapFont, "01:00", GameSettings.SCR_WIDTH / 2 - 220, (int) (GameSettings.SCR_HEIGHT * 0.8));        rightIcon = new ImageView(GameSettings.SCR_WIDTH - rightIconBgWidth, GameSettings.SCR_HEIGHT - rightIconBgHeight, rightIconBgWidth, rightIconBgHeight, "icons/icon" + MemoryLoader.loadIconState() + ".png");
        rightIcon = new ImageView(GameSettings.SCR_WIDTH - rightIconBgWidth, GameSettings.SCR_HEIGHT - rightIconBgHeight, rightIconBgWidth, rightIconBgHeight, "icons/icon" + MemoryLoader.loadIconState() + ".png");
        components.add(rightIcon);
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
                    component.isHit((int) myGdxGame.touch.x, (int) myGdxGame.touch.y);
            }
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
//        clearData();
    }

//    public void initItems(int i) {
//        int itemNum = new Random().nextInt(2);
//        String itemTitle = itemNum == 1 && i % 2 == 0 ? "apple" : "ball";
//        Texture texture = new Texture("icons/game3/" + itemTitle + ".png");
//        final Item element = new Item(texture, 100 * (i % 2 + 1) + new Random().nextInt(GameSettings.SCR_WIDTH - 200 * (i % 2 + 1)), 4 * borderPosition + new Random().nextInt(GameSettings.SCR_HEIGHT - 5 * borderPosition), itemNum, onKillItemListener);
//
//        element.actorImgView.setOnClickListener(new UiComponent.OnClickListener() {
//            @Override
//            public void onClick() {
//                if (element.isActive) {
//                    if (element.getTypeItem() == 1) XP++;
//                    else XP--;
//                    element.isActive = false;
//                    element.actorImgView.y = -element.height;
//                    hpText.setText(String.valueOf(XP));
//                }
//            }
//        });
//
//        itemsComponents.add(element);
//        itemsUIcomponents.add(element.actorImgView);
//    }
//
//    public void generateItems() {
//        for (int i = 0; i < 7; i++) initItems(i);
//        createObjectTask = new Timer.Task() {
//            @Override
//            public void run() {
//                if (!isGameFinished) for (int i = 0; i < 4; i++) initItems(i);
//            }
//        };
//
//
//        Timer.schedule(createObjectTask, 1f, 1f);
//        components.add(timerExpires);
//    }

    void loadActors() {
        character2 = new Character(new Texture("icons/game1/flower.png"), (int) (GameSettings.SCR_WIDTH * 0.6), (int) (GameSettings.SCR_HEIGHT * 0.15));
        character1 = new Character(new Texture("icons/game1/flower.png"), (int) (GameSettings.SCR_WIDTH * 0.05), (int) (GameSettings.SCR_HEIGHT * 0.15));

        character1.actorImgView.setOnClickListener(new UiComponent.OnClickListener() {
            @Override
            public void onClick() {
                Gdx.app.debug("hit1", "is hit 1");
                gameScore += character1.getEx();
                score.setText(String.valueOf(gameScore));
                character1.setEx((int) ((MathUtils.random(0, 1) - 0.5) * 2));
                character1.update();
            }
        });

        character2.actorImgView.setOnClickListener(new UiComponent.OnClickListener() {
            @Override
            public void onClick() {
                Gdx.app.debug("hit2", "is hit 2");
                gameScore += character2.getEx();
                score.setText(String.valueOf(gameScore));
                character2.setEx((int) ((MathUtils.random(0, 1) - 0.5) * 2));
                character2.update();
            }
        });

        characterEx1 = character1.getEx();
        characterEx2 = character2.getEx();
        actorsComponents.add(character1);
        actorsComponents.add(character2);
        components.add(character1.actorImgView);
        components.add(character2.actorImgView);
//        components.add(timerExpires);


    }


    public void initGenerateItemsTimer() {
        intervalTimer += Gdx.graphics.getDeltaTime();
        if (intervalTimer >= 2f) {
            intervalTimer -= 2f;
            createObjectTask.run();
        }
    }

//    public void initMainTimer() {
//        secondsToEnd -= Gdx.graphics.getDeltaTime();
//        if (secondsToEnd < 0) {
//            secondsToEnd = 0;
//            Timer.instance().clear();
//            whiteRect.setResult(String.valueOf(gameScore));
//            isGameFinished = true;
//            Timer.instance().scheduleTask(new Timer.Task() {
//                @Override
//                public void run() {
//                    isClickableFinishButtons = true;
//                }
//            }, 1);
//        }
//
//        Timer.schedule(new Timer.Task() {
//            @Override
//            public void run() {
//                timerExpires.setText(String.valueOf((int) secondsToEnd));
//            }
//        }, 1, 1);
//    }

//    public void clearData() {
//        for (Item component: itemsComponents) {
//            component.actorImgView.imgTexture.dispose();
//        }
//        for (UiComponent component: components) {
//            component.isVisible = false;
//        }
//        itemsComponents.clear();
//        secondsToEnd = 30f;
//        gameScore = 0;
//        intervalTimer = 0f;
////        isClickableFinishButtons = false;
//        Timer.instance().clear();
//        isGameFinished = false;
//    }

    private Character.isHitListener isHitListenerCharacter1 = new Character.isHitListener() {
        @Override
        public void onClick() {
            Gdx.app.debug("hit1", "is hit 1");
            gameScore += character1.getEx();
            character1.setEx((int) ((MathUtils.random(0, 1) - 0.5) * 2));
            character1.update();
        }
    };

    private Character.isHitListener isHitListenerCharacter2 = new Character.isHitListener() {
        @Override
        public void onClick() {
            Gdx.app.debug("hit2", "is hit 2");
            gameScore += character2.getEx();
            character2.setEx((int) ((MathUtils.random(0, 1) - 0.5) * 2));
            character2.update();
        }
    };


    private UiComponent.OnClickListener onClickBtnReturn = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            myGdxGame.setScreen(myGdxGame.menuScreen);
        }
    };
//    private UiComponent.OnClickListener onClickBtnReturn = new UiComponent.OnClickListener() {
//        @Override
//        public void onClick() {
//            myGdxGame.setScreen(myGdxGame.menuScreen);
//        }
//    };

//    UiComponent.OnClickListener onClickBtnRestart = new UiComponent.OnClickListener() {
//        @Override
//        public void onClick() {
//            if (isClickableFinishButtons) clearData();
//        }
//    };
}
