package com.example.chess.serviceImpl.judgeStrategy;

import com.example.chess.enums.ChessPiece;
import com.example.chess.enums.GameState;
import com.example.chess.po.chessBoard.Game;

public abstract class JudgeStrategy {
    public abstract GameState judge(Game game, ChessPiece chessPiece, Integer x, Integer y);
}


