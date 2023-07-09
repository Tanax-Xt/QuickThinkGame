package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.TextView;
import com.mygdx.game.ui.UiComponent;
import com.mygdx.game.utils.GameSettings;
import com.mygdx.game.utils.MemoryLoader;

import java.util.ArrayList;

public class SettingsScreen implements Screen {
    MyGdxGame myGdxGame;
    ArrayList<UiComponent> components;
    ArrayList<ImageView> iconsList;
    ImageView activeIconImage;
    int returnMenuWidth = (int) (GameSettings.SCR_WIDTH * 0.6);
    int returnMenuHeight = (int) (GameSettings.SCR_HEIGHT * 0.1);
    int bgSettingsHeight = (int) (GameSettings.SCR_HEIGHT * 0.4);
    int settingsIconWidth = (int) (GameSettings.SCR_WIDTH * 0.2);
    int settingsIconHeight = (int) (GameSettings.SCR_HEIGHT * 0.1);
    int iconWidth = (int) (GameSettings.SCR_WIDTH * 0.28);
    int iconHeight = (int) (GameSettings.SCR_HEIGHT * 0.15);
    int activeIcon = 1;
    int activeMusic = 1;
    ArrayList<Music> musicList;
    TextView musicTitle;
    TextView audioNumText;

    public SettingsScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        activeMusic = MemoryLoader.loadActiveMusic();

        components = new ArrayList<>();
        iconsList = new ArrayList<>();
        musicList = new ArrayList<>();

        ImageView bgSettings = new ImageView(0, GameSettings.SCR_HEIGHT - bgSettingsHeight, GameSettings.SCR_WIDTH, bgSettingsHeight, "backgrounds/settingsbg.png");
        ImageView returnMenu = new ImageView(0, GameSettings.SCR_HEIGHT - returnMenuHeight, returnMenuWidth, returnMenuHeight, "buttons/settingsReturn.png");
        ImageView settingsIcon = new ImageView(GameSettings.SCR_WIDTH - settingsIconWidth, GameSettings.SCR_HEIGHT - settingsIconHeight, settingsIconWidth, settingsIconHeight, "icons/settings.png");

        musicTitle = new TextView(myGdxGame.gameFont2.bitmapFont, "Music:", 200, 200);
        ImageView arrowLeft = new ImageView(500, 130, 64, musicTitle.height, "images/left.png");
        ImageView arrowRight = new ImageView(700, 130, 64, musicTitle.height, "images/right.png");
        audioNumText = new TextView(myGdxGame.gameFont2.bitmapFont, String.valueOf(activeMusic), 610, 200);

        arrowLeft.setOnClickListener(onClickBtnArrowLeft);
        arrowRight.setOnClickListener(onClickBtnArrowRight);

        components.add(bgSettings);
        components.add(returnMenu);
        components.add(settingsIcon);
        components.add(musicTitle);
        components.add(arrowLeft);
        components.add(arrowRight);
        components.add(audioNumText);


        bgSettings.setOnClickListener(onClickBtnReturn);
    }

    public void initImages(final int sourceI, final int i) {
        // костыль
        double isTop = sourceI != i ? 2.2 : 1;
        ImageView isActiveIconImage = new ImageView((int) (GameSettings.SCR_WIDTH / 2 - iconWidth * 0.7), GameSettings.SCR_HEIGHT / 2 + iconHeight, (int) (iconWidth * 1.5), (int) (iconHeight * 1.5), "icons/icon" + sourceI + ".png");
        if (activeIcon == sourceI) activeIconImage = isActiveIconImage;

        final ImageView icon = new ImageView((int) (i * 50 + (i - 1) * (GameSettings.SCR_WIDTH * 0.27)), (int) (0.95 * GameSettings.SCR_HEIGHT - bgSettingsHeight - isTop * iconHeight), iconWidth, iconHeight, "icons/icon" + sourceI + ".png");
        icon.setOnClickListener(new UiComponent.OnClickListener() {
            @Override
            public void onClick() {
                MemoryLoader.saveIconState(sourceI);
                activeIconImage.setImgTexture(icon.imgTexture);
                activeIcon = sourceI;
            }
        });

        iconsList.add(icon);
    }

    @Override
    public void show() {
        activeIcon = MemoryLoader.loadIconState();

        for (int i = 1; i <= 3; i++) initImages(i, i);
        for (int i = 4; i <= 6; i++) initImages(i,i - 3);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.camera.unproject(myGdxGame.touch);
            for (UiComponent component : components) {
                if (component.isVisible) component.isHit((int) myGdxGame.touch.x, (int) myGdxGame.touch.y);
            }
            for (UiComponent component : iconsList) {
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
        for (UiComponent component: iconsList) {
            component.draw(myGdxGame.batch);
        }
        activeIconImage.draw(myGdxGame.batch);
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

    public void changeMusic(int activeMusic) {
        myGdxGame.music.stop();
        myGdxGame.music.dispose();
        myGdxGame.music = Gdx.audio.newMusic(Gdx.files.internal("audio/music" + activeMusic + ".mp3"));
        myGdxGame.music.setLooping(true);
        myGdxGame.music.play();
        audioNumText.setText(String.valueOf(activeMusic));
    }

    UiComponent.OnClickListener onClickBtnReturn = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            myGdxGame.setScreen(myGdxGame.menuScreen);
        }
    };

    UiComponent.OnClickListener onClickBtnArrowLeft = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            if (activeMusic > 1) changeMusic(--activeMusic);
        }
    };
    UiComponent.OnClickListener onClickBtnArrowRight = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            if (activeMusic < 6) changeMusic(++activeMusic);
        }
    };
}
