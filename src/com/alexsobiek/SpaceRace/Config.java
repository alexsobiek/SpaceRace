package com.alexsobiek.SpaceRace;

import java.awt.*;
import java.util.HashMap;
public class Config {

    private final HashMap<String, Object> overrides = new HashMap<>();

    enum Defaults {
        MAX_STARS(70),
        TIME(15),
        TPS(200),
        WINDOW_HEIGHT(800),
        WINDOW_LENGTH(1200),
        PAUSE_COLOR(new Color(25, 25, 25)),
        FOREGROUND_COLOR(Color.WHITE),
        BACKGROUND_COLOR(Color.BLACK);

        Object value;
        Defaults(Object value) {
            this.value = value;
        }
        Object getVal() {
            return value;
        }
    }

    public void set(String key, Object value) {
        overrides.put(key, value);
    }

    public Object get(String key) {
        key = key.toUpperCase();
        if (overrides.containsKey(key)) return overrides.get(key);
        else return Defaults.valueOf(key).getVal();
    }

    public int getInt(String key) {
        return (int) get(key);
    }

    public String getString(String key) {
        return (String) get(key);
    }

    public Color getColor(String key) {
        return (Color) get(key);
    }
}

