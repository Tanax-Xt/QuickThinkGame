package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.utils.GameSettings;

import java.util.ArrayList;

public class WhiteRectangle {

    int whiteRectHigh = (int) (GameSettings.SCR_HEIGHT );
    int whiteRectWidth = (int) (GameSettings.SCR_WIDTH / 3);
    int whiteRectPositionX = (int) (GameSettings.SCR_WIDTH / 2 - whiteRectWidth);
    int whiteRectPositionY = (int) (GameSettings.SCR_HEIGHT / 2);

    TextView pointsView;
    ImageView whiteRectangle;

    ArrayList<UiComponent> components;

    public WhiteRectangle(String points, BitmapFont font) {

        ImageView whiteRectangle = new ImageView(whiteRectPositionX, whiteRectPositionY, whiteRectWidth, whiteRectHigh, "backgrounds/gameoverWhiteRect.png");
        pointsView = new TextView(font, "Your points!", 300, 700);

        components = new ArrayList<>();

        components.add(new Blueout());
        components.add(whiteRectangle);
        components.add(pointsView);
    }

    public ArrayList<UiComponent> getComponents() {
        return components;
    }

/*    public void draw(SpriteBatch spriteBatch) {

    }*/
}
