package com.mygdx.game.utils;

import static com.mygdx.game.utils.GameSettings.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class MemoryLoader {

    private static final Preferences prefs = Gdx.app.getPreferences("User saves");

    public static void saveMusicState(boolean isPlaying) {
        prefs.putString("musicState", String.valueOf(isPlaying));
        prefs.flush();
    }

    public static boolean loadMusicState() {
        if (prefs.contains("musicState"))
            return Boolean.parseBoolean(prefs.getString("musicState"));
        saveMusicState(DEFAULT_SOUND_STATE);
        return true;
    }

    public static void saveIconState(int numElement) {
        prefs.putInteger("iconState", numElement);
        prefs.flush();
    }

    public static int loadIconState() {
        if (prefs.contains("iconState")) return prefs.getInteger("iconState");
        return 1;
    }

    public static void saveResultThirdGame(int XP) {
        prefs.putInteger("resultThirdGame", XP);
        prefs.flush();
    }

    public static int loadResultThirdGame() {
        if (prefs.contains("resultThirdGame")) return prefs.getInteger("resultThirdGame");
        return 0;
    }
}