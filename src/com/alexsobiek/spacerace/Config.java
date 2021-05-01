package com.alexsobiek.spacerace;

import java.awt.*;
import java.util.HashMap;

public enum Config {
    MAX_STARS(70),
    TIME(30),
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

    /**
     * Returns the enum's value
     * @return Object
     */
    Object getValue() {
        return value;
    }

    /**
     * Returns the value for the supplied key as an Object
     * @param key Config
     * @return Object
     */
    public static Object get(Config key) {
        if (ConfigUtility.get(key) != null) return ConfigUtility.get(key);
        else return key.getValue();
    }

    /**
     * Returns the value for the supplied key as an int
     * @param key Config
     * @return int
     */
    public static int getInt(Config key) {
        return (int) get(key);
    }

    /**
     * Returns the value for the supplied key as a Color
     * @param key Config key
     * @return Color
     */
    public static Color getColor(Config key) {
        return (Color) get(key);
    }

    /**
     * Returns the value for the supplied key as a String
     * @param key Config
     * @return String
     */
    public static String getString(Config key) {
        return (String) get(key);
    }

    /**
     * Sets the config entry's value
     * @param value Object
     */
    void set(Object value) {
        ConfigUtility.set(this, value);
    }
}

class ConfigUtility {
    private static final HashMap<Config, Object> overrides = new HashMap<>();

    /**
     * Returns the value for the supplied key from the overrides HashMap as an Object
     * @param key Config key
     * @return Object
     */
    public static Object get(Config key) {
        return overrides.getOrDefault(key, null);
    }

    /**
     * Sets the config value for the supplied key
     * @param key Config
     * @param value Object
     */
    public static void set(Config key, Object value) {
        overrides.put(key, value);
    }
}