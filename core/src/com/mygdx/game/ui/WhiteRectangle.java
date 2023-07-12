package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.utils.GameSettings;

import java.util.ArrayList;

public class WhiteRectangle {
    int whiteRectHigh = (int) (GameSettings.SCR_HEIGHT * 0.25);
    int whiteRectWidth = (int) (GameSettings.SCR_WIDTH * 0.7);
    int whiteRectPositionX = GameSettings.SCR_WIDTH / 2 - whiteRectWidth / 2;
    int whiteRectPositionY = GameSettings.SCR_HEIGHT / 2;
    String result = "";
    TextView pointsView;
    TextView statsInfoResult;
    ImageView whiteRectangle;
    TextView menuLink;
    public TextView restartButton;
    ArrayList<UiComponent> components;

    public WhiteRectangle(BitmapFont firstFont, BitmapFont secondFont) {
        whiteRectangle = new ImageView(whiteRectPositionX, whiteRectPositionY, whiteRectWidth, whiteRectHigh, "backgrounds/gameoverWhiteRect.png");
        pointsView = new TextView(secondFont, "Your points!", GameSettings.SCR_WIDTH / 2, GameSettings.SCR_HEIGHT - whiteRectHigh - 50);
        pointsView.x = GameSettings.SCR_WIDTH / 2 - pointsView.width / 2;

        statsInfoResult = new TextView(firstFont, result, -1, GameSettings.SCR_HEIGHT - whiteRectHigh - 200);
        statsInfoResult.x = GameSettings.SCR_WIDTH / 2 - pointsView.height;

        menuLink = new TextView(secondFont, "Menu", GameSettings.SCR_WIDTH, GameSettings.SCR_HEIGHT - whiteRectHigh * 2);
        menuLink.x = GameSettings.SCR_WIDTH - 2 * menuLink.width - 50;
        menuLink.y += menuLink.height + 50;

        restartButton = new TextView(secondFont, "Restart", 0, GameSettings.SCR_HEIGHT - whiteRectHigh * 2);
        restartButton.x = restartButton.width;
        restartButton.y += restartButton.height + 50;

        components = new ArrayList<>();
        components.add(new Blueout());
        components.add(whiteRectangle);
        components.add(pointsView);
        components.add(statsInfoResult);
    }

    public WhiteRectangle(BitmapFont firstFont, BitmapFont secondFont, BitmapFont thirdFont) {
        this(firstFont, secondFont);
        statsInfoResult.font = thirdFont;
    }

    public void initRestartButton(UiComponent.OnClickListener listener) {
        restartButton.setOnClickListener(listener);
        components.add(restartButton);
    }

    public void initReturnMenu(UiComponent.OnClickListener listener) {
        menuLink.setOnClickListener(listener);
        components.add(menuLink);
    }

    public ArrayList<UiComponent> getComponents() {
        return components;
    }

    public void setResult(String result) {
        this.result = result;
        statsInfoResult.setText(this.result, true);
    }
}
