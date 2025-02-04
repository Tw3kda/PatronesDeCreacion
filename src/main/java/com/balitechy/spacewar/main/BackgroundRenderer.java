package com.balitechy.spacewar.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BackgroundRenderer {

	private boolean isVectorMode;  // Para almacenar el estado del fondo
	private boolean isColorMode;

	public BackgroundRenderer(boolean isVectorMode, boolean isColorMode) {
		this.isVectorMode = isVectorMode;
		this.isColorMode = isColorMode;
	}

	public void render(Graphics g, Canvas c) throws IOException {
		if (isVectorMode) {
			renderV(g, c);  // Renderizar en modo vectorial
		} else if(isColorMode){
			renderC(g, c);  // Renderizar en modo color
		} else {
			renderS(g,c); // Renderizar en modo sprite
		}
	}

	// Método para renderizar el fondo tipo sprite
	public void renderS(Graphics g, Canvas c) throws IOException {
		BufferedImage background = null;
		SpritesImageLoader bg = new SpritesImageLoader("/bg.png");
		bg.loadImage();
		background = bg.getImage(0, 0, 640, 480);
		g.drawImage(background, 0, 0, c.getWidth(), c.getHeight(), c);

		// Agregar texto "Presionar 'ENTER' para siguiente versión" en un cuadrado
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.setColor(Color.WHITE);
		g.drawString("Presionar 'ENTER'", 30, 465);
	}

	// Método para renderizar el fondo tipo vector
	public void renderV(Graphics g, Canvas c) throws IOException {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, c.getWidth(), c.getHeight());
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3)); // Establecer el grosor de línea a 3 unidades

		g.setColor(Color.BLACK);
		g.drawOval(35, 30, 100, 100);

		// Agregar texto "Presionar 'ENTER' para siguiente versión" en un cuadrado
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.setColor(Color.BLACK);
		g.drawString("Presionar 'ENTER'", 30, 465);
	}

	public void renderC(Graphics g, Canvas c) throws IOException {
		g.setColor(Color.PINK);
		g.fillRect(0, 0, c.getWidth(), c.getHeight());

		// Convertir g a Graphics2D para poder usar setLineWidth()
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3)); // Establecer el grosor de línea a 3 unidades

		g.setColor(new Color(44, 142, 181));
		g.drawOval(35, 30, 100, 100);

		// Agregar texto "Presionar 'ENTER'
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.setColor(new Color(137, 23, 89 ));
		g.drawString("Presionar 'ENTER'", 30, 465);
	}
}
