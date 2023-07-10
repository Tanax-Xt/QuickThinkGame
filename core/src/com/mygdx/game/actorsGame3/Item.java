package com.mygdx.game.actorsGame3;

import static com.mygdx.game.utils.GameSettings.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.UiComponent;

public class Item {
    OnKillItemListener onKillItemListener;
    public ImageView actorImgView;
    int x;
    int y;
    public int width = 120;
    public int height = 120;
    // 1 - apple, 0 - ball
    int typeItem = 1;
    int velocityY = 5;
    public boolean isActive = true;

    public Item(Texture texture, int x, int y, int type, OnKillItemListener onKillItemListener) {
        this.x = x;
        this.y = y;
        this.typeItem = type;
        actorImgView = new ImageView(x, y, width, height, texture);
        this.onKillItemListener = onKillItemListener;
        actorImgView.setOnClickListener(itemOnClicked);
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

    UiComponent.OnClickListener itemOnClicked = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            if (!isActive) return;
            isActive = false;
            actorImgView.setImgTexture(new Texture("images/left.png"));
            onKillItemListener.onKill();
        }
    };

    public interface OnKillItemListener {
        void onKill();
    }
}
