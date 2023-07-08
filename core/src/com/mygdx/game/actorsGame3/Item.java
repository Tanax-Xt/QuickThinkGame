package com.mygdx.game.actorsGame3;

import static com.mygdx.game.utils.GameSettings.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.UiComponent;

public class Item {
    UiComponent.OnClickListener onClickListener;
    public ImageView actorImgView;
    int x;
    int y;
    public int width = height = 100;
    public int height;
    // 1 - apple, 0 - ball
    int typeItem = 1;
    int velocityY = 10;

    public Item(Texture texture, int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.typeItem = type;
        actorImgView = new ImageView(x, y, width, height, texture);
    }

    public void update() {
        if (actorImgView.y > -height) actorImgView.y = y -= velocityY;
    }

    public int getY() {
        return y;
    }

    public int getTypeItem() {
        return typeItem;
    }
}
