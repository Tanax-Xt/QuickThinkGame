package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.utils.GameSettings;

import java.util.ArrayList;

public class WhiteRectangle extends UiComponent {

    int whiteRectHigh = (int) (GameSettings.SCR_HEIGHT * 0.75);
    int whiteRectWidth = (int) (GameSettings.SCR_WIDTH * 0.75);
    int whiteRectPositionX = (int) (GameSettings.SCR_WIDTH / 2 - 0.5);
    int whiteRectPositionY = (int) (GameSettings.SCR_HEIGHT / 2 - 0.5);

    TextView pointsView;
    ImageView whiteRectangle;

    ArrayList<UiComponent> components;

    public WhiteRectangle(String points, BitmapFont font) {
        super((int) (GameSettings.SCR_HEIGHT * 0.75),
                (int) (GameSettings.SCR_WIDTH * 0.75),
                (int) (GameSettings.SCR_WIDTH / 2 - 0.5),
                (int) (GameSettings.SCR_HEIGHT / 2 - 0.5));

        ImageView whiteRectangle = new ImageView(100, whiteRectPositionY, 900, whiteRectWidth, "backgrounds/gameoverWhiteRect.png");
        pointsView = new TextView(font, "Your points!", 300, 700);

        components = new ArrayList<>();

        components.add(new Blueout());
        components.add(whiteRectangle);
        components.add(pointsView);
    }

    public ArrayList<UiComponent> getComponents() {
        return components;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {

    }
}
