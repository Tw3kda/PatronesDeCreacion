package com.balitechy.spacewar.main;

public class VectorGameFactory implements GameFactory {

    @Override
    public Player createPlayer(int x, int y, Game game) {
        return new VectorPlayer(x, y, game); // Usamos la versión vectorial del jugador
    }

    @Override
    public BulletController createBulletController() {
        return new BulletController();
    }

    @Override
    public BackgroundRenderer createBackgroundRenderer() {
        return new BackgroundRenderer(true, false); // true para vector
    }
}


