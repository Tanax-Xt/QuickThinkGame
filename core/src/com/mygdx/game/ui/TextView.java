package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.utils.GameSettings;

public class TextView extends UiComponent {
    public String text;
    public BitmapFont font;

    public TextView(BitmapFont font, String text, int x, int y) {
        super(x, y);
        this.font = font;
        this.text = text;

        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        width = (int) glyphLayout.width;
        height = (int) glyphLayout.height;

        if ((int) x == -1) this.x = GameSettings.SCR_WIDTH / 2 - width / 2;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        font.draw(spriteBatch, text, x, y);
    }

    public void setText(String text) {
        this.text = text;
    }
}
