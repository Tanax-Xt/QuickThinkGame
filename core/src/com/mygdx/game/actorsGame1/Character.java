package com.mygdx.game.actorsGame1;

import static com.mygdx.game.utils.GameSettings.SCR_WIDTH;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ui.ImageView;

public class Character {
    public Texture texture;
    public ImageView actorImgView;

    protected int x;
    protected int y;
    final Texture textureCactus = new Texture("icons/game1/cactus.png");
    final Texture textureFlower = new Texture("icons/game1/flower.png");
    int ex = 1;
    public int width = height = (int) (SCR_WIDTH * 0.35);
    public int height;

    public Character(Texture texture, int x, int y) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        actorImgView = new ImageView(x, y, width, height, texture);
    }

    public void setEx(int ex) {
        this.ex = ex;
    }

    public int getEx() {
        return ex;
    }
    public void update() {
        if (this.ex == 1)
            texture = textureFlower;
        else
            texture = textureCactus;
        this.actorImgView.setImgTexture(texture);
    }
}