package com.alexsobiek.SpaceRace;

import com.alexsobiek.SpaceRace.entity.entities.Player;
import com.alexsobiek.SpaceRace.entity.entities.Star;
import com.alexsobiek.SpaceRace.event.EventHandler;
import com.alexsobiek.SpaceRace.event.Listener;
import com.alexsobiek.SpaceRace.event.events.GameTickEvent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Window extends JPanel implements Listener {

    public static JFrame frame = null;

    public static final int winHeight = 800;
    public static final int winWidth = 1200;
    public static final int halfY = Window.winHeight / 2;
    public static final int halfX = Window.winWidth / 2;
    public static final int maxStars = 50;

    private static final List<Star> stars = new ArrayList<>();

    public static Player player1;
    public static Player player2;

    public Window() {
        SpaceRace.EVENT_BUS.subscribe(this);
        frame = new JFrame("Space Race");
        frame.add(this);
        frame.setSize(winWidth, winHeight);
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        spawnStars();
        spawnPlayers();
        Timer.start(30);
    }

    public static void reset() {
        stars.clear();
        spawnStars();
        Timer.start(10);
        frame.repaint();
    }

    private static void spawnStars() {
        for (int i = 0; i <= maxStars; i++) stars.add(new Star());
    }

    private static void spawnPlayers() {
        player1 = new Player((halfX - 100), (winHeight - 100), 5);
        player2 = new Player((halfX + 100), (winHeight - 100), 5);
    }

    @EventHandler
    public void onTick(GameTickEvent event) {
        if (frame != null) {
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
        if (GameManager.isRunning()) Timer.drawTimer(g2d);
        else drawCenteredString(g2d, "Game Over!");
        stars.forEach(star -> {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.fillOval(star.getX(), star.getY(), 6, 6);
        });
        drawPlayer(g2d, player1);
        drawPlayer(g2d, player2);
        drawPlayerScores(g2d);
    }

    private void drawPlayer(Graphics2D g2d, Player player) {
        if (player != null) {
            g2d.fillOval(player.getX(), player.getY(), 40, 40);
        }
    }

    private void drawPlayerScores(Graphics2D g2d) {
        // Setup our larger font
        Font baseFont = g2d.getFont();
        Font newFont = baseFont.deriveFont(Font.PLAIN, 58);
        g2d.setFont(newFont);

        // Draw player scores
        if (player1 != null) {
            g2d.drawString("" + player1.getScore(), halfX - 175, winHeight - 50);
        }
        if (player2 != null) {
            g2d.drawString("" + player2.getScore(), halfX + 175, winHeight - 50);
        }

        // reset our font
        g2d.setFont(baseFont);
    }

    private void drawCenteredString(Graphics2D g2d, String string) {
        int width = (int) g2d.getFontMetrics().getStringBounds(string, g2d).getWidth();
        int height = (int) g2d.getFontMetrics().getStringBounds(string, g2d).getHeight();
        g2d.drawString(string, (halfX - (width / 2)), (halfY - (height / 2)));
    }

}
