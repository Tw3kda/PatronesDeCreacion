package com.balitechy.spacewar.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class ColorPlayer extends Player {
        private static final int SIZE = 56;
        private static final int HEIGHT = 56;
        private static final int INNER_SIZE = 36;
        private static final int INNER_HEIGHT = 36;
        private static final Color OUTER_COLOR = new Color(103, 22, 101); // Color morado
        private static final Color INNER_COLOR = Color.PINK; // Color rosa
        private static final Color BORDER_COLOR = new Color(80, 10, 80); // Color borde morado oscuro
    
        public ColorPlayer(int x, int y, Game game) {
            super(x, y, game);
        }
    
        @Override
        public void render(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); // Bordes redondeados y más delgados
    
            int centerX = (int) x;
            int centerY = (int) y;
    
            // Definir los puntos del triángulo exterior
            int[] xPoints = {
                    centerX - SIZE / 2,
                    centerX + SIZE / 2,
                    centerX
            };
            int[] yPoints = {
                    centerY + HEIGHT / 2,
                    centerY + HEIGHT / 2,
                    centerY - HEIGHT / 2
            };
            
            // Rellenar el triángulo exterior con color morado
            g2d.setColor(OUTER_COLOR);
            g2d.fillPolygon(xPoints, yPoints, 3);
            g2d.setColor(BORDER_COLOR);
            g2d.drawPolygon(xPoints, yPoints, 3);
            
            // Definir los puntos del triángulo interior
            int[] innerXPoints = {
                    centerX - INNER_SIZE / 2,
                    centerX + INNER_SIZE / 2,
                    centerX
            };
            int[] innerYPoints = {
                    centerY + INNER_HEIGHT / 2,
                    centerY + INNER_HEIGHT / 2,
                    centerY - INNER_HEIGHT / 2
            };
            
            // Rellenar el triángulo interior con color rosa
            g2d.setColor(INNER_COLOR);
            g2d.fillPolygon(innerXPoints, innerYPoints, 3);
            g2d.setColor(BORDER_COLOR);
            g2d.drawPolygon(innerXPoints, innerYPoints, 3);
        }
    }