import { axios } from '@/utils/request';

const api = {
    chessBoardPre: '/api/chessBoard'
};

export function startGameAPI(data){
    return axios({
        url: `${api.chessBoardPre}/startGame`,
        method: 'POST',
        data
    });
}

export function moveAPI(data){
    return axios({
        url: `${api.chessBoardPre}/move`,
        method: 'POST',
        data
    });
}


export function endGameAPI(){
    return axios({
        url: `${api.chessBoardPre}/endGame`,
        method: 'POST',
    });
}

