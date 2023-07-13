package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class MemoryLoader {

    private static final Preferences prefs = Gdx.app.getPreferences("User saves");

    public static int loadActiveMusic() {
        if (prefs.contains("musicActive")) return prefs.getInteger("musicActive");
        return 1;
    }

    public static void saveActiveMusic(int num) {
        prefs.putInteger("musicActive", num);
        prefs.flush();
    }

    public static void saveIconState(int numElement) {
        prefs.putInteger("iconState", numElement);
        prefs.flush();
    }

    public static int loadIconState() {
        if (prefs.contains("iconState")) return prefs.getInteger("iconState");
        return 1;
    }

    public static void saveSoundOn(boolean isTrue) {
        prefs.putBoolean("isSoundOn", isTrue);
        prefs.flush();
    }

    public static boolean loadSoundOn() {
        if (prefs.contains("isSoundOn")) return prefs.getBoolean("isSoundOn");
        return true;
    }

    public static void saveNewExperience(int result) {
        prefs.putInteger("exp", prefs.getInteger("exp") + result);
        prefs.flush();
    }

    public static int loadExperience() {
        if (prefs.contains("exp")) return prefs.getInteger("exp");
        return 0;
    }
}