package com.balitechy.spacewar.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class AbstractPlayer extends Player {
    private final Color outerColor;
    private final Color innerColor;

    public AbstractPlayer(int x, int y, Game game, Color outerColor, Color innerColor) {
        super(x, y, game);
        this.outerColor = outerColor;
        this.innerColor = innerColor;
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        int centerX = (int) x;
        int centerY = (int) y;

        // Triángulo exterior
        int[] xPoints = { centerX - 28, centerX + 28, centerX };
        int[] yPoints = { centerY + 28, centerY + 28, centerY - 28 };

        g2d.setColor(outerColor);
        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(xPoints, yPoints, 3);

        // Triángulo interior
        int[] innerXPoints = { centerX - 18, centerX + 18, centerX };
        int[] innerYPoints = { centerY + 18, centerY + 18, centerY - 18 };

        g2d.setColor(innerColor);
        g2d.fillPolygon(innerXPoints, innerYPoints, 3);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(innerXPoints, innerYPoints, 3);
    }
}