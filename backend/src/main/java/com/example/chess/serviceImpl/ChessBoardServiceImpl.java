package com.example.chess.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.example.chess.enums.ChessPiece;
import com.example.chess.enums.GameMode;
import com.example.chess.enums.GameState;
import com.example.chess.po.chessBoard.Game;
import com.example.chess.po.chessBoard.GoGame;
import com.example.chess.po.chessBoard.GobangGame;
import com.example.chess.service.ChessBoardService;
import com.example.chess.serviceImpl.judgeStrategy.GoJudge;
import com.example.chess.serviceImpl.judgeStrategy.GobangJudge;
import com.example.chess.serviceImpl.judgeStrategy.JudgeContext;
import com.example.chess.vo.ResponseVO;
import org.springframework.stereotype.Service;

@Service
public class ChessBoardServiceImpl implements ChessBoardService {

    Game game;

    @Override
    public ResponseVO startGame(GameMode gameMode, int boardSize) {
//        this.game = switch (gameMode) {
            // TODO
//            case Go -> new GoGame(gameMode, boardSize);
//            case Gobang -> new GobangGame(gameMode, boardSize);
//        };
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO endGame() {
        this.game = null;
        return ResponseVO.buildSuccess(true);
    }

    public ResponseVO move(ChessPiece chessPiece, Integer x, Integer y){
        this.game.move(chessPiece, x, y);
        JudgeContext judgeContext = switch (this.game.getGameMode()) {
            case Go -> new JudgeContext(new GoJudge());
            case Gobang -> new JudgeContext(new GobangJudge());
        };

        GameState gameState = judgeContext.judge(this.game, chessPiece, x, y);
        JSONObject res = new JSONObject();
        ChessPiece[][] piecesArray = this.game.getPieces();
        int[][] chessArray = new int[piecesArray.length][piecesArray.length];
        for (int i = 0; i < piecesArray.length; i++) {
            for (int j = 0; j < piecesArray.length; j++) {
                chessArray[i][j] = piecesArray[i][j].toInt();
            }
        }

        res.put("gameState", gameState);
        res.put("chessArray", chessArray);
        return ResponseVO.buildSuccess(res);
    }



}
