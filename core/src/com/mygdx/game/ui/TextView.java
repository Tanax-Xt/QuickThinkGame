package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.utils.GameSettings;

public class TextView extends UiComponent {
    public String text;
    public BitmapFont font;
    GlyphLayout glyphLayout;

    public TextView(BitmapFont font, String text, int x, int y) {
        super(x, y);
        this.font = font;
        this.text = text;

        glyphLayout = new GlyphLayout(font, text);
        width = (int) glyphLayout.width;
        height = (int) glyphLayout.height;

        if ((int) x == -1) this.x = GameSettings.SCR_WIDTH / 2 - width / 2;
        if (x == -2) this.x = 682 - width / 2;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        font.draw(spriteBatch, text, x, y);
    }

    public void setText(String text) {
        this.text = text;
    }
    public void setText(String text, boolean lineCenter) {
        this.text = text;

        glyphLayout.setText(font, text);
        width = (int) glyphLayout.width;
        this.x = GameSettings.SCR_WIDTH / 2 - width / 2;

    }
    public void setText(String text, int x) {
        this.text = text;

        glyphLayout.setText(font, text);
        width = (int) glyphLayout.width;
        this.x = 682 - width / 2;

    }
}
