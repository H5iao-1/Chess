package com.example.chess.po.chessBoard;

import com.example.chess.enums.ChessPiece;
import com.example.chess.enums.GameMode;
import com.example.chess.po.User;

public class ReversiGame extends Game{

    public ReversiGame(GameMode gameMode, Integer boardSize, User user1, User user2) {
        super(gameMode, boardSize, user1, user2);
    }

    @Override
    public void move(ChessPiece chessPiece, Integer x, Integer y) {

    }
}
