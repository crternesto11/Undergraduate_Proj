package com.fellowchicken.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fellowchicken.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {
    void add(@Param("user")User user);
}
