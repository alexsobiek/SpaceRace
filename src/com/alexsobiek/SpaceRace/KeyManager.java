package com.alexsobiek.SpaceRace;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        // Handle player movement
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        if (key == 'r' || key == 'R') GameManager.restart();
        else if (key == 'p' || key == 'P') GameManager.togglePause();
    }
}
