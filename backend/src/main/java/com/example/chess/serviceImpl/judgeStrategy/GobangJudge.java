package com.example.chess.serviceImpl.judgeStrategy;

import com.example.chess.enums.ChessPiece;
import com.example.chess.enums.GameState;
import com.example.chess.po.chessBoard.Game;

public class GobangJudge extends JudgeStrategy {

    @Override
    public GameState judge(Game game, ChessPiece chessPiece, Integer x, Integer y) {
        ChessPiece[][] chessArray = game.getPieces();
        if (left_right(chessArray, x, y) >= 5
                || up_down(chessArray, x, y) >= 5
                || leftUp_RightDown(chessArray, x, y) >= 5
                || leftDown_RightUp(chessArray, x, y) >= 5) {
            if (chessPiece == ChessPiece.Black) {
                return GameState.BlackWin;
            } else {
                return GameState.WhiteWin;
            }
        } else {
            for (int i = 0; i < chessArray.length; i++) {
                for (int j = 0; j < chessArray.length; j++) {
                    if (chessArray[i][j] == ChessPiece.Empty) {
                        return GameState.Gaming;
                    }
                }
            }
            return GameState.Draw;
        }
    }


    public int left_right(ChessPiece[][] chessArray, int x, int y) {
        int count = 0;
        ChessPiece chessNum = chessArray[x][y];
        for (int i = y + 1; i < chessArray[x].length; i++) {
            if (chessNum == chessArray[x][i]) {
                count++;
            } else {
                break;
            }
        }
        for(int i = y-1;i>=0;i--){
            if(chessNum == chessArray[x][i]){
                count++;
            }else{
                break;
            }
        }
        count++;
        return count;
    }

    public int up_down(ChessPiece[][] chessArray, int x, int y){
        int count = 0;
        ChessPiece chessNum = chessArray[x][y];
        for(int i = x-1; i>=0;i--){
            if(chessNum == chessArray[i][y]){
                count++;
            }else{
                break;
            }
        }
        for(int i = x+1;i<chessArray.length;i++){
            if(chessNum == chessArray[i][y]){
                count++;
            }else{
                break;
            }
        }
        count++;
        return count;
    }

    public int leftUp_RightDown(ChessPiece[][] chessArray, int x, int y){
        int count = 0;
        ChessPiece chessNum = chessArray[x][y];
        for(int i = x+1,j=y+1;i<chessArray.length&&j<chessArray[x].length;i++,j++){
            if(chessNum == chessArray[i][j]){
                count++;
            }else{
                break;
            }
        }
        for(int i=x-1,j=y-1;i>=0&&j>=0;i--,j--){
            if(chessNum == chessArray[i][j]){
                count++;
            }else{
                break;
            }
        }
        count++;
        return count;
    }

    public int leftDown_RightUp(ChessPiece[][] chessArray, int x, int y){
        int count = 0;
        ChessPiece chessNum = chessArray[x][y];
        for(int i = x+1,j=y-1;i<chessArray.length&&j>=0;i++,j--){
            if(chessNum == chessArray[i][j]){
                count++;
            }else{
                break;
            }
        }
        for(int i=x-1,j=y+1;i>=0&&j<chessArray[x].length;i--,j++){
            if(chessNum == chessArray[i][j]){
                count++;
            }else{
                break;
            }
        }
        count++;
        return count;
    }
}


