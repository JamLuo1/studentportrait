package com.cjl.test.controller;

import com.cjl.test.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class UserController {
    @GetMapping("/user")
    public Result user (){
        return Result.success();
    }
}
