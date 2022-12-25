package com.example.chess.enums;

public enum GameState {
    Draw(0),
    BlackWin(3),
    WhiteWin(1),
    Gaming(-1);


    private int value;

    GameState(int value) {
        this.value = value;
    }

    public int toInt() {
        return this.value;
    }
}
