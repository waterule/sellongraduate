package com.example.demo.controller;

import com.example.demo.entity.Goods;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@Api("登陆入口")
@RestController

public class Init {
    @Autowired
    UserService userser;

    @ApiOperation(value="首页", notes = "首页信息")
   @GetMapping("/")
   public ModelAndView init(){
       return new ModelAndView("html/login");
   }
    @ApiOperation(value = "登陆校验")

    @GetMapping("/login/{username}/{password}")

    public String getUser(@PathVariable String username, @PathVariable String password){
        Long usreid  =   userser.getuser(username,password);
        return String.valueOf(usreid);

    }
    @ApiOperation(value = "注册用户")
    @GetMapping("/regs/{username}/{password}")
    public String regis(@PathVariable String username, @PathVariable String password){
        userser.saveUser(username,password);
        return "sucess";
    }
    @ApiOperation(value = "发布物品")
    @PostMapping(value = "/savegoods")
    public String saveGood(@RequestBody Goods goods){
        return null;
    }


}
