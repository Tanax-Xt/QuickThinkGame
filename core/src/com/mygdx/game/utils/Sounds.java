package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
    static Sound[] backSounds = {
            Gdx.audio.newSound(Gdx.files.internal("audio/sounds/game1.mp3")),
            Gdx.audio.newSound(Gdx.files.internal("audio/sounds/game2.mp3")),
            Gdx.audio.newSound(Gdx.files.internal("audio/sounds/game3.mp3")),
            Gdx.audio.newSound(Gdx.files.internal("audio/sounds/gameOver.mp3")),
            Gdx.audio.newSound(Gdx.files.internal("audio/sounds/MenuButtons.mp3")),
    };

    public static void playGame1() {
        backSounds[0].play();
    }
    public static void playGame2() {
        backSounds[1].play();
    }
    public static void playGame3() {
        backSounds[2].play();
    }
    public static void gameOver() {
        backSounds[3].play();
    }
    public static void MenuButtons() {
        backSounds[4].play();
    }

}
