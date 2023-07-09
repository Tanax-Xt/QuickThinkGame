package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.utils.GameSettings;

public abstract class UiComponent {
    public int x;
    public int y;
    public int width, height;
    public boolean isVisible;

    public OnClickListener onClickListener;

    public UiComponent(int x, int y, int wight, int height) {
        this(x, y);
        this.width = wight;
        this.height = height;
    }

    public UiComponent(int x, int y) {
        this.x = x;
        this.y = y;
        this.onClickListener = null;
        isVisible = true;
    }

    public void draw(SpriteBatch spriteBatch) {}

    public boolean isHit(int touchX, int touchY) {
        Gdx.app.debug("is touch", "touch");
        if (isVisible && touchX > x && touchY < y && x + width > touchX && y - height < touchY) {
            if (onClickListener != null) onClickListener.onClick();
            return true;
        }
        return false;
    }

    public interface OnClickListener {
        void onClick();
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setX(float vx) {
        this.x += vx;
        if (this.x < 0 || this.x + width > GameSettings.SCR_WIDTH) this.x = (int) -vx;
    }

    public void setY(float vy) {
        this.y += vy;
        if (this.y < 0 || this.y + height > GameSettings.SCR_HEIGHT) this.y = (int) -vy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void update() {

    }

}
