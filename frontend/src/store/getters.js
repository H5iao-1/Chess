const getters = {
    //chessBoard
    chessArray: state => state.chessBoard.chessArray,
    chessPiece: state => state.chessBoard.chessPiece,
    gameMode: state => state.chessBoard.gameMode,
    gameState: state => state.chessBoard.gameState,

    // user
    token: state => state.user.token,
    userId: state => state.user.userId,



};

    export default getters;