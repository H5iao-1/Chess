package com.example.chess.data;

import com.example.chess.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User login(@Param("username") String username);
    Integer register(User user);
    User getUserInfo(@Param("id") int id);

}
