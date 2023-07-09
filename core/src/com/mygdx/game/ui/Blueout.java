package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ui.UiComponent;
import com.mygdx.game.utils.GameSettings;

public class Blueout extends UiComponent {

    Texture blueoutTexture;

    public Blueout() {
        super(0, 0, GameSettings.SCR_WIDTH, GameSettings.SCR_HEIGHT);
        Pixmap pixmap = new Pixmap((int) width, (int) height, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 100, 0.33f);
        pixmap.fill();
        blueoutTexture = new Texture(pixmap);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(blueoutTexture, x, y, width, height);
    }
}