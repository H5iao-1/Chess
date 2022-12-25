package com.example.chess.service;

import com.example.chess.vo.ResponseVO;

public interface UserService {
    ResponseVO login(Object object);
    ResponseVO register(Object object);
    ResponseVO getUserInfo(int id);
}
