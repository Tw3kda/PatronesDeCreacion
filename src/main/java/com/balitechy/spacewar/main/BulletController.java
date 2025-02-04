package com.balitechy.spacewar.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class BulletController {

	private LinkedList<Bullet> bl = new LinkedList<Bullet>(); // Balas normales
	private LinkedList<VectorBullet> vb = new LinkedList<VectorBullet>(); // Balas vectoriales
	private LinkedList<ColorBullet> cb = new LinkedList<ColorBullet>(); //Balas coloridas

	public void tick() {
		for (int i = 0; i < bl.size(); i++) {
			if (bl.get(i).getY() < 0) {
				removeBullet(bl.get(i)); // Eliminar balas sprite fuera de la pantalla
			} else {
				bl.get(i).tick(); // Actualiza la posicion de la bala sprite
			}
		}

		for (int i = 0; i < vb.size(); i++) {
			if (vb.get(i).getY() < 0) {
				removeBulletV(vb.get(i)); // Eliminar balas vectoriales fuera de pantalla
			} else {
				vb.get(i).tick(); // Actualiza la posición de la bala vectorial
			}
		}

		for (int i = 0; i < cb.size(); i++) {
			if (cb.get(i).getY() < 0) {
				removeBulletC(cb.get(i)); // Eliminar balas coloridas fuera de pantalla
			} else {
				cb.get(i).tick(); // Actualiza la posición de la bala colorida
			}
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < bl.size(); i++) {
			bl.get(i).render(g); // Dibuja las balas con imagen
		}

		for (int i = 0; i < vb.size(); i++) {
			vb.get(i).render(g); // Dibuja las balas vectoriales
		}

		for (int i = 0; i < cb.size(); i++) {
			cb.get(i).render(g); // Dibuja las balas coloridas
		}
	}

	public void addBullet(Bullet bullet) {
		bl.add(bullet);
	}

	public void removeBullet(Bullet bullet) {
		bl.remove(bullet);
	}

	public void addBulletV(VectorBullet vectorBullet) {
		vb.add(vectorBullet);
	}

	public void removeBulletV(VectorBullet vectorBullet) {
		vb.remove(vectorBullet);
	}

	public void addBulletC(ColorBullet colorBullet) {
		cb.add(colorBullet);
	}

	public void removeBulletC(ColorBullet colorBullet) {
		cb.remove(colorBullet);
	}

}
