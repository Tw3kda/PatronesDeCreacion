package com.balitechy.spacewar.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class VectorPlayer extends Player {
        private static final int SIZE = 56;
        private static final int HEIGHT = 56;
        private static final int INNER_SIZE = 36;
        private static final int INNER_HEIGHT = 36;
    
        public VectorPlayer(int x, int y, Game game) {
            super(x, y, game);
        }
    
        @Override
        public void render(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    
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
            
            // Rellenar el triángulo con color blanco
            g2d.setColor(Color.WHITE);
            g2d.fillPolygon(xPoints, yPoints, 3);
            g2d.setColor(Color.BLACK);
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
            
            // Rellenar el triángulo interior con color blanco
            g2d.setColor(Color.WHITE);
            g2d.fillPolygon(innerXPoints, innerYPoints, 3);
            
            // Dibujar el contorno del triángulo interior con el mismo grosor
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(innerXPoints, innerYPoints, 3);
        }
    }
    