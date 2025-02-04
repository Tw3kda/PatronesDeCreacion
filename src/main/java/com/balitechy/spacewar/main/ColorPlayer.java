package com.balitechy.spacewar.main;

import java.awt.Color;

public class ColorPlayer extends AbstractPlayer {
    public ColorPlayer(int x, int y, Game game) {
        super(x, y, game, new Color(103, 22, 101), Color.PINK);
    }
}