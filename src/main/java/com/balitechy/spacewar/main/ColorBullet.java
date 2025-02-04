package com.balitechy.spacewar.main;
import java.awt.*;

public class ColorBullet {

    private double x;
    private double y;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 21;

    public ColorBullet(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void tick() {
        y -= 5;
    }

    public void render(Graphics g) {
        g.setColor(new Color(176, 41, 142));
        g.drawLine((int) x, (int) y, (int) x, (int) (y - 10));
    }

    // Getter para la posición Y
    public double getY() {
        return y;
    }
}