package com.example.chess.enums;

public enum GameMode {

    Gobang("Gobang"),
    Go("Go"),
    Reversi("Reversi");

    private String value;

    GameMode(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
