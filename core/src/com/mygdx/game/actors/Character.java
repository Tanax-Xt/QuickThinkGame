package com.mygdx.game.actors;

import static com.mygdx.game.utils.GameSettings.SCR_HEIGHT;
import static com.mygdx.game.utils.GameSettings.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.UiComponent;

import java.util.ArrayList;

public class Character {
    UiComponent.OnClickListener onClickListener;
    ArrayList<Texture> texturesArray;
    public ImageView actorImgView;

    protected int x;
    protected int y;
    public int width;
    public int height;
    protected float velocityX;
    protected float velocityY;
    protected int textureStage;

    public boolean isAlive;

    Character(ArrayList<Texture> texturesArray) {
        this.texturesArray = texturesArray;
        width = height = MathUtils.random(100, 250);
        textureStage = MathUtils.random(0, texturesArray.size() - 1);
        actorImgView = new ImageView(x, y, width, height, texturesArray.get(textureStage));
        isAlive = true;
    }

    public void update() {
        actorImgView.x = x += velocityX;
        actorImgView.y = y += velocityY;

        if (x < 0 || x > SCR_WIDTH - width) velocityX = -velocityX;
        if (y < 0 || y > SCR_HEIGHT - height) velocityY = -velocityY;

        textureStage = (textureStage + 1) % texturesArray.size();
        actorImgView.setImgTexture(texturesArray.get(textureStage));
    }
}
