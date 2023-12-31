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
        if (MemoryLoader.loadSoundOn()) backSounds[0].play();
    }
    public static void playGame2() {
        if (MemoryLoader.loadSoundOn()) backSounds[1].play();
    }
    public static void playGame3() {
        if (MemoryLoader.loadSoundOn()) backSounds[2].play();
    }
    public static void gameOver() {
        if (MemoryLoader.loadSoundOn()) backSounds[3].play();
    }
    public static void MenuButtons() {
        if (MemoryLoader.loadSoundOn()) backSounds[4].play();
    }
}
