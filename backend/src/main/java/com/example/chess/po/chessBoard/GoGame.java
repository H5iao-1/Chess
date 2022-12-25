package com.example.chess.po.chessBoard;

import com.example.chess.enums.ChessPiece;
import com.example.chess.enums.GameMode;
import com.example.chess.po.User;

public class GoGame extends Game {

    int[] block;
    int blockLength;

    public GoGame(GameMode gameMode, Integer boardSize, User user1, User user2) {
        super(gameMode, boardSize, user1, user2);
    }


    @Override
    public void move(ChessPiece chessPiece, Integer x, Integer y) {
        this.pieces[x][y] = chessPiece;
        block = null;
        blockLength = 0;
        for(int i = 0;i < this.boardSize; i++){
            for(int j = 0;j < this.boardSize; j++){
                if(this.pieces[i][j] == ChessPiece.Empty)
                    continue;
                else{
                    block = new int[this.boardSize * this.boardSize];
                    blockLength = 1;
                    block[0] = i*100 + j;

                    recursion(i,j);

                    if(hasQi())
                        continue;
                    else {
                        for(int t = 0;t < blockLength; t++)
                            this.pieces[block[t]/100][block[t]%100] = ChessPiece.Empty;
                    }
                }
            }
        }
    }


    public void recursion(int i,int j){
        //Left
        if(i-1 >= 0 && this.pieces[i-1][j] == this.pieces[i][j] && isInBlock((i-1)*100+j)){
            this.block[this.blockLength] = (i-1)*100 + j;
            this.blockLength++;
            recursion(i-1,j);
        }
        //Up
        if(j-1 >= 0 && this.pieces[i][j-1] == this.pieces[i][j] && isInBlock(i*100+j-1)){
            this.block[this.blockLength] = i*100 + j-1;
            this.blockLength++;
            recursion(i,j-1);
        }
        //Right
        if(i+1 < this.boardSize && this.pieces[i+1][j] == this.pieces[i][j] && isInBlock((i+1)*100+j)){
            this.block[this.blockLength] = (i+1)*100 + j;
            this.blockLength++;
            recursion(i+1,j);
        }
        //Down
        if(j+1 < this.boardSize && this.pieces[i][j+1] == this.pieces[i][j] && isInBlock(i*100+j+1)){
            this.block[this.blockLength] = i*100 + j+1;
            this.blockLength++;
            recursion(i,j+1);
        }
    }

    public boolean hasQi(){
        int i,j;
        for(int t = 0;t < this.blockLength; t++){
            i = this.block[t]/100;
            j = this.block[t]%100;
            if(i-1 >= 0 && this.pieces[i-1][j] == ChessPiece.Empty) return true;
            if(i+1 < this.boardSize && this.pieces[i+1][j] == ChessPiece.Empty) return true;
            if(j-1 >= 0 && this.pieces[i][j-1] == ChessPiece.Empty) return true;
            if(j+1 < this.boardSize && this.pieces[i][j+1] == ChessPiece.Empty) return true;
        }
        return false;
    }

    public boolean isInBlock(int neighbor){
        for(int i = 0;i < this.blockLength; i++)
        {
            if (this.block[i] == neighbor) return false;
        }
        return true;
    }


}
