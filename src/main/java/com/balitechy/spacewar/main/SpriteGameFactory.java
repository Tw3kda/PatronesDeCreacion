package com.balitechy.spacewar.main;

public class SpriteGameFactory implements GameFactory {

    @Override
    public Player createPlayer(int x, int y, Game game) {
        return new Player(x, y, game); // Usamos la versión sprite del jugador
    }

    @Override
    public BulletController createBulletController() {
        return new BulletController();
    }

    @Override
    public BackgroundRenderer createBackgroundRenderer() {
        return new BackgroundRenderer(false,false); // false para sprite
    }
}

