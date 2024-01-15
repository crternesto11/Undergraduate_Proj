package com.fellowchicken.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fellowchicken.mapper.UserMapper;
import com.fellowchicken.model.User;
import com.fellowchicken.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
}
