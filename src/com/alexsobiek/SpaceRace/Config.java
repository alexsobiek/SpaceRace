package com.alexsobiek.SpaceRace;

import java.awt.*;
import java.util.HashMap;

public enum Config {
    MAX_STARS(70),
    TIME(15),
    TPS(200),
    WINDOW_HEIGHT(800),
    WINDOW_LENGTH(1200),
    PAUSE_COLOR(new Color(25, 25, 25)),
    FOREGROUND_COLOR(Color.WHITE),
    BACKGROUND_COLOR(Color.BLACK);

    Object value;
    Config(Object value) {
        this.value = value;
    }

    Object getValue() {
        return value;
    }

    public static Object get(Config key) {
        if (ConfigUtility.get(key) != null) return ConfigUtility.get(key);
        else return key.getValue();
    }

    public static int getInt(Config key) {
        return (int) get(key);
    }

    public static Color getColor(Config key) {
        return (Color) get(key);
    }

    public static String getString(Config key) {
        return (String) get(key);
    }

    void set(Object value) {
        ConfigUtility.set(this, value);
    }
}

class ConfigUtility {
    private static final HashMap<Config, Object> overrides = new HashMap<>();
    public static Object get(Config key) {
        return overrides.getOrDefault(key, null);
    }
    public static void set(Config key, Object value) {
        overrides.put(key, value);
    }
}