package com.alexsobiek.SpaceRace;

import com.alexsobiek.SpaceRace.event.EventHandler;
import com.alexsobiek.SpaceRace.event.Listener;
import com.alexsobiek.SpaceRace.event.events.GameTickEvent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Window extends JPanel implements Listener {

    private JFrame frame = null;

    public static final int winHeight = 800;
    public static final int winWidth = 1200;
    public static final int halfY = Window.winHeight / 2;
    public static final int halfX = Window.winWidth / 2;
    public static final int maxStars = 100;

    java.util.List<Star> stars = new ArrayList<>();

    public Window() {
        SpaceRace.EVENT_BUS.subscribe(this);
        frame = new JFrame("Space Race");
        frame.add(this);
        frame.setSize(winWidth, winHeight);
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        spawnStars();

    }

    private void spawnStars() {
        for (int i = 0; i <= maxStars; i++) stars.add(new Star());
    }

    @EventHandler
    public void onTick(GameTickEvent event) {
        if (frame != null) {
            Timer.handleTick();
            moveStars();
            frame.repaint();
        }
    }


    public void moveStars() {
        for (Star star : stars) {
            if (checkStar(star)) return;
            star.move(star.getDirection());
        }
    }

    private boolean checkStar(Star star) {
        if (star.isOutOfBounds()) {
            star.reset();
            return true;
        } else return false;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        stars.forEach(star -> {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.fillOval(star.getX(), star.getY(), 10, 10);
        });
        Timer.drawTimer(g2d);

    }
}
