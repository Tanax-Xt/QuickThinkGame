package com.mygdx.game.actorsGame2;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ui.ImageView;

public class Card {
    int x;
    int y;
    int type;
    int size1 = 500;
    int size2 = 370;

    public boolean isVisible = false;

    public ImageView cardImgView1, cardImgView2;

    public Card(Texture texture, int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        cardImgView1 = new ImageView(x, y, size1, size1, texture);
        cardImgView2 = new ImageView(x, y, size2, size2, texture);
    }

}