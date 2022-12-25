package com.example.chess.serviceImpl;

import com.example.chess.data.UserMapper;
import com.example.chess.po.User;
import com.example.chess.service.UserService;
import com.example.chess.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override

    public ResponseVO login(Object object) {
        LinkedHashMap<?, ?> data = (LinkedHashMap<?, ?>) object;
        String username = (String) data.get("username");
        String password = (String) data.get("password");
        User user = this.userMapper.login(username);
        if (null == user || !Objects.equals(password, user.getPassword())) {
            return ResponseVO.buildFailure("Wrong username or password!");
        }
//        System.out.println(user.getId());
        return ResponseVO.buildSuccess(user);
    }

    @Override
    public ResponseVO register(Object object) {
        LinkedHashMap<?, ?> data = (LinkedHashMap<?, ?>) object;
        String username = (String) data.get("username");
        String password = (String) data.get("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Integer id;
        try {
            id = userMapper.register(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure("Something wrong");
        }
        return ResponseVO.buildSuccess(id);
    }

    @Override
    public ResponseVO getUserInfo(int id) {
        User user = userMapper.getUserInfo(id);
        return null;
    }
}
