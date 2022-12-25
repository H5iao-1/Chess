package com.example.chess.po.chessBoard;

import com.example.chess.enums.ChessPiece;
import com.example.chess.enums.GameMode;
import com.example.chess.enums.GameState;
import com.example.chess.po.User;

public abstract class Game {

//    private static ChessBoard chessBoard;

    protected GameMode gameMode;

    protected Integer boardSize;

    protected ChessPiece[][] pieces;

    protected GameState gameState;

    protected User user1;
    protected User user2;
    protected Integer result;

    public Game(GameMode gameMode, Integer boardSize, User user1, User user2) {
        this.gameMode = gameMode;
        this.boardSize = boardSize;
        this.pieces = new ChessPiece[this.boardSize][this.boardSize];
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                pieces[i][j] = ChessPiece.Empty;
            }
        }
        this.gameState = GameState.Gaming;
        this.user1 = user1;
        this.user2 = user2;
    }
//
//    public static synchronized ChessBoard getChessBoard() {
//        return chessBoard;
//    }
//
//    public static synchronized ChessBoard getChessBoard(GameMode gameMode, Integer boardSize) {
//        if (gameMode == null || boardSize == null) {
//            return chessBoard;
//        }
//        if (chessBoard == null) {
//            chessBoard = new ChessBoard(gameMode, boardSize);
//        }
//        return chessBoard;
//    }
//
//    public void end() {
//        chessBoard = null;
//    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public GameMode getGameMode() {
        return this.gameMode;
    }

    public void setBoardSize(Integer boardSize) {
        this.boardSize = boardSize;
    }

    public Integer getBoardSize() {
        return boardSize;
    }

    public abstract void move(ChessPiece chessPiece, Integer x, Integer y);

    public ChessPiece[][] getPieces() {
        return this.pieces;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }
}
