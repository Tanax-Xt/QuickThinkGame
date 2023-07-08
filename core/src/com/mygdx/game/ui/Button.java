package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button extends UiComponent {
    public String buttonText;
    public BitmapFont font;

    public Button(BitmapFont font, String text, int x, int y) {
        super(x, y);
        this.isVisible = true;
        this.buttonText = text;
        this.font = font;

        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        width = (int) glyphLayout.width;
        height = (int) glyphLayout.height;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (isVisible) font.draw(spriteBatch, buttonText, x, y);
    }
}
