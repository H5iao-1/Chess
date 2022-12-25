package com.example.chess.service;

import com.example.chess.enums.ChessPiece;
import com.example.chess.enums.GameMode;
import com.example.chess.enums.GameState;
import com.example.chess.vo.ResponseVO;

public interface ChessBoardService {

    ResponseVO startGame(GameMode gameMode, int boardSize);

    ResponseVO endGame();

    ResponseVO move(ChessPiece chessPiece, Integer x, Integer y);

}
