package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.utils.GameSettings;

public class Colorout extends UiComponent {
    Texture coloroutTexture;

    public Colorout(Color color) {
        super(0, 0, GameSettings.SCR_WIDTH, GameSettings.SCR_HEIGHT);
        Pixmap pixmap = new Pixmap((int) width, (int) height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        coloroutTexture = new Texture(pixmap);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(coloroutTexture, x, y, width, height);
    }
}