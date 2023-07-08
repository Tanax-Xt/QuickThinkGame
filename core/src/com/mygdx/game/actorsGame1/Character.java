package com.mygdx.game.actorsGame1;

import static com.mygdx.game.utils.GameSettings.SCR_HEIGHT;
import static com.mygdx.game.utils.GameSettings.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.ui.ImageView;
import com.mygdx.game.ui.UiComponent;
import com.mygdx.game.utils.GameSettings;

import java.util.ArrayList;

public class Character {
    UiComponent.OnClickListener onClickListener;
    Texture texture;
    public ImageView actorImgView;

    protected int x;
    protected int y;
    final Texture textureCactus = new Texture("icons/game1/cactus.png");
    final Texture textureFlower = new Texture("icons/game1/flower.png");


    int ex = 1;
    public int width = height = (int) (SCR_WIDTH * 0.35);
    public int height;

//    protected float velocityX;
//    protected float velocityY;
//    protected int textureStage;

//    public boolean isAlive;
    isHitListener isHitListenerCharacter;

    public Character(Texture texture, int x, int y, isHitListener isHitListenerCharacter) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        actorImgView = new ImageView(x, y, width, height, texture);
//        isAlive = true;
        this.isHitListenerCharacter = isHitListenerCharacter;
        actorImgView.setOnClickListener(isHitListener);
    }

    public void setEx(int ex) {
        this.ex = ex;
    }

    public int getEx() {
        return ex;
    }

    UiComponent.OnClickListener isHitListener = new UiComponent.OnClickListener() {
        @Override
        public void onClick() {
            isHitListenerCharacter.onClick();
        }
    };

    public interface isHitListener {
        void onClick();
    }

    public void update() {
        if (this.ex == 1)
            texture = textureFlower;
        else
            texture = textureCactus;
        actorImgView = new ImageView(x, y, width, height, texture);
    }
}