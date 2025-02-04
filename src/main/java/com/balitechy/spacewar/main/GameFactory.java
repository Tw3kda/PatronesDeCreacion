package com.balitechy.spacewar.main;

 interface GameFactory {
    
    Player createPlayer(int x, int y, Game game);
    BulletController createBulletController();
    BackgroundRenderer createBackgroundRenderer();
   
    
}
