package com.balitechy.spacewar.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "Space War 2D";
	private boolean fKeyPressed = false;

	private boolean running = false;
	private Thread thread;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	private SpritesImageLoader sprites;
	private GameFactory factory;

	// Game components
	private Player player;
	private BulletController bullets;
	private BackgroundRenderer backgRenderer;
	private boolean isSpriteMode = true;

	public Game(GameFactory factory) {
		this.factory = factory;
	}

	public void init() {
		requestFocus();

		sprites = new SpritesImageLoader("/sprites.png");
		try {
			sprites.loadImage();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Add keyboard listener
		addKeyListener(new InputHandler(this));

		// inicialización de componentes
		player = factory.createPlayer((WIDTH * SCALE - Player.WIDTH) / 2, HEIGHT * SCALE - 50, this);
		bullets = factory.createBulletController();
		backgRenderer = factory.createBackgroundRenderer();
	}

	public void changeFactory(GameFactory newFactory) {
		this.factory = newFactory;

		init();

		if (factory instanceof SpriteGameFactory) {
			backgRenderer = new BackgroundRenderer(false, false); // Modo sprite
		} else if (factory instanceof VectorGameFactory) {
			backgRenderer = new BackgroundRenderer(true, false); // Modo vector
		} else if (factory instanceof ColorGameFactory) {
			backgRenderer = new BackgroundRenderer(false, true);
		}
	}


	public SpritesImageLoader getSprites() {
		return sprites;
	}

	public BulletController getBullets() {
		return bullets;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_ENTER) {
			if (!fKeyPressed) {
				if (factory instanceof SpriteGameFactory) {
					System.out.println("Cambiando a modo vector");
					changeFactory(new VectorGameFactory());
				} else if (factory instanceof VectorGameFactory) {
					System.out.println("Cambiando a modo colorful");
					changeFactory(new ColorGameFactory());
				} else {
					System.out.println("Cambiando a modo sprite");
					changeFactory(new SpriteGameFactory());
				}
				// Marcar que la tecla F está presionada
				fKeyPressed = true;
			}
		}

		switch (key) {
			case KeyEvent.VK_RIGHT:
				player.setVelX(5);
				break;

			case KeyEvent.VK_LEFT:
				player.setVelX(-5);
				break;

			case KeyEvent.VK_UP:
				player.setVelY(-5);
				break;

			case KeyEvent.VK_DOWN:
				player.setVelY(5);
				break;

			case KeyEvent.VK_SPACE:
				player.shoot();
				break;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_ENTER) {
			fKeyPressed = false;
		}

		switch (key) {
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_LEFT:
				player.setVelX(0);
				break;

			case KeyEvent.VK_UP:
			case KeyEvent.VK_DOWN:
				player.setVelY(0);
				break;
		}
	}

	private synchronized void start() {
		if (running) {
			return;
		}

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	private synchronized void stop() {
		if (!running) {
			return;
		}

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	@Override
	public void run() {
		init();

		long lastTime = System.nanoTime();
		final double numOfTicks = 60.0;
		double ns = 1000000000 / numOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " ticks, fps " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	public void tick() {
		player.tick();
		bullets.tick();
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		try {
			backgRenderer.render(g, this);
			player.render(g);
			bullets.render(g);
		} catch (IOException e) {
			e.printStackTrace();
		}

		g.dispose();
		bs.show();
	}

	private void toggleBackgroundMode() {
		isSpriteMode = !isSpriteMode;
		if (isSpriteMode) {
			backgRenderer = new BackgroundRenderer(false, false);  // Modo sprite
		} else if(isSpriteMode) {
			backgRenderer = new BackgroundRenderer(true, false);  // Modo vector
		} else {
			backgRenderer = new BackgroundRenderer(false, true);  // Modo color
		}
	}

	public static void main(String args[]) {
		GameFactory factory = new SpriteGameFactory();
		Game game = new Game(factory);
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}
}