package com.example.chess.controller;

import com.example.chess.enums.ChessPiece;
import com.example.chess.enums.GameMode;
import com.example.chess.service.ChessBoardService;
import com.example.chess.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.Objects;

@RestController
@RequestMapping("/api/chessBoard")
public class ChessController {

    @Autowired
    ChessBoardService chessBoardService;

    @PostMapping("/startGame")
    public ResponseVO startGame(@RequestBody Object object){
        LinkedHashMap<?, ?> data = (LinkedHashMap<?, ?>) object;
        String sign = (String) data.get("gameMode");
        GameMode gameMode = null;
        if (Objects.equals(sign, "Go")) {
            gameMode = GameMode.Go;
        } else if (Objects.equals(sign, "Gobang")) {
            gameMode = GameMode.Gobang;
        }
        Integer boardSize = (Integer) data.get("boardSize");

        return chessBoardService.startGame(gameMode, boardSize);
    }

    @PostMapping("/endGame")
    public ResponseVO endGame(){

        return chessBoardService.endGame();
    }

    @PostMapping("/move")
    public ResponseVO move(@RequestBody Object object){
        LinkedHashMap<?, ?> data = (LinkedHashMap<?, ?>) object;
        int sign = (int) data.get("chessPiece");
        ChessPiece chessPiece = null;
        if (sign == 1) {
            chessPiece = ChessPiece.Black;
        } else if (sign == -1) {
            chessPiece = ChessPiece.White;
        }

        Integer x = (Integer) data.get("x");
        Integer y = (Integer) data.get("y");

        return chessBoardService.move(chessPiece, x, y);



    }
}
