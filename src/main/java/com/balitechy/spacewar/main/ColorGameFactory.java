package com.balitechy.spacewar.main;

public class ColorGameFactory implements GameFactory {

    @Override
    public Player createPlayer(int x, int y, Game game) {
        return new ColorPlayer(x, y, game); // Usamos la versión vectorial colorida del jugador
    }

    @Override
    public BulletController createBulletController() {
        return new BulletController();
    }

    @Override
    public BackgroundRenderer createBackgroundRenderer() {
        return new BackgroundRenderer(false,true);    
    }    
}
