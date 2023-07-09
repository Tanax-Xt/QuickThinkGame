package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.utils.GameSettings;

import java.util.ArrayList;

public class WhiteRectangle {

    int whiteRectHigh = (int) (GameSettings.SCR_HEIGHT * 0.25);
    int whiteRectWidth = (int) (GameSettings.SCR_WIDTH * 0.7);
    int whiteRectPositionX = (int) (GameSettings.SCR_WIDTH / 2 - whiteRectWidth / 2);
    int whiteRectPositionY = (int) (GameSettings.SCR_HEIGHT / 2);
    String result = "";
    TextView pointsView;
    TextView statsInfoResult;
    ImageView whiteRectangle;

    ArrayList<UiComponent> components;

    public WhiteRectangle(BitmapFont font) {
        whiteRectangle = new ImageView(whiteRectPositionX, whiteRectPositionY, whiteRectWidth, whiteRectHigh, "backgrounds/gameoverWhiteRect.png");
        pointsView = new TextView(font, "Your points!", GameSettings.SCR_WIDTH / 2, GameSettings.SCR_HEIGHT - whiteRectHigh - 50);
        pointsView.x = GameSettings.SCR_WIDTH / 2 - pointsView.width / 2;

        statsInfoResult = new TextView(font, result, GameSettings.SCR_WIDTH / 2, GameSettings.SCR_HEIGHT - whiteRectHigh - 150);
        statsInfoResult.x = GameSettings.SCR_WIDTH / 2 - pointsView.height;

        components = new ArrayList<>();

        components.add(new Blueout());
        components.add(whiteRectangle);
        components.add(pointsView);
        components.add(statsInfoResult);
    }

    public ArrayList<UiComponent> getComponents() {
        return components;
    }

/*    public void draw(SpriteBatch spriteBatch) {

    }*/

    public void setResult(String result) {
        this.result = result;
        statsInfoResult.setText(this.result);
    }
}
