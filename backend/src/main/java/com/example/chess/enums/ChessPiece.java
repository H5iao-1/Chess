package com.example.chess.enums;

public enum ChessPiece {

    Empty(0),
    Black(1),
    White(-1);


    private int value;

    ChessPiece(int value) {
        this.value = value;
    }

    public int toInt() {
        return this.value;
    }
}

