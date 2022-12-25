package com.example.chess.serviceImpl.judgeStrategy;

import com.example.chess.enums.ChessPiece;
import com.example.chess.enums.GameState;
import com.example.chess.po.chessBoard.Game;

public class JudgeContext {
    private JudgeStrategy judgeStrategy;

    public JudgeContext(JudgeStrategy judgeStrategy) {
        this.judgeStrategy = judgeStrategy;
    }

    public GameState judge(Game game, ChessPiece chessPiece, Integer x, Integer y) {
        return judgeStrategy.judge(game, chessPiece, x, y);
    }

}

