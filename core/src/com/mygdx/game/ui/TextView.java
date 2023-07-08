package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        font.draw(spriteBatch, text, x, y);
    }
}
