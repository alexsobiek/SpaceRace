package com.alexsobiek.spacerace.graphics;

import com.alexsobiek.spacerace.Config;
import com.alexsobiek.spacerace.GameManager;
import com.alexsobiek.spacerace.SpaceRace;
import com.alexsobiek.spacerace.entity.Location;
import com.alexsobiek.spacerace.entity.entities.Player;
import com.alexsobiek.spacerace.entity.entities.Star;
import com.alexsobiek.spacerace.event.EventHandler;
import com.alexsobiek.spacerace.event.Listener;
import com.alexsobiek.spacerace.event.events.GameTickEvent;
import com.alexsobiek.spacerace.event.events.WindowResizeEvent;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class Window extends JPanel implements Listener {

    public static JFrame frame = null;

    public static int winHeight;
    public static int winWidth;
    public static int halfY;
    public static int halfX;

    private static final List<Star> stars = new ArrayList<>();

    public static Player player1;
    public static Player player2;

    private static Timer timer;

    public static Color pauseColor;
    public static Color foregroundColor;
    public static Color backgroundColor;

    private final Font gameFont;

    /**
     * Constructor:
     * Creates a new window and begins painting
     */
    public Window() {
        SpaceRace.EVENT_BUS.subscribe(this);

        frame = new JFrame("Space Race");
        frame.add(this);
        frame.setSize(Config.getInt(Config.WINDOW_LENGTH), Config.getInt(Config.WINDOW_HEIGHT));

        init();

        frame.setBackground(backgroundColor);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startTimer();

        try {
            gameFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts", "FFFFORWA.TTF")).deriveFont(Font.PLAIN, 16F);
        } catch (IOException | FontFormatException e) {
            throw new Error(e);
        }

        spawnStars();
        spawnPlayers();
    }

    /**
     * Utility class for initializing class variables
     */
    private static void init() {
        pauseColor = Config.getColor(Config.PAUSE_COLOR);
        foregroundColor = Config.getColor(Config.FOREGROUND_COLOR);
        backgroundColor = Config.getColor(Config.BACKGROUND_COLOR);
        winHeight = frame.getHeight();
        winWidth = frame.getWidth();
        halfX = winWidth / 2;
        halfY = winHeight / 2;
    }

    /**
     * Resets the window
     */
    public static void reset() {
        init();
        stars.clear();
        spawnStars();
        startTimer();
        frame.repaint();
    }

    public static void startTimer() {
        if (timer != null) clearTimer();
        timer = new Timer(Config.getInt(Config.TIME));
    }

    private static void clearTimer() {
        SpaceRace.EVENT_BUS.unSubscribe(timer);
        timer = null;
    }

    /**
     * Spawns stars to be painted in the window
     */
    private static void spawnStars() {
        int maxStars = Config.getInt(Config.MAX_STARS);
        for (int i = 0; i <= maxStars; i++) stars.add(new Star());
    }

    public static List<Star> getStars() {
        return stars;
    }

    /**
     * Spawns players to be painted in the window
     */
    private void spawnPlayers() {
        player1 = new Player((halfX - 87), (winHeight - 100), 10);
        player2 = new Player((halfX + 87), (winHeight - 100), 10);
    }

    /**
     * Called when a GameTickEvent is invoked
     * @param event GameTickEvent
     */
    @EventHandler
    public void onTick(GameTickEvent event) {
        if (frame != null) {
            moveStars();
            frame.repaint();
        }
    }

    @EventHandler
    public void onResize(WindowResizeEvent event) {
        int prevHeight = winHeight;
        init();


        /*
        ========================================
        To Do:
        Clearing and respawning all the stars
        creates a break in the game where there
        are no stars. Fix this by dynamically
        resetting the stars origin positions
        instead.
        ========================================
        */

        stars.clear();
        spawnStars();


        Component component = event.getComponentEvent().getComponent();
        if (component.getHeight() != prevHeight) {
            long ticksRemaining = timer.getRemainingTicks();
            clearTimer();
            timer = new Timer(ticksRemaining);
        }

        Location player1Location = new Location((halfX - 100), (winHeight - 100));
        Location player2Location = new Location((halfX + 100), (winHeight - 100));

        player1.setLocation(player1Location);
        player2.setLocation(player2Location);

        frame.repaint();
    }

    /**
     * Loops through stars and moves them
     */
    public void moveStars() {
        try {
            for (Star star : stars) {
                if (checkStar(star)) return;
                star.move(star.getDirection());
            }
        } catch (ConcurrentModificationException ignored) {}
    }

    /**
     * Checks if a star is out of bounds and if so, resets them
     * @param star Star instance to check
     * @return boolean
     */
    private boolean checkStar(Star star) {
        if (star.isOutOfBounds()) {
            star.reset();
            return true;
        } else return false;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(gameFont);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(foregroundColor);
        if (GameManager.isRunning() && timer != null) timer.drawTimer(g2d);
        else drawCenteredString(g2d, "Game Over!");

        if (stars.size() > 0) {
            try {
                stars.forEach(star -> {
                    Location loc = star.getLocation();
                    g.fillOval(loc.getX(), loc.getY(), 6, 4);
                });
            } catch (ConcurrentModificationException ignored) {}

        }

        drawPlayer(g2d, player1);
        drawPlayer(g2d, player2);
        drawPlayerScores(g2d);
    }

    /**
     * Creates a player model to paint
     * @param g2d Graphics2D to use for painting
     * @param player Player instance to paint
     */
    private void drawPlayer(Graphics2D g2d, Player player) {
        if (player != null) player.getModel().draw(g2d);
    }

    /**
     * Paints the text used to display player scores
     * @param g2d Graphics2D to use for painting
     */
    private void drawPlayerScores(Graphics2D g2d) {
        if (gameFont == null) return;
        g2d.setFont(gameFont.deriveFont(Font.PLAIN, 58F));
        // Draw player scores
        if (player1 != null) {
            g2d.drawString(String.valueOf(player1.getScore()), halfX - 185, winHeight - 50);
        }
        if (player2 != null) {
            g2d.drawString(String.valueOf(player2.getScore()), halfX + 150, winHeight - 50);
        }
        g2d.setFont(gameFont);
    }

    /**
     * Utility method for centering and painting strings
     * @param g2d Graphics2D to use for painting
     * @param string String to center and paint
     */
    private void drawCenteredString(Graphics2D g2d, String string) {
        int width = (int) g2d.getFontMetrics().getStringBounds(string, g2d).getWidth();
        int height = (int) g2d.getFontMetrics().getStringBounds(string, g2d).getHeight();
        g2d.drawString(string, (halfX - (width / 2)), (halfY - (height / 2)));
    }
}
