package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;

public class GameSettings {
    public static int SCR_WIDTH = 1080;
    public static int SCR_HEIGHT = 1920;

    public static boolean DEFAULT_SOUND_STATE = true;

    public static String toString(int n) {
        String s = "off";

        if (n == 1)
            s = "8-bit";
        else if (n == 2)
            s = "Ukulele";
        else if (n == 3)
            s = "Mexico";
        else if (n == 4)
            s = "Cubism";
        else if (n == 5)
            s = "Morning";
        else if (n == 6)
            s = "High tech";

        return s;
    }
}
