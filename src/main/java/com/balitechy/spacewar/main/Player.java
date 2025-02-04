package com.balitechy.spacewar.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {

	double x;
	double y;

	private double velX;
	private double velY;

	public static final int WIDTH = 56;
	public static final int HEIGHT = 28;

	private BufferedImage image;
	private Game game;

	public Player(double x, double y, Game game) {
		this.x = x;
		this.y = y;
		this.game = game;

		// Get image for Player
		image = game.getSprites().getImage(219, 304, WIDTH, HEIGHT);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public void shoot() {
		if (this instanceof VectorPlayer) {
			// Si es un VectorPlayer, dispara una bala vectorial
			VectorBullet vectorBullet = new VectorBullet(this.x, this.y);
			game.getBullets().addBulletV(vectorBullet);
		} else if (this instanceof ColorPlayer){
			// Si es un ColorPlayer, dispara una bala colorida
			ColorBullet colorBullet = new ColorBullet(this.x,this.y);
			game.getBullets().addBulletC(colorBullet);
		} else {
			// Si no es un VectorPlayer, dispara una bala normal
			Bullet bullet = new Bullet(this.x, this.y, game);
			game.getBullets().addBullet(bullet);
		}
	}

	public void tick() {
		x += velX;
		y += velY;

		// To avoid player go outside the arena.
		if (x <= 0) {
			x = 0;
		}
		if (x >= (Game.WIDTH * Game.SCALE) - WIDTH) {
			x = (Game.WIDTH * Game.SCALE) - WIDTH;
		}
		if (y <= 0) {
			y = 0;
		}
		if (y >= (Game.HEIGHT * Game.SCALE) - HEIGHT) {
			y = (Game.HEIGHT * Game.SCALE) - HEIGHT;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawImage(image, (int) x, (int) y, null);
	}
}
