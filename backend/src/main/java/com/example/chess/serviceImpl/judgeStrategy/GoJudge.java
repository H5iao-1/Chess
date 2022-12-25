package com.example.chess.serviceImpl.judgeStrategy;

import com.example.chess.enums.ChessPiece;
import com.example.chess.enums.GameState;
import com.example.chess.po.chessBoard.Game;

public class GoJudge extends JudgeStrategy {

    @Override
    public GameState judge(Game game, ChessPiece chessPiece, Integer x, Integer y) {
//        PythonInterpreter pythonInterpreter = new PythonInterpreter();
//        pythonInterpreter.execfile("/Users/hsueh/Desktop/GoJudge.py");
//        PyFunction pyFunction = pythonInterpreter.get("test", PyFunction.class);
//        PyObject pyObject = pyFunction.__call__(new PyInteger(1));
//        System.out.println(pyObject);
        return GameState.Gaming;
    }


}
