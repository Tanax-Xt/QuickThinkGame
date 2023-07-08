package com.mygdx.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ImageView extends UiComponent {
    Texture imgTexture;

    public ImageView(int x, int y, int wight, int height, String imdSource) {
        super(x, y, wight, height);
        this.imgTexture = new Texture(imdSource);
    }
    public ImageView(int x, int y, int wight, int height, Texture imgTexture) {
        super(x, y, wight, height);
        this.imgTexture = imgTexture;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(imgTexture, x, y, width, height);
    }

    @Override
    public boolean isHit(int tx, int ty) {
        boolean isTouchHitComponent = x < tx && tx < x + width && y < ty && ty < y + height;
        if (isTouchHitComponent && onClickListener != null) onClickListener.onClick();
        return isTouchHitComponent;
    }

    public void setImgTexture(Texture imgTexture) {
        this.imgTexture = imgTexture;
    }

}
