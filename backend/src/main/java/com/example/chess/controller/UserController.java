package com.example.chess.controller;

import com.example.chess.service.UserService;
import com.example.chess.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.Objects;


@RestController()
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseVO login(@RequestBody Object object){
        return this.userService.login(object);
    }

    @PostMapping("/register")
    public ResponseVO register(@RequestBody Object object) {
        return this.userService.register(object);
    }

    @PostMapping("/getUserInfo")
    public ResponseVO getUserInfo(@RequestBody int id) {
        return this.userService.getUserInfo(id);
    }

}
