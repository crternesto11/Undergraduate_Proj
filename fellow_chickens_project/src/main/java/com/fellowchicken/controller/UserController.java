package com.fellowchicken.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fellowchicken.common.Result;
import com.fellowchicken.common.ResultGenerator;
import com.fellowchicken.mapper.UserMapper;
import com.fellowchicken.model.User;
import com.fellowchicken.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/user")
@Api(value = "用户管理器", tags = {"user"})
public class UserController {

    @Resource
    private UserService userService;

    @ResponseBody
    @ApiOperation(value = "login", notes = "login notes")
    @GetMapping(name = "signin", path = "/sign_in")
    public Result<Boolean> login(@RequestParam("UserID") Integer userId,
                                 @RequestParam("Password") String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("UserID", userId);
        User user = userService.getOne(queryWrapper);
        if (user != null) {
            return ResultGenerator.genSuccessResult(user.getPassword().replace(" ", "").equals(password));
        } else {
            return ResultGenerator.genSuccessResult(false);
        }
    }

    @ResponseBody
    @ApiOperation(value = "register", notes = "login notes")
    @GetMapping(name = "signup", path = "/sign_up")
    public Result<Integer> signIn(@RequestParam("UserName") String userName,
                                  @RequestParam("Email") String email,
                                  @RequestParam("Phone") String phone,
                                  @RequestParam("Password") String password) {
        UserMapper userMapper = (UserMapper) userService.getBaseMapper();
        User user = new User(userName, email, phone, password);
        userMapper.add(user);
        Integer ret = user.getId();
        return ResultGenerator.genSuccessResult(ret);
    }
}
