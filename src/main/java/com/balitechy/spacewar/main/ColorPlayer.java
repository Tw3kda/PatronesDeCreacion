package com.balitechy.spacewar.main;

import java.awt.*;

public class ColorPlayer extends Player {
    private static final int SIZE = 56;
    private static final int HEIGHT = 56;
    private static final int INNER_SIZE = 28;
    private static final int INNER_HEIGHT = 28;
    private static final Color OUTER_COLOR = new Color(103, 22, 101); // Color morado RGB (103, 22, 101)
    private static final Color INNER_COLOR = Color.PINK; // Color de fondo del triángulo interior

    public ColorPlayer(int x, int y, Game game) {
        super(x, y, game);
    }

    @Override
    public void render(Graphics g) {
        int centerX = (int) x;
        int centerY = (int) y;

        // Triángulo exterior
        int[] xPoints = {
                (int) (centerX - SIZE / 2),
                (int) (centerX + SIZE / 2),
                (int) centerX
        };
        int[] yPoints = {
                (int) (centerY + HEIGHT / 2),
                (int) (centerY + HEIGHT / 2),
                (int) (centerY - HEIGHT / 2)
        };
        g.setColor(OUTER_COLOR); // Establecer el color morado
        g.fillPolygon(xPoints, yPoints, 3);

        // Triángulo interior
        int[] innerXPoints = {
                (int) (centerX - INNER_SIZE / 2),
                (int) (centerX + INNER_SIZE / 2),
                (int) centerX
        };
        int[] innerYPoints = {
                (int) (centerY + INNER_HEIGHT / 2),
                (int) (centerY + INNER_HEIGHT / 2),
                (int) (centerY - INNER_HEIGHT / 2)
        };
        g.setColor(INNER_COLOR); // Establecer el color de fondo
        g.fillPolygon(innerXPoints, innerYPoints, 3);
    }
}